package edu.vt.cs.cs5254.multiquiz

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.view.children
import androidx.lifecycle.ViewModelProvider
import edu.vt.cs.cs5254.multiquiz.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {

    private val DEFAULT_BUTTON_COLOR = "#3C797B"
    private val SELECTED_BUTTON_COLOR = "#F39706"

    lateinit var ui: ActivityQuizBinding

    // view fields (only one)
    lateinit var answerButtonList: List<Button>

    // view model access
    private val vm: QuizViewModel by lazy {
        ViewModelProvider(this).get(QuizViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ------------------------------------------------------
        // Create binding and content view
        // ------------------------------------------------------

        ui = ActivityQuizBinding.inflate(layoutInflater)
        val view = ui.root
        setContentView(view)

        // ------------------------------------------------------
        // Initialize answer-button list
        // ------------------------------------------------------

        answerButtonList = ui.answerButtons
            .children
            .toList()
            .filterIsInstance<Button>()

        ui.hintButton.setText(R.string.hint_button)
        ui.submitButton.setText(R.string.submit_button)

        // ------------------------------------------------------
        // Add listeners to buttons
        // ------------------------------------------------------
        answerButtonList.forEachIndexed { buttonIndex, button ->
            button.setOnClickListener {
                processAnswerButtonClick(buttonIndex)
            }
        }

        ui.hintButton.setOnClickListener {
            processHintButtonClick()
        }
        ui.submitButton.setOnClickListener {
            processSubmitButtonClick()
        }

        // ------------------------------------------------------
        // Refresh the view
        // ------------------------------------------------------

        refreshView()
    }

    private fun processAnswerButtonClick(buttonIndex: Int) {
        val origIsSelected = vm.answerList[buttonIndex].isSelected
        vm.answerList.forEach {
            it.isSelected = false
        }

        vm.answerList[buttonIndex].isSelected = !origIsSelected
        refreshView()
    }

    private fun processHintButtonClick() {

        val randomAnswer = vm.answerList
            .filter { it.isEnabled }
            .filterNot { it.isCorrect }
            .random()

        randomAnswer.isEnabled = false
        randomAnswer.isSelected = false

        vm.incrementTotalHintsUsed()

        refreshView()
    }

    private fun processSubmitButtonClick() {
        if (vm.answerList.first { it.isSelected }.isCorrect) {
            vm.incrementTotalCorrectAnswers()
        }

        if (vm.hasMoreQuestions) {
            vm.gotoNextQuestion()
            vm.answerList.forEach {
                it.isEnabled = true
                it.isSelected = false
            }

            refreshView()
        } else {
            val resultsIntent = ResultsActivity.newIntent(
                this@QuizActivity,
                vm.questionBankSize,
                vm.totalCorrectAnswers,
                vm.totalHintsUsed
            )
            startActivity(resultsIntent)
        }

    }

    private fun refreshView() {

        // ------------------------------------------------------
        // Set text of question and answer buttons
        // ------------------------------------------------------

        ui.questionTextView.setText(vm.questionText)

        val answerButtonPairs = vm.answerList.zip(answerButtonList)
        answerButtonPairs.forEach { (answer, button) -> button.setText(answer.textResId) }

        ui.hintButton.isEnabled = false
        ui.submitButton.isEnabled = false
        answerButtonPairs.forEach { (answer, button) ->
            button.isEnabled = answer.isEnabled
            button.isSelected = answer.isSelected
            if (answer.isSelected) {
                setButtonColor(button, SELECTED_BUTTON_COLOR)
            } else {
                setButtonColor(button, DEFAULT_BUTTON_COLOR)
            }

            if (!answer.isEnabled) {
                button.alpha = .5f
            }
        }

        // Handle submit button
        if (answerButtonPairs.any { (answer) -> answer.isSelected }) {
            ui.submitButton.isEnabled = true // enable if any answer is selected
        }

        // Handle hint button
        if (answerButtonPairs.any { (answer) -> answer.isEnabled && !answer.isCorrect }) {
            ui.hintButton.isEnabled = true // enable if at least one wrong answer is enabled
        }
    }

    private fun setButtonColor(button: Button, colorString: String) {
        button.backgroundTintList =
            ColorStateList.valueOf(Color.parseColor(colorString))
        button.setTextColor(Color.WHITE)
        button.alpha = 1f
    }
}

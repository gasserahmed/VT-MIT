package edu.vt.cs.cs5254.multiquiz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.vt.cs.cs5254.multiquiz.databinding.ActivityResultsBinding

private const val EXTRA_TOTAL_QUESTIONS = "total_questions"
private const val EXTRA_TOTAL_CORRECT = "total_correct"
private const val EXTRA_TOTAL_HINTS = "total_hints"

lateinit var ui: ActivityResultsBinding

class ResultsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityResultsBinding.inflate(layoutInflater)
        val view = ui.root
        setContentView(view)

        val totalQuestionsCount = intent.getIntExtra(EXTRA_TOTAL_QUESTIONS, -1)
        val totalCorrectAnswers = intent.getIntExtra(EXTRA_TOTAL_CORRECT, -1)
        val totalHintsUsed = intent.getIntExtra(EXTRA_TOTAL_HINTS, -1)
        ui.totalQuestionsValue.text = totalQuestionsCount.toString()
        ui.totalAnswersCorrectValue.text = totalCorrectAnswers.toString()
        ui.totalHintsUsedValue.text = totalHintsUsed.toString()
        ui.retakeQuizButton.setOnClickListener {
            val quizIntent = Intent(this, QuizActivity::class.java)
            startActivity(quizIntent)
        }
    }

    companion object {
        fun newIntent(
            packageContext: Context,
            totalQuestions: Int,
            totalCorrect: Int,
            totalHints: Int
        ): Intent {
            return Intent(packageContext, ResultsActivity::class.java).apply {
                putExtra(EXTRA_TOTAL_QUESTIONS, totalQuestions)
                putExtra(EXTRA_TOTAL_CORRECT, totalCorrect)
                putExtra(EXTRA_TOTAL_HINTS, totalHints)
            }
        }
    }
}
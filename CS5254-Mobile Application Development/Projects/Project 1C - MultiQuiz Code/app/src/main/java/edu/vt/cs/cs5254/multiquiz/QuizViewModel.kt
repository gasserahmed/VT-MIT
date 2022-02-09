package edu.vt.cs.cs5254.multiquiz

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlin.math.log

class QuizViewModel : ViewModel() {
    private val questionBank = listOf(
        Question(
            R.string.australia_question,
            listOf(
                Answer(R.string.australia_answer_canberra, true),
                Answer(R.string.australia_answer_brisbane, false),
                Answer(R.string.australia_answer_perth, false),
                Answer(R.string.australia_answer_sydney, false)
            )
        ),
        Question(
            R.string.opera_question,
            listOf(
                Answer(R.string.australia_answer_brisbane, false),
                Answer(R.string.australia_answer_sydney, true),
                Answer(R.string.australia_answer_perth, false),
                Answer(R.string.australia_answer_canberra, false)
            )
        ),
        Question(
            R.string.france_question,
            listOf(
                Answer(R.string.france_answer_lyon, false),
                Answer(R.string.france_answer_marseille, false),
                Answer(R.string.france_answer_paris, true),
                Answer(R.string.france_answer_nice, false)
            )
        ),
        Question(
            R.string.eiffel_question,
            listOf(
                Answer(R.string.france_answer_lyon, false),
                Answer(R.string.france_answer_marseille, false),
                Answer(R.string.france_answer_nice, false),
                Answer(R.string.france_answer_paris, true)
            )
        )
    )
    val questionBankSize: Int
        get() = questionBank.size

    private var questionIndex = 0
    val questionText
        get() = questionBank[questionIndex].textResId
    val answerList
        get() = questionBank[questionIndex].answerList

    fun gotoNextQuestion() {
        questionIndex = (questionIndex + 1) % questionBank.size
    }

    val hasMoreQuestions
        get() = questionIndex < questionBank.size - 1

    var totalCorrectAnswers : Int = 0
    fun incrementTotalCorrectAnswers() {
        totalCorrectAnswers++
    }

    var totalHintsUsed = 0
    fun incrementTotalHintsUsed() {
        totalHintsUsed++
    }
}
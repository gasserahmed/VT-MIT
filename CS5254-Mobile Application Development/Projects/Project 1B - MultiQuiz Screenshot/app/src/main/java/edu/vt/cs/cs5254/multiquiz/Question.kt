package edu.vt.cs.cs5254.multiquiz

import androidx.annotation.StringRes

data class Question(
    @StringRes val textResId: Int,
    val answerList: List<Answer>)

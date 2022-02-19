package edu.vt.cs.cs5254.dreamcatcher

import androidx.lifecycle.ViewModel

class DreamListViewModel : ViewModel() {
    val dreams = mutableListOf<Dream>()

    init {
        for (i in 0 until 100) {
            val dream = Dream()
            dream.title = "Dream #$i"
            dream.isFulfilled = i % 2 == 0
            dream.isDeferred = i % 2 == 0
            dreams += dream
        }
    }
}
package edu.vt.cs.cs5254.dreamcatcher

import androidx.lifecycle.ViewModel
import java.util.*

class DreamDetailViewModel : ViewModel() {
    private val dreamRepository = DreamRepository.get()
    lateinit var dream: Dream
    
    fun loadDream(dreamId: UUID) {
        dream = dreamRepository.getDream(dreamId)
            ?: throw IllegalArgumentException("Dream with ID $dreamId not found")
    }
}
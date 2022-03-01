package edu.vt.cs.cs5254.dreamcatcher

import androidx.lifecycle.ViewModel
import java.util.*

class DreamDetailViewModel : ViewModel() {
    private val dreamRepository = DreamRepository.get()
    lateinit var dreamWithEntries: DreamWithEntries
    
    fun loadDream(dreamId: UUID) {
        dreamWithEntries = dreamRepository.getDreamWithEntries(dreamId)
            ?: throw IllegalArgumentException("Dream with ID $dreamId not found")
    }
}
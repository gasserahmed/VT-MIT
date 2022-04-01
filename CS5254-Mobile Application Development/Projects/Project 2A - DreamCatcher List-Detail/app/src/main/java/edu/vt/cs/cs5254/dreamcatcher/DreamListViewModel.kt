package edu.vt.cs.cs5254.dreamcatcher

import androidx.lifecycle.ViewModel

class DreamListViewModel : ViewModel() {
    private val dreamRepository = DreamRepository.get()
    val dreams = dreamRepository.getDreams()
}
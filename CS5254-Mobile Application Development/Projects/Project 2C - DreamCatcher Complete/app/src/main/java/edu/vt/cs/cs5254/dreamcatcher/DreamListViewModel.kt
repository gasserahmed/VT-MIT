package edu.vt.cs.cs5254.dreamcatcher

import androidx.lifecycle.ViewModel
import edu.vt.cs.cs5254.dreamcatcher.database.DreamRepository

class DreamListViewModel : ViewModel() {
    private val dreamRepository = DreamRepository.get()
    val dreamListLiveData = dreamRepository.getDreams()
}
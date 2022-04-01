package edu.vt.cs.cs5254.dreamcatcher

import androidx.lifecycle.ViewModel
import edu.vt.cs.cs5254.dreamcatcher.database.DreamRepository

class DreamListViewModel : ViewModel() {
    private val dreamRepository = DreamRepository.get()
    val dreamListLiveData = dreamRepository.getDreams()

    fun addDream(dreamWithEntries: DreamWithEntries) {
        val entries = mutableListOf<DreamEntry>()
        entries += DreamEntry(kind = DreamEntryKind.CONCEIVED, dreamId = dreamWithEntries.dream.id)
        dreamWithEntries.dreamEntries = entries
        dreamRepository.addDreamWithEntries(dreamWithEntries)
    }

    fun deleteAllDreams() {
        dreamRepository.deleteAllDreamsInDatabase()
    }
}
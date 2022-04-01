package edu.vt.cs.cs5254.dreamcatcher

import android.content.Context
import java.util.UUID

class DreamRepository private constructor(context: Context) {

    private val dreamWithEntriesList = mutableListOf<DreamWithEntries>()

    init {
        for (i in 0 until 100) {
            val dream = Dream()
            dream.title = "Dream #$i"
            val entries = mutableListOf<DreamEntry>()
            entries += DreamEntry(kind = DreamEntryKind.CONCEIVED, dreamId = dream.id)
            when (i % 4) {
                1 -> entries += DreamEntry(text = "Dream Entry ${i}A", dreamId = dream.id)
                2 -> {
                    entries += DreamEntry(text = "Dream Entry ${i}A", dreamId = dream.id)
                    entries += DreamEntry(text = "Dream Entry ${i}B", dreamId = dream.id)
                }
                3 -> {
                    entries += DreamEntry(text = "Dream Entry ${i}A", dreamId = dream.id)
                    entries += DreamEntry(text = "Dream Entry ${i}B", dreamId = dream.id)
                    entries += DreamEntry(text = "Dream Entry ${i}C", dreamId = dream.id)
                }
            }
            when (i % 3) {
                1 -> {
                    dream.isDeferred = true
                    entries += DreamEntry(kind = DreamEntryKind.DEFERRED, dreamId = dream.id)
                }
                2 -> {
                    dream.isFulfilled = true
                    entries += DreamEntry(kind = DreamEntryKind.FULFILLED, dreamId = dream.id)
                }
            }
            dreamWithEntriesList += DreamWithEntries(dream, entries)
        }
    }

    fun getDreams() = dreamWithEntriesList.map { it.dream }

    fun getDreamWithEntries(dreamId: UUID): DreamWithEntries? =
        dreamWithEntriesList.find { it.dream.id == dreamId }

    companion object {

        private var INSTANCE: DreamRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = DreamRepository(context)
            }
        }

        fun get(): DreamRepository {
            return INSTANCE ?:
            throw IllegalStateException("DreamRepository must be initialized")
        }
    }
}

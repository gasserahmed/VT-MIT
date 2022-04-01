package edu.vt.cs.cs5254.dreamcatcher

import androidx.room.Embedded
import androidx.room.Relation

data class DreamWithEntries(
    @Embedded var dream: Dream,
    @Relation(
        parentColumn = "id",
        entityColumn = "dreamId"
    ) var dreamEntries: List<DreamEntry>
)

package edu.vt.cs.cs5254.dreamcatcher

import java.util.*

data class DreamEntry(
    val id: UUID = UUID.randomUUID(),
    val date: Date = Date(),
    val text: String = "",
    val kind: DreamEntryKind = DreamEntryKind.REFLECTION,
    val dreamId: UUID
)

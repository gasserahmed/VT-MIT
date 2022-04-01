package edu.vt.cs.cs5254.dreamcatcher

import java.util.*

data class Dream(
    val id: UUID = UUID.randomUUID(),
    var title: String = "",
    var date: Date = Date(),
    var isFulfilled: Boolean = false,
    var isDeferred: Boolean = false
)

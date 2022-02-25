package edu.vt.cs.cs5254.dreamcatcher

import android.content.Context
import java.util.*

class DreamRepository private constructor(context: Context) {
    private val dreams = mutableListOf<Dream>()
    init {
        for (i in 0 until 100) {
            val dream = Dream()
            dream.title = "Dream #$i"
            dream.isFulfilled = i % 2 == 0
            dreams += dream
        }
    }
    fun getDreams() = dreams
    fun getFirstDream(): Dream = dreams.first()
    fun getDream(id: UUID): Dream? = dreams.find { it.id == id }
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
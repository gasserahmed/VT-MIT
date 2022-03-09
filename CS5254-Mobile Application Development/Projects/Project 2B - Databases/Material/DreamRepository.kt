package edu.vt.cs.cs5254.dreamcatcher

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import edu.vt.cs.cs5254.dreamcatcher.database.DreamDatabase
import java.util.UUID
import java.util.concurrent.Executors

private const val DATABASE_NAME = "dream_database"

class DreamRepository private constructor(context: Context) {

    private val initializeDreamDatabaseCallback: RoomDatabase.Callback =
        object : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                executor.execute {

                    // delete all dreams and entries
                    deleteAllDreamsInDatabase()
                    deleteAllDreamEntriesInDatabase()

                    for (i in 0 until 100) {
                        val dream = Dream()
                        dream.title = "Dream #$i"
                        val entries = mutableListOf<DreamEntry>()
                        entries += DreamEntry(kind = DreamEntryKind.CONCEIVED, dreamId = dream.id)
                        when (i % 4) {
                            1 -> entries += DreamEntry(
                                text = "Dream Entry ${i}A",
                                dreamId = dream.id
                            )
                            2 -> {
                                entries += DreamEntry(
                                    text = "Dream Entry ${i}A",
                                    dreamId = dream.id
                                )
                                entries += DreamEntry(
                                    text = "Dream Entry ${i}B",
                                    dreamId = dream.id
                                )
                            }
                            3 -> {
                                entries += DreamEntry(
                                    text = "Dream Entry ${i}A",
                                    dreamId = dream.id
                                )
                                entries += DreamEntry(
                                    text = "Dream Entry ${i}B",
                                    dreamId = dream.id
                                )
                                entries += DreamEntry(
                                    text = "Dream Entry ${i}C",
                                    dreamId = dream.id
                                )
                            }
                        }
                        when (i % 3) {
                            1 -> {
                                dream.isDeferred = true
                                entries += DreamEntry(
                                    kind = DreamEntryKind.DEFERRED,
                                    dreamId = dream.id
                                )
                            }
                            2 -> {
                                dream.isFulfilled = true
                                entries += DreamEntry(
                                    kind = DreamEntryKind.FULFILLED,
                                    dreamId = dream.id
                                )
                            }
                        }
                        addDreamWithEntries(DreamWithEntries(dream, entries))
                    }
                }
            }
        }

    private val database : DreamDatabase = Room.databaseBuilder(
        context.applicationContext,
        DreamDatabase::class.java,
        DATABASE_NAME
    ).addCallback(initializeDreamDatabaseCallback).build()

    private val dreamDao = database.dreamDao()
    private val executor = Executors.newSingleThreadExecutor()

    fun getDreams() = dreamDao.getDreams()

    fun getDreamWithEntries(dreamId: UUID) = dreamDao.getDreamWithEntries(dreamId)

    fun addDreamWithEntries(dreamWithEntries: DreamWithEntries) {
        executor.execute {
            dreamDao.addDreamWithEntries(dreamWithEntries)
        }
    }

    fun updateDreamWithEntries(dreamWithEntries: DreamWithEntries) {
        executor.execute {
            dreamDao.updateDreamWithEntries(dreamWithEntries)
        }
    }

    fun deleteAllDreamsInDatabase() {
        executor.execute {
            dreamDao.deleteAllDreamsInDatabase()
        }
    }

    fun deleteAllDreamEntriesInDatabase() {
        executor.execute {
            dreamDao.deleteAllDreamEntriesInDatabase()
        }
    }

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

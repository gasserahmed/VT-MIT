package edu.vt.cs.cs5254.dreamcatcher.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import edu.vt.cs.cs5254.dreamcatcher.Dream
import edu.vt.cs.cs5254.dreamcatcher.DreamEntry

@Database(entities = [Dream::class, DreamEntry::class], version = 1)
@TypeConverters(DreamTypeConverters::class)
abstract class DreamDatabase : RoomDatabase() {
    abstract fun dreamDao(): DreamDao
}
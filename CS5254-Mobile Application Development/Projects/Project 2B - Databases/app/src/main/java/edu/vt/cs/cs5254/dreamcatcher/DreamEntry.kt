package edu.vt.cs.cs5254.dreamcatcher

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName= "dream_entry",
    foreignKeys = [ForeignKey(entity = Dream::class,
        parentColumns = ["id"],
        childColumns = ["dreamId"],
        onDelete = CASCADE)],
    indices = [Index(name = "dream_entry_i1",  value = ["dreamId"])])
data class DreamEntry(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    val date: Date = Date(),
    val text: String = "",
    val kind: DreamEntryKind = DreamEntryKind.REFLECTION,
    val dreamId: UUID
)

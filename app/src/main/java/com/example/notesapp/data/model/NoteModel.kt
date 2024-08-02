package com.example.notesapp.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
@Entity(tableName = "note_table")
data class NoteModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo("title") val title: String,
    @ColumnInfo("note") val note: String,
    @ColumnInfo("created_at") var createdAt: Date = Date(System.currentTimeMillis()),
    @ColumnInfo("updated_at") var updatedAt: Date = Date(System.currentTimeMillis())
) : Parcelable {
    constructor(title: String, note: String) : this(0, title, note)
}

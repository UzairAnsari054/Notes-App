package com.example.notesapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.notesapp.data.model.NoteModel
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface NoteDao {

    @Query(
        "SELECT * FROM note_table ORDER BY " +
                "CASE WHEN :sortBy = 'title' AND :orderBy = 'ASC' THEN title END ASC," +
                "CASE WHEN :sortBy = 'title' AND :orderBy = 'DESC' THEN title END DESC," +
                "CASE WHEN :sortBy = 'created_at' AND :orderBy = 'ASC' THEN created_at END ASC," +
                "CASE WHEN :sortBy = 'created_at' AND :orderBy = 'DESC' THEN created_at END DESC," +
                "CASE WHEN :sortBy = 'updated_at' AND :orderBy = 'ASC' THEN created_at END ASC," +
                "CASE WHEN :sortBy = 'updated_at' AND :orderBy = 'DESC' THEN created_at END DESC"
    )
    fun getNoteList(sortBy: String, orderBy: String): Flow<List<NoteModel>>

    @Query("SELECT * FROM note_table WHERE id=:id")
    fun getNoteById(id: Int): Flow<NoteModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveNote(note: NoteModel): Long

    fun saveNoteWithTimestamp(note: NoteModel): Long {
        return saveNote(note.apply {
            createdAt = Date(System.currentTimeMillis())
            updatedAt = Date(System.currentTimeMillis())
        })
    }

    @Update
    fun updateNote(note: NoteModel): Int

    fun updateNoteWithTimeStamp(note: NoteModel): Int {
        return updateNote(note.apply {
            updatedAt = Date(System.currentTimeMillis())
        })
    }

    @Delete
    fun deleteNote(vararg notes: NoteModel): Int
}
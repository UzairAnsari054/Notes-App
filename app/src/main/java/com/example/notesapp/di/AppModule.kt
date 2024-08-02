package com.example.notesapp.di

import android.content.Context
import androidx.room.Room
import com.example.notesapp.data.local.NoteDao
import com.example.notesapp.data.local.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(@ApplicationContext app: Context) = Room.databaseBuilder(
        app,
        NoteDatabase::class.java,
        "note_db"
    ).build()

    @Provides
    @Singleton
    fun provideNoteDao(db: NoteDatabase): NoteDao {
        return db.noteDao()
    }
}
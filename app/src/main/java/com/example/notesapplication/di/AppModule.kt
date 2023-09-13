package com.example.notesapplication.di

import android.content.Context
import androidx.room.Room
import com.example.notesapplication.model.AppDatabase
import com.example.notesapplication.model.NoteDao
import com.example.notesapplication.viewmodel.NotesViewModel
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
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "notes_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideNoteDao(appDatabase: AppDatabase): NoteDao {
        return appDatabase.noteDao()
    }

    @Provides
    @Singleton
    fun provideNotesViewModel(noteDao: NoteDao): NotesViewModel {
        return NotesViewModel(noteDao)
    }
}
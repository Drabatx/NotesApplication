package com.example.notesapplication

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NotesApp: Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize Hilt
        // Initialize Timber
    }
}
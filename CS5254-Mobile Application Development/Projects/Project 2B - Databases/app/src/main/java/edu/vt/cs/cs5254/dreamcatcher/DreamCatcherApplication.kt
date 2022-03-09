package edu.vt.cs.cs5254.dreamcatcher

import android.app.Application
import edu.vt.cs.cs5254.dreamcatcher.database.DreamRepository

class DreamCatcherApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DreamRepository.initialize(this)
    }
}
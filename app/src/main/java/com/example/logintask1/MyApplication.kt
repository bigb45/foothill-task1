package com.example.logintask1

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        // Perform any app-wide initialization here
        // Example: Initializing libraries, setting up global configurations, etc.
    }

}
package com.danielebachicchi.badgelogk

import android.app.Application

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Logger.setup(ConsoleDestination(), FileDestination(this))
        Logger.info("Badgelog Initialized!")
    }
}
package hoods.com.jetpetrescue

import android.app.Application

class JetPetApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}
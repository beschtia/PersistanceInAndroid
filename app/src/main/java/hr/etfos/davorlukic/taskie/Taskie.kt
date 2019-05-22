package hr.etfos.davorlukic.taskie

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

class Taskie: Application() {

    companion object {
        private lateinit var instance: Taskie
        fun getAppContext(): Context = instance.applicationContext

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }
}
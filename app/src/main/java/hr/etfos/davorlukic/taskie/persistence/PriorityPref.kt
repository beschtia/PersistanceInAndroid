package hr.etfos.davorlukic.taskie.persistence

import android.preference.PreferenceManager
import hr.etfos.davorlukic.taskie.Taskie


object PriorityPref {
    const val KEY_PRIORITY_POSITION = "KEY_PRIORITY_POSITION"

    private fun sharedPrefs() = PreferenceManager.getDefaultSharedPreferences(Taskie.getAppContext())

    fun store(key: String, value: String){
        sharedPrefs().edit().putString(key,value).apply()
    }

    fun getString(key: String, defaultValue: String): String? {
        return sharedPrefs().getString(key, defaultValue)
    }
}
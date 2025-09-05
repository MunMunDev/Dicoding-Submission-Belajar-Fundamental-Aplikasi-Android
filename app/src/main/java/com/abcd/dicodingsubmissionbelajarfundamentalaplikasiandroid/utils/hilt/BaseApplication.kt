package com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.utils.hilt

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.R
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        // ambil SharedPreferences default
        val sharedPreferences =
            androidx.preference.PreferenceManager.getDefaultSharedPreferences(this)

        val darkModeEnabled = sharedPreferences.getBoolean(getString(R.string.key_theme), false)

        if (darkModeEnabled) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}
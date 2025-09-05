package com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.ui.fragment.settings

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.CheckBoxPreference
import androidx.preference.PreferenceFragmentCompat
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.R

class SettingsFragment : PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {

    private lateinit var DARK_MODE: String
    private lateinit var REMINDER: String

    private lateinit var darkModePreferences: CheckBoxPreference
    private lateinit var reminderPreferences: CheckBoxPreference

    companion object {
        fun newInstance() = SettingsFragment()
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
        init()
        setSummaries()
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences?.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences?.unregisterOnSharedPreferenceChangeListener(this)
    }

    private fun init() {
        DARK_MODE = resources.getString(R.string.key_theme)
        REMINDER = resources.getString(R.string.key_reminder)

        darkModePreferences = findPreference<CheckBoxPreference>(DARK_MODE)!!
        reminderPreferences = findPreference<CheckBoxPreference>(REMINDER)!!
    }

    private fun setSummaries() {
        val sh = preferenceManager.sharedPreferences
        darkModePreferences.isChecked = sh.getBoolean(DARK_MODE, false)
        reminderPreferences.isChecked = sh.getBoolean(REMINDER, false)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        when (key) {
            DARK_MODE -> {
                val enabled = sharedPreferences?.getBoolean(DARK_MODE, false) ?: false
                darkModePreferences.isChecked = enabled

                if(enabled)
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                else
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

            }
            REMINDER -> {
                val enabled = sharedPreferences?.getBoolean(REMINDER, false) ?: false
                reminderPreferences.isChecked = enabled
            }
        }
    }
}

package com.danielebachicchi.badgelogk

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            findPreference<Preference>("verbose")?.setOnPreferenceClickListener {
                Logger.verbose("I am a verbose log!")
                true
            }
            findPreference<Preference>("debug")?.setOnPreferenceClickListener {
                Logger.debug("I am a debug log!")
                true
            }
            findPreference<Preference>("info")?.setOnPreferenceClickListener {
                Logger.info("I am a info log!")
                true
            }
            findPreference<Preference>("warning")?.setOnPreferenceClickListener {
                Logger.warning("I am a warning log!")
                true
            }
            findPreference<Preference>("error")?.setOnPreferenceClickListener {
                Logger.error("I am an error log!")
                true
            }
            findPreference<Preference>("error_exception")?.setOnPreferenceClickListener {
                Logger.error("I am an error with exception log!", Throwable("Custom Fake Exception"))
                true
            }
            findPreference<Preference>("share")?.setOnPreferenceClickListener {
                //TODO share logs
                true
            }
        }

    }
}
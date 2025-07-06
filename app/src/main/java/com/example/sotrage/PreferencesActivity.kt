package com.example.sotrage

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.sotrage.databinding.ActivityPreferencesBinding

class PreferencesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPreferencesBinding

    private val prefsName = "app_prefs"
    private val darkModeKey = "dark_mode"
    private val notificationsKey = "notifications_enabled"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPreferencesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPrefs = getSharedPreferences(prefsName, Context.MODE_PRIVATE)

        // Load saved preferences
        val isDarkModeEnabled = sharedPrefs.getBoolean(darkModeKey, false)
        val notificationsEnabled = sharedPrefs.getBoolean(notificationsKey, false)

        // Set switches initial state
        binding.switchDarkMode.isChecked = isDarkModeEnabled
        binding.switchNotifications.isChecked = notificationsEnabled

        // Dark Mode toggle listener
        binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            toggleDarkMode(isChecked)
        }

        // Notifications toggle listener
        binding.switchNotifications.setOnCheckedChangeListener { _, isChecked ->
            toggleNotifications(isChecked)
        }

        // Reset preferences button listener
        binding.btnResetPrefs.setOnClickListener {
            resetPreferences()
        }

        // Show preferences button listener
        binding.btnShowPrefs.setOnClickListener {
            showCurrentPreferences()
        }

        // Example button listener
        binding.btnExample.setOnClickListener {
            Toast.makeText(this, "Example button clicked!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun toggleDarkMode(enable: Boolean) {
        val sharedPrefs = getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        sharedPrefs.edit().putBoolean(darkModeKey, enable).apply()

        binding.switchDarkMode.isChecked = enable

        AppCompatDelegate.setDefaultNightMode(
            if (enable) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )
    }

    private fun toggleNotifications(enable: Boolean) {
        val sharedPrefs = getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        sharedPrefs.edit().putBoolean(notificationsKey, enable).apply()

        binding.switchNotifications.isChecked = enable
    }

    data class UserPrefs(val darkMode: Boolean, val notifications: Boolean)

    private fun getUserPreferences(): UserPrefs {
        val sharedPrefs = getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        return UserPrefs(
            darkMode = sharedPrefs.getBoolean(darkModeKey, false),
            notifications = sharedPrefs.getBoolean(notificationsKey, false)
        )
    }

    private fun resetPreferences() {
        val sharedPrefs = getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        sharedPrefs.edit().clear().apply()

        binding.switchDarkMode.isChecked = false
        binding.switchNotifications.isChecked = false

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        Toast.makeText(this, "Preferences reset to default", Toast.LENGTH_SHORT).show()
    }

    private fun showCurrentPreferences() {
        val prefs = getUserPreferences()
        val message = "Dark Mode: ${if (prefs.darkMode) "ON" else "OFF"}\n" +
                "Notifications: ${if (prefs.notifications) "ON" else "OFF"}"
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}

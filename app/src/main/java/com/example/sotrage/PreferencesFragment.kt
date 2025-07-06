package com.example.sotrage

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.sotrage.databinding.FragmentPreferencesBinding

class PreferencesFragment : Fragment() {

    private var _binding: FragmentPreferencesBinding? = null
    private val binding get() = _binding!!

    private val prefsName = "app_prefs"
    private val darkModeKey = "dark_mode"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPreferencesBinding.inflate(inflater, container, false)

        // Load saved dark mode preference
        val sharedPrefs = requireActivity().getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        val isDarkModeEnabled = sharedPrefs.getBoolean(darkModeKey, false)

        // Set switch initial state
        binding.switchDarkMode.isChecked = isDarkModeEnabled

        // Toggle dark mode on switch change
        binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            sharedPrefs.edit().putBoolean(darkModeKey, isChecked).apply()

            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

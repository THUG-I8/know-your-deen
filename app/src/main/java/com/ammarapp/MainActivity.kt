package com.ammarapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ammarapp.databinding.ActivityMainBinding
import com.ammarapp.fragments.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currentFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupCardClickListeners()
    }

    private fun setupCardClickListeners() {
        // Quran Card
        binding.cardQuran.setOnClickListener {
            loadFragment(QuranFragment(), "quran")
        }

        // Morning Dhikr Card
        binding.cardMorningDhikr.setOnClickListener {
            loadFragment(MorningDhikrFragment(), "morning_dhikr")
        }

        // Evening Dhikr Card
        binding.cardEveningDhikr.setOnClickListener {
            loadFragment(EveningDhikrFragment(), "evening_dhikr")
        }

        // Hadith Card
        binding.cardHadith.setOnClickListener {
            loadFragment(HadithFragment(), "hadith")
        }

        // Tasbih Card
        binding.cardTasbih.setOnClickListener {
            loadFragment(TasbihFragment(), "tasbih")
        }

        // Islamic Knowledge Card
        binding.cardIslamicKnowledge.setOnClickListener {
            loadFragment(IslamicKnowledgeFragment(), "islamic_knowledge")
        }

        // Prayer Times Card
        binding.cardPrayerTimes.setOnClickListener {
            loadFragment(PrayerTimesFragment(), "prayer_times")
        }

        // Settings Card
        binding.cardSettings.setOnClickListener {
            loadFragment(SettingsFragment(), "settings")
        }
    }

    private fun loadFragment(fragment: Fragment, tag: String) {
        // Show fragment container and hide cards
        binding.fragmentContainer.visibility = View.VISIBLE
        
        // Hide all cards
        hideAllCards()

        // Add or show fragment
        val existingFragment = supportFragmentManager.findFragmentByTag(tag)
        if (existingFragment != null) {
            // Fragment already exists, just show it
            supportFragmentManager.beginTransaction()
                .hide(currentFragment!!)
                .show(existingFragment)
                .commit()
            currentFragment = existingFragment
        } else {
            // Add new fragment
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment, tag)
                .commit()
            currentFragment = fragment
        }
    }

    private fun hideAllCards() {
        // This will be handled by the ScrollView automatically
        // The cards will be scrolled out of view when fragment is shown
    }

    override fun onBackPressed() {
        if (binding.fragmentContainer.visibility == View.VISIBLE) {
            // If fragment is visible, hide it and show cards
            binding.fragmentContainer.visibility = View.GONE
            currentFragment = null
        } else {
            super.onBackPressed()
        }
    }
}
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
            loadFragment(QuranFragment(), "القرآن الكريم")
        }
        
        // Morning Dhikr Card
        binding.cardMorningDhikr.setOnClickListener {
            loadFragment(MorningDhikrFragment(), "أذكار الصباح")
        }
        
        // Evening Dhikr Card
        binding.cardEveningDhikr.setOnClickListener {
            loadFragment(EveningDhikrFragment(), "أذكار المساء")
        }
        
        // Hadith Card
        binding.cardHadith.setOnClickListener {
            loadFragment(HadithFragment(), "الحديث الشريف")
        }
        
        // Tasbih Card
        binding.cardTasbih.setOnClickListener {
            loadFragment(TasbihFragment(), "السبحة الإلكترونية")
        }
        
        // Islamic Knowledge Card
        binding.cardIslamicKnowledge.setOnClickListener {
            loadFragment(IslamicKnowledgeFragment(), "المعرفة الإسلامية")
        }
        
        // Settings Card
        binding.cardSettings.setOnClickListener {
            loadFragment(SettingsFragment(), "الإعدادات")
        }
    }
    
    private fun loadFragment(fragment: Fragment, title: String) {
        // Hide the main content and show fragment container
        binding.root.findViewById<View>(R.id.fragment_container).visibility = View.VISIBLE
        
        // Add back button functionality
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = title
        }
        
        // Load the fragment
        currentFragment = fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
    
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
            // Hide fragment container when back to main screen
            if (supportFragmentManager.backStackEntryCount == 0) {
                binding.root.findViewById<View>(R.id.fragment_container).visibility = View.GONE
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                supportActionBar?.title = getString(R.string.app_name)
            }
        } else {
            super.onBackPressed()
        }
    }
    
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
package com.ammarapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.ammarapp.databinding.ActivityMainBinding
import com.ammarapp.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    private lateinit var fragmentManager: FragmentManager
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupUI()
        setupNavigation()
        setupInitialFragment()
    }
    
    private fun setupUI() {
        // إعداد شريط الحالة
        window.statusBarColor = ContextCompat.getColor(this, R.color.primary)
        
        // إعداد شريط التنقل
        window.navigationBarColor = ContextCompat.getColor(this, R.color.background_primary)
        
        fragmentManager = supportFragmentManager
    }
    
    private fun setupNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_quran -> {
                    loadFragment(QuranFragment(), "quran")
                    true
                }
                R.id.nav_morning_dhikr -> {
                    loadFragment(MorningDhikrFragment(), "morning_dhikr")
                    true
                }
                R.id.nav_evening_dhikr -> {
                    loadFragment(EveningDhikrFragment(), "evening_dhikr")
                    true
                }
                R.id.nav_hadith -> {
                    loadFragment(HadithFragment(), "hadith")
                    true
                }
                R.id.nav_tasbih -> {
                    loadFragment(TasbihFragment(), "tasbih")
                    true
                }
                R.id.nav_settings -> {
                    loadFragment(SettingsFragment(), "settings")
                    true
                }
                else -> false
            }
        }
    }
    
    private fun setupInitialFragment() {
        // تحميل شاشة المصحف كشاشة افتراضية
        loadFragment(QuranFragment(), "quran")
        binding.bottomNavigation.selectedItemId = R.id.nav_quran
    }
    
    private fun loadFragment(fragment: Fragment, tag: String) {
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        
        // إخفاء جميع الشاشات
        val fragments = fragmentManager.fragments
        for (frag in fragments) {
            transaction.hide(frag)
        }
        
        // إظهار الشاشة المطلوبة أو إضافتها إذا لم تكن موجودة
        if (fragmentManager.findFragmentByTag(tag) != null) {
            transaction.show(fragmentManager.findFragmentByTag(tag)!!)
        } else {
            transaction.add(R.id.fragment_container, fragment, tag)
        }
        
        transaction.commit()
    }
    
    override fun onBackPressed() {
        // إذا كان المستخدم في شاشة المصحف، أغلق التطبيق
        if (binding.bottomNavigation.selectedItemId == R.id.nav_quran) {
            super.onBackPressed()
        } else {
            // العودة إلى شاشة المصحف
            binding.bottomNavigation.selectedItemId = R.id.nav_quran
        }
    }
}
package com.ammarapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import com.ammarapp.adapters.IslamicFeaturesAdapter
import com.ammarapp.databinding.ActivityMainBinding
import com.ammarapp.fragments.*
import com.ammarapp.models.IslamicFeature
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    private lateinit var fragmentManager: FragmentManager
    private lateinit var featuresAdapter: IslamicFeaturesAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupUI()
        setupFeaturesGrid()
        setupNavigation()
    }
    
    private fun setupUI() {
        // إعداد شريط الحالة
        window.statusBarColor = ContextCompat.getColor(this, R.color.islamic_blue)
        
        // إعداد شريط التنقل
        window.navigationBarColor = ContextCompat.getColor(this, R.color.background_islamic)
        
        fragmentManager = supportFragmentManager
    }
    
    private fun setupFeaturesGrid() {
        binding.featuresGrid.layoutManager = GridLayoutManager(this, 2)
        featuresAdapter = IslamicFeaturesAdapter { feature ->
            openFeature(feature)
        }
        binding.featuresGrid.adapter = featuresAdapter
        
        loadIslamicFeatures()
    }
    
    private fun loadIslamicFeatures() {
        val features = listOf(
            IslamicFeature(
                id = 1,
                title = "المصحف الشريف",
                subtitle = "القرآن الكريم",
                description = "اقرأ القرآن الكريم كاملاً مع التفسير والترجمة والبحث في السور",
                iconResId = R.drawable.ic_quran,
                backgroundColor = ContextCompat.getColor(this, R.color.islamic_blue),
                textColor = ContextCompat.getColor(this, R.color.islamic_white),
                category = "quran"
            ),
            IslamicFeature(
                id = 2,
                title = "أذكار الصباح",
                subtitle = "أذكار الصباح المباركة",
                description = "أذكار الصباح مع عداد إلكتروني وشريط تقدم مرئي",
                iconResId = R.drawable.ic_morning,
                backgroundColor = ContextCompat.getColor(this, R.color.islamic_gold),
                textColor = ContextCompat.getColor(this, R.color.islamic_white),
                category = "morning_dhikr"
            ),
            IslamicFeature(
                id = 3,
                title = "أذكار المساء",
                subtitle = "أذكار المساء المباركة",
                description = "أذكار المساء مع عداد إلكتروني وشريط تقدم مرئي",
                iconResId = R.drawable.ic_evening,
                backgroundColor = ContextCompat.getColor(this, R.color.islamic_brown),
                textColor = ContextCompat.getColor(this, R.color.islamic_white),
                category = "evening_dhikr"
            ),
            IslamicFeature(
                id = 4,
                title = "الأحاديث النبوية",
                subtitle = "السنة النبوية",
                description = "مجموعة من الأحاديث النبوية الشريفة مع البحث والمفضلة",
                iconResId = R.drawable.ic_hadith,
                backgroundColor = ContextCompat.getColor(this, R.color.islamic_green),
                textColor = ContextCompat.getColor(this, R.color.islamic_white),
                category = "hadith"
            ),
            IslamicFeature(
                id = 5,
                title = "المسبحة الإلكترونية",
                subtitle = "التسبيح الرقمي",
                description = "مسبحة إلكترونية مع اهتزاز وصوت وخيارات متعددة للعدد",
                iconResId = R.drawable.ic_tasbih,
                backgroundColor = ContextCompat.getColor(this, R.color.accent_purple),
                textColor = ContextCompat.getColor(this, R.color.islamic_white),
                category = "tasbih"
            ),
            IslamicFeature(
                id = 6,
                title = "الإعدادات",
                subtitle = "تخصيص التطبيق",
                description = "إعدادات التطبيق والوضع المظلم والإشعارات",
                iconResId = R.drawable.ic_settings,
                backgroundColor = ContextCompat.getColor(this, R.color.text_secondary),
                textColor = ContextCompat.getColor(this, R.color.islamic_white),
                category = "settings"
            )
        )
        
        featuresAdapter.submitList(features)
    }
    
    private fun openFeature(feature: IslamicFeature) {
        when (feature.category) {
            "quran" -> {
                loadFragment(QuranFragment(), "quran")
                binding.bottomNavigation.selectedItemId = R.id.nav_quran
            }
            "morning_dhikr" -> {
                loadFragment(MorningDhikrFragment(), "morning_dhikr")
                binding.bottomNavigation.selectedItemId = R.id.nav_morning_dhikr
            }
            "evening_dhikr" -> {
                loadFragment(EveningDhikrFragment(), "evening_dhikr")
                binding.bottomNavigation.selectedItemId = R.id.nav_evening_dhikr
            }
            "hadith" -> {
                loadFragment(HadithFragment(), "hadith")
                binding.bottomNavigation.selectedItemId = R.id.nav_hadith
            }
            "tasbih" -> {
                loadFragment(TasbihFragment(), "tasbih")
                binding.bottomNavigation.selectedItemId = R.id.nav_tasbih
            }
            "settings" -> {
                loadFragment(SettingsFragment(), "settings")
                binding.bottomNavigation.selectedItemId = R.id.nav_settings
            }
        }
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
            transaction.add(R.id.features_grid, fragment, tag)
        }
        
        transaction.commit()
    }
    
    override fun onBackPressed() {
        // إذا كان المستخدم في شاشة الميزات، أغلق التطبيق
        if (binding.bottomNavigation.selectedItemId == R.id.nav_quran) {
            super.onBackPressed()
        } else {
            // العودة إلى شاشة الميزات
            binding.bottomNavigation.selectedItemId = R.id.nav_quran
        }
    }
}
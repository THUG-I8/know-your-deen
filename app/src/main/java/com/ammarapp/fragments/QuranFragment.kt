package com.ammarapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.ammarapp.R
import com.ammarapp.adapters.SurahAdapter
import com.ammarapp.databinding.FragmentQuranBinding
import com.ammarapp.models.Surah

class QuranFragment : Fragment() {
    
    private var _binding: FragmentQuranBinding? = null
    private val binding get() = _binding!!
    private lateinit var surahAdapter: SurahAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuranBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        loadSurahs()
    }
    
    private fun setupUI() {
        // إعداد RecyclerView
        binding.recyclerViewSurahs.layoutManager = GridLayoutManager(context, 2)
        surahAdapter = SurahAdapter { surah ->
            // فتح السورة
            openSurah(surah)
        }
        binding.recyclerViewSurahs.adapter = surahAdapter
        
        // إعداد البحث
        binding.searchView.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            
            override fun onQueryTextChange(newText: String?): Boolean {
                filterSurahs(newText)
                return true
            }
        })
        
        // إعداد الأزرار
        binding.cardLastRead.setOnClickListener {
            // فتح آخر قراءة
        }
        
        binding.cardBookmarks.setOnClickListener {
            // فتح المحفوظات
        }
    }
    
    private fun loadSurahs() {
        val surahs = listOf(
            Surah(1, "الفاتحة", "Al-Fatiha", 7, "مكية"),
            Surah(2, "البقرة", "Al-Baqarah", 286, "مدنية"),
            Surah(3, "آل عمران", "Aal-Imran", 200, "مدنية"),
            Surah(4, "النساء", "An-Nisa", 176, "مدنية"),
            Surah(5, "المائدة", "Al-Ma'idah", 120, "مدنية"),
            Surah(6, "الأنعام", "Al-An'am", 165, "مكية"),
            Surah(7, "الأعراف", "Al-A'raf", 206, "مكية"),
            Surah(8, "الأنفال", "Al-Anfal", 75, "مدنية"),
            Surah(9, "التوبة", "At-Tawbah", 129, "مدنية"),
            Surah(10, "يونس", "Yunus", 109, "مكية"),
            Surah(11, "هود", "Hud", 123, "مكية"),
            Surah(12, "يوسف", "Yusuf", 111, "مكية"),
            Surah(13, "الرعد", "Ar-Ra'd", 43, "مدنية"),
            Surah(14, "إبراهيم", "Ibrahim", 52, "مكية"),
            Surah(15, "الحجر", "Al-Hijr", 99, "مكية"),
            Surah(16, "النحل", "An-Nahl", 128, "مكية"),
            Surah(17, "الإسراء", "Al-Isra", 111, "مكية"),
            Surah(18, "الكهف", "Al-Kahf", 110, "مكية"),
            Surah(19, "مريم", "Maryam", 98, "مكية"),
            Surah(20, "طه", "Ta-Ha", 135, "مكية"),
            Surah(21, "الأنبياء", "Al-Anbya", 112, "مكية"),
            Surah(22, "الحج", "Al-Hajj", 78, "مدنية"),
            Surah(23, "المؤمنون", "Al-Mu'minun", 118, "مكية"),
            Surah(24, "النور", "An-Nur", 64, "مدنية"),
            Surah(25, "الفرقان", "Al-Furqan", 77, "مكية"),
            Surah(26, "الشعراء", "Ash-Shu'ara", 227, "مكية"),
            Surah(27, "النمل", "An-Naml", 93, "مكية"),
            Surah(28, "القصص", "Al-Qasas", 88, "مكية"),
            Surah(29, "العنكبوت", "Al-Ankabut", 69, "مكية"),
            Surah(30, "الروم", "Ar-Rum", 60, "مكية"),
            Surah(31, "لقمان", "Luqman", 34, "مكية"),
            Surah(32, "السجدة", "As-Sajdah", 30, "مكية"),
            Surah(33, "الأحزاب", "Al-Ahzab", 73, "مدنية"),
            Surah(34, "سبأ", "Saba", 54, "مكية"),
            Surah(35, "فاطر", "Fatir", 45, "مكية"),
            Surah(36, "يس", "Ya-Sin", 83, "مكية"),
            Surah(37, "الصافات", "As-Saffat", 182, "مكية"),
            Surah(38, "ص", "Sad", 88, "مكية"),
            Surah(39, "الزمر", "Az-Zumar", 75, "مكية"),
            Surah(40, "غافر", "Ghafir", 85, "مكية"),
            Surah(41, "فصلت", "Fussilat", 54, "مكية"),
            Surah(42, "الشورى", "Ash-Shuraa", 53, "مكية"),
            Surah(43, "الزخرف", "Az-Zukhruf", 89, "مكية"),
            Surah(44, "الدخان", "Ad-Dukhan", 59, "مكية"),
            Surah(45, "الجاثية", "Al-Jathiyah", 37, "مكية"),
            Surah(46, "الأحقاف", "Al-Ahqaf", 35, "مكية"),
            Surah(47, "محمد", "Muhammad", 38, "مدنية"),
            Surah(48, "الفتح", "Al-Fath", 29, "مدنية"),
            Surah(49, "الحجرات", "Al-Hujurat", 18, "مدنية"),
            Surah(50, "ق", "Qaf", 45, "مكية"),
            Surah(51, "الذاريات", "Adh-Dhariyat", 60, "مكية"),
            Surah(52, "الطور", "At-Tur", 49, "مكية"),
            Surah(53, "النجم", "An-Najm", 62, "مكية"),
            Surah(54, "القمر", "Al-Qamar", 55, "مكية"),
            Surah(55, "الرحمن", "Ar-Rahman", 78, "مدنية"),
            Surah(56, "الواقعة", "Al-Waqi'ah", 96, "مكية"),
            Surah(57, "الحديد", "Al-Hadid", 29, "مدنية"),
            Surah(58, "المجادلة", "Al-Mujadila", 22, "مدنية"),
            Surah(59, "الحشر", "Al-Hashr", 24, "مدنية"),
            Surah(60, "الممتحنة", "Al-Mumtahanah", 13, "مدنية"),
            Surah(61, "الصف", "As-Saf", 14, "مدنية"),
            Surah(62, "الجمعة", "Al-Jumu'ah", 11, "مدنية"),
            Surah(63, "المنافقون", "Al-Munafiqun", 11, "مدنية"),
            Surah(64, "التغابن", "At-Taghabun", 18, "مدنية"),
            Surah(65, "الطلاق", "At-Talaq", 12, "مدنية"),
            Surah(66, "التحريم", "At-Tahrim", 12, "مدنية"),
            Surah(67, "الملك", "Al-Mulk", 30, "مكية"),
            Surah(68, "القلم", "Al-Qalam", 52, "مكية"),
            Surah(69, "الحاقة", "Al-Haqqah", 52, "مكية"),
            Surah(70, "المعارج", "Al-Ma'arij", 44, "مكية"),
            Surah(71, "نوح", "Nuh", 28, "مكية"),
            Surah(72, "الجن", "Al-Jinn", 28, "مكية"),
            Surah(73, "المزمل", "Al-Muzzammil", 20, "مكية"),
            Surah(74, "المدثر", "Al-Muddathir", 56, "مكية"),
            Surah(75, "القيامة", "Al-Qiyamah", 40, "مكية"),
            Surah(76, "الإنسان", "Al-Insan", 31, "مدنية"),
            Surah(77, "المرسلات", "Al-Mursalat", 50, "مكية"),
            Surah(78, "النبأ", "An-Naba", 40, "مكية"),
            Surah(79, "النازعات", "An-Nazi'at", 46, "مكية"),
            Surah(80, "عبس", "Abasa", 42, "مكية"),
            Surah(81, "التكوير", "At-Takwir", 29, "مكية"),
            Surah(82, "الإنفطار", "Al-Infitar", 19, "مكية"),
            Surah(83, "المطففين", "Al-Mutaffifin", 36, "مكية"),
            Surah(84, "الإنشقاق", "Al-Inshiqaq", 25, "مكية"),
            Surah(85, "البروج", "Al-Buruj", 22, "مكية"),
            Surah(86, "الطارق", "At-Tariq", 17, "مكية"),
            Surah(87, "الأعلى", "Al-A'la", 19, "مكية"),
            Surah(88, "الغاشية", "Al-Ghashiyah", 26, "مكية"),
            Surah(89, "الفجر", "Al-Fajr", 30, "مكية"),
            Surah(90, "البلد", "Al-Balad", 20, "مكية"),
            Surah(91, "الشمس", "Ash-Shams", 15, "مكية"),
            Surah(92, "الليل", "Al-Layl", 21, "مكية"),
            Surah(93, "الضحى", "Ad-Duha", 11, "مكية"),
            Surah(94, "الشرح", "Ash-Sharh", 8, "مكية"),
            Surah(95, "التين", "At-Tin", 8, "مكية"),
            Surah(96, "العلق", "Al-'Alaq", 19, "مكية"),
            Surah(97, "القدر", "Al-Qadr", 5, "مكية"),
            Surah(98, "البينة", "Al-Bayyinah", 8, "مدنية"),
            Surah(99, "الزلزلة", "Az-Zalzalah", 8, "مدنية"),
            Surah(100, "العاديات", "Al-'Adiyat", 11, "مكية"),
            Surah(101, "القارعة", "Al-Qari'ah", 11, "مكية"),
            Surah(102, "التكاثر", "At-Takathur", 8, "مكية"),
            Surah(103, "العصر", "Al-'Asr", 3, "مكية"),
            Surah(104, "الهمزة", "Al-Humazah", 9, "مكية"),
            Surah(105, "الفيل", "Al-Fil", 5, "مكية"),
            Surah(106, "قريش", "Quraysh", 4, "مكية"),
            Surah(107, "الماعون", "Al-Ma'un", 7, "مكية"),
            Surah(108, "الكوثر", "Al-Kawthar", 3, "مكية"),
            Surah(109, "الكافرون", "Al-Kafirun", 6, "مكية"),
            Surah(110, "النصر", "An-Nasr", 3, "مدنية"),
            Surah(111, "المسد", "Al-Masad", 5, "مكية"),
            Surah(112, "الإخلاص", "Al-Ikhlas", 4, "مكية"),
            Surah(113, "الفلق", "Al-Falaq", 5, "مكية"),
            Surah(114, "الناس", "An-Nas", 6, "مكية")
        )
        
        surahAdapter.submitList(surahs)
    }
    
    private fun filterSurahs(query: String?) {
        // تصفية السور حسب البحث
    }
    
    private fun openSurah(surah: Surah) {
        // فتح السورة المحددة
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.ammarapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ammarapp.adapters.DhikrAdapter
import com.ammarapp.databinding.FragmentMorningDhikrBinding
import com.ammarapp.models.Dhikr

class MorningDhikrFragment : Fragment() {
    
    private var _binding: FragmentMorningDhikrBinding? = null
    private val binding get() = _binding!!
    private lateinit var dhikrAdapter: DhikrAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMorningDhikrBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        loadMorningDhikr()
    }
    
    private fun setupUI() {
        // إعداد RecyclerView
        binding.recyclerViewDhikr.layoutManager = LinearLayoutManager(context)
        dhikrAdapter = DhikrAdapter { dhikr ->
            // فتح الذكر
            openDhikr(dhikr)
        }
        binding.recyclerViewDhikr.adapter = dhikrAdapter
        
        // إعداد زر إعادة تعيين العداد
        binding.buttonReset.setOnClickListener {
            resetAllCounters()
        }
    }
    
    private fun loadMorningDhikr() {
        val morningDhikr = listOf(
            Dhikr(
                id = 1,
                title = "سُبْحَانَ اللَّهِ وَبِحَمْدِهِ",
                arabicText = "سُبْحَانَ اللَّهِ وَبِحَمْدِهِ",
                translation = "سبحان الله وبحمده",
                count = 100,
                currentCount = 0,
                description = "من قالها مائة مرة غفرت ذنوبه وإن كانت مثل زبد البحر"
            ),
            Dhikr(
                id = 2,
                title = "لا إله إلا الله وحده لا شريك له",
                arabicText = "لا إله إلا الله وحده لا شريك له، له الملك وله الحمد وهو على كل شيء قدير",
                translation = "لا إله إلا الله وحده لا شريك له، له الملك وله الحمد وهو على كل شيء قدير",
                count = 100,
                currentCount = 0,
                description = "من قالها مائة مرة كانت له عدل عشر رقاب وكتبت له مائة حسنة ومحيت عنه مائة سيئة"
            ),
            Dhikr(
                id = 3,
                title = "أستغفر الله وأتوب إليه",
                arabicText = "أستغفر الله وأتوب إليه",
                translation = "أستغفر الله وأتوب إليه",
                count = 100,
                currentCount = 0,
                description = "من قالها مائة مرة غفر الله له ذنوبه"
            ),
            Dhikr(
                id = 4,
                title = "اللَّهُمَّ صَلِّ عَلَى مُحَمَّدٍ",
                arabicText = "اللَّهُمَّ صَلِّ عَلَى مُحَمَّدٍ وَعَلَى آلِ مُحَمَّدٍ",
                translation = "اللهم صل على محمد وعلى آل محمد",
                count = 100,
                currentCount = 0,
                description = "من صلى على النبي صلى الله عليه وسلم مرة واحدة صلى الله عليه بها عشراً"
            ),
            Dhikr(
                id = 5,
                title = "سُبْحَانَ اللَّهِ",
                arabicText = "سُبْحَانَ اللَّهِ",
                translation = "سبحان الله",
                count = 33,
                currentCount = 0,
                description = "من قالها ثلاثاً وثلاثين مرة غفرت ذنوبه"
            ),
            Dhikr(
                id = 6,
                title = "الْحَمْدُ لِلَّهِ",
                arabicText = "الْحَمْدُ لِلَّهِ",
                translation = "الحمد لله",
                count = 33,
                currentCount = 0,
                description = "من قالها ثلاثاً وثلاثين مرة غفرت ذنوبه"
            ),
            Dhikr(
                id = 7,
                title = "اللَّهُ أَكْبَرُ",
                arabicText = "اللَّهُ أَكْبَرُ",
                translation = "الله أكبر",
                count = 33,
                currentCount = 0,
                description = "من قالها ثلاثاً وثلاثين مرة غفرت ذنوبه"
            ),
            Dhikr(
                id = 8,
                title = "لا إله إلا الله",
                arabicText = "لا إله إلا الله",
                translation = "لا إله إلا الله",
                count = 100,
                currentCount = 0,
                description = "من قالها مائة مرة كتب الله له مائة حسنة"
            ),
            Dhikr(
                id = 9,
                title = "اللَّهُمَّ إِنِّي أَسْأَلُكَ الْجَنَّةَ",
                arabicText = "اللَّهُمَّ إِنِّي أَسْأَلُكَ الْجَنَّةَ وَأَعُوذُ بِكَ مِنَ النَّارِ",
                translation = "اللهم إني أسألك الجنة وأعوذ بك من النار",
                count = 7,
                currentCount = 0,
                description = "من قالها سبع مرات استجاب الله له"
            ),
            Dhikr(
                id = 10,
                title = "رَضِيتُ بِاللَّهِ رَبًّا",
                arabicText = "رَضِيتُ بِاللَّهِ رَبًّا وَبِالْإِسْلَامِ دِينًا وَبِمُحَمَّدٍ نَبِيًّا",
                translation = "رضيت بالله رباً وبالإسلام ديناً وبمحمد نبياً",
                count = 3,
                currentCount = 0,
                description = "من قالها ثلاث مرات وجبت له الجنة"
            )
        )
        
        dhikrAdapter.submitList(morningDhikr)
    }
    
    private fun openDhikr(dhikr: Dhikr) {
        // فتح شاشة الذكر مع العداد
        // يمكن إضافة شاشة منفصلة للذكر مع عداد
    }
    
    private fun resetAllCounters() {
        // إعادة تعيين جميع العدادات
        dhikrAdapter.resetAllCounters()
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
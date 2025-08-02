package com.ammarapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ammarapp.adapters.DhikrAdapter
import com.ammarapp.databinding.FragmentEveningDhikrBinding
import com.ammarapp.models.Dhikr

class EveningDhikrFragment : Fragment() {
    
    private var _binding: FragmentEveningDhikrBinding? = null
    private val binding get() = _binding!!
    private lateinit var dhikrAdapter: DhikrAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEveningDhikrBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        loadEveningDhikr()
    }
    
    private fun setupUI() {
        binding.recyclerViewDhikr.layoutManager = LinearLayoutManager(context)
        dhikrAdapter = DhikrAdapter { dhikr ->
            openDhikr(dhikr)
        }
        binding.recyclerViewDhikr.adapter = dhikrAdapter
        
        binding.buttonReset.setOnClickListener {
            resetAllCounters()
        }
    }
    
    private fun loadEveningDhikr() {
        val eveningDhikr = listOf(
            Dhikr(
                id = 1,
                title = "أَمْسَيْنَا وَأَمْسَى الْمُلْكُ لِلَّهِ",
                arabicText = "أَمْسَيْنَا وَأَمْسَى الْمُلْكُ لِلَّهِ، وَالْحَمْدُ لِلَّهِ، لاَ إِلَهَ إِلاَّ اللَّهُ وَحْدَهُ لاَ شَرِيكَ لَهُ",
                translation = "أمسينا وأمسى الملك لله، والحمد لله، لا إله إلا الله وحده لا شريك له",
                count = 1,
                currentCount = 0,
                description = "من قالها في المساء كان له أجر عتق رقبة"
            ),
            Dhikr(
                id = 2,
                title = "اللَّهُمَّ بِكَ أَمْسَيْنَا",
                arabicText = "اللَّهُمَّ بِكَ أَمْسَيْنَا، وَبِكَ أَصْبَحْنَا، وَبِكَ نَحْيَا، وَبِكَ نَمُوتُ، وَإِلَيْكَ النُّشُورُ",
                translation = "اللهم بك أمسينا، وبك أصبحنا، وبك نحيا، وبك نموت، وإليك النشور",
                count = 1,
                currentCount = 0,
                description = "من قالها في المساء كان له أجر عتق رقبة"
            ),
            Dhikr(
                id = 3,
                title = "سُبْحَانَ اللَّهِ وَبِحَمْدِهِ",
                arabicText = "سُبْحَانَ اللَّهِ وَبِحَمْدِهِ",
                translation = "سبحان الله وبحمده",
                count = 100,
                currentCount = 0,
                description = "من قالها مائة مرة غفرت ذنوبه وإن كانت مثل زبد البحر"
            ),
            Dhikr(
                id = 4,
                title = "لا إله إلا الله وحده لا شريك له",
                arabicText = "لا إله إلا الله وحده لا شريك له، له الملك وله الحمد وهو على كل شيء قدير",
                translation = "لا إله إلا الله وحده لا شريك له، له الملك وله الحمد وهو على كل شيء قدير",
                count = 100,
                currentCount = 0,
                description = "من قالها مائة مرة كانت له عدل عشر رقاب وكتبت له مائة حسنة ومحيت عنه مائة سيئة"
            ),
            Dhikr(
                id = 5,
                title = "أستغفر الله وأتوب إليه",
                arabicText = "أستغفر الله وأتوب إليه",
                translation = "أستغفر الله وأتوب إليه",
                count = 100,
                currentCount = 0,
                description = "من قالها مائة مرة غفر الله له ذنوبه"
            ),
            Dhikr(
                id = 6,
                title = "اللَّهُمَّ صَلِّ عَلَى مُحَمَّدٍ",
                arabicText = "اللَّهُمَّ صَلِّ عَلَى مُحَمَّدٍ وَعَلَى آلِ مُحَمَّدٍ",
                translation = "اللهم صل على محمد وعلى آل محمد",
                count = 100,
                currentCount = 0,
                description = "من صلى على النبي صلى الله عليه وسلم مرة واحدة صلى الله عليه بها عشراً"
            ),
            Dhikr(
                id = 7,
                title = "سُبْحَانَ اللَّهِ",
                arabicText = "سُبْحَانَ اللَّهِ",
                translation = "سبحان الله",
                count = 33,
                currentCount = 0,
                description = "من قالها ثلاثاً وثلاثين مرة غفرت ذنوبه"
            ),
            Dhikr(
                id = 8,
                title = "الْحَمْدُ لِلَّهِ",
                arabicText = "الْحَمْدُ لِلَّهِ",
                translation = "الحمد لله",
                count = 33,
                currentCount = 0,
                description = "من قالها ثلاثاً وثلاثين مرة غفرت ذنوبه"
            ),
            Dhikr(
                id = 9,
                title = "اللَّهُ أَكْبَرُ",
                arabicText = "اللَّهُ أَكْبَرُ",
                translation = "الله أكبر",
                count = 33,
                currentCount = 0,
                description = "من قالها ثلاثاً وثلاثين مرة غفرت ذنوبه"
            ),
            Dhikr(
                id = 10,
                title = "لا إله إلا الله",
                arabicText = "لا إله إلا الله",
                translation = "لا إله إلا الله",
                count = 100,
                currentCount = 0,
                description = "من قالها مائة مرة كتب الله له مائة حسنة"
            )
        )
        
        dhikrAdapter.submitList(eveningDhikr)
    }
    
    private fun openDhikr(dhikr: Dhikr) {
        // فتح شاشة الذكر مع العداد
    }
    
    private fun resetAllCounters() {
        dhikrAdapter.resetAllCounters()
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
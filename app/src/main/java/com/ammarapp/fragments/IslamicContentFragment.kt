package com.ammarapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ammarapp.adapters.IslamicContentAdapter
import com.ammarapp.databinding.FragmentIslamicContentBinding
import com.ammarapp.models.IslamicContent

class IslamicContentFragment : Fragment() {
    
    private var _binding: FragmentIslamicContentBinding? = null
    private val binding get() = _binding!!
    private lateinit var contentAdapter: IslamicContentAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIslamicContentBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        loadIslamicContent()
    }
    
    private fun setupUI() {
        binding.recyclerViewContent.layoutManager = LinearLayoutManager(context)
        contentAdapter = IslamicContentAdapter { content ->
            openContent(content)
        }
        binding.recyclerViewContent.adapter = contentAdapter
    }
    
    private fun loadIslamicContent() {
        val contents = listOf(
            IslamicContent(
                id = 1,
                title = "أركان الإسلام",
                subtitle = "الأركان الخمسة",
                description = "تعرف على أركان الإسلام الخمسة وأهميتها في حياة المسلم",
                iconResId = com.ammarapp.R.drawable.ic_islamic_pillars,
                backgroundColor = com.ammarapp.R.color.islamic_green,
                category = "pillars"
            ),
            IslamicContent(
                id = 2,
                title = "أركان الإيمان",
                subtitle = "الإيمان بالله وملائكته",
                description = "تعرف على أركان الإيمان الستة وأهميتها في العقيدة الإسلامية",
                iconResId = com.ammarapp.R.drawable.ic_faith,
                backgroundColor = com.ammarapp.R.color.islamic_blue,
                category = "faith"
            ),
            IslamicContent(
                id = 3,
                title = "أخلاق المسلم",
                subtitle = "الآداب الإسلامية",
                description = "تعرف على الأخلاق الحميدة التي يجب أن يتحلى بها المسلم",
                iconResId = com.ammarapp.R.drawable.ic_ethics,
                backgroundColor = com.ammarapp.R.color.islamic_gold,
                category = "ethics"
            ),
            IslamicContent(
                id = 4,
                title = "أحكام الصلاة",
                subtitle = "فقه العبادات",
                description = "تعرف على أحكام الصلاة وشروطها وأركانها وواجباتها",
                iconResId = com.ammarapp.R.drawable.ic_prayer,
                backgroundColor = com.ammarapp.R.color.islamic_brown,
                category = "prayer"
            ),
            IslamicContent(
                id = 5,
                title = "أحكام الصيام",
                subtitle = "رمضان المبارك",
                description = "تعرف على أحكام الصيام وشروطه ومبطلاته ومستحباته",
                iconResId = com.ammarapp.R.drawable.ic_fasting,
                backgroundColor = com.ammarapp.R.color.accent_purple,
                category = "fasting"
            ),
            IslamicContent(
                id = 6,
                title = "أحكام الزكاة",
                subtitle = "الزكاة والصدقة",
                description = "تعرف على أحكام الزكاة ومصارفها وشروط وجوبها",
                iconResId = com.ammarapp.R.drawable.ic_zakat,
                backgroundColor = com.ammarapp.R.color.islamic_green,
                category = "zakat"
            ),
            IslamicContent(
                id = 7,
                title = "أحكام الحج",
                subtitle = "الحج والعمرة",
                description = "تعرف على أحكام الحج والعمرة ومناسكهما وشروطهما",
                iconResId = com.ammarapp.R.drawable.ic_hajj,
                backgroundColor = com.ammarapp.R.color.islamic_blue,
                category = "hajj"
            ),
            IslamicContent(
                id = 8,
                title = "التفسير",
                subtitle = "تفسير القرآن",
                description = "تفسير آيات القرآن الكريم بأقوال المفسرين المعتبرين",
                iconResId = com.ammarapp.R.drawable.ic_tafsir,
                backgroundColor = com.ammarapp.R.color.islamic_gold,
                category = "tafsir"
            ),
            IslamicContent(
                id = 9,
                title = "السيرة النبوية",
                subtitle = "حياة الرسول ﷺ",
                description = "تعرف على سيرة النبي محمد ﷺ وحياته وأخلاقه",
                iconResId = com.ammarapp.R.drawable.ic_prophet,
                backgroundColor = com.ammarapp.R.color.islamic_brown,
                category = "prophet"
            ),
            IslamicContent(
                id = 10,
                title = "الفتاوى",
                subtitle = "الأسئلة الشرعية",
                description = "فتاوى العلماء المعتبرين في المسائل الشرعية المختلفة",
                iconResId = com.ammarapp.R.drawable.ic_fatwa,
                backgroundColor = com.ammarapp.R.color.accent_purple,
                category = "fatwa"
            )
        )
        
        contentAdapter.submitList(contents)
    }
    
    private fun openContent(content: IslamicContent) {
        // فتح المحتوى الإسلامي
        when (content.category) {
            "pillars" -> {
                // فتح أركان الإسلام
            }
            "faith" -> {
                // فتح أركان الإيمان
            }
            "ethics" -> {
                // فتح أخلاق المسلم
            }
            "prayer" -> {
                // فتح أحكام الصلاة
            }
            "fasting" -> {
                // فتح أحكام الصيام
            }
            "zakat" -> {
                // فتح أحكام الزكاة
            }
            "hajj" -> {
                // فتح أحكام الحج
            }
            "tafsir" -> {
                // فتح التفسير
            }
            "prophet" -> {
                // فتح السيرة النبوية
            }
            "fatwa" -> {
                // فتح الفتاوى
            }
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
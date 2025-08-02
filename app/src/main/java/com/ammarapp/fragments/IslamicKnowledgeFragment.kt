package com.ammarapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ammarapp.adapters.IslamicKnowledgeAdapter
import com.ammarapp.databinding.FragmentIslamicKnowledgeBinding
import com.ammarapp.models.IslamicKnowledge

class IslamicKnowledgeFragment : Fragment() {

    private var _binding: FragmentIslamicKnowledgeBinding? = null
    private val binding get() = _binding!!
    private lateinit var knowledgeAdapter: IslamicKnowledgeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIslamicKnowledgeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        loadIslamicKnowledge()
    }

    private fun setupRecyclerView() {
        knowledgeAdapter = IslamicKnowledgeAdapter { knowledge ->
            openKnowledgeItem(knowledge)
        }
        
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = knowledgeAdapter
        }
    }

    private fun loadIslamicKnowledge() {
        val knowledgeList = listOf(
            IslamicKnowledge(
                id = 1,
                title = "العقيدة الإسلامية",
                subtitle = "أصول الإيمان والإسلام",
                description = "تعلم أساسيات العقيدة الإسلامية وأركان الإيمان والإسلام",
                category = "عقيدة",
                iconResId = R.drawable.ic_quran,
                backgroundColor = "#74C3F5"
            ),
            IslamicKnowledge(
                id = 2,
                title = "الفقه الإسلامي",
                subtitle = "أحكام العبادات والمعاملات",
                description = "أحكام الصلاة والصيام والزكاة والحج والمعاملات المالية",
                category = "فقه",
                iconResId = R.drawable.ic_quran,
                backgroundColor = "#AB74F5"
            ),
            IslamicKnowledge(
                id = 3,
                title = "السيرة النبوية",
                subtitle = "حياة النبي محمد ﷺ",
                description = "سيرة النبي محمد ﷺ وأحداث حياته وأخلاقه الكريمة",
                category = "سيرة",
                iconResId = R.drawable.ic_hadith,
                backgroundColor = "#F59173"
            ),
            IslamicKnowledge(
                id = 4,
                title = "الأدعية والأذكار",
                subtitle = "أدعية مأثورة وأذكار يومية",
                description = "أدعية النبي ﷺ وأذكار الصباح والمساء وأدعية مأثورة",
                category = "أدعية",
                iconResId = R.drawable.ic_morning,
                backgroundColor = "#644680"
            ),
            IslamicKnowledge(
                id = 5,
                title = "الأحاديث النبوية",
                subtitle = "السنة النبوية الشريفة",
                description = "أحاديث النبي ﷺ الصحيحة مع شرحها وفوائدها",
                category = "حديث",
                iconResId = R.drawable.ic_hadith,
                backgroundColor = "#F57492"
            ),
            IslamicKnowledge(
                id = 6,
                title = "الأخلاق الإسلامية",
                subtitle = "مكارم الأخلاق",
                description = "الأخلاق الحميدة والصفات الكريمة في الإسلام",
                category = "أخلاق",
                iconResId = R.drawable.ic_settings,
                backgroundColor = "#F59173"
            ),
            IslamicKnowledge(
                id = 7,
                title = "التفسير القرآني",
                subtitle = "تفسير القرآن الكريم",
                description = "تفسير آيات القرآن الكريم وبيان معانيها",
                category = "تفسير",
                iconResId = R.drawable.ic_quran,
                backgroundColor = "#6f6767"
            ),
            IslamicKnowledge(
                id = 8,
                title = "الآداب الإسلامية",
                subtitle = "آداب المسلم في حياته",
                description = "آداب الطعام والشراب والزيارة والكلام وغيرها",
                category = "آداب",
                iconResId = R.drawable.ic_settings,
                backgroundColor = "#E0963F"
            )
        )
        
        knowledgeAdapter.submitList(knowledgeList)
    }

    private fun openKnowledgeItem(knowledge: IslamicKnowledge) {
        // TODO: Implement opening specific knowledge item
        // This could open a detailed view or another fragment
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
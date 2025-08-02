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
    private lateinit var adapter: IslamicKnowledgeAdapter

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
        adapter = IslamicKnowledgeAdapter { knowledge ->
            // Handle item click - show detailed content
            showKnowledgeDetails(knowledge)
        }
        
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@IslamicKnowledgeFragment.adapter
        }
    }

    private fun loadIslamicKnowledge() {
        val knowledgeList = listOf(
            IslamicKnowledge(
                id = 1,
                title = "العقيدة الإسلامية",
                subtitle = "أصول الإيمان والإسلام",
                description = "تعلم أساسيات العقيدة الإسلامية وأركان الإيمان والإسلام",
                icon = "🕌",
                color = "#74C3F5",
                content = listOf(
                    "أركان الإيمان الستة",
                    "أركان الإسلام الخمسة",
                    "التوحيد وأقسامه",
                    "أسماء الله الحسنى",
                    "صفات الله تعالى"
                )
            ),
            IslamicKnowledge(
                id = 2,
                title = "الفقه الإسلامي",
                subtitle = "أحكام العبادات والمعاملات",
                description = "تعلم أحكام الصلاة والصيام والزكاة والحج والمعاملات",
                icon = "📖",
                color = "#AB74F5",
                content = listOf(
                    "أحكام الطهارة",
                    "أحكام الصلاة",
                    "أحكام الصيام",
                    "أحكام الزكاة",
                    "أحكام الحج والعمرة"
                )
            ),
            IslamicKnowledge(
                id = 3,
                title = "السيرة النبوية",
                subtitle = "حياة النبي محمد ﷺ",
                description = "تعرف على سيرة النبي محمد ﷺ وأخلاقه وأعماله",
                icon = "🌙",
                color = "#F59173",
                content = listOf(
                    "مولد النبي ﷺ",
                    "نشأة النبي ﷺ",
                    "البعثة النبوية",
                    "الهجرة إلى المدينة",
                    "الفتوحات الإسلامية"
                )
            ),
            IslamicKnowledge(
                id = 4,
                title = "الأداب الإسلامية",
                subtitle = "آداب المسلم في حياته",
                description = "تعلم الآداب الإسلامية في التعامل مع الناس",
                icon = "🤝",
                color = "#E0963F",
                content = listOf(
                    "آداب الطعام والشراب",
                    "آداب المسجد",
                    "آداب الزيارة",
                    "آداب السفر",
                    "آداب النوم والاستيقاظ"
                )
            ),
            IslamicKnowledge(
                id = 5,
                title = "الأخلاق الإسلامية",
                subtitle = "مكارم الأخلاق",
                description = "تعلم الأخلاق الحميدة التي حث عليها الإسلام",
                icon = "💎",
                color = "#F57492",
                content = listOf(
                    "الصدق والأمانة",
                    "الصبر والتحمل",
                    "التواضع والكرم",
                    "الرحمة والعطف",
                    "العدل والإحسان"
                )
            ),
            IslamicKnowledge(
                id = 6,
                title = "تفسير القرآن",
                subtitle = "معاني آيات القرآن الكريم",
                description = "تعلم تفسير القرآن الكريم وأسباب النزول",
                icon = "📜",
                color = "#6f6767",
                content = listOf(
                    "أسباب النزول",
                    "المكي والمدني",
                    "أحكام القرآن",
                    "قصص القرآن",
                    "إعجاز القرآن"
                )
            )
        )
        
        adapter.submitList(knowledgeList)
    }

    private fun showKnowledgeDetails(knowledge: IslamicKnowledge) {
        // TODO: Implement detailed view for each knowledge section
        // This could open a new fragment or dialog with detailed content
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
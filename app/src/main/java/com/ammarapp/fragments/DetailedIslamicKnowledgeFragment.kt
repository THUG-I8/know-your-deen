package com.ammarapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ammarapp.adapters.DetailedIslamicKnowledgeAdapter
import com.ammarapp.data.IslamicContent
import com.ammarapp.databinding.FragmentDetailedIslamicKnowledgeBinding
import com.ammarapp.models.IslamicKnowledge

class DetailedIslamicKnowledgeFragment : Fragment() {

    private var _binding: FragmentDetailedIslamicKnowledgeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: DetailedIslamicKnowledgeAdapter
    private var currentCategory: String = "all"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailedIslamicKnowledgeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupCategoryButtons()
        loadContent("all")
    }

    private fun setupRecyclerView() {
        adapter = DetailedIslamicKnowledgeAdapter { knowledge ->
            showKnowledgeDetails(knowledge)
        }
        
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@DetailedIslamicKnowledgeFragment.adapter
        }
    }

    private fun setupCategoryButtons() {
        binding.apply {
            btnAll.setOnClickListener { loadContent("all") }
            btnAqeedah.setOnClickListener { loadContent("aqeedah") }
            btnFiqh.setOnClickListener { loadContent("fiqh") }
            btnSeerah.setOnClickListener { loadContent("seerah") }
            btnAdab.setOnClickListener { loadContent("adab") }
            btnAkhlaq.setOnClickListener { loadContent("akhlaq") }
            btnTafseer.setOnClickListener { loadContent("tafseer") }
        }
    }

    private fun loadContent(category: String) {
        currentCategory = category
        val content = when (category) {
            "aqeedah" -> IslamicContent.getAqeedahContent()
            "fiqh" -> IslamicContent.getFiqhContent()
            "seerah" -> IslamicContent.getSeerahContent()
            "adab" -> IslamicContent.getAdabContent()
            "akhlaq" -> IslamicContent.getAkhlaqContent()
            "tafseer" -> IslamicContent.getTafseerContent()
            else -> IslamicContent.getAllContent()
        }
        
        adapter.submitList(content)
        updateCategorySelection(category)
    }

    private fun updateCategorySelection(category: String) {
        binding.apply {
            // Reset all buttons
            btnAll.isSelected = false
            btnAqeedah.isSelected = false
            btnFiqh.isSelected = false
            btnSeerah.isSelected = false
            btnAdab.isSelected = false
            btnAkhlaq.isSelected = false
            btnTafseer.isSelected = false

            // Select current category
            when (category) {
                "all" -> btnAll.isSelected = true
                "aqeedah" -> btnAqeedah.isSelected = true
                "fiqh" -> btnFiqh.isSelected = true
                "seerah" -> btnSeerah.isSelected = true
                "adab" -> btnAdab.isSelected = true
                "akhlaq" -> btnAkhlaq.isSelected = true
                "tafseer" -> btnTafseer.isSelected = true
            }
        }
    }

    private fun showKnowledgeDetails(knowledge: IslamicKnowledge) {
        // TODO: Show detailed dialog or navigate to detail fragment
        // This could show all the content items in a beautiful dialog
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
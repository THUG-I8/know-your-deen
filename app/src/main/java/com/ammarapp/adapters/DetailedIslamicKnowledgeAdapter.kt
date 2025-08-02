package com.ammarapp.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ammarapp.databinding.ItemDetailedIslamicKnowledgeBinding
import com.ammarapp.models.IslamicKnowledge

class DetailedIslamicKnowledgeAdapter(
    private val onItemClick: (IslamicKnowledge) -> Unit
) : ListAdapter<IslamicKnowledge, DetailedIslamicKnowledgeAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDetailedIslamicKnowledgeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemDetailedIslamicKnowledgeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(getItem(position))
                }
            }
        }

        fun bind(knowledge: IslamicKnowledge) {
            binding.apply {
                tvTitle.text = knowledge.title
                tvSubtitle.text = knowledge.subtitle
                tvDescription.text = knowledge.description
                tvIcon.text = knowledge.icon
                
                // Set background color based on knowledge color
                try {
                    val color = Color.parseColor(knowledge.color)
                    cardBackground.setBackgroundColor(color)
                } catch (e: IllegalArgumentException) {
                    // Use default color if parsing fails
                    cardBackground.setBackgroundColor(Color.parseColor("#74C3F5"))
                }
                
                // Show content preview (first 2 items)
                val contentPreview = knowledge.content.take(2).joinToString("\n• ")
                tvContentPreview.text = if (contentPreview.isNotEmpty()) {
                    "• $contentPreview"
                } else {
                    "اضغط لعرض المحتوى"
                }
                
                // Show content count
                tvContentCount.text = "${knowledge.content.size} موضوع"
            }
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<IslamicKnowledge>() {
        override fun areItemsTheSame(oldItem: IslamicKnowledge, newItem: IslamicKnowledge): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: IslamicKnowledge, newItem: IslamicKnowledge): Boolean {
            return oldItem == newItem
        }
    }
}
package com.ammarapp.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ammarapp.databinding.ItemIslamicKnowledgeBinding
import com.ammarapp.models.IslamicKnowledge

class IslamicKnowledgeAdapter(
    private val onItemClick: (IslamicKnowledge) -> Unit
) : ListAdapter<IslamicKnowledge, IslamicKnowledgeAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemIslamicKnowledgeBinding.inflate(
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
        private val binding: ItemIslamicKnowledgeBinding
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
                titleText.text = knowledge.title
                subtitleText.text = knowledge.subtitle
                descriptionText.text = knowledge.description
                categoryText.text = knowledge.category
                iconImage.setImageResource(knowledge.iconResId)
                
                // Set background color
                try {
                    val color = Color.parseColor(knowledge.backgroundColor)
                    cardBackground.setBackgroundColor(color)
                } catch (e: IllegalArgumentException) {
                    // Use default color if parsing fails
                    cardBackground.setBackgroundColor(Color.parseColor("#74C3F5"))
                }
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
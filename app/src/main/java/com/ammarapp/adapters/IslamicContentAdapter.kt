package com.ammarapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ammarapp.databinding.ItemIslamicContentBinding
import com.ammarapp.models.IslamicContent

class IslamicContentAdapter(
    private val onContentClick: (IslamicContent) -> Unit
) : ListAdapter<IslamicContent, IslamicContentAdapter.ContentViewHolder>(ContentDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val binding = ItemIslamicContentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ContentViewHolder(binding, onContentClick)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ContentViewHolder(
        private val binding: ItemIslamicContentBinding,
        private val onContentClick: (IslamicContent) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(content: IslamicContent) {
            binding.apply {
                textViewTitle.text = content.title
                textViewSubtitle.text = content.subtitle
                textViewDescription.text = content.description
                
                // تعيين الألوان
                cardViewContent.setCardBackgroundColor(content.backgroundColor)
                textViewTitle.setTextColor(ContextCompat.getColor(root.context, com.ammarapp.R.color.islamic_white))
                textViewSubtitle.setTextColor(ContextCompat.getColor(root.context, com.ammarapp.R.color.overlay_light))
                textViewDescription.setTextColor(ContextCompat.getColor(root.context, com.ammarapp.R.color.overlay_light))
                
                // تعيين الأيقونة
                imageViewIcon.setImageResource(content.iconResId)
                imageViewIcon.setColorFilter(ContextCompat.getColor(root.context, com.ammarapp.R.color.islamic_white))
                
                root.setOnClickListener {
                    onContentClick(content)
                }
            }
        }
    }

    private class ContentDiffCallback : DiffUtil.ItemCallback<IslamicContent>() {
        override fun areItemsTheSame(oldItem: IslamicContent, newItem: IslamicContent): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: IslamicContent, newItem: IslamicContent): Boolean {
            return oldItem == newItem
        }
    }
}
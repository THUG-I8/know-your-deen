package com.ammarapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ammarapp.databinding.ItemIslamicFeatureBinding
import com.ammarapp.models.IslamicFeature

class IslamicFeaturesAdapter(
    private val onFeatureClick: (IslamicFeature) -> Unit
) : ListAdapter<IslamicFeature, IslamicFeaturesAdapter.FeatureViewHolder>(FeatureDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeatureViewHolder {
        val binding = ItemIslamicFeatureBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FeatureViewHolder(binding, onFeatureClick)
    }

    override fun onBindViewHolder(holder: FeatureViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FeatureViewHolder(
        private val binding: ItemIslamicFeatureBinding,
        private val onFeatureClick: (IslamicFeature) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(feature: IslamicFeature) {
            binding.apply {
                textViewTitle.text = feature.title
                textViewSubtitle.text = feature.subtitle
                textViewDescription.text = feature.description
                
                // تعيين الألوان
                cardViewFeature.setCardBackgroundColor(feature.backgroundColor)
                textViewTitle.setTextColor(feature.textColor)
                textViewSubtitle.setTextColor(feature.textColor)
                textViewDescription.setTextColor(feature.textColor)
                
                // تعيين الأيقونة
                imageViewIcon.setImageResource(feature.iconResId)
                imageViewIcon.setColorFilter(feature.textColor)
                
                root.setOnClickListener {
                    onFeatureClick(feature)
                }
            }
        }
    }

    private class FeatureDiffCallback : DiffUtil.ItemCallback<IslamicFeature>() {
        override fun areItemsTheSame(oldItem: IslamicFeature, newItem: IslamicFeature): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: IslamicFeature, newItem: IslamicFeature): Boolean {
            return oldItem == newItem
        }
    }
}
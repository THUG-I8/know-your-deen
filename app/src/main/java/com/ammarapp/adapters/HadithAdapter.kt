package com.ammarapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ammarapp.databinding.ItemHadithBinding
import com.ammarapp.models.Hadith

class HadithAdapter(
    private val onHadithClick: (Hadith) -> Unit
) : ListAdapter<Hadith, HadithAdapter.HadithViewHolder>(HadithDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HadithViewHolder {
        val binding = ItemHadithBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HadithViewHolder(binding, onHadithClick)
    }

    override fun onBindViewHolder(holder: HadithViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class HadithViewHolder(
        private val binding: ItemHadithBinding,
        private val onHadithClick: (Hadith) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(hadith: Hadith) {
            binding.apply {
                textViewTitle.text = hadith.title
                textViewArabicText.text = hadith.arabicText
                textViewTranslation.text = hadith.translation
                textViewNarrator.text = "رواه: ${hadith.narrator}"
                textViewSource.text = hadith.source
                
                // تحديث أيقونة المفضلة
                val favoriteIcon = if (hadith.isFavorite) {
                    com.ammarapp.R.drawable.ic_favorite_filled
                } else {
                    com.ammarapp.R.drawable.ic_favorite_border
                }
                buttonFavorite.setIconResource(favoriteIcon)
                
                buttonFavorite.setOnClickListener {
                    hadith.isFavorite = !hadith.isFavorite
                    val newIcon = if (hadith.isFavorite) {
                        com.ammarapp.R.drawable.ic_favorite_filled
                    } else {
                        com.ammarapp.R.drawable.ic_favorite_border
                    }
                    buttonFavorite.setIconResource(newIcon)
                }
                
                buttonShare.setOnClickListener {
                    // مشاركة الحديث
                }
                
                root.setOnClickListener {
                    onHadithClick(hadith)
                }
            }
        }
    }

    private class HadithDiffCallback : DiffUtil.ItemCallback<Hadith>() {
        override fun areItemsTheSame(oldItem: Hadith, newItem: Hadith): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Hadith, newItem: Hadith): Boolean {
            return oldItem == newItem
        }
    }
}
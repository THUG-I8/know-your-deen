package com.ammarapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ammarapp.databinding.ItemSurahBinding
import com.ammarapp.models.Surah

class SurahAdapter(
    private val onSurahClick: (Surah) -> Unit
) : ListAdapter<Surah, SurahAdapter.SurahViewHolder>(SurahDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurahViewHolder {
        val binding = ItemSurahBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SurahViewHolder(binding, onSurahClick)
    }

    override fun onBindViewHolder(holder: SurahViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SurahViewHolder(
        private val binding: ItemSurahBinding,
        private val onSurahClick: (Surah) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(surah: Surah) {
            binding.apply {
                textViewSurahNumber.text = surah.number.toString()
                textViewSurahName.text = surah.arabicName
                textViewSurahNameEnglish.text = surah.englishName
                textViewVersesCount.text = "${surah.versesCount} آية"
                textViewRevelationType.text = surah.revelationType
                
                // تحديد لون الخلفية حسب نوع السورة
                val backgroundColor = when (surah.revelationType) {
                    "مكية" -> root.context.getColor(com.ammarapp.R.color.primary_light)
                    "مدنية" -> root.context.getColor(com.ammarapp.R.color.secondary_light)
                    else -> root.context.getColor(com.ammarapp.R.color.background_card)
                }
                cardViewSurah.setCardBackgroundColor(backgroundColor)
                
                root.setOnClickListener {
                    onSurahClick(surah)
                }
            }
        }
    }

    private class SurahDiffCallback : DiffUtil.ItemCallback<Surah>() {
        override fun areItemsTheSame(oldItem: Surah, newItem: Surah): Boolean {
            return oldItem.number == newItem.number
        }

        override fun areContentsTheSame(oldItem: Surah, newItem: Surah): Boolean {
            return oldItem == newItem
        }
    }
}
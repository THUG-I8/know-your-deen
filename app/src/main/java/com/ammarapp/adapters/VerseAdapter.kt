package com.ammarapp.adapters

import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ammarapp.databinding.ItemVerseBinding
import com.ammarapp.models.ReadingSettings
import com.ammarapp.models.VerseExtended

class VerseAdapter(
    private var verses: List<VerseExtended>,
    private var settings: ReadingSettings,
    private val onVerseClick: (VerseExtended) -> Unit,
    private val onBookmarkClick: (VerseExtended) -> Unit
) : ListAdapter<VerseExtended, VerseAdapter.VerseViewHolder>(VerseDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerseViewHolder {
        val binding = ItemVerseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return VerseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VerseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun updateSettings(newSettings: ReadingSettings) {
        settings = newSettings
        notifyDataSetChanged()
    }

    fun updateVerses(newVerses: List<VerseExtended>) {
        verses = newVerses
        submitList(newVerses)
    }

    fun getVersePosition(verseId: Int): Int {
        return verses.indexOfFirst { it.id == verseId }
    }

    inner class VerseViewHolder(
        private val binding: ItemVerseBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onVerseClick(getItem(position))
                }
            }

            binding.btnBookmark.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onBookmarkClick(getItem(position))
                }
            }
        }

        fun bind(verse: VerseExtended) {
            binding.apply {
                // النص العربي
                tvArabicText.text = verse.arabicText
                tvVerseNumber.text = verse.verseNumber.toString()

                // تطبيق إعدادات الخط
                applyFontSettings(verse)

                // الترجمة (إذا كانت مفعلة)
                if (settings.showTranslation && verse.translation.isNotEmpty()) {
                    tvTranslation.text = verse.translation
                    tvTranslation.visibility = android.view.View.VISIBLE
                } else {
                    tvTranslation.visibility = android.view.View.GONE
                }

                // التفسير (إذا كان مفعلاً)
                if (settings.showTafseer && verse.tafseer.isNotEmpty()) {
                    tvTafseer.text = verse.tafseer
                    tvTafseer.visibility = android.view.View.VISIBLE
                } else {
                    tvTafseer.visibility = android.view.View.GONE
                }

                // تطبيق الألوان (وضع ليلي/نهاري)
                applyTheme()
            }
        }

        private fun applyFontSettings(verse: VerseExtended) {
            binding.apply {
                // حجم الخط
                tvArabicText.textSize = settings.fontSize
                tvTranslation.textSize = settings.fontSize - 2f
                tvTafseer.textSize = settings.fontSize - 3f

                // المسافة بين الأسطر
                tvArabicText.setLineSpacing(0f, settings.lineSpacing)
                tvTranslation.setLineSpacing(0f, settings.lineSpacing)

                // نوع الخط (يمكن تحسينه لاحقاً بتحميل خطوط مخصصة)
                when (settings.fontFamily) {
                    "Uthmanic" -> {
                        tvArabicText.typeface = Typeface.DEFAULT
                    }
                    "Noor" -> {
                        tvArabicText.typeface = Typeface.SERIF
                    }
                    "Amiri" -> {
                        tvArabicText.typeface = Typeface.SERIF
                    }
                    else -> {
                        tvArabicText.typeface = Typeface.DEFAULT
                    }
                }
            }
        }

        private fun applyTheme() {
            binding.apply {
                val backgroundColor = Color.parseColor(settings.backgroundColor)
                val textColor = Color.parseColor(settings.textColor)
                
                cardView.setCardBackgroundColor(backgroundColor)
                tvArabicText.setTextColor(textColor)
                tvTranslation.setTextColor(textColor)
                tvTafseer.setTextColor(textColor)
                tvVerseNumber.setTextColor(textColor)

                // تغيير لون العلامة المرجعية حسب الثيم
                if (settings.nightMode) {
                    btnBookmark.setColorFilter(Color.WHITE)
                } else {
                    btnBookmark.setColorFilter(Color.BLACK)
                }
            }
        }
    }

    private class VerseDiffCallback : DiffUtil.ItemCallback<VerseExtended>() {
        override fun areItemsTheSame(oldItem: VerseExtended, newItem: VerseExtended): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: VerseExtended, newItem: VerseExtended): Boolean {
            return oldItem == newItem
        }
    }
}
package com.ammarapp.adapters

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ammarapp.R
import com.ammarapp.models.*

class EnhancedVerseAdapter(
    private val context: Context,
    private var settings: QuranReadingSettings,
    private val onVerseClick: (VerseEnhanced) -> Unit,
    private val onBookmarkClick: (VerseEnhanced) -> Unit,
    private val onShareClick: (VerseEnhanced) -> Unit,
    private val onMoreClick: (VerseEnhanced) -> Unit
) : ListAdapter<VerseEnhanced, EnhancedVerseAdapter.EnhancedVerseViewHolder>(VerseDiffCallback()) {

    private var searchQuery: String = ""
    private var currentReadingVerse: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EnhancedVerseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_enhanced_verse, parent, false)
        return EnhancedVerseViewHolder(view)
    }

    override fun onBindViewHolder(holder: EnhancedVerseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun updateSettings(newSettings: QuranReadingSettings) {
        settings = newSettings
        notifyDataSetChanged()
    }

    fun updateSearchQuery(query: String) {
        searchQuery = query
        notifyDataSetChanged()
    }

    fun setCurrentReadingVerse(verseId: Int) {
        currentReadingVerse = verseId
        notifyDataSetChanged()
    }

    fun getVersePosition(verseId: Int): Int {
        val currentList = currentList
        return currentList.indexOfFirst { it.id == verseId }
    }

    inner class EnhancedVerseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cardView: CardView = itemView.findViewById(R.id.card_verse)
        private val tvVerseNumber: TextView = itemView.findViewById(R.id.tv_verse_number)
        private val tvArabicText: TextView = itemView.findViewById(R.id.tv_arabic_text)
        private val tvTranslation: TextView = itemView.findViewById(R.id.tv_translation)
        private val tvTafseer: TextView = itemView.findViewById(R.id.tv_tafseer)
        private val tvPageInfo: TextView = itemView.findViewById(R.id.tv_page_info)
        private val tvJuzInfo: TextView = itemView.findViewById(R.id.tv_juz_info)
        private val btnBookmark: ImageButton = itemView.findViewById(R.id.btn_bookmark)
        private val btnShare: ImageButton = itemView.findViewById(R.id.btn_share)
        private val btnMore: ImageButton = itemView.findViewById(R.id.btn_more)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onVerseClick(getItem(position))
                }
            }

            btnBookmark.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onBookmarkClick(getItem(position))
                }
            }

            btnShare.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onShareClick(getItem(position))
                }
            }

            btnMore.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onMoreClick(getItem(position))
                }
            }
        }

        fun bind(verse: VerseEnhanced) {
            // رقم الآية
            if (settings.showVerseNumbers) {
                tvVerseNumber.text = verse.verseNumber.toString()
                tvVerseNumber.visibility = View.VISIBLE
            } else {
                tvVerseNumber.visibility = View.GONE
            }

            // النص العربي مع التمييز للبحث
            val arabicText = if (searchQuery.isNotEmpty() && verse.arabicText.contains(searchQuery)) {
                highlightSearchText(verse.arabicText, searchQuery)
            } else {
                SpannableString(verse.arabicText)
            }
            tvArabicText.text = arabicText

            // الترجمة
            if (settings.showTranslation && verse.translation.isNotEmpty()) {
                tvTranslation.text = verse.translation
                tvTranslation.visibility = View.VISIBLE
            } else {
                tvTranslation.visibility = View.GONE
            }

            // التفسير
            if (settings.showTafseer && verse.tafseer.isNotEmpty()) {
                tvTafseer.text = verse.tafseer
                tvTafseer.visibility = View.VISIBLE
            } else {
                tvTafseer.visibility = View.GONE
            }

            // معلومات الصفحة والجزء
            if (settings.showPageNumbers) {
                tvPageInfo.text = "ص ${verse.pageNumber}"
                tvJuzInfo.text = "ج ${verse.juzNumber}"
                tvPageInfo.visibility = View.VISIBLE
                tvJuzInfo.visibility = View.VISIBLE
            } else {
                tvPageInfo.visibility = View.GONE
                tvJuzInfo.visibility = View.GONE
            }

            // العلامة المرجعية
            if (verse.isBookmarked) {
                btnBookmark.setImageResource(R.drawable.ic_bookmark_filled)
                btnBookmark.setColorFilter(ContextCompat.getColor(context, R.color.accent_color))
            } else {
                btnBookmark.setImageResource(R.drawable.ic_bookmark_border)
                btnBookmark.setColorFilter(ContextCompat.getColor(context, R.color.text_secondary))
            }

            // تطبيق إعدادات الخط والمظهر
            applyFontSettings()
            applyThemeSettings()

            // تمييز الآية الحالية في القراءة
            if (verse.id == currentReadingVerse) {
                cardView.strokeColor = ContextCompat.getColor(context, R.color.accent_color)
                cardView.strokeWidth = 4
            } else {
                cardView.strokeWidth = 0
            }
        }

        private fun highlightSearchText(text: String, query: String): SpannableString {
            val spannableString = SpannableString(text)
            val startIndex = text.indexOf(query, ignoreCase = true)
            if (startIndex >= 0) {
                val endIndex = startIndex + query.length
                val highlightColor = Color.parseColor(settings.highlightColor)
                spannableString.setSpan(
                    BackgroundColorSpan(highlightColor),
                    startIndex,
                    endIndex,
                    SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
            return spannableString
        }

        private fun applyFontSettings() {
            // حجم الخط
            tvArabicText.textSize = settings.fontSize
            tvTranslation.textSize = settings.fontSize - 2f
            tvTafseer.textSize = settings.fontSize - 3f
            tvVerseNumber.textSize = settings.fontSize - 4f

            // المسافة بين الأسطر
            tvArabicText.setLineSpacing(0f, settings.lineSpacing)
            tvTranslation.setLineSpacing(0f, settings.lineSpacing * 0.9f)

            // نوع الخط
            val typeface = when (settings.fontFamily) {
                FontFamily.UTHMANIC -> Typeface.DEFAULT
                FontFamily.NOOR -> Typeface.SERIF
                FontFamily.AMIRI -> Typeface.SERIF
                FontFamily.CAIRO -> Typeface.DEFAULT_BOLD
                FontFamily.SIMPLE -> Typeface.SANS_SERIF
                FontFamily.TRADITIONAL -> Typeface.SERIF
            }
            tvArabicText.typeface = typeface
        }

        private fun applyThemeSettings() {
            // ألوان الخلفية والنص
            val backgroundColor = Color.parseColor(settings.backgroundColor)
            val textColor = Color.parseColor(settings.textColor)
            val secondaryTextColor = if (settings.nightMode) {
                Color.parseColor("#CCCCCC")
            } else {
                Color.parseColor("#666666")
            }

            cardView.setCardBackgroundColor(backgroundColor)
            tvArabicText.setTextColor(textColor)
            tvTranslation.setTextColor(secondaryTextColor)
            tvTafseer.setTextColor(secondaryTextColor)
            tvVerseNumber.setTextColor(Color.WHITE)
            tvPageInfo.setTextColor(secondaryTextColor)
            tvJuzInfo.setTextColor(secondaryTextColor)

            // ألوان الأزرار
            val buttonTint = if (settings.nightMode) Color.WHITE else Color.BLACK
            btnShare.setColorFilter(buttonTint)
            btnMore.setColorFilter(buttonTint)

            // لون رقم الآية
            val verseNumberBackground = if (settings.nightMode) {
                ContextCompat.getColor(context, R.color.primary_dark)
            } else {
                ContextCompat.getColor(context, R.color.primary_color)
            }
            tvVerseNumber.background.setTint(verseNumberBackground)
        }
    }

    private class VerseDiffCallback : DiffUtil.ItemCallback<VerseEnhanced>() {
        override fun areItemsTheSame(oldItem: VerseEnhanced, newItem: VerseEnhanced): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: VerseEnhanced, newItem: VerseEnhanced): Boolean {
            return oldItem == newItem
        }
    }
}
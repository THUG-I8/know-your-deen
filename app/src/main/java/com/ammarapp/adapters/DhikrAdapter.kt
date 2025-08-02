package com.ammarapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ammarapp.databinding.ItemDhikrBinding
import com.ammarapp.models.Dhikr

class DhikrAdapter(
    private val onDhikrClick: (Dhikr) -> Unit
) : ListAdapter<Dhikr, DhikrAdapter.DhikrViewHolder>(DhikrDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DhikrViewHolder {
        val binding = ItemDhikrBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DhikrViewHolder(binding, onDhikrClick)
    }

    override fun onBindViewHolder(holder: DhikrViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun resetAllCounters() {
        val currentList = currentList.toMutableList()
        currentList.forEach { it.currentCount = 0 }
        submitList(currentList)
    }

    class DhikrViewHolder(
        private val binding: ItemDhikrBinding,
        private val onDhikrClick: (Dhikr) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(dhikr: Dhikr) {
            binding.apply {
                textViewTitle.text = dhikr.title
                textViewArabicText.text = dhikr.arabicText
                textViewTranslation.text = dhikr.translation
                textViewDescription.text = dhikr.description
                textViewCount.text = "${dhikr.currentCount}/${dhikr.count}"
                
                // تحديث شريط التقدم
                progressBar.progress = if (dhikr.count > 0) {
                    (dhikr.currentCount * 100 / dhikr.count)
                } else {
                    0
                }
                
                // تحديد لون شريط التقدم
                val progressColor = when {
                    dhikr.currentCount >= dhikr.count -> root.context.getColor(com.ammarapp.R.color.success)
                    dhikr.currentCount > 0 -> root.context.getColor(com.ammarapp.R.color.primary)
                    else -> root.context.getColor(com.ammarapp.R.color.border_light)
                }
                progressBar.progressTintList = android.content.res.ColorStateList.valueOf(progressColor)
                
                // إعداد أزرار العداد
                buttonMinus.setOnClickListener {
                    if (dhikr.currentCount > 0) {
                        dhikr.currentCount--
                        textViewCount.text = "${dhikr.currentCount}/${dhikr.count}"
                        progressBar.progress = if (dhikr.count > 0) {
                            (dhikr.currentCount * 100 / dhikr.count)
                        } else {
                            0
                        }
                    }
                }
                
                buttonPlus.setOnClickListener {
                    if (dhikr.currentCount < dhikr.count) {
                        dhikr.currentCount++
                        textViewCount.text = "${dhikr.currentCount}/${dhikr.count}"
                        progressBar.progress = if (dhikr.count > 0) {
                            (dhikr.currentCount * 100 / dhikr.count)
                        } else {
                            0
                        }
                    }
                }
                
                buttonReset.setOnClickListener {
                    dhikr.currentCount = 0
                    textViewCount.text = "${dhikr.currentCount}/${dhikr.count}"
                    progressBar.progress = 0
                }
                
                root.setOnClickListener {
                    onDhikrClick(dhikr)
                }
            }
        }
    }

    private class DhikrDiffCallback : DiffUtil.ItemCallback<Dhikr>() {
        override fun areItemsTheSame(oldItem: Dhikr, newItem: Dhikr): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Dhikr, newItem: Dhikr): Boolean {
            return oldItem == newItem
        }
    }
}
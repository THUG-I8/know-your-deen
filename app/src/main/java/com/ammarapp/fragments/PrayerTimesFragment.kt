package com.ammarapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ammarapp.databinding.FragmentPrayerTimesBinding
import java.text.SimpleDateFormat
import java.util.*

class PrayerTimesFragment : Fragment() {

    private var _binding: FragmentPrayerTimesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPrayerTimesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPrayerTimes()
    }

    private fun setupPrayerTimes() {
        // Get current date
        val currentDate = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale("ar")).format(Date())
        binding.dateText.text = currentDate

        // Set prayer times (these would normally come from an API)
        binding.fajrTime.text = "04:30"
        binding.sunriseTime.text = "06:15"
        binding.dhuhrTime.text = "12:30"
        binding.asrTime.text = "15:45"
        binding.maghribTime.text = "18:20"
        binding.ishaTime.text = "19:45"

        // Highlight current prayer time
        highlightCurrentPrayer()
    }

    private fun highlightCurrentPrayer() {
        val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val currentMinute = Calendar.getInstance().get(Calendar.MINUTE)
        val currentTime = currentHour * 60 + currentMinute

        // Reset all backgrounds
        binding.fajrCard.setBackgroundResource(0)
        binding.sunriseCard.setBackgroundResource(0)
        binding.dhuhrCard.setBackgroundResource(0)
        binding.asrCard.setBackgroundResource(0)
        binding.maghribCard.setBackgroundResource(0)
        binding.ishaCard.setBackgroundResource(0)

        // Highlight current prayer based on time
        when {
            currentTime < 6 * 60 + 15 -> { // Before sunrise
                binding.fajrCard.setBackgroundResource(R.drawable.current_prayer_background)
            }
            currentTime < 12 * 60 + 30 -> { // Before Dhuhr
                binding.sunriseCard.setBackgroundResource(R.drawable.current_prayer_background)
            }
            currentTime < 15 * 60 + 45 -> { // Before Asr
                binding.dhuhrCard.setBackgroundResource(R.drawable.current_prayer_background)
            }
            currentTime < 18 * 60 + 20 -> { // Before Maghrib
                binding.asrCard.setBackgroundResource(R.drawable.current_prayer_background)
            }
            currentTime < 19 * 60 + 45 -> { // Before Isha
                binding.maghribCard.setBackgroundResource(R.drawable.current_prayer_background)
            }
            else -> { // After Isha
                binding.ishaCard.setBackgroundResource(R.drawable.current_prayer_background)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
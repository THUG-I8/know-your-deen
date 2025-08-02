package com.ammarapp.fragments

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ammarapp.databinding.FragmentTasbihBinding

class TasbihFragment : Fragment() {
    
    private var _binding: FragmentTasbihBinding? = null
    private val binding get() = _binding!!
    private var count = 0
    private var targetCount = 33
    private var isVibrationEnabled = true
    private var isSoundEnabled = true
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTasbihBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupClickListeners()
    }
    
    private fun setupUI() {
        updateCountDisplay()
        updateTargetCountDisplay()
    }
    
    private fun setupClickListeners() {
        // زر العداد الرئيسي
        binding.buttonCount.setOnClickListener {
            incrementCount()
        }
        
        // زر إعادة تعيين
        binding.buttonReset.setOnClickListener {
            resetCount()
        }
        
        // أزرار تغيير العدد المستهدف
        binding.button33.setOnClickListener {
            setTargetCount(33)
        }
        
        binding.button99.setOnClickListener {
            setTargetCount(99)
        }
        
        binding.button100.setOnClickListener {
            setTargetCount(100)
        }
        
        binding.button313.setOnClickListener {
            setTargetCount(313)
        }
        
        // زر الاهتزاز
        binding.buttonVibrate.setOnClickListener {
            toggleVibration()
        }
        
        // زر الصوت
        binding.buttonSound.setOnClickListener {
            toggleSound()
        }
        
        // زر الإعدادات
        binding.buttonSettings.setOnClickListener {
            showSettings()
        }
    }
    
    private fun incrementCount() {
        count++
        updateCountDisplay()
        
        // اهتزاز
        if (isVibrationEnabled) {
            vibrate()
        }
        
        // صوت
        if (isSoundEnabled) {
            playSound()
        }
        
        // التحقق من اكتمال العدد المستهدف
        if (count >= targetCount) {
            showCompletionMessage()
        }
    }
    
    private fun resetCount() {
        count = 0
        updateCountDisplay()
    }
    
    private fun setTargetCount(newTarget: Int) {
        targetCount = newTarget
        updateTargetCountDisplay()
        resetCount()
    }
    
    private fun updateCountDisplay() {
        binding.textViewCount.text = count.toString()
        
        // تحديث شريط التقدم
        val progress = if (targetCount > 0) {
            (count * 100 / targetCount)
        } else {
            0
        }
        binding.progressBar.progress = progress
        
        // تغيير لون العداد عند اكتمال العدد المستهدف
        if (count >= targetCount) {
            binding.textViewCount.setTextColor(resources.getColor(com.ammarapp.R.color.success, null))
        } else {
            binding.textViewCount.setTextColor(resources.getColor(com.ammarapp.R.color.text_primary, null))
        }
    }
    
    private fun updateTargetCountDisplay() {
        binding.textViewTargetCount.text = "/ $targetCount"
    }
    
    private fun vibrate() {
        val vibrator = requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            @Suppress("DEPRECATION")
            vibrator.vibrate(50)
        }
    }
    
    private fun playSound() {
        // يمكن إضافة صوت هنا
    }
    
    private fun toggleVibration() {
        isVibrationEnabled = !isVibrationEnabled
        updateVibrationButton()
    }
    
    private fun toggleSound() {
        isSoundEnabled = !isSoundEnabled
        updateSoundButton()
    }
    
    private fun updateVibrationButton() {
        val icon = if (isVibrationEnabled) {
            com.ammarapp.R.drawable.ic_vibrate_on
        } else {
            com.ammarapp.R.drawable.ic_vibrate_off
        }
        binding.buttonVibrate.setIconResource(icon)
    }
    
    private fun updateSoundButton() {
        val icon = if (isSoundEnabled) {
            com.ammarapp.R.drawable.ic_volume_on
        } else {
            com.ammarapp.R.drawable.ic_volume_off
        }
        binding.buttonSound.setIconResource(icon)
    }
    
    private fun showCompletionMessage() {
        // إظهار رسالة اكتمال العداد
        binding.textViewCompletionMessage.visibility = View.VISIBLE
        binding.textViewCompletionMessage.text = "تم الانتهاء من العداد! 🎉"
    }
    
    private fun showSettings() {
        // إظهار إعدادات المسبحة
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
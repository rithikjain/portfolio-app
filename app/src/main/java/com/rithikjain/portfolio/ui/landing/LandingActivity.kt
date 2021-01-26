package com.rithikjain.portfolio.ui.landing

import `in`.codeshuffle.typewriterview.TypeWriterListener
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.github.florent37.viewanimator.ViewAnimator
import com.rithikjain.portfolio.databinding.ActivityLandingBinding
import kotlin.math.atan2

open class LandingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLandingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.introTextWriter.apply {
            setWithMusic(false)
            animateText("Hey! I'm Rithik Jain.")
            setTypeWriterListener(IntroTextListener(binding))
        }
    }

    class IntroTextListener(private val binding: ActivityLandingBinding) : TypeWriterListener {
        override fun onTypingStart(text: String?) {}
        override fun onCharacterTyped(text: String?, position: Int) {}
        override fun onTypingRemoved(text: String?) {}

        override fun onTypingEnd(text: String?) {
            binding.descriptionTextWriter.visibility = View.VISIBLE
            binding.descriptionTextWriter.apply {
                setWithMusic(false)
                animateText("A Mobile Fullstack Dev :)")
                setTypeWriterListener(DescriptionTextListener(binding))
            }
        }
    }

    class DescriptionTextListener(private val binding: ActivityLandingBinding) : TypeWriterListener {
        override fun onTypingStart(text: String?) {}
        override fun onCharacterTyped(text: String?, position: Int) {}
        override fun onTypingRemoved(text: String?) {}

        override fun onTypingEnd(text: String?) {
            binding.findMoreAbout.visibility = View.VISIBLE
            ViewAnimator.animate(binding.findMoreAbout).tada().start()
        }
    }
}
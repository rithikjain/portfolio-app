package com.rithikjain.portfolio.ui.landing

import `in`.codeshuffle.typewriterview.TypeWriterListener
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.rithikjain.portfolio.databinding.ActivityLandingBinding

class LandingActivity : AppCompatActivity() {
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
            }
        }
    }
}
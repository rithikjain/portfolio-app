package com.rithikjain.portfolio.ui.landing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rithikjain.portfolio.R
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
        }

        binding.descriptionTextWriter.apply {
            setWithMusic(false)
            animateText("A Mobile Fullstack Dev :)")
        }
    }
}
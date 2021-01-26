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
import com.rithikjain.portfolio.databinding.ActivityLandingBinding
import kotlin.math.atan2

open class LandingActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var binding: ActivityLandingBinding
    private lateinit var sensorManager: SensorManager
    private var gravSensorVals: FloatArray = FloatArray(10)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        binding.introTextWriter.apply {
            setWithMusic(false)
            animateText("Hey! I'm Rithik Jain.")
            setTypeWriterListener(IntroTextListener(binding))
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onResume() {
        super.onResume()

        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)?.also { accelerometer ->
            sensorManager.registerListener(
                this,
                accelerometer,
                SensorManager.SENSOR_DELAY_GAME,
                SensorManager.SENSOR_DELAY_GAME
            )
        }
    }

    private val alpha = 0.1f
    private fun lowPass(input: FloatArray, output: FloatArray?): FloatArray {
        if (output == null) return input

        for (i in input.indices) {
            output[i] = output[i] + alpha * (input[i] - output[i])
        }
        return output
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            gravSensorVals = lowPass(event.values!!.clone(), gravSensorVals)
        }

        val x = gravSensorVals[0]
        val y = gravSensorVals[1]

        val degreeRotation = atan2(x,y)

        val rotation = Math.toDegrees(degreeRotation.toDouble())

        if (rotation > -15 && rotation < 15) {
            binding.landingLayout.rotation = rotation.toFloat()
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
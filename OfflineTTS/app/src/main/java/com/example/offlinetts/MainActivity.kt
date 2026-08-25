package com.example.offlinetts

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var tts: TextToSpeech
    private lateinit var editText: EditText
    private lateinit var spinnerLanguage: Spinner
    private lateinit var buttonSpeak: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)
        spinnerLanguage = findViewById(R.id.spinnerLanguage)
        buttonSpeak = findViewById(R.id.buttonSpeak)

        // Initialize TTS
        tts = TextToSpeech(this, this)

        buttonSpeak.setOnClickListener {
            val text = editText.text.toString()
            if (text.isNotEmpty()) {
                speak(text)
            } else {
                Toast.makeText(this, "Please enter text", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            // Set default language to English
            val result = tts.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(this, "Language not supported", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "TTS Initialization failed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun speak(text: String) {
        val selectedLanguage = spinnerLanguage.selectedItem.toString()
        val locale = when (selectedLanguage) {
            "English" -> Locale.US
            "Arabic" -> Locale("ar")
            "French" -> Locale.FRANCE
            else -> Locale.US
        }

        tts.setLanguage(locale)
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    override fun onDestroy() {
        if (::tts.isInitialized) {
            tts.stop()
            tts.shutdown()
        }
        super.onDestroy()
    }
}

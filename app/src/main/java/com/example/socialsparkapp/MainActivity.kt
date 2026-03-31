package com.example.socialsparkapp

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat.setOnApplyWindowInsetsListener
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)

            WindowInsetsCompat.CONSUMED
            // --- Assuming this code is within onCreate ---
            // [1] Using standard Android findViewById to reference UI elements from XML
            val etInput = findViewById<EditText>(R.id.etTimeInput)
            val btnSpark = findViewById<Button>(R.id.btnSpark)
            val btnReset = findViewById<Button>(R.id.btnReset)
            val tvSuggestion = findViewById<TextView>(R.id.tvResult)

            Log.d("SparkApp", "UI Initialized")
            /**
             * Adapted from (Google.Gemini)
             * Prompt: "Generate a Kotlin if-else statement for an Android app. Based on a val input string, set a val suggestion for a social action"
             * Modified by: (Dhanush)
             * Description: Added error handling and renamed variables and strings for if statements instead of if else.
             */
            btnSpark.setOnClickListener {
                // Add Reset background tint So the visibility of view state was clear
                tvSuggestion.backgroundTintList = null
                tvSuggestion .setBackgroundColor(Color.BLACK)
                tvSuggestion.visibility = View.VISIBLE

                val input = etInput.text.toString().trim().lowercase()

                // Using if chains to match the specific "spark" times
                var suggestion = "No suggestion for this time."
                if (input == "morning") {
                        suggestion = "Send a Good morning text to a family member."
                }
                if (input == "mid-morning") {
                        suggestion = "Reach out to a colleague with a quick Thank you."
                }
                if (input == "afternoon") {
                        suggestion = "Share a funny meme or interesting link with a friend."
                }
                if (input == "afternoon snack time") {
                        suggestion = "Send a quick thinking of you message."
                }
                if (input == "dinner") {
                        suggestion = "Call a friend or relative for a 5-minute catch-up."
                }
                if (input == "after dinner" || input == "night") {
                        suggestion = "Leave a thoughtful comment on a friend's post."
                }
                val sparkTimes = listOf("Morning", "Afternoon", "Night")
                // Specific feedback for empty input

                if (input.isEmpty()) {
                        suggestion = "Input is empty, please provide a time."
                }
                // Constructive error message for invalid text
                if (input !in sparkTimes) {
                    println("Hmm, '$input' doesn't seem to match a spark time. Try typing 'Morning', 'Afternoon', or 'Night' to find your next connection!")
            }

        tvSuggestion.text = suggestion
                Log.d("SparkApp", "Suggestion shown: $suggestion")

            }
            btnReset.setOnClickListener {
                // Reset view state
                tvSuggestion.backgroundTintList = null
                tvSuggestion.setBackgroundColor(Color.TRANSPARENT)
                etInput.text.clear() // Clear text
                tvSuggestion.text = ""// Clear input field
                Log.d("SparkApp", "UI Reset")
            }
            insets
        }
    }
}

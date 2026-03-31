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

            btnSpark.setOnClickListener {
                // Add Reset background tint So the visibility of view state was clear
                tvSuggestion.backgroundTintList = null
                tvSuggestion .setBackgroundColor(Color.BLACK)
                tvSuggestion.visibility = View.VISIBLE

                val input = etInput.text.toString().trim().lowercase()

                // Using if-else chains to match the specific "spark" times
                val suggestion = if (input == "morning") {
                    "Send a 'Good morning' text to a family member."
                } else if (input == "mid-morning") {
                    "Reach out to a colleague with a quick 'Thank you'."
                } else if (input == "afternoon") {
                    "Share a funny meme or interesting link with a friend."
                } else if (input == "afternoon snack time") {
                    "Send a quick 'thinking of you' message."
                } else if (input == "dinner") {
                    "Call a friend or relative for a 5-minute catch-up."
                } else if (input == "after dinner" || input == "night") {
                    "Leave a thoughtful comment on a friend's post."
                } else if (input.isEmpty()) {
                    // Specific feedback for empty input
                    "It looks like you haven't typed anything yet! Try entering a time like 'Morning' or 'Dinner' to get started."
                } else {
                    // Constructive error message for invalid text
                    "Hmm, '$input' doesn't seem to match a spark time. Try typing 'Morning', 'Afternoon', or 'Night' to find your next connection!"
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

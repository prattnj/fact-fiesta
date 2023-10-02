package com.noahpratt.factfiesta

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        updateFact()

        val refreshButton = findViewById<Button>(R.id.refreshButton)
        refreshButton.setOnClickListener {
            updateFact()
        }
    }

    private fun updateFact() {
        Thread {
            val fact = ServerProxy().getFact()
            runOnUiThread {
                val factText = findViewById<TextView>(R.id.factText)
                factText.text = removeQuotes(fact)
            }
        }.start()
    }

    private fun removeQuotes(str: String): String {
        return str.substring(1, str.length - 2)
    }
}




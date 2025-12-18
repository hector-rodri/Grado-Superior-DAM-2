package com.example.plandeentrenamiento

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        val txtResum = findViewById<TextView>(R.id.textViewSummary)
        val btnConfirm = findViewById<Button>(R.id.buttonConfirm)
        val btnCancell = findViewById<Button>(R.id.buttonCancel)
        val days = intent.getIntExtra("value1", 0)
        val weeks = intent.getIntExtra("value2", 0)
        val sessions = days * weeks
        val resum = "Has seleccionat un entrenament de $days dies per setmana, " + "durant $weeks setmanes ($sessions sessions)"

        txtResum.text = resum

        btnConfirm.setOnClickListener {
            val data = Intent()
            data.putExtra("resum", resum)
            setResult(RESULT_OK, data)
            finish()
        }

        btnCancell.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }
}

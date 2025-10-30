package com.example.plandeentrenamiento

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val editTxt = findViewById<EditText>(R.id.editTextText)
        val editTxtValue = editTxt.text.toString()
        val editTxt2 = findViewById<EditText>(R.id.editTextText2)
        val editTxtValue2 = editTxt2.text.toString()

        val btnCreate = findViewById<Button>(R.id.button)
        btnCreate.setOnClickListener{
            if (editTxtValue.isEmpty() or editTxtValue2.isEmpty()){

            }
        }
    }
}
package com.example.plandeentrenamiento

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.EditText
import android.content.Intent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val editTxt = findViewById<EditText>(R.id.editTextText)
        val editTxt2 = findViewById<EditText>(R.id.editTextText2)
        val btnCreate = findViewById<Button>(R.id.button)

        btnCreate.setOnClickListener{
            val editTxtValue = editTxt.text.toString()
            val editTxtValue2 = editTxt2.text.toString()
            if (editTxtValue.isEmpty() or editTxtValue2.isEmpty()){
                Error("The fields mustn't be empty")
            }else{
                val intent =  Intent(this, MainActivity2::class.java)
                intent.putExtra("value1",editTxtValue)
                intent.putExtra("value2",editTxtValue2)
                startActivity(intent)
            }
        }
    }
}
package com.example.plandeentrenamiento

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val editTxt = findViewById<EditText>(R.id.editTextText)
        val editTxt2 = findViewById<EditText>(R.id.editTextText2)
        val btnCreate = findViewById<Button>(R.id.button)
        val textResult = findViewById<TextView>(R.id.textView)

        val launcher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val resum = result.data?.getStringExtra("resum")
                textResult.text = resum
            } else {
                textResult.text = "Operació cancel·lada"
            }
        }

        btnCreate.setOnClickListener {
            val txt1 = editTxt.text.toString()
            val txt2 = editTxt2.text.toString()
            val intent = Intent(this, MainActivity2::class.java)

            if (txt1.isEmpty() || txt2.isEmpty()) {
                Error( "Els camps no poden estar buits")
            }

            intent.putExtra("value1", txt1.toInt())
            intent.putExtra("value2", txt2.toInt())
            launcher.launch(intent)
        }
    }
}

package com.example.plandeentrenamiento

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class RegisterExerciseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register_exercise)

        val planId = intent.getIntExtra("plan_id", -1).toLong()
        val btnSave = findViewById<Button>(R.id.btn1)

        btnSave.setOnClickListener {
            val nombre = findViewById<EditText>(R.id.et1).text.toString()
            val fecha = findViewById<EditText>(R.id.et2).text.toString()
            val peso = findViewById<EditText>(R.id.et3).text.toString().trim().toDouble()
            val repes = findViewById<EditText>(R.id.et4).text.toString().trim().toInt()
            val tipo = findViewById<EditText>(R.id.et5).text.toString()

            val ejercicio = Ejercicio(
                nombre = nombre,
                fecha = fecha,
                peso = peso,
                repes = repes,
                tipo = tipo,
                planId = planId
            )

            Toast.makeText(this, "Ejercicio guardado", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}

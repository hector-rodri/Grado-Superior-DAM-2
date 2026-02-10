package com.example.plandeentrenamiento

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class RegisterExerciseActivity : AppCompatActivity() {

    private lateinit var dbHelper: SQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register_exercise)

        dbHelper = SQLiteHelper(this)

        val planId = intent.getLongExtra("plan_id", -1)
        val btnSave = findViewById<Button>(R.id.btn1)

        if (planId == -1L) {
            Toast.makeText(this, "Error: Plan ID not found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        btnSave.setOnClickListener {
            val nombre = findViewById<EditText>(R.id.et1).text.toString().trim()
            val fecha = findViewById<EditText>(R.id.et2).text.toString().trim()
            val pesoStr = findViewById<EditText>(R.id.et3).text.toString().trim()
            val repesStr = findViewById<EditText>(R.id.et4).text.toString().trim()
            val tipo = findViewById<EditText>(R.id.et5).text.toString().trim()

            // NUEVO: Necesitas añadir un EditText o Spinner para el día
            // Por ahora, asumo que tienes un EditText con id "et6" para el día
            // Si no lo tienes, añádelo al layout o usa un Spinner
            val diaStr = findViewById<EditText>(R.id.et6).text.toString().trim()

            // Validaciones
            if (nombre.isEmpty() || fecha.isEmpty() || pesoStr.isEmpty() || repesStr.isEmpty() || tipo.isEmpty() || diaStr.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val peso = pesoStr.toDoubleOrNull()
            val repes = repesStr.toIntOrNull()
            val dia = diaStr.toIntOrNull()

            if (peso == null || repes == null || dia == null) {
                Toast.makeText(this, "Invalid weight, reps or day", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (dia <= 0) {
                Toast.makeText(this, "Day must be greater than 0", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val ejercicio = EjercicioRegistrado(
                planId = planId,
                dia = dia,
                nombre = nombre,
                fecha = fecha,
                peso = peso,
                repes = repes,
                tipo = tipo
            )

            val id = dbHelper.insertEjercicioRegistrado(ejercicio)

            if (id != -1L) {
                Toast.makeText(this, "Exercise saved successfully for Day $dia", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Error saving exercise", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

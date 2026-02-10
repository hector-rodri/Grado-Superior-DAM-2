package com.example.plandeentrenamiento

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity4 : AppCompatActivity() {

    private lateinit var dbHelper: SQLiteHelper
    private lateinit var adapter: EjercicioAdapter
    private lateinit var spinnerDays: Spinner
    private lateinit var editExerciseName: EditText
    private lateinit var editWeight: EditText
    private lateinit var editReps: EditText
    private lateinit var btnAddExercise: Button
    private lateinit var recyclerExercises: RecyclerView

    private var planId: Long = -1
    private var planDays: Int = 0
    private var planWeeks: Int = 0
    private var currentSelectedDay: Int = 1
    private val ejerciciosTemporales = mutableListOf<EjercicioRegistrado>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        // Inicializar BD
        dbHelper = SQLiteHelper(this)

        // Obtener datos del plan desde el Intent
        planId = intent.getLongExtra("PLAN_ID", -1)
        val planName = intent.getStringExtra("PLAN_NAME") ?: "Unknown Plan"
        planDays = intent.getIntExtra("PLAN_DAYS", 0)
        planWeeks = intent.getIntExtra("PLAN_WEEKS", 0)  // número de semanas

        if (planId == -1L || planDays == 0 || planWeeks == 0) {
            Toast.makeText(this, "Error: Plan data not received", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Configurar título
        title = "Add exercises to: $planName"

        // Inicializar vistas
        spinnerDays = findViewById(R.id.spinnerDays)
        editExerciseName = findViewById(R.id.editExerciseName)
        editWeight = findViewById(R.id.editWeight)
        editReps = findViewById(R.id.editReps)
        btnAddExercise = findViewById(R.id.btnAddExercise)
        recyclerExercises = findViewById(R.id.recyclerExercises)

        // Configurar Spinner con los días del plan
        setupDaysSpinner()

        // Configurar RecyclerView
        setupRecyclerView()

        // Configurar botón de añadir ejercicio
        btnAddExercise.setOnClickListener {
            addExercise()
        }
    }

    private fun setupDaysSpinner() {
        // Número total de días = días por semana × semanas
        val totalDias = planDays * planWeeks

        // Crear lista de números del 1 hasta totalDias
        val daysList = (1..totalDias).toList()

        val spinnerAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            daysList
        )
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerDays.adapter = spinnerAdapter

        // Listener para actualizar currentSelectedDay
        spinnerDays.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: android.view.View?,
                position: Int,
                id: Long
            ) {
                // currentSelectedDay es igual al número seleccionado
                currentSelectedDay = daysList[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                currentSelectedDay = 1
            }
        }
    }


    private fun setupRecyclerView() {
        adapter = EjercicioAdapter(ejerciciosTemporales)
        recyclerExercises.layoutManager = LinearLayoutManager(this)
        recyclerExercises.adapter = adapter
    }

    private fun addExercise() {
        val exerciseName = editExerciseName.text.toString().trim()
        val weightStr = editWeight.text.toString().trim()
        val repsStr = editReps.text.toString().trim()

        // Validaciones
        if (exerciseName.isEmpty()) {
            Toast.makeText(this, "Please enter exercise name", Toast.LENGTH_SHORT).show()
            return
        }

        val weight = weightStr.toDoubleOrNull()
        if (weight == null) {
            Toast.makeText(this, "Please enter valid weight", Toast.LENGTH_SHORT).show()
            return
        }

        val reps = repsStr.toIntOrNull()
        if (reps == null || reps <= 0) {
            Toast.makeText(this, "Please enter valid repetitions", Toast.LENGTH_SHORT).show()
            return
        }

        // Crear ejercicio temporal (sin ID aún porque no está en BD)
        val currentDate = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).format(java.util.Date())
        val ejercicio = EjercicioRegistrado(
            id = 0,
            planId = planId,
            dia = currentSelectedDay,
            nombre = exerciseName,
            fecha = currentDate,
            peso = weight,
            repes = reps,
            tipo = "Fuerza"
        )


        // Guardar directamente en la base de datos
        val ejercicioId = dbHelper.insertEjercicioBase(ejercicio)

        if (ejercicioId != -1L) {
            // Añadir al RecyclerView con el ID real
            val ejercicioConId = ejercicio.copy(id = ejercicioId)
            adapter.addEjercicio(ejercicioConId)

            Toast.makeText(this, "Exercise added to Day $currentSelectedDay", Toast.LENGTH_SHORT).show()

            // Limpiar campos
            clearInputFields()
        } else {
            Toast.makeText(this, "Error adding exercise", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clearInputFields() {
        editExerciseName.text.clear()
        editWeight.text.clear()
        editReps.text.clear()
        editExerciseName.requestFocus()
    }
}

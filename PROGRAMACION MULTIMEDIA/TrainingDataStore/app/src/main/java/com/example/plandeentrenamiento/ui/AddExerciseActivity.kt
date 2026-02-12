package com.example.plandeentrenamiento.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plandeentrenamiento.R
import com.example.plandeentrenamiento.SQLiteHelper
import com.example.plandeentrenamiento.data.EjercicioRegistrado
import com.example.plandeentrenamiento.ui.resources.EjercicioAdapter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AddExerciseActivity : AppCompatActivity() {

    private lateinit var dbHelper: SQLiteHelper
    private lateinit var adapter: EjercicioAdapter
    private lateinit var spinnerDays: Spinner
    private lateinit var editExerciseName: EditText
    private lateinit var editWeight: EditText
    private lateinit var editReps: EditText
    private lateinit var btnAddExercise: Button
    private lateinit var btnClosePlan: Button
    private lateinit var recyclerExercises: RecyclerView
    private var planId: Long = -1
    private var planDays: Int = 0
    private var planWeeks: Int = 0
    private var currentSelectedDay: Int = 1
    private val ejerciciosTemporales = mutableListOf<EjercicioRegistrado>()
    private var isPlanActive: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_exercise)

        dbHelper = SQLiteHelper(this)

        // Obtener datos del plan desde el Intent
        planId = intent.getLongExtra("PLAN_ID", -1)
        val planName = intent.getStringExtra("PLAN_NAME") ?: "Unknown Plan"
        planDays = intent.getIntExtra("PLAN_DAYS", 0)
        planWeeks = intent.getIntExtra("PLAN_WEEKS", 0)  // número de semanas
        isPlanActive = intent.getBooleanExtra("PLAN_STATUS",false)

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
        btnClosePlan = findViewById(R.id.btnClosePlan)

        setupDaysSpinner()
        setupRecyclerView()

        btnClosePlan.setOnClickListener {
            showActivatePlanDialog {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        }

        btnAddExercise.setOnClickListener {
            addExercise()
        }
    }

    fun showActivatePlanDialog(onResult: () -> Unit) {
        AlertDialog.Builder(this)
            .setTitle("Activate training plan")
            .setMessage("Do you want to activate this plan?")
            .setPositiveButton("Yes") { _, _ ->
                isPlanActive = true
                onResult()
            }
            .setNegativeButton("No") { _, _ ->
                isPlanActive = false
                onResult()
            }
            .show()
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
                view: View?,
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
        val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        val ejercicio = EjercicioRegistrado(
            id = 0,
            planId = planId,
            dia = currentSelectedDay,
            nombre = exerciseName,
            peso = weight,
            repes = reps
        )


        // Guardar directamente en la base de datos
        val ejercicioId = dbHelper.insertExercise(ejercicio)

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

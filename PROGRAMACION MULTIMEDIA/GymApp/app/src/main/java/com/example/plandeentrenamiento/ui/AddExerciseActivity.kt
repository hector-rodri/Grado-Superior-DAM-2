package com.example.plandeentrenamiento.ui

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plandeentrenamiento.R
import com.example.plandeentrenamiento.SQLiteHelper
import com.example.plandeentrenamiento.data.EjercicioRegistrado
import com.example.plandeentrenamiento.ui.resources.EjercicioAdapter
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.NotificationCompat

class AddExerciseActivity : AppCompatActivity() {
    private lateinit var dbHelper: SQLiteHelper
    private lateinit var adapter: EjercicioAdapter
    private var planId: Long = -1
    private var planDays: Int = 0
    private var planWeeks: Int = 0
    private var isPlanActive: Boolean = false
    private var planName: String = ""
    private lateinit var spinnerDays: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_exercise)

        dbHelper = SQLiteHelper(this)
        planId = intent.getLongExtra("PLAN_ID", -1)
        planDays = intent.getIntExtra("PLAN_DAYS", 0)
        planWeeks = intent.getIntExtra("PLAN_WEEKS", 0)
        isPlanActive = intent.getBooleanExtra("PLAN_STATUS", false)
        planName = intent.getStringExtra("PLAN_NAME") ?: "Training Plan"

        spinnerDays = findViewById(R.id.spinnerDays)
        val btnAddExercise = findViewById<Button>(R.id.btnAddExercise)
        val recyclerExercises = findViewById<RecyclerView>(R.id.recyclerExercises)
        val btnClosePlan = findViewById<Button>(R.id.btnClosePlan)

        setupDaysSpinner(spinnerDays)
        setupRecyclerView(recyclerExercises)

        btnClosePlan.setOnClickListener {
            showActivatePlanDialog()
        }

        btnAddExercise.setOnClickListener {
            addExercise()
        }
    }

    fun showActivatePlanDialog() {
        val dialogo = AlertDialog.Builder(this)

        dialogo.setTitle("Activate training plan")
        dialogo.setMessage("Do you want to activate this plan?")

        dialogo.setPositiveButton("Yes") { dialog, which ->
            isPlanActive = true
            dbHelper.updatePlan(planId, isPlanActive)
            sendPlanActivatedNotification(planName)
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        dialogo.setNegativeButton("No") { dialog, which ->
            isPlanActive = false
            dbHelper.updatePlan(planId, isPlanActive)
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        dialogo.show()
    }

    private fun sendPlanActivatedNotification(planName: String) {
        val channelId = "training_channel"
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Training Plans",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Training Plan Activated")
            .setContentText("'$planName' has been activated successfully")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS)
                == PackageManager.PERMISSION_GRANTED) {
                notificationManager.notify(1, notification)
            }
        } else {
            notificationManager.notify(1, notification)
        }
    }

    fun setupDaysSpinner(spinnerDays: Spinner) {
        val totalDays = planDays * planWeeks

        val daysList = mutableListOf<Int>()
        for (day in 1..totalDays) {
            daysList.add(day)
        }

        val spinnerAdapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_item, daysList
        )
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerDays.adapter = spinnerAdapter
    }

    private fun setupRecyclerView(recyclerExercises: RecyclerView) {
        val ejerciciosTemporales = mutableListOf<EjercicioRegistrado>()
        adapter = EjercicioAdapter(ejerciciosTemporales)
        recyclerExercises.layoutManager = LinearLayoutManager(this)
        recyclerExercises.adapter = adapter
    }

    private fun addExercise() {
        val editExerciseName = findViewById<EditText>(R.id.editExerciseName)
        val editWeight = findViewById<EditText>(R.id.editWeight)
        val editReps = findViewById<EditText>(R.id.editReps)
        val exerciseName = editExerciseName.text.toString().trim()
        val weightStr = editWeight.text.toString().trim()
        val repsStr = editReps.text.toString().trim()

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
        if (reps == null) {
            Toast.makeText(this, "Please enter valid repetitions", Toast.LENGTH_SHORT).show()
            return
        }

        val selectedDay = spinnerDays.selectedItem as Int

        val ejercicio = EjercicioRegistrado(
            id = 0,
            planId = planId,
            dia = selectedDay,
            nombre = exerciseName,
            peso = weight,
            repes = reps
        )

        val ejercicioId = dbHelper.insertExercise(ejercicio)

        if (ejercicioId != -1L) {
            val ejercicioFinal = EjercicioRegistrado(
                ejercicioId, planId, selectedDay, exerciseName, weight, reps
            )

            adapter.addEjercicio(ejercicioFinal)

            Toast.makeText(
                this, "Exercise added to Day $selectedDay", Toast.LENGTH_SHORT
            ).show()

            editExerciseName.text.clear()
            editWeight.text.clear()
            editReps.text.clear()
            editExerciseName.requestFocus()
        } else {
            Toast.makeText(this, "Error adding exercise", Toast.LENGTH_SHORT).show()
        }
    }
}

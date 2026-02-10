package com.example.plandeentrenamiento

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    private lateinit var dataStoreManager: DataStoreManager
    private lateinit var dbHelper: SQLiteHelper
    private var isPlanActive: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        dataStoreManager = DataStoreManager(this)
        dbHelper = SQLiteHelper(this)

        val editTxt = findViewById<EditText>(R.id.editTextText)
        val editTxt2 = findViewById<EditText>(R.id.editTextText2)
        val editTxtName = findViewById<EditText>(R.id.editTextPlanName)
        val btnCreate = findViewById<Button>(R.id.button)
        val fabOptions = findViewById<FloatingActionButton>(R.id.fabID)

        // Precargar valores de DataStore
        lifecycleScope.launch {
            val savedDays = dataStoreManager.getDays().first()
            val savedWeeks = dataStoreManager.getWeeks().first()

            if (savedDays != null) editTxt.setText(savedDays.toString())
            if (savedWeeks != null) editTxt2.setText(savedWeeks.toString())
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

        fun createPlanAndGoToAddExercises() {
            val days = editTxt.text.toString().trim().toIntOrNull()
            val weeks = editTxt2.text.toString().trim().toIntOrNull()
            val name = editTxtName.text.toString().trim()

            if (days == null || weeks == null || name.isEmpty()) {
                Toast.makeText(this, "No fields can be empty", Toast.LENGTH_SHORT).show()
                return
            }

            if (days <= 0 || weeks <= 0) {
                Toast.makeText(this, "Days and weeks must be greater than 0", Toast.LENGTH_SHORT).show()
                return
            }

            // Guardar días y semanas en DataStore
            lifecycleScope.launch {
                dataStoreManager.saveDaysWeeks(days, weeks)
            }

            // Crear el plan en la base de datos
            val plan = PlanEntrenamiento(
                nombre = name,
                dias = days,
                semanas = weeks,
                activo = isPlanActive
            )

            val planId = dbHelper.insertPlan(plan)

            if (planId != -1L) {
                Toast.makeText(this, "Plan created successfully", Toast.LENGTH_SHORT).show()

                // Ir a MainActivity4 para añadir ejercicios
                val intent = Intent(this, MainActivity4::class.java)
                intent.putExtra("PLAN_ID", planId)
                intent.putExtra("PLAN_NAME", name)
                intent.putExtra("PLAN_DAYS", days)
                intent.putExtra("PLAN_WEEKS", weeks)
                startActivity(intent)
                finish() // Opcional: cerrar MainActivity
            } else {
                Toast.makeText(this, "Error creating plan", Toast.LENGTH_SHORT).show()
            }
        }

        btnCreate.setOnClickListener {
            showActivatePlanDialog {
                createPlanAndGoToAddExercises()
            }
        }

        fabOptions.setOnClickListener { view ->
            val popup = PopupMenu(this, view)
            popup.menuInflater.inflate(R.menu.fabmenu, popup.menu)

            popup.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.fab_setmanesiguals -> {
                        showActivatePlanDialog {
                            createPlanAndGoToAddExercises()
                        }
                        true
                    }
                    R.id.fab_setmanesdiferents -> {
                        showActivatePlanDialog {
                            createPlanAndGoToAddExercises()
                        }
                        true
                    }
                    else -> false
                }
            }
            popup.show()
        }
    }
}

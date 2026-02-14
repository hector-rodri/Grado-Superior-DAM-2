package com.example.plandeentrenamiento.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plandeentrenamiento.ui.resources.PlanAdapter
import com.example.plandeentrenamiento.R
import com.example.plandeentrenamiento.SQLiteHelper
import com.example.plandeentrenamiento.data.PlanEntrenamiento
import kotlinx.coroutines.*

class ListPlansActivity : AppCompatActivity() {
    private lateinit var dbHelper: SQLiteHelper
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PlanAdapter
    private val scope = CoroutineScope(Dispatchers.Main + Job())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_planes)

        dbHelper = SQLiteHelper(this)
        recyclerView = findViewById(R.id.recyclerPlanes)
        recyclerView.layoutManager = LinearLayoutManager(this)

        loadPlans()
    }

    private fun loadPlans() {
        scope.launch {
            val planes = withContext(Dispatchers.IO) {
                dbHelper.getAllPlans()
            }

            if (planes.isEmpty()) {
                Toast.makeText(this@ListPlansActivity, "No training plans found", Toast.LENGTH_SHORT).show()
            }

            adapter = PlanAdapter(
                planes,
                onActivoClick = { plan, position -> showToggleDialog(plan, position) },
                onDeleteClick = { plan, position -> showDeleteDialog(plan, position) },
                onVerEjerciciosClick = { plan ->
                    val intent = Intent(this@ListPlansActivity, ExercisesActivity::class.java)
                    intent.putExtra("PLAN_ID", plan.id)
                    intent.putExtra("PLAN_NOMBRE", plan.nombre)
                    intent.putExtra("PLAN_DIAS", plan.dias)
                    intent.putExtra("PLAN_SEMANAS", plan.semanas)
                    startActivity(intent)
                })
            recyclerView.adapter = adapter
        }
    }

    private fun showToggleDialog(plan: PlanEntrenamiento, position: Int) {
        val nuevoEstado = !plan.activo

        val mensaje = if (nuevoEstado) {
            "Do you want to activate '${plan.nombre}'?"
        } else {
            "Do you want to deactivate '${plan.nombre}'?"
        }

        val dialogo = AlertDialog.Builder(this)
        dialogo.setTitle("Change Status")
        dialogo.setMessage(mensaje)

        dialogo.setPositiveButton("Yes") { dialog, _ ->
            togglePlanActivo(plan, position, nuevoEstado)
            dialog.dismiss()
        }
        dialogo.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }
        dialogo.show()
    }

    private fun togglePlanActivo(plan: PlanEntrenamiento, position: Int, nuevoEstado: Boolean) {
        scope.launch {
            val success = withContext(Dispatchers.IO) {
                dbHelper.updatePlan(plan.id, nuevoEstado)
            }

            if (success) {
                val planActualizado = plan.copy(activo = nuevoEstado)
                adapter.updatePlan(position, planActualizado)

                val mensaje = if (nuevoEstado) {
                    "${plan.nombre} is now ACTIVE"
                } else {
                    "${plan.nombre} is now INACTIVE"
                }
                Toast.makeText(this@ListPlansActivity, mensaje, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@ListPlansActivity, "Error updating plan status", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showDeleteDialog(plan: PlanEntrenamiento, position: Int) {
        val dialogo = AlertDialog.Builder(this)
        dialogo.setTitle("Delete Plan")
        dialogo.setMessage("Are you sure you want to delete '${plan.nombre}'?")

        dialogo.setPositiveButton("Yes, Delete") { dialog, _ ->
            deletePlan(plan, position)
            dialog.dismiss()
        }
        dialogo.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }
        dialogo.show()
    }

    private fun deletePlan(plan: PlanEntrenamiento, position: Int) {
        scope.launch {
            val success = withContext(Dispatchers.IO) {
                dbHelper.deletePlan(plan.id)
            }

            if (success) {
                adapter.removePlan(position)
                Toast.makeText(this@ListPlansActivity, "'${plan.nombre}' deleted successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@ListPlansActivity, "Error deleting plan", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}
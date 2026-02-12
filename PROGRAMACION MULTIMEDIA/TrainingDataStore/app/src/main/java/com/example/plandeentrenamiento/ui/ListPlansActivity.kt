package com.example.plandeentrenamiento.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plandeentrenamiento.ui.ExercisesActivity
import com.example.plandeentrenamiento.ui.resources.PlanAdapter
import com.example.plandeentrenamiento.R
import com.example.plandeentrenamiento.SQLiteHelper
import com.example.plandeentrenamiento.data.PlanEntrenamiento

class ListPlansActivity : AppCompatActivity() {

    private lateinit var dbHelper: SQLiteHelper
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PlanAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_planes)

        dbHelper = SQLiteHelper(this)
        recyclerView = findViewById(R.id.recyclerPlanes)

        setupRecyclerView()
        loadPlanes()
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun loadPlanes() {
        val planes = dbHelper.getAllPlans()

        if (planes.isEmpty()) {
            Toast.makeText(this, "No training plans found", Toast.LENGTH_SHORT).show()
        }

        // Pasamos los lambdas para manejar los clicks
        adapter = PlanAdapter(
            planes,
            onActivoClick = { plan, position -> showToggleDialog(plan, position) },
            onDeleteClick = { plan, position -> showDeleteDialog(plan, position) },
            onVerEjerciciosClick = { plan -> openEjerciciosActivity(plan) }
        )
        recyclerView.adapter = adapter
    }

    /**
     * Muestra un diálogo de confirmación antes de cambiar el estado
     */
    private fun showToggleDialog(plan: PlanEntrenamiento, position: Int) {
        val nuevoEstado = !plan.activo
        val mensaje = if (nuevoEstado) {
            "Do you want to activate '${plan.nombre}'?"
        } else {
            "Do you want to deactivate '${plan.nombre}'?"
        }

        AlertDialog.Builder(this)
            .setTitle("Change Status")
            .setMessage(mensaje)
            .setPositiveButton("Yes") { dialog, _ ->
                togglePlanActivo(plan, position, nuevoEstado)
                dialog.dismiss()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    /**
     * Cambia el estado activo/inactivo del plan
     */
    private fun togglePlanActivo(plan: PlanEntrenamiento, position: Int, nuevoEstado: Boolean) {
        // Actualizamos en la base de datos
        val success = dbHelper.updatePlan(plan.id, nuevoEstado)

        if (success) {
            // Creamos el plan actualizado
            val planActualizado = plan.copy(activo = nuevoEstado)

            // Actualizamos el adapter
            adapter.updatePlan(position, planActualizado)

            // Mostramos un mensaje de confirmación
            val mensaje = if (nuevoEstado) {
                "${plan.nombre} is now ACTIVE"
            } else {
                "${plan.nombre} is now INACTIVE"
            }
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Error updating plan status", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Muestra un diálogo de confirmación antes de eliminar
     */
    private fun showDeleteDialog(plan: PlanEntrenamiento, position: Int) {
        AlertDialog.Builder(this)
            .setTitle("Delete Plan")
            .setMessage("Are you sure you want to delete '${plan.nombre}'? This action cannot be undone and will delete all exercises associated with this plan.")
            .setPositiveButton("Yes, Delete") { dialog, _ ->
                deletePlan(plan, position)
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    /**
     * Elimina el plan de la base de datos
     */
    private fun deletePlan(plan: PlanEntrenamiento, position: Int) {
        val success = dbHelper.deletePlan(plan.id)

        if (success) {
            // Eliminamos del adapter
            adapter.removePlan(position)
            Toast.makeText(this, "'${plan.nombre}' deleted successfully", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Error deleting plan", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Abre la actividad para ver los ejercicios del plan
     */
    private fun openEjerciciosActivity(plan: PlanEntrenamiento) {
        val intent = Intent(this, ExercisesActivity::class.java)
        intent.putExtra("PLAN_ID", plan.id)
        intent.putExtra("PLAN_NOMBRE", plan.nombre)
        intent.putExtra("PLAN_DIAS", plan.dias)
        startActivity(intent)
    }
}

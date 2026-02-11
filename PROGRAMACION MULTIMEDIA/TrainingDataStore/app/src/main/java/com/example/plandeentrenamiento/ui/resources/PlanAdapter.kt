package com.example.plandeentrenamiento.ui.resources

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.plandeentrenamiento.R
import com.example.plandeentrenamiento.data.PlanEntrenamiento

class PlanAdapter(
    private var planes: List<PlanEntrenamiento>,
    private val onActivoClick: (PlanEntrenamiento, Int) -> Unit,
    private val onDeleteClick: (PlanEntrenamiento, Int) -> Unit,
    private val onVerEjerciciosClick: (PlanEntrenamiento) -> Unit
) : RecyclerView.Adapter<PlanAdapter.PlanViewHolder>() {

    inner class PlanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombre: TextView = itemView.findViewById(R.id.tvPlanNombre)
        val tvDias: TextView = itemView.findViewById(R.id.tvPlanDias)
        val tvSemanas: TextView = itemView.findViewById(R.id.tvPlanSemanas)
        val tvActivo: TextView = itemView.findViewById(R.id.tvPlanActivo)
        val btnDelete: ImageButton = itemView.findViewById(R.id.btnDeletePlan)
        val btnVerEjercicios: ImageButton = itemView.findViewById(R.id.btnVerEjercicios)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_plan, parent, false)
        return PlanViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlanViewHolder, position: Int) {
        val plan = planes[position]

        holder.tvNombre.text = plan.nombre
        holder.tvDias.text = "Days: ${plan.dias}"
        holder.tvSemanas.text = "Weeks: ${plan.semanas}"

        // Configurar el texto y color del estado
        if (plan.activo) {
            holder.tvActivo.text = "ACTIVE"
            holder.tvActivo.setTextColor(
                ContextCompat.getColor(holder.itemView.context, android.R.color.holo_green_dark)
            )
        } else {
            holder.tvActivo.text = "INACTIVE"
            holder.tvActivo.setTextColor(
                ContextCompat.getColor(holder.itemView.context, android.R.color.holo_red_dark)
            )
        }

        // Click listener para cambiar el estado
        holder.tvActivo.setOnClickListener {
            onActivoClick(plan, position)
        }

        // Click listener para eliminar
        holder.btnDelete.setOnClickListener {
            onDeleteClick(plan, position)
        }

        // Click listener para ver ejercicios
        holder.btnVerEjercicios.setOnClickListener {
            onVerEjerciciosClick(plan)
        }
    }

    override fun getItemCount(): Int = planes.size

    // Método para actualizar la lista de planes
    fun updatePlanes(newPlanes: List<PlanEntrenamiento>) {
        planes = newPlanes
        notifyDataSetChanged()
    }

    // Método para actualizar un solo item
    fun updatePlan(position: Int, updatedPlan: PlanEntrenamiento) {
        val mutableList = planes.toMutableList()
        mutableList[position] = updatedPlan
        planes = mutableList
        notifyItemChanged(position)
    }

    // Método para eliminar un item
    fun removePlan(position: Int) {
        val mutableList = planes.toMutableList()
        mutableList.removeAt(position)
        planes = mutableList
        notifyItemRemoved(position)
    }
}

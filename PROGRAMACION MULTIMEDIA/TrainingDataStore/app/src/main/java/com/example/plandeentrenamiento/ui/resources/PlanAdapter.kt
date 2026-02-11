package com.example.plandeentrenamiento.ui.resources

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.plandeentrenamiento.R
import com.example.plandeentrenamiento.data.PlanEntrenamiento

class PlanAdapter(private val planes: List<PlanEntrenamiento>) :
    RecyclerView.Adapter<PlanAdapter.PlanViewHolder>() {

    class PlanViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNombre: TextView = view.findViewById(R.id.tvPlanNombre)
        val tvDias: TextView = view.findViewById(R.id.tvPlanDias)
        val tvSemanas: TextView = view.findViewById(R.id.tvPlanSemanas)
        val tvActivo: TextView = view.findViewById(R.id.tvPlanActivo)
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

        if (plan.activo) {
            holder.tvActivo.text = "ACTIVE"
            holder.tvActivo.setTextColor(Color.parseColor("#4CAF50")) // Verde
        } else {
            holder.tvActivo.text = "INACTIVE"
            holder.tvActivo.setTextColor(Color.parseColor("#9E9E9E")) // Gris
        }
    }

    override fun getItemCount(): Int = planes.size
}
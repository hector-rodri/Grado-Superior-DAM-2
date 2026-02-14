package com.example.plandeentrenamiento.ui.resources

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.plandeentrenamiento.R
import com.example.plandeentrenamiento.data.EjercicioRegistrado

class EjercicioAdapter(
    private var ejercicios: MutableList<EjercicioRegistrado>
) : RecyclerView.Adapter<EjercicioAdapter.EjercicioViewHolder>() {

    inner class EjercicioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombre: TextView = itemView.findViewById(R.id.tvEjercicioNombre)
        val tvPeso: TextView = itemView.findViewById(R.id.tvEjercicioPeso)
        val tvRepes: TextView = itemView.findViewById(R.id.tvEjercicioRepes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EjercicioViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ejercicio, parent, false)
        return EjercicioViewHolder(view)
    }

    override fun onBindViewHolder(holder: EjercicioViewHolder, position: Int) {
        val ejercicio = ejercicios[position]

        holder.tvNombre.text = ejercicio.nombre
        holder.tvPeso.text = "${ejercicio.peso} kg"
        holder.tvRepes.text = "${ejercicio.repes} reps"
    }

    override fun getItemCount(): Int = ejercicios.size

    fun addEjercicio(ejercicio: EjercicioRegistrado) {
        ejercicios.add(ejercicio)
        notifyItemInserted(ejercicios.size - 1)
    }

}

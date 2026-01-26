package com.example.plandeentrenamiento.ListsAndMenus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.plandeentrenamiento.R

class RegisteredExercisesAdapter(
    private val items: List<RegisteredExercise>
) : RecyclerView.Adapter<RegisteredExercisesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nom: TextView = view.findViewById(R.id.tvNom)
        val data: TextView = view.findViewById(R.id.tvData)
        val pes: TextView = view.findViewById(R.id.tvPes)
        val reps: TextView = view.findViewById(R.id.tvReps)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.exercise_list, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val e = items[position]
        holder.nom.text = e.nom
        holder.data.text = e.data
        holder.pes.text = e.pes
        holder.reps.text = e.reps
    }

    override fun getItemCount(): Int = items.size
}

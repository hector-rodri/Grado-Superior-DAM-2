package com.example.plandeentrenamiento.ListsAndMenus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.plandeentrenamiento.R

class ListAdapter(
    private val items: List<ElementLlista>,
    private val onClick: (ElementLlista) -> Unit = {}
) : RecyclerView.Adapter<ListAdapter.ItemViewHolder>() {

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titol: TextView = view.findViewById(R.id.tvTitol)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return ItemViewHolder(v)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val e = items[position]
        holder.titol.text = e.titol
        holder.itemView.setOnClickListener { onClick(e) }
    }

    override fun getItemCount(): Int = items.size
}
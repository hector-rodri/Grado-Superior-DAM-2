package com.example.plandeentrenamiento

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListPlanesActivity : AppCompatActivity() {

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
        val planes = dbHelper.getAllPlanes()

        if (planes.isEmpty()) {
            Toast.makeText(this, "No training plans found", Toast.LENGTH_SHORT).show()
        }

        adapter = PlanAdapter(planes)
        recyclerView.adapter = adapter
    }
}

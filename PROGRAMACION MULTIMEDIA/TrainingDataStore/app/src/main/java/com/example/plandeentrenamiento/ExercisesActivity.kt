package com.example.plandeentrenamiento

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plandeentrenamiento.ui.resources.EjercicioAdapter
import com.google.android.material.tabs.TabLayout

class ExercisesActivity : AppCompatActivity() {
    private lateinit var dbHelper: SQLiteHelper
    private lateinit var tvPlanNombre: TextView
    private lateinit var tabLayout: TabLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: EjercicioAdapter

    private var planId: Long = -1
    private var planNombre: String = ""
    private var planDias: Int = 0
    private var currentDay: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercises)

        // Obtener datos del Intent
        planId = intent.getLongExtra("PLAN_ID", -1)
        planNombre = intent.getStringExtra("PLAN_NOMBRE") ?: ""
        planDias = intent.getIntExtra("PLAN_DIAS", 0)

        if (planId == -1L) {
            Toast.makeText(this, "Error loading plan", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        dbHelper = SQLiteHelper(this)

        // Inicializar vistas
        tvPlanNombre = findViewById(R.id.tvEjerciciosPlanNombre)
        tabLayout = findViewById(R.id.tabLayoutDias)
        recyclerView = findViewById(R.id.recyclerEjercicios)

        setupViews()
        setupTabs()
        setupRecyclerView()
        loadEjercicios(currentDay)
    }

    private fun setupViews() {
        tvPlanNombre.text = planNombre
        supportActionBar?.title = "Exercises"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupTabs() {
        // Crear tabs para cada día
        for (day in 1..planDias) {
            tabLayout.addTab(tabLayout.newTab().setText("Day $day"))
        }

        // Listener para cambio de tab
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    currentDay = it.position + 1
                    loadEjercicios(currentDay)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun loadEjercicios(dia: Int) {
        val ejercicios = dbHelper.getEjerciciosByPlanAndDay(planId, dia)

        if (ejercicios.isEmpty()) {
            // No mostrar Toast, solo dejar la lista vacía
            adapter = EjercicioAdapter(mutableListOf())
            recyclerView.adapter = adapter
        } else {
            adapter = EjercicioAdapter(ejercicios.toMutableList())
            recyclerView.adapter = adapter
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

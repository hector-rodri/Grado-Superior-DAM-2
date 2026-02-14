package com.example.plandeentrenamiento.ui

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plandeentrenamiento.R
import com.example.plandeentrenamiento.SQLiteHelper
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

        planId = intent.getLongExtra("PLAN_ID", -1)
        planNombre = intent.getStringExtra("PLAN_NOMBRE") ?: ""
        planDias = intent.getIntExtra("PLAN_DIAS", 0)
        dbHelper = SQLiteHelper(this)
        tvPlanNombre = findViewById(R.id.tvEjerciciosPlanNombre)
        tabLayout = findViewById(R.id.tabLayoutDias)
        recyclerView = findViewById(R.id.recyclerEjercicios)

        if (planId == -1L) {
            Toast.makeText(this, "Error loading plan", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        tvPlanNombre.text = planNombre//Nombre del plan en el txtView
        supportActionBar?.title = "Exercises"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupTabs()
        recyclerView.layoutManager = LinearLayoutManager(this)
        loadEjercicios(currentDay)
    }

    private fun setupTabs() {
        for (day in 1..planDias) {
            val tab = tabLayout.newTab()
            tab.text = "Day $day"
            tabLayout.addTab(tab)
        }

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    currentDay = tab.position + 1
                    loadEjercicios(currentDay)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun loadEjercicios(dia: Int) {
        val ejercicios = dbHelper.getExercises(planId, dia)
        adapter = EjercicioAdapter(ejercicios.toMutableList())
        recyclerView.adapter = adapter
    }
}

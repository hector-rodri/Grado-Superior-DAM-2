package com.example.plandeentrenamiento.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import android.widget.TextView
import com.example.plandeentrenamiento.R
import com.example.plandeentrenamiento.ui.resources.ListAdapter
import com.example.plandeentrenamiento.ui.resources.ListController


class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        val planName = intent.getStringExtra("plan_name")
        val toolbarTitle = findViewById<TextView>(R.id.toolbar_title)
        toolbarTitle.text = planName
        val days = intent.getIntExtra("value1", 0)
        val weeks = intent.getIntExtra("value2", 0)
        val controller = ListController()
        val planId = intent.getIntExtra("plan_id", 0)
        val llista = controller.carregarDies(weeks, days, planId)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerDays)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ListAdapter(llista) { diaSeleccionado ->

            val intent = Intent(this, AddExerciseActivity::class.java)
            intent.putExtra("dia_id", diaSeleccionado.idDia)
            intent.putExtra("plan_id", diaSeleccionado.planId)

            startActivity(intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.activateplan -> {
                Toast.makeText(this, "Activar Pla seleccionat", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.registerexercise -> {
                Toast.makeText(this, "Registrar exercici seleccionat", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
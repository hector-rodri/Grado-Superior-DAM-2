package com.example.plandeentrenamiento

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar

class MainActivity3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar3)
        setSupportActionBar(toolbar)

        val llista = listOf(
            RegisteredExercise("Press banca", "10/03/2005", "60 kg", "10 reps"),//la fecha es mi cumple xd
            RegisteredExercise("Squat", "10/03/2005", "80 kg", "8 reps"),
            RegisteredExercise("Peso muerto", "10/03/2005", "90 kg", "6 reps")
        )

        val recycler = findViewById<RecyclerView>(R.id.recyclerRegistered)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = RegisteredExercisesAdapter(llista)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu2, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.modify_exercise -> {
                Toast.makeText(this, "Modificar exercici seleccionat", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.view_exercise_stats -> {
                Toast.makeText(this, "Visualitzar estadístiques d’un exercici", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.view_plan_stats -> {
                Toast.makeText(this, "Visualitzar estadístiques de plans", Toast.LENGTH_SHORT).show()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
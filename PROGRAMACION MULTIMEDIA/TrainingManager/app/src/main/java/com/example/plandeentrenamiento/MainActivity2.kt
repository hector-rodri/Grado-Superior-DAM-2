package com.example.plandeentrenamiento

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

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val days = intent.getIntExtra("value1", 0)
        val weeks = intent.getIntExtra("value2", 0)
        val controller = ListController()
        val llista = controller.carregarDies(weeks, days)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerDays)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ListAdapter(llista)
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
            R.id.viewregisteredexerc -> {
                val intent = Intent(this, MainActivity3::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}


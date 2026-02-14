package com.example.plandeentrenamiento.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.plandeentrenamiento.R
import android.content.Intent
import android.widget.Button

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        val buttonCreatePlan = findViewById<Button>(R.id.buttonCreatePlan)
        val buttonViewPlans = findViewById<Button>(R.id.buttonViewPlans)
        val buttonLogout = findViewById<Button>(R.id.buttonLogout)

        buttonCreatePlan.setOnClickListener {
            val intent = Intent(this, CreatePlanActivity::class.java)
            startActivity(intent)
        }

        buttonViewPlans.setOnClickListener {
            val intent = Intent(this, ListPlansActivity::class.java)
            startActivity(intent)
        }

        buttonLogout.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("LOGOUT", true)
            startActivity(intent)
        }
    }
}

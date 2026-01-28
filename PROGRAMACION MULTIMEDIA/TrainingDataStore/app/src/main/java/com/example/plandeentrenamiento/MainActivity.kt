package com.example.plandeentrenamiento

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var dataStoreManager: DataStoreManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        dataStoreManager = DataStoreManager(this)

        val editTxt = findViewById<EditText>(R.id.editTextText)
        val editTxt2 = findViewById<EditText>(R.id.editTextText2)
        val btnCreate = findViewById<Button>(R.id.button)
        val fabOptions = findViewById<FloatingActionButton>(R.id.fabID)

        lifecycleScope.launch {
            val savedDays = dataStoreManager.getDays().first()
            val savedWeeks = dataStoreManager.getWeeks().first()

            if (savedDays != null) editTxt.setText(savedDays.toString())
            if (savedWeeks != null) editTxt2.setText(savedWeeks.toString())
        }

        fun goToMainActivity2() {
            val days = editTxt.text.toString().trim().toIntOrNull()
            val weeks = editTxt2.text.toString().trim().toIntOrNull()

            if (days == null || weeks == null) {
                Toast.makeText(this, "Introdueix només números vàlids", Toast.LENGTH_SHORT).show()
                return
            }

            lifecycleScope.launch {
                dataStoreManager.saveDaysWeeks(days, weeks)
            }

            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("value1", days)
            intent.putExtra("value2", weeks)
            startActivity(intent)
        }

        btnCreate.setOnClickListener { goToMainActivity2() }

        fabOptions.setOnClickListener { view ->
            val popup = PopupMenu(this, view)
            popup.menuInflater.inflate(R.menu.fabmenu, popup.menu)

            popup.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.fab_setmanesiguals -> {
                        goToMainActivity2()
                        true
                    }
                    R.id.fab_setmanesdiferents -> {
                        goToMainActivity2()
                        true
                    }
                    R.id.fab_logout -> {
                        val intent = Intent(this, LoginActivity::class.java)
                        intent.putExtra("LOGOUT", true)
                        startActivity(intent)
                        finish()
                        true
                    }
                    else -> false
                }
            }
            popup.show()
        }
    }
}
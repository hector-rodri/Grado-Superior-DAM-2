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
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import androidx.datastore.preferences.core.edit


private val AppCompatActivity.dataStore by preferencesDataStore(
    name = "MyPreferences"
)

class MainActivity : AppCompatActivity() {
    private val daysKey = intPreferencesKey("DAYS")
    private val weeksKey = intPreferencesKey("WEEKS")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val editTxt = findViewById<EditText>(R.id.editTextText)
        val editTxt2 = findViewById<EditText>(R.id.editTextText2)
        val btnCreate = findViewById<Button>(R.id.button)
        val fabOptions = findViewById<FloatingActionButton>(R.id.fabID);

        lifecycleScope.launch {
            val preferences = dataStore.data.first()
            val savedDays = preferences[daysKey]
            val savedWeeks = preferences[weeksKey]

            if (savedDays != null) {
                editTxt.setText(savedDays.toString())
            }
            if (savedWeeks != null) {
                editTxt2.setText(savedWeeks.toString())
            }
        }

        fun goToMainActivity2() {
            val txt1 = editTxt.text.toString().trim()
            val txt2 = editTxt2.text.toString().trim()

            val days = txt1.toIntOrNull()
            val weeks = txt2.toIntOrNull()

            if (days == null || weeks == null) {
                Toast.makeText(
                    this,
                    "Introdueix només números vàlids",
                    Toast.LENGTH_SHORT
                ).show()
                return
            }

            lifecycleScope.launch {
                dataStore.edit { preferences ->
                    preferences[daysKey] = days
                    preferences[weeksKey] = weeks
                }
            }

            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("value1", days)
            intent.putExtra("value2", weeks)
            startActivity(intent)
        }



        btnCreate.setOnClickListener {
            goToMainActivity2()
        }

        fabOptions.setOnClickListener { view ->
            val popup = PopupMenu(this, view)
            popup.menuInflater.inflate(R.menu.fabmenu, popup.menu)
            popup.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.fab_setmanesiguals -> {
                        Toast.makeText(this, "Setmantes iguals", Toast.LENGTH_SHORT).show()
                        goToMainActivity2()
                        true
                    }
                    R.id.fab_setmanesdiferents -> {
                        Toast.makeText(this, "Setmanes diferents", Toast.LENGTH_SHORT).show()
                        goToMainActivity2()
                        true
                    }
                    R.id.fab_logout -> {
                        val intent = Intent(this, DataStoreActivity::class.java)
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
package com.example.plandeentrenamiento

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

private val AppCompatActivity.dataStore by preferencesDataStore(
    name = "MyPreferences"
)

class DataStoreActivity : AppCompatActivity() {
    private val userKey = stringPreferencesKey("User")
    private val passKey = stringPreferencesKey("Pass")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val logout = intent.getBooleanExtra("LOGOUT", false)

        lifecycleScope.launch {
            val preferences = dataStore.data.first()
            val user = preferences[userKey]

            if (user != null && !logout) {
                startActivity(
                    Intent(this@DataStoreActivity, MainActivity::class.java)
                )
                finish()
            }
        }

        val etUser = findViewById<EditText>(R.id.etUsername)
        val etPass = findViewById<EditText>(R.id.etPassword)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnRegister.setOnClickListener {
            val user = etUser.text.toString().trim()
            val pass = etPass.text.toString().trim()
            if (user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Rellena tots els camps", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                dataStore.edit { preferences ->
                    preferences[userKey] = user
                    preferences[passKey] = pass
                }
                Toast.makeText(this@DataStoreActivity, "Usuari guardat", Toast.LENGTH_SHORT).show()
            }
        }

        btnLogin.setOnClickListener {
            val inputUser = etUser.text.toString().trim()
            val pass = etPass.text.toString().trim()

            if (inputUser.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Rellena tots els camps", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val preferences = dataStore.data.first()
                val storedUser = preferences[userKey]
                if (storedUser == inputUser) {
                    startActivity(
                        Intent(this@DataStoreActivity, MainActivity::class.java)
                    )
                    finish()
                } else {
                    Toast.makeText(
                        this@DataStoreActivity,
                        "L’usuari no és correcte",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }
}

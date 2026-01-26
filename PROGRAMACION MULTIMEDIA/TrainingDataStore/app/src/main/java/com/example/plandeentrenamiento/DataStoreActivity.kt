package com.example.plandeentrenamiento

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

private val AppCompatActivity.dataStore by preferencesDataStore(
    name = "MyPreferences"
)

class DataStoreActivity : AppCompatActivity() {
    private val usernameKey = stringPreferencesKey("username")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        lifecycleScope.launch {
            val preferences = dataStore.data.first()
            val savedUser = preferences[usernameKey]

            if (!savedUser.isNullOrEmpty()) {
                startActivity(Intent(this@DataStoreActivity, MainActivity::class.java))
            }
        }

        // 🔹 LOGIN
        btnLogin.setOnClickListener {
            val inputUser = etUsername.text.toString()

            lifecycleScope.launch {
                val preferences = dataStore.data.first()
                val savedUser = preferences[usernameKey]

                if (inputUser == savedUser) {
                    startActivity(
                        Intent(this@DataStoreActivity, MainActivity::class.java)
                    )
                } else {
                    Toast.makeText(
                        this@DataStoreActivity,
                        "Incorrect username",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        // 🔹 REGISTER (overwrite stored user)
        btnRegister.setOnClickListener {
            val inputUser = etUsername.text.toString()

            lifecycleScope.launch {
                dataStore.edit { preferences ->
                    preferences[usernameKey] = inputUser
                }

                startActivity(
                    Intent(this@DataStoreActivity, MainActivity::class.java)
                )
            }
        }
    }
}

/*
package com.example.plandeentrenamiento

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        lifecycleScope.launch {
            val preferences = dataStore.data.first()
            val savedUser = preferences[PreferencesKeys.USERNAME]

            if (!savedUser.isNullOrEmpty()) {
                startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                finish()
            }
        }

        btnLogin.setOnClickListener {
            val inputUser = etUsername.text.toString()

            lifecycleScope.launch {
                val preferences = dataStore.data.first()
                val savedUser = preferences[PreferencesKeys.USERNAME]

                if (inputUser == savedUser) {
                    startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        "Incorrect user",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        // REGISTER (overwrite user)
        btnRegister.setOnClickListener {
            val inputUser = etUsername.text.toString()

            lifecycleScope.launch {
                dataStore.edit { preferences ->
                    preferences[PreferencesKeys.USERNAME] = inputUser
                }

                startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
            }
        }
    }
}
*/

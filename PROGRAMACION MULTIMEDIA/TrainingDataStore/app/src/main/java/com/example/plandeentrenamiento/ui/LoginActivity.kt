package com.example.plandeentrenamiento.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.plandeentrenamiento.DataStoreManager
import com.example.plandeentrenamiento.R
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var dataStoreManager: DataStoreManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        dataStoreManager = DataStoreManager(this)

        val logout = intent.getBooleanExtra("LOGOUT", false)

        lifecycleScope.launch {
            val user = dataStoreManager.getUser().first()

            if (user != null && !logout) {
                startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
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
                dataStoreManager.saveUser(user, pass)
                Toast.makeText(this@LoginActivity, "Usuari guardat", Toast.LENGTH_SHORT).show()
            }
        }

        btnLogin.setOnClickListener {
            val inputUser = etUser.text.toString().trim()
            val inputPass = etPass.text.toString().trim()

            if (inputUser.isEmpty() || inputPass.isEmpty()) {
                Toast.makeText(this, "Rellena tots els camps", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val storedUser = dataStoreManager.getUser().first()

                if (storedUser == inputUser) {
                    startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        "L’usuari o la contrasenya no és correcte",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
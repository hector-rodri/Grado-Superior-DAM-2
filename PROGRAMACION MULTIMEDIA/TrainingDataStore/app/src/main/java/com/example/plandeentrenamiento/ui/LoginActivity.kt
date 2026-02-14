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
        val etUser = findViewById<EditText>(R.id.etUsername)
        val etPass = findViewById<EditText>(R.id.etPassword)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        lifecycleScope.launch {
            val user = dataStoreManager.getUser().first()

            if (user != null && !logout) {
                startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                finish()
            }
        }


        btnRegister.setOnClickListener {
            val user = etUser.text.toString().trim()
            val psswd = etPass.text.toString().trim()


            if (user.isEmpty() || psswd.isEmpty()) {
                Toast.makeText(this, "Please enter all required information", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                dataStoreManager.saveUser(user, "")
                Toast.makeText(this@LoginActivity, "Saved user", Toast.LENGTH_SHORT).show()
            }
        }

        btnLogin.setOnClickListener {
            val user2 = etUser.text.toString().trim()
            val psswd2 = etPass.text.toString().trim()

            if (user2.isEmpty() || psswd2.isEmpty()) {
                Toast.makeText(this, "Please enter all required information", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val storedUser = dataStoreManager.getUser().first()

                if (storedUser == user2) {
                    startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(
                        this@LoginActivity, "The user is incorrect", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
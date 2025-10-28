package com.example.cicledevida

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.util.Log
class MainActivity : AppCompatActivity() {
    private val TAG = this::class.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart llamado")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume llamado")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause llamado")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop llamado")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart llamado")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy llamado")
    }
}
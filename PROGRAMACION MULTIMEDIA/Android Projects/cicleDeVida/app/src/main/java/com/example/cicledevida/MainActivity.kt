package com.example.cicledevida

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val TAG = this::class.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val openWeb = findViewById<Button>(R.id.button3)
        val openWindow = findViewById<Button>(R.id.button4)

        openWeb.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.cide.es")
            startActivity(intent)
        }

        openWindow.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState llamado")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(TAG, "onRestoreInstanceState llamado")
    }
}
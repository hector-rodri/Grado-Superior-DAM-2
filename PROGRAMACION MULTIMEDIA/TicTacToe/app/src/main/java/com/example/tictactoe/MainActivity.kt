package com.example.tictactoe

import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val canvasView = TicTacToeView(this)

        val frameLayout = findViewById<FrameLayout>(R.id.canvasContainer)
        frameLayout.addView(canvasView)

        val btnReiniciar = findViewById<Button>(R.id.btnReload)
        btnReiniciar.setOnClickListener {
            canvasView.resetBoard()
        }
    }
}

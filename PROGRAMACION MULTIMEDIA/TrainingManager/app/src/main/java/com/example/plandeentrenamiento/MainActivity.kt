package com.example.plandeentrenamiento

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.widget.PopupMenu
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val editTxt = findViewById<EditText>(R.id.editTextText)
        val editTxt2 = findViewById<EditText>(R.id.editTextText2)
        val btnCreate = findViewById<Button>(R.id.button)
        val fabOptions = findViewById<FloatingActionButton>(R.id.fabID);

        fun goToMainActivity2(){
            val txt1 = editTxt.text.toString()
            val txt2 = editTxt2.text.toString()
            val intent = Intent(this, MainActivity2::class.java)

            if (txt1.isEmpty() || txt2.isEmpty()) {
                Error( "Els camps no poden estar buits")
            }

            intent.putExtra("value1", txt1.toInt())
            intent.putExtra("value2", txt2.toInt())
            startActivity(intent);
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
                        goToMainActivity2()
                        true
                    }
                    R.id.fab_setmanesdiferents -> {
                        goToMainActivity2()
                        true
                    }
                    else -> false
                }
            }
            popup.show()
        }
    }
}

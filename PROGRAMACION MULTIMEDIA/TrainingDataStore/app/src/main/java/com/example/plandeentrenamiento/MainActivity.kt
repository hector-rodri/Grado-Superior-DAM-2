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

            if (txt1.isNotEmpty() || txt2.isNotEmpty()) {
                intent.putExtra("value1", txt1.toInt())
                intent.putExtra("value2", txt2.toInt())
                startActivity(intent);
            }else{
                Toast.makeText(this, "Els camps no poden estar buits", Toast.LENGTH_SHORT).show()
            }
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
                    else -> false
                }
            }
            popup.show()
        }
    }
}
package com.example.trazacolor.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trazacolor.R
import kotlin.system.exitProcess

class MainCarrito : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_carrito)
        setSupportActionBar(findViewById(R.id.toolbarCarrito))
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
/*
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_carrito, menu)
        return true
    }
*/
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                startActivity(Intent(this, MainActivity::class.java))
                true
            }
            R.id.action_exit -> {
                Toast.makeText(this, "Gracias por su visita.", Toast.LENGTH_SHORT).show()
                exitProcess(1)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
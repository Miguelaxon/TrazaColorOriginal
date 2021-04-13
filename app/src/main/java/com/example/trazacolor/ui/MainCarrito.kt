package com.example.trazacolor.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.trazacolor.R

class MainCarrito : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_carrito)
        setSupportActionBar(findViewById(R.id.toolbar))
    }
}
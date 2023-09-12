package com.example.proyectocascosmoto.actividades


import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectocascosmoto.R


class Terminos: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.terminos)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // Habilita el botón de retroceso.
        supportActionBar?.title = "Términos y Condiciones" // Establece el título de la Toolbar.

        // Cargar el texto de los términos desde el recurso de cadena
        val termsTextView = findViewById<TextView>(R.id.tvTerminos)
        termsTextView.text = getString(R.string.terminos)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed() // Esta línea permite que el botón de retroceso funcione.
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}

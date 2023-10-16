package com.example.proyectocascosmoto.actividades

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageButton
import android.content.Context
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.proyectocascosmoto.R
import com.example.proyectocascosmoto.clasesdatos.Tipo


class Modulares : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modelos)

        // Creacion de los textos
        val tipo = intent.getSerializableExtra("tip") as Tipo

        findViewById<TextView>(R.id.amTv1).text = getString(R.string.Modelo1, tipo.modelo1)
        findViewById<TextView>(R.id.amTv2).text = getString(R.string.Modelo2, tipo.modelo2)
        findViewById<TextView>(R.id.amTv3).text = getString(R.string.Modelo3, tipo.modelo3)
        findViewById<TextView>(R.id.amTv4).text = getString(R.string.Modelo4, tipo.modelo4)
        findViewById<TextView>(R.id.amTv5).text = getString(R.string.Modelo5, tipo.modelo5)

        // La toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Configura los textos de los modelos específicos para "Abiertos"
        findViewById<ImageButton>(R.id.amEstrella1).tag = "amEM1" // Estrella Modulares 1
        findViewById<ImageButton>(R.id.amEstrella2).tag = "amEM2" // Estrella Modulares 2
        findViewById<ImageButton>(R.id.amEstrella3).tag = "amEM3" // Estrella Modulares 3
        findViewById<ImageButton>(R.id.amEstrella4).tag = "amEM4" // Estrella Modulares 4
        findViewById<ImageButton>(R.id.amEstrella5).tag = "amEM5" // Estrella Modulares 5


        val starButtons3: MutableList<ImageButton> = ArrayList()
        starButtons3.add(findViewById(R.id.amEstrella1))
        starButtons3.add(findViewById(R.id.amEstrella2))
        starButtons3.add(findViewById(R.id.amEstrella3))
        starButtons3.add(findViewById(R.id.amEstrella4))
        starButtons3.add(findViewById(R.id.amEstrella5))

        val ModularessharedPreferences = getSharedPreferences("StarPrefs", Context.MODE_PRIVATE)




        for (button in starButtons3) {
            // Verifica que la etiqueta no sea null antes de configurarla
            if (button.tag == null) {
                button.tag = "button_${button.id}"
            }

            // Recupera el estado anterior de cada botón (si existe)
            val isFavorite = ModularessharedPreferences.getBoolean(button.tag.toString(), false)

            // Verifica si la información se ha guardado en SharedPreferences
            val isSaved = ModularessharedPreferences.contains(button.tag.toString())

            if (isSaved) {
                Log.d("SharedPreferences", "La información para ${button.tag} se ha guardado correctamente.")
            } else {
                Log.d("SharedPreferences", "La información para ${button.tag} no se ha guardado.")
            }

            // Luego, puedes continuar configurando los OnClickListener y actualizando la imagen del botón como lo hacías antes
            button.setOnClickListener { toggleStarState(button, ModularessharedPreferences) }
            updateStarImage(button, isFavorite)
        }
    }

    private fun toggleStarState(button: ImageButton, sharedPreferences: SharedPreferences) {
        val currentisFavorite = sharedPreferences.getBoolean(button.tag.toString(), false) // Obtener el estado actual del botón
        Log.d("ModularesActivity", "toggleStarState() called")
        Log.d("ToggleButton", "Button ID: ${button.id}, Current State: $currentisFavorite")

        val newIsFavorite = !currentisFavorite // Cambiar el estado del boton

        if (newIsFavorite) {
            // Cambiar a favorito
            button.setImageResource(R.drawable.ic_star)
        } else {
            // Cambiar a no favorito
            button.setImageResource(R.drawable.baseline_star_border_24)
        }



        Log.d("ToggleButton", "Button ID: ${button.id}, New State: $newIsFavorite")


        // Guardar el estado de la estrella en SharedPreferences
        Log.d("SharedPreferences", "Before: Button ID: ${button.id}, isFavorite: $currentisFavorite")
        sharedPreferences.edit().putBoolean(button.tag.toString(), newIsFavorite).apply()
        Log.d("SharedPreferences", "After: Button ID: ${button.id}, isFavorite: $newIsFavorite")

        //Actualizar la etiqueta del boton con el nuevo estado
        button.tag = newIsFavorite
    }
    private fun updateStarImage(button: ImageButton, isFavorite: Boolean) {
        Log.d("UpdateStarImage", "Button ID: ${button.id}")
        Log.d("ModularesActivity", "updateStarImage() called")


        if (isFavorite) {
            // Cambiar a favorito
            button.setImageResource(R.drawable.ic_star)
            Log.d("UpdateStarImage: IF is Favorite", "True Condition")
        } else {
            // Cambiar a no favorito
            button.setImageResource(R.drawable.baseline_star_border_24)
            Log.d("UpdateStarImage: IF is Favorite", "False Condition")
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
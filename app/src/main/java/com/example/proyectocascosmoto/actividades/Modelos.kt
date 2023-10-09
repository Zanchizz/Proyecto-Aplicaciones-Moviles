package com.example.proyectocascosmoto.actividades


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.proyectocascosmoto.R
import com.example.proyectocascosmoto.clasesdatos.Tipo
import android.widget.ImageButton
import android.content.SharedPreferences
import android.content.Context




class Modelos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modelos)

        //lateinit var sharedPreferences: SharedPreferences

        val tipo = intent.getSerializableExtra("tip") as Tipo

        findViewById<TextView>(R.id.amTv1).text = getString(R.string.Modelo1, tipo.modelo1)
        findViewById<TextView>(R.id.amTv2).text = getString(R.string.Modelo2, tipo.modelo2)
        findViewById<TextView>(R.id.amTv3).text = getString(R.string.Modelo3, tipo.modelo3)
        findViewById<TextView>(R.id.amTv4).text = getString(R.string.Modelo4, tipo.modelo4)
        findViewById<TextView>(R.id.amTv5).text = getString(R.string.Modelo5, tipo.modelo5)

        val starButtons: MutableList<ImageButton> = ArrayList()
        starButtons.add(findViewById(R.id.amEstrella1))
        starButtons.add(findViewById(R.id.amEstrella2))
        starButtons.add(findViewById(R.id.amEstrella3))
        starButtons.add(findViewById(R.id.amEstrella4))
        starButtons.add(findViewById(R.id.amEstrella5))

        val starsharedPreferences = getSharedPreferences("StarPrefs", Context.MODE_PRIVATE)



        for (button in starButtons) {
            // Configura un OnClickListener para cada botón de estrella
            button.setOnClickListener { toggleStarState(button, starsharedPreferences) }

            //Verifica que la etiqueta no sea null antes de configurarla
            if (button.tag == null) {
                button.tag = "button_${button.id}"

            }

            //Recupera el estado anterior de cada boton ( si existe)
            val isFavorite = starsharedPreferences.getBoolean(button.tag.toString(), false)
            updateStarImage(button, isFavorite)
        }
    }

    private fun toggleStarState(button: ImageButton, sharedPreferences: SharedPreferences) {
        val isFavorite = button.tag as? Boolean ?: false // Obtener el estado actual del botón

        if (isFavorite) {
            // Cambiar a no favorito
            button.setImageResource(R.drawable.baseline_star_border_24)
        } else {
            // Cambiar a favorito
            button.setImageResource(R.drawable.ic_star)
        }

        // Actualizar el estado del botón
        button.tag = !isFavorite

        // Guardar el estado de la estrella en SharedPreferences
        sharedPreferences.edit().putBoolean(button.tag.toString(), !isFavorite).apply()
    }
    private fun updateStarImage(button: ImageButton, isFavorite: Boolean) {
        val isFavorite = button.tag as? Boolean ?: false

        if (isFavorite) {
            // Cambiar a favorito
            button.setImageResource(R.drawable.ic_star)
        } else {
            // Cambiar a no favorito
            button.setImageResource(R.drawable.baseline_star_border_24)
        }
    }

}

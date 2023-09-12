package com.example.proyectocascosmoto.actividades


import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.proyectocascosmoto.R
import com.example.proyectocascosmoto.R.id.eltTvTipo
import com.example.proyectocascosmoto.clasesdatos.Tipo

class Modelos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modelos)

        val tipo = intent.getSerializableExtra("tip") as Tipo

        findViewById<TextView>(R.id.amTv1).text = getString(R.string.Modelo1, tipo.modelo1)
        findViewById<TextView>(R.id.amTv2).text = getString(R.string.Modelo2, tipo.modelo2)
        findViewById<TextView>(R.id.amTv3).text = getString(R.string.Modelo3, tipo.modelo3)
        findViewById<TextView>(R.id.amTv4).text = getString(R.string.Modelo4, tipo.modelo4)
        findViewById<TextView>(R.id.amTv5).text = getString(R.string.Modelo5, tipo.modelo5)
    }
}
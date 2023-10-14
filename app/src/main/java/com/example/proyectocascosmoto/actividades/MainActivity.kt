package com.example.proyectocascosmoto.actividades

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectocascosmoto.adaptadores.AdaptadorTipos
import com.example.proyectocascosmoto.R
import com.example.proyectocascosmoto.clasesdatos.Tipo
import androidx.appcompat.widget.Toolbar


class MainActivity : AppCompatActivity(), AdaptadorTipos.TipoCascoListener {

    private lateinit var recyclerView: RecyclerView
    lateinit var toolbar: Toolbar

    private val listaTipos: ArrayList<Tipo> = generarDatosPrueba()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title =  resources.getString(R.string.Titulo)

        recyclerView = findViewById(R.id.amRvTipos)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = AdaptadorTipos(generarDatosPrueba(), this)
        recyclerView.adapter = adapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.item_listado){
            val intentTerminos = Intent(this, Terminos::class.java)
            startActivity(intentTerminos)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun generarDatosPrueba() : ArrayList<Tipo>{
        val lista = ArrayList<Tipo>()
        lista.add(Tipo("Abiertos", "601 Bob", "562 Airflow", "599 Spitfire", "570 Verso", "597 Cabrio"))
        lista.add(Tipo("Integrales","352 Rookie", "353 Rapid", "320 Stream", "800 Storm", "805 Thunder"))
        lista.add(Tipo("Modulares", "370 Easy", "902 Scope", "399 Valiant", "900 Valiant II", "903 Valiant III"))
        lista.add(Tipo("MX", "437 Fast", "437 Fast Evo", "436 Pioneer", "700 Subverter Evo", "470 Pioneer Evo"))
        return lista
    }

    override fun onTipoCascoClicked(nombreTipo: String) {
        abrirTipoCascos(nombreTipo)
    }


    private fun obtenerTipoPorNombre(nombre: String, listaTipos: List<Tipo>): Tipo? {
        for (tipo in listaTipos) {
            if (tipo.nombre == nombre) {
                return tipo
            }
        }
        return null
    }


    private fun abrirTipoCascos(tipo: String) {
        when (tipo) {
            "Abiertos" -> {
                val nombreTipoSeleccionado = "Abiertos"
                val tipoSeleccionado = obtenerTipoPorNombre(nombreTipoSeleccionado, listaTipos)
                if (tipoSeleccionado != null) {


                    val intentAbiertos = Intent(this, Abiertos::class.java)
                    intentAbiertos.putExtra("tip", tipoSeleccionado)
                    startActivity(intentAbiertos)
                } else {
                    Log.d("IntentAbiertos", "No se pudo intentAbiertos")
                }
            }

            "Integrales" -> {
                val nombreTipoSeleccionado = "Integrales" // Se puede reutilizar el nombre de variable
                val tipoSeleccionado = obtenerTipoPorNombre(nombreTipoSeleccionado, listaTipos)
                if (tipoSeleccionado != null) {
                    val intentIntegrales = Intent(this, Integrales::class.java)
                    intentIntegrales.putExtra("tip", tipoSeleccionado)
                    startActivity(intentIntegrales)
                } else {
                    Log.d("IntentIntegrales", "No se pudo IntentIntegrales")
                }
            }

            "Modulares" -> {
                val nombreTipoSeleccionado = "Modulares" // Se puede reutilizar el nombre de variable
                val tipoSeleccionado = obtenerTipoPorNombre(nombreTipoSeleccionado, listaTipos)
                if (tipoSeleccionado != null) {
                    val intentModulares = Intent(this, Modulares::class.java)
                    intentModulares.putExtra("tip", tipoSeleccionado)
                    startActivity(intentModulares)
                } else {
                    Log.d("IntentModulares", "No se pudo IntentModulares")
                }
            }

            "MX" -> {
                val nombreTipoSeleccionado = "MX" // Se puede reutilizar el nombre de variable
                val tipoSeleccionado = obtenerTipoPorNombre(nombreTipoSeleccionado, listaTipos)
                if (tipoSeleccionado != null) {
                    val intentMX = Intent(this, MXActivity::class.java)
                    intentMX.putExtra("tip", tipoSeleccionado)
                    startActivity(intentMX)
                } else {
                    Log.d("IntentModulares", "No se pudo IntentModulares")
                }
            }
            // Agrega más tipos aquí según sea necesario
            else -> {
                // Manejar casos no previstos
            }
        }
    }
}

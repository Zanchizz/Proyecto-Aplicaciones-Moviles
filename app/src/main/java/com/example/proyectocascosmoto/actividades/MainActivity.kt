package com.example.proyectocascosmoto.actividades

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectocascosmoto.adaptadores.AdaptadorTipos
import com.example.proyectocascosmoto.R
import com.example.proyectocascosmoto.clasesdatos.Tipo
import androidx.appcompat.widget.Toolbar


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = resources.getString(R.string.Titulo)

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
}

package com.example.proyectocascosmoto.actividades


import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectocascosmoto.R
import com.example.proyectocascosmoto.configuracion.RetrofitClient
import com.example.proyectocascosmoto.dtos.Post
import com.example.proyectocascosmoto.endpoints.MyApi
import retrofit2.Call
import retrofit2.Response


class Terminos: AppCompatActivity() {

    private lateinit var tvServicioRest : TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.terminos)

        //-----------------------------IMPLEMENTEACION API REST--------------------------------

        //LLAMO A LA API CON LA INFO QUE SE MUESTRA EN TERMINOS Y CONDICIONES
        val api = RetrofitClient.retrofit.create(MyApi::class.java)
        val callPost = api.getPost()

        callPost.enqueue(object : retrofit2.Callback<List<Post>>


        {

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                val post = response.body()

                if (post != null){
                    tvServicioRest = findViewById(R.id.tvServicioRest)
                    tvServicioRest.text = post.toString()

                }
            }

            //SI FALLA...
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.e("ERROR",t.message?:"")
            }

        })

       //-----------------------------FIN IMPLEMENTEACION API REST--------------------------------


        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // Habilita el botón de retroceso.
        supportActionBar?.title = "" // Establece el título de la Toolbar.

        /*
        // Cargar el texto de los términos desde el recurso de cadena
        val termsTextView = findViewById<TextView>(R.id.tvTerminos)
        termsTextView.text = getString(R.string.terminos)

         */
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed() // Esta línea permite que el botón de retroceso funcione.
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}

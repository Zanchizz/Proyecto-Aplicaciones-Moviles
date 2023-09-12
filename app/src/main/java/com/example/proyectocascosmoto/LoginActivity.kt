package com.example.proyectocascosmoto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.proyectocascosmoto.actividades.MainActivity


class LoginActivity : AppCompatActivity() {

    // Variables para vincular con la vista
    lateinit var etUsuario: EditText
    lateinit var etPass: EditText
    lateinit var cbRecordar: CheckBox
    lateinit var btnRegistrar: Button
    lateinit var btnIniciar: Button
    lateinit var toolbar: Toolbar

    // Funcion que se ejecuta al iniciar un Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Vinculamos las variables con los IDs correspondientes a la vista
        etUsuario = findViewById(R.id.etUsuario)
        etPass = findViewById(R.id.etPass)
        cbRecordar = findViewById(R.id.cbRecordar)
        btnIniciar = findViewById(R.id.botonIniciar)
        btnRegistrar = findViewById(R.id.botonRegistrar)

        var preferencias = getSharedPreferences(resources.getString((R.string.sp_credenciales)), MODE_PRIVATE)
        var usuarioGuardado = preferencias.getString(resources.getString(R.string.nombre_usuario), "")
        var passwordGuardado = preferencias.getString(resources.getString(R.string.password_usuario), "")

        Log.d("LoginActivity", "Usuario guardado: $usuarioGuardado")
        Log.d("LoginActivity", "Contraseña guardada: $passwordGuardado")

        etUsuario.setText(usuarioGuardado)


        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = resources.getString(R.string.Titulo)

        //registrar usuario
        //Agregamos funcionalidad al Boton
        btnRegistrar.setOnClickListener {
            // Mostramos un mensaje
            Toast.makeText(this, "Registro de Usuarios", Toast.LENGTH_SHORT).show()

            val pantallaRegistrar = Intent(this, RegisterActivity::class.java)
            // Cambiamos de pantalla
            startActivity(pantallaRegistrar)
            // Eliminamos la Activity actual para sacarla de la Pila

        }


        //INICIAR SESION
        //Agregamos funcionalidad al Boton
        btnIniciar.setOnClickListener {
            var mensaje = "Iniciar Sesion"
            // Obtenemos el dato que se ingreso en la vista
            var nombreUsuario = etUsuario.text.toString()
            var passwordUsuario = etPass.text.toString()
            if (nombreUsuario.isEmpty() || etPass.text.toString().isEmpty()) {
                Toast.makeText(this,"FALTAN COMPLETAR DATOS",Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this,"INICIO DE SESION EXITOSO",Toast.LENGTH_SHORT).show()
                // Verificamos si esta tildado el CechBox
                    if (cbRecordar.isChecked) {
                        var preferencias = getSharedPreferences(
                        resources.getString((R.string.sp_credenciales)),
                        MODE_PRIVATE)

                        preferencias.edit().putString(resources.getString(R.string.nombre_usuario), nombreUsuario).apply()

                        preferencias.edit().putString(resources.getString(R.string.password_usuario), passwordUsuario).apply()

                }

                    startMainActivity(nombreUsuario)
            }
        }

    }
    private fun startMainActivity(usuarioGuardado: String) {
        // Indicamos a que pantalla queremos ir
        val intentMain = Intent(this, MainActivity::class.java)
        // Agregamos datos que queremos pasar a la proxima pantalla
        intentMain.putExtra(resources.getString(R.string.nombre_usuario), usuarioGuardado)
        // Cambiamos de pantalla
        startActivity(intentMain)
        // Eliminamos la Activity actual para sacarla de la Pila

    }
}

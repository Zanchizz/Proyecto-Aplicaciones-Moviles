package com.example.proyectocascosmoto

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
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
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Build
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.getSystemService
import android.provider.Settings




class LoginActivity : AppCompatActivity() {

    // Variables para vincular con la vista
    lateinit var etUsuario: EditText
    lateinit var etPass: EditText
    lateinit var cbRecordar: CheckBox
    lateinit var btnRegistrar: Button
    lateinit var btnIniciar: Button
    lateinit var toolbar: Toolbar


    private val notificationManager by lazy { getSystemService<NotificationManager>() }
    private val channelId = "MyChannelId"
    private val CODIGO_DE_PERMISO = 123

    private val preferencias by lazy {
        getSharedPreferences(
            resources.getString(R.string.sp_credenciales),
            MODE_PRIVATE
        )
    }


    // Funcion que se ejecuta al iniciar un Activity
    @SuppressLint("NewApi")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Vinculamos las variables con los IDs correspondientes a la vista
        etUsuario = findViewById(R.id.etUsuario)
        etPass = findViewById(R.id.etPass)
        cbRecordar = findViewById(R.id.cbRecordar)
        btnIniciar = findViewById(R.id.botonIniciar)
        btnRegistrar = findViewById(R.id.botonRegistrar)

        // var preferencias = getSharedPreferences(resources.getString((R.string.sp_credenciales)), MODE_PRIVATE)
        // var usuarioGuardado = preferencias.getString(resources.getString(R.string.nombre_usuario), "")
        var passwordGuardado = preferencias.getString(resources.getString(R.string.password_usuario), "")

        crearCanalNotificaciones()


        val usuarioGuardado = obtenerUsuario()
        etUsuario.setText(usuarioGuardado)

        Log.d("LoginActivity", "Usuario guardado: $usuarioGuardado")
        Log.d("LoginActivity", "Contraseña guardada: $passwordGuardado")

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
            // Obtenemos el dato que se ingreso en la vista
            var nombreUsuario = etUsuario.text.toString()
            var passwordUsuario = etPass.text.toString()
            if (nombreUsuario.isEmpty() || etPass.text.toString().isEmpty()) {
                Toast.makeText(this,"FALTAN COMPLETAR DATOS",Toast.LENGTH_SHORT).show()
            } else {

                // Verificamos si esta tildado el CechBox
                if (cbRecordar.isChecked) {

                    Toast.makeText(this, "INICIO DE SESION EXITOSO", Toast.LENGTH_SHORT).show()

                    preferencias.edit()
                        .putString(resources.getString(R.string.nombre_usuario), nombreUsuario)
                        .apply()

                    preferencias.edit()
                        .putString(resources.getString(R.string.password_usuario), passwordUsuario)
                        .apply()


                    if (areNotificationPermissionsGranted()) {
                        Log.d("Notificacion", "Notificación enviada desde aquí")
                        enviarNotificacion(
                            "Usuario Guardado con Exito",
                            "¡Bienvenido/a, $nombreUsuario!"
                        )
                    } else {
                        ActivityCompat.requestPermissions(
                            this,
                            arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                            CODIGO_DE_PERMISO
                        )

                    }
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

    private fun obtenerUsuario(): String {
        return preferencias.getString(resources.getString(R.string.nombre_usuario), "") ?: ""
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun crearCanalNotificaciones() {
        val name = "My Channel"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelId, name, importance)
        notificationManager?.createNotificationChannel(channel)
    }
    //@SuppressLint("MissingPermission")
    private fun enviarNotificacion(title: String, content: String) {
        Log.d("Notificacion", "Enviando notificación: title=$title, content=$content")
            val builder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            with(NotificationManagerCompat.from(this)) {
                if (ActivityCompat.checkSelfPermission(
                        this@LoginActivity,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return
                }
                notify(1, builder.build())
            }
    }





    private fun areNotificationPermissionsGranted(): Boolean {
        val notificationManager = getSystemService<NotificationManager>()
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager?.notificationChannels?.any { it.importance != NotificationManager.IMPORTANCE_NONE } == true
        } else {
            true // Si el dispositivo no es Android Oreo o superior, se asume que tiene permisos.
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_login, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_settings) {
            abrirConfiguracionNotificaciones()

        }
        return super.onOptionsItemSelected(item)
    }

    private fun abrirConfiguracionNotificaciones() {
        val intent = Intent()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            intent.action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
        } else {
            intent.action = "android.settings.APP_NOTIFICATION_SETTINGS"
            intent.putExtra("app_package", packageName)
            intent.putExtra("app_uid", applicationInfo.uid)
        }
        startActivity(intent)
    }

}

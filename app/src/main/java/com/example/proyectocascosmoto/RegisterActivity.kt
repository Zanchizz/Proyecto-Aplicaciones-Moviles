package com.example.proyectocascosmoto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.proyectocascosmoto.actividades.MainActivity

class RegisterActivity : AppCompatActivity() {

    lateinit var etNombreApe: EditText
    lateinit var etCorreo: EditText
    lateinit var etPass: EditText
    lateinit var etPassRep: EditText
    lateinit var btnCrear: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        etNombreApe= findViewById(R.id.ETnombreApellido)
        etCorreo = findViewById(R.id.ETcorreo)
        etPass = findViewById(R.id.ETpass)
        etPassRep= findViewById(R.id.ETpassRep)
        btnCrear = findViewById(R.id.BTNcrear)


        btnCrear.setOnClickListener {

            var nombre_y_apellidos = etNombreApe.text.toString()
            var correo = etCorreo.text.toString()
            var contraseña = etPass.text.toString()
            var contraRep =etPassRep.toString()


            if(contraseña.equals(contraRep) ){

                Toast.makeText(this,"LAS CONSTRASEÑAS NO COINCIDEN",Toast.LENGTH_SHORT).show()

            }


            if (nombre_y_apellidos.isEmpty() || correo.isEmpty() || contraseña.isEmpty() ){


                Toast.makeText(this,"FALTAN COMPLETAR DATOS",Toast.LENGTH_SHORT).show()

            }else{

                    var NuevoUsuario = Usuario(nombre_y_apellidos,correo,contraseña)
                    AppDataBase.getDatabase(this).usuarioDao().insertUsuarios(NuevoUsuario)
                    Toast.makeText(this,"REGISTRADO CON EXITO",Toast.LENGTH_SHORT).show()

                    //una vez registrados nos movemos a la pantalla principal
                    val intentMain = Intent(this, MainActivity::class.java)
                    // Cambiamos de pantalla
                    startActivity(intentMain)

            }

        }

    }
}
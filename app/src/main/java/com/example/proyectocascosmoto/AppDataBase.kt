package com.example.proyectocascosmoto

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Usuario::class], version = 1)
abstract class AppDataBase: RoomDatabase(){

        //por si llega a modificarse la base de datos

        abstract fun usuarioDao():UsuarioDao
        companion object{

            private var INSTANCIA: AppDataBase?=null
            fun getDatabase (contexto: Context): AppDataBase{

                //en caso de ser null creo la base de datos
                if (INSTANCIA == null){

                    synchronized(this){

                        INSTANCIA = Room.databaseBuilder(

                            //construimos la base de datos
                            contexto,AppDataBase::class.java,"base_app_usuarios")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build()

                    }


                }
                return  INSTANCIA!!
            }



        }


}
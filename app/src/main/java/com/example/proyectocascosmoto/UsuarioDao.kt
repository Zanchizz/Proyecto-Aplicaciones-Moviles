package com.example.proyectocascosmoto

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.selects.select


@Dao
interface UsuarioDao {

    // para consultar todos los registros almacenados en la tabla
    @Query("select * from usuariosTabla")
    fun getALL(): List<Usuario>

    // para consultar un unico registro
    @Query("select * from usuariosTabla where NombreUsuario = :nombreAux")
    fun getNombre(nombreAux: String): Usuario



    @Insert
    fun insertUsuarios (usuario: Usuario)


}
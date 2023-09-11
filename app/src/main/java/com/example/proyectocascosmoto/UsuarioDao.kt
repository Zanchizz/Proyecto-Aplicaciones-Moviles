package com.example.proyectocascosmoto

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.selects.select


@Dao
interface UsuarioDao {

    @Query("select * from usuariosTabla")
    fun getALL(): List<Usuario>

    @Insert
    fun insertUsuarios (usuario: Usuario)

}
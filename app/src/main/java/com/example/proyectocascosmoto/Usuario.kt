package com.example.proyectocascosmoto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity (tableName = "usuariosTabla")
data class Usuario(

    @ColumnInfo(name = "NombreUsuario") var NombreUsuario: String,
    @ColumnInfo(name = "Correo") var Correo: String,
    @ColumnInfo(name = "Contraseña") var Contraseña: String,


    ){

    @PrimaryKey (autoGenerate = true) var id: Int = 0


}

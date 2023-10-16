package com.example.proyectocascosmoto.endpoints

import com.example.proyectocascosmoto.dtos.Post
import retrofit2.Call
import retrofit2.http.GET

interface MyApi {


    @GET("/comments")
    fun getPost() : Call<List<Post>>

}
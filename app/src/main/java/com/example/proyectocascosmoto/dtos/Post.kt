package com.example.proyectocascosmoto.dtos

import com.squareup.moshi.JsonClass
/*
  {
    "postId": 1,
    "id": 1,
    "name": "id labore ex et quam laborum",
    "email": "Eliseo@gardner.biz",
    "body": "laudantium enim quasi est quidem magnam voluptate ipsam eos\ntempora quo necessitatibus\ndolor quam autem quasi\nreiciendis et nam sapiente accusantium"
  }

*/

//serializar/deserealizar la informacion
@JsonClass (generateAdapter = true)
data class Post(
    var postId: Int?,
    var id: Int,
    var name: String,
    var body: String


)

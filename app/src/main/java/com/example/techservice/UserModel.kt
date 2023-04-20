package com.example.techservice

data class UserModel(
    val username: String,
    val password: String,
    val isadmin: Int = 0,
    val id: Int = 0
){}

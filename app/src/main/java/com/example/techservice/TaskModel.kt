package com.example.techservice

data class TaskModel(
    val client: String,
    val email: String,
    val details: String,
    val status: String,
    val id: Int = 0
){}

package com.example.techservice

data class TaskModel(
    val client: String,
    val email: String,
    val details: String,
    val date: String,
    val id: Int = 0
){}

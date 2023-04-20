package com.example.techservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var sqLiteHelper: SQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        sqLiteHelper = SQLiteHelper(this)
    }
    private fun addTask(){

        val model = TaskModel(client = "Stefan", email = "123@gmai.com", details = "haslo na plycie glownej", status = "Tak")
        val status = sqLiteHelper.addTASK(model)
        if(status > -1){
            Toast.makeText(this, "Zlecenie dodane!", Toast.LENGTH_SHORT).show()
            finish()
        }else {
            Toast.makeText(this, "Blad!", Toast.LENGTH_SHORT).show()
        }
    }
}
package com.example.techservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class loggedasadmin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loggedasadmin)
        //hide action bar
        supportActionBar?.hide()
        val logoutbtn = findViewById<Button>(R.id.admin_view_logout)
        val addservicebtn = findViewById<Button>(R.id.admin_view_add_service)
        logoutbtn.setOnClickListener {
            Toast.makeText(this,"Wylogowano!", Toast.LENGTH_SHORT).show()
            finish()
        }
        addservicebtn.setOnClickListener {
            val intent = Intent(this, admin_add_service::class.java)
            startActivity(intent)
        }
    }
}
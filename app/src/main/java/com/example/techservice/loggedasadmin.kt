package com.example.techservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class loggedasadmin : AppCompatActivity() {

    private lateinit var sqLiteHelper: SQLiteHelper
    private lateinit var recyclerView: RecyclerView
    private var adapter: AdminAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loggedasadmin)
        //hide action bar
        supportActionBar?.hide()

        recyclerView = findViewById(R.id.Recycler_admin_view)
        initRecyclerView()
        sqLiteHelper = SQLiteHelper(this)
        getTasks()

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

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = AdminAdapter()
        recyclerView.adapter = adapter
    }

    private fun getTasks() {
        val TaskList = sqLiteHelper.getTASKS()
        adapter?.addItems(TaskList)
    }
}
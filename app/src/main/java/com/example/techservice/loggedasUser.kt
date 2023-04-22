package com.example.techservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class loggedasUser : AppCompatActivity() {

    private lateinit var sqLiteHelper: SQLiteHelper
    private lateinit var recyclerView: RecyclerView
    private var adapter: UserAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loggedas_user)
        supportActionBar?.hide()

        val logoutbtn = findViewById<Button>(R.id.user_recycler_logout)

        logoutbtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        recyclerView = findViewById(R.id.user_recycler_view)
        initRecyclerView()
        sqLiteHelper = SQLiteHelper(this)
        getTasks()
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = UserAdapter()
        recyclerView.adapter = adapter
    }

    private fun getTasks() {
        val TaskList = sqLiteHelper.getTASKS()
        adapter?.addItems(TaskList)
    }
}
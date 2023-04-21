package com.example.techservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call.Details
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class admin_add_service : AppCompatActivity() {

    lateinit var editName: EditText
    lateinit var editEmail: EditText
    lateinit var editDetails: EditText

    private lateinit var sqLiteHelper: SQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_add_service)
        //views init
        initViews()
        //sqlite init
        sqLiteHelper = SQLiteHelper(this)
        //hide action bar
        supportActionBar?.hide()
        //buttons
        val cancelBtn = findViewById<Button>(R.id.cancelButton)
        val addBtn = findViewById<Button>(R.id.addButton)
        //listeners
        cancelBtn.setOnClickListener {
            finish()
        }
        addBtn.setOnClickListener {
            addTask()
        }

    }

    private fun initViews(){
        editName = findViewById(R.id.Add_client_name)
        editEmail = findViewById(R.id.Add_client_email)
        editDetails = findViewById(R.id.Add_client_details)
    }
    //function that transfer data from edit text to a model and into database
    private fun addTask(){
        val name = editName.text.toString()
        val email = editEmail.text.toString()
        val details = editDetails.text.toString()
        if(name.isEmpty() || email.isEmpty() || details.isEmpty()){
            Toast.makeText(this,"Wpisz wszystkie potrzebne informacje!", Toast.LENGTH_SHORT).show()
        }else{
            val task = AddModel(client = name, email = email, details = details)
            val status = sqLiteHelper.addTASK(task)
            if(status > -1){
                Toast.makeText(this,"Task zlecony!", Toast.LENGTH_SHORT).show()
                finish()
            }else{
                Toast.makeText(this,"Błąd!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
package com.example.techservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var sqLiteHelper: SQLiteHelper
    lateinit var username: EditText
    lateinit var password: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        val loginbtn = findViewById<Button>(R.id.loginbutton)
        val exitbtn = findViewById<Button>(R.id.exitbutton)

        sqLiteHelper = SQLiteHelper(this)
        loginbtn.setOnClickListener {
            login()
        }
        exitbtn.setOnClickListener {
            finish()
        }
    }
    private fun addTask(){
        //to trzeba przeniesc pozniej
        val model = TaskModel(client = "Stefan", email = "123@gmai.com", details = "haslo na plycie glownej", status = "Tak")
        val status = sqLiteHelper.addTASK(model)
        if(status > -1){
            Toast.makeText(this, "Zlecenie dodane!", Toast.LENGTH_SHORT).show()
            finish()
        }else {
            Toast.makeText(this, "Blad!", Toast.LENGTH_SHORT).show()
        }
    }
    private fun addUser(){
        //ta funkcje dodalem admin i usera zwyklego
        val model = UserModel(username = "admin", password = "admin", isadmin = 0)
        val status = sqLiteHelper.addUSER(model)
        if(status > -1){
            Toast.makeText(this, "Uzytkownik dodany!", Toast.LENGTH_SHORT).show()
            finish()
        }else {
            Toast.makeText(this, "Blad!", Toast.LENGTH_SHORT).show()
        }
    }
    private fun login(){
        //trzeba poprawic bo if nie dziala
        val userlist = sqLiteHelper.getALLUSERS()
        username = findViewById(R.id.login)
        password = findViewById(R.id.password)
        val usernametext = (username.text).toString()
        val passwordtext = (password.text).toString()
        if(userlist.contains(UserModel(username = usernametext, password = passwordtext))){
            Toast.makeText(this,"Zalogowano", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, loggedasUser::class.java)
            startActivity(intent)
        }else{
            Toast.makeText(this,"Niepoprawna nazwa użytkownika lub hasło!", Toast.LENGTH_SHORT).show()
            username.setText("")
            password.setText("")
        }
    }
}
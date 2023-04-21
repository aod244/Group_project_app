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
    lateinit var usernameEditText: EditText
    lateinit var passwordEditText: EditText

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
    private fun addUser(){
        //ta funkcje dodalem admin i usera zwyklego
        val model = UserModel(username = "admin", password = "admin")
        val status = sqLiteHelper.addUSER(model)
        if(status > -1){
            Toast.makeText(this, "Uzytkownik dodany!", Toast.LENGTH_SHORT).show()
            finish()
        }else {
            Toast.makeText(this, "Blad!", Toast.LENGTH_SHORT).show()
        }
    }
    private fun login(){
        val userlist = sqLiteHelper.getALLUSERS()
        usernameEditText = findViewById(R.id.login)
        passwordEditText = findViewById(R.id.password)
        val usernametext = (usernameEditText.text).toString()
        val passwordtext = (passwordEditText.text).toString()
        val inputlist = UserModel(username = usernametext, password = passwordtext)
        fun checkIfUserExist(): Boolean {
            return userlist[0] == inputlist || userlist[1] == inputlist
        }
        val validator = checkIfUserExist()
        if(validator){
            if(usernametext == "user"){
                Toast.makeText(this,"Zalogowano", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, loggedasUser::class.java)
                startActivity(intent)
            }else if(usernametext == "admin"){
                Toast.makeText(this,"Zalogowano", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, loggedasadmin::class.java)
                startActivity(intent)
            }
        }else{
            Toast.makeText(this,"Niepoprawna nazwa użytkownika lub hasło!", Toast.LENGTH_SHORT).show()

            usernameEditText.setText("")
            passwordEditText.setText("")

        }
    }
}
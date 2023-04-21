package com.example.techservice

import android.content.Context
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        //nazwa i wersja bd
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "TechServ.db"
        //nazwy tabel
        const val TABLE1 = "Users"
        const val TABLE2 = "Tasks"
        const val TABLE3 = "DoneTasks"
        //kolumny we wszystkich tabelach
        const val ID = "id"
        const val DATE = "data"
        //kolumny dla tab1
        const val USERNAME = "nazwaUzytkownika"
        const val PASSWORD = "haslo"
        // jesli isadmin = 1 to ma uprawnienia admina
        const val ISADMIN = "uprawnieniaAdmina"
        //kolumny dla tab2 i tab3
        const val CLIENTNAME = "nazwaKlienta"
        const val CLIENTMAIL = "mailKlienta"
        const val TASKDETAILS = "szczegolyZlecenia"
        const val ISTASKFINISHED = "czySkonczone"
        //kolumny dla tab3
        const val FINISHDATE = "dataZakonczenia"
        const val PRICE = "cenaUslugi"

    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable1 = ("CREATE TABLE " + TABLE1 + "(" + ID +  " INTEGER PRIMARY KEY AUTOINCREMENT, " + USERNAME + " TEXT, " + PASSWORD + " TEXT, " + ISADMIN + " TEXT" + ")")
        db.execSQL(createTable1)
        val createTable2 = ("CREATE TABLE " + TABLE2 + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CLIENTNAME + " TEXT, " + CLIENTMAIL + " TEXT," + TASKDETAILS + " TEXT, " + ISTASKFINISHED + " TEXT, " + DATE + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL" + ")")
        db.execSQL(createTable2)
        val createTable3 = ("CREATE TABLE " + TABLE3 + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CLIENTNAME + " TEXT, " + CLIENTMAIL + " TEXT, " + TASKDETAILS + " TEXT, " + FINISHDATE  + " TEXT, " + PRICE + " TEXT" + ")")
        db.execSQL(createTable3)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE1")
        db.execSQL("DROP TABLE IF EXISTS $TABLE2")
        db.execSQL("DROP TABLE IF EXISTS $TABLE3")
        onCreate(db)
    }

    fun addTASK(model: TaskModel): Long {
        val db = this.writableDatabase

        val contentValues = ContentValues()

        contentValues.put(CLIENTNAME, model.client)
        contentValues.put(CLIENTMAIL, model.email)
        contentValues.put(TASKDETAILS, model.details)
        contentValues.put(ISTASKFINISHED, model.status)

        val success = db.insert(TABLE2, null, contentValues)
        db.close()
        return success

    }

    fun addUSER(model: UserModel): Long {
        val db = this.writableDatabase

        val contentValues = ContentValues()

        contentValues.put(USERNAME, model.username)
        contentValues.put(PASSWORD, model.password)

        val success = db.insert(TABLE1, null, contentValues)
        db.close()
        return success

    }

    fun getALLUSERS(): ArrayList<UserModel> {
        val userList: ArrayList<UserModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TABLE1"
        val db = this.readableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var user: String
        var password: String

        if (cursor.moveToFirst()){
            do{
                user = cursor.getString(1)
                password = cursor.getString(2)

                val model = UserModel(username = user, password = password)
                userList.add(model)
            }
            while (cursor.moveToNext())
        }

        return userList
    }
}
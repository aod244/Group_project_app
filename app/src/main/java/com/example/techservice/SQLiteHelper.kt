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
        const val USERNAME = "NazwaUzytkownika"
        const val PASSWORD = "Haslo"
        const val ISADMIN = "UprawnieniaAdmina"
        //kolumny dla tab2 i tab3
        const val CLIENTNAME = "NazwaKlienta"
        const val CLIENTMAIL = "MailKlienta"
        const val TASKDETAILS = "SzczegolyZlecenia"
        const val ISTASKFINISHED = "CzySkonczone"
        //kolumny dla tab3
        const val FINISHDATE = "DataZakonczenia"
        const val PRICE = "CenaUslugi"

    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable1 = ("CREATE TABLE " + TABLE1 + "(" + ID +  " INTEGER PRIMARY KEY AUTOINCREMENT,"
               + USERNAME + " TEXT," + PASSWORD + " TEXT," + ISADMIN + " INTEGER" + ")");
        db.execSQL(createTable1)
        val createTable2 = ("CREATE TABLE " + TABLE2 + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + CLIENTNAME + " TEXT," + CLIENTMAIL + " TEXT," + TASKDETAILS + " TEXT,"
                + ISTASKFINISHED + " TEXT," + DATE + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL" + ")");
        db.execSQL(createTable2)
        val createTable3 = ("CREATE TABLE " + TABLE3 + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + CLIENTNAME + " TEXT," + CLIENTMAIL + " TEXT," + TASKDETAILS + " TEXT," + FINISHDATE
                + " TEXT," + PRICE + " TEXT" + ")");
        db.execSQL(createTable3)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE1")
        db.execSQL("DROP TABLE IF EXISTS $TABLE2")
        db.execSQL("DROP TABLE IF EXISTS $TABLE3")
        onCreate(db)
    }
}
package com.example.androidinformationofdien

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase


class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE $TABLE_NAME (ID INTEGER PRIMARY KEY " +
                "AUTOINCREMENT,NAME TEXT,EMAIL TEXT,ADDRESS TEXT)")
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun insertData(id: String, name: String, email: String, address: String) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_ID, id)
        contentValues.put(COL_NAME, name)
        contentValues.put(COL_EMAIL, email)
        contentValues.put(COL_ADDRESS, address)
        db.insert(TABLE_NAME, null, contentValues)
    }


    fun updateData(id: String, name: String, email: String, address: String):
            Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_ID, id)
        contentValues.put(COL_NAME, name)
        contentValues.put(COL_EMAIL, email)
        contentValues.put(COL_ADDRESS, address)
        db.update(TABLE_NAME, contentValues, "ID = ?", arrayOf(id))
        return true
    }


    fun deleteData(id : String) : Int {
        val db = this.writableDatabase
        return db.delete(TABLE_NAME,"ID = ?", arrayOf(id))
    }


    val allData : Cursor
        get() {
            val db = this.writableDatabase
            val res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null)
            return res
        }


    companion object {
        val DATABASE_NAME = "thigiuaki.db"
        val TABLE_NAME = "thongtin"
        val COL_ID = "ID"
        val COL_NAME = "NAME"
        val COL_EMAIL = "EMAIL"
        val COL_ADDRESS = "ADDRESS"
    }
}
//end
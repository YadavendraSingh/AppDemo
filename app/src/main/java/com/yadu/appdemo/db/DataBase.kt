package com.yadu.appdemo.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.yadu.appdemo.model.User

class DataBase(context: Context?): SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {

    val TAG ="MainActivity"
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "UserDB"
        private val TABLE_USER = "User"
        private const val KEY_ID = "id"
        private val KEY_NAME = "name"
        private val KEY_ADDRESS = "address"
    }


    override fun onCreate(db: SQLiteDatabase?) {
        //creating table with fields
        val createUserTable = ("CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_ADDRESS + " TEXT" + ")")
        db?.execSQL(createUserTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    //method to add data
    fun addUser(user: User):Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, user.userId)
        contentValues.put(KEY_NAME, user.userName) // EmpModelClass Name
        contentValues.put(KEY_ADDRESS,user.userAddress ) // EmpModelClass Phone
        // Inserting Row
        val success = db.insert(TABLE_USER, null, contentValues)
        db.close() // Closing database connection
        Log.d(TAG, "${user.userName} ${user.userAddress} inserted")
        return success
    }

    //method to read data
    fun getAllUsers():List<User>{
        val empList:ArrayList<User> = ArrayList<User>()
        val selectQuery = "SELECT  * FROM $TABLE_USER"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var userId: Int
        var userName: String
        var userAddress: String
        if (cursor.moveToFirst()) {
            do {
                userId = cursor.getInt(cursor.getColumnIndex("id"))
                userName = cursor.getString(cursor.getColumnIndex("name"))
                userAddress = cursor.getString(cursor.getColumnIndex("address"))
                val emp= User(userId = userId, userName = userName, userAddress = userAddress)
                empList.add(emp)
            } while (cursor.moveToNext())
        }
        return empList
    }
}
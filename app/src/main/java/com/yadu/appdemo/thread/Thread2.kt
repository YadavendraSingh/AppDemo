package com.yadu.appdemo.thread

import android.util.Log
import com.yadu.appdemo.db.DataBase

class Thread2(val databaseHandler: DataBase): Thread() {
    val TAG = "MainActivity"
    public override fun run() {
        var userList = databaseHandler.getAllUsers()
        for(user in userList){
            Log.d(TAG, "${user.userName} ${user.userAddress}")
        }
    }
}
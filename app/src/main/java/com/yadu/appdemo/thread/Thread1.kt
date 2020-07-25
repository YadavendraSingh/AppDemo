package com.yadu.appdemo.thread

import com.yadu.appdemo.db.DataBase
import com.yadu.appdemo.model.User
import java.util.*

class Thread1(val databaseHandler: DataBase): Thread() {

    public override fun run() {
        Thread.sleep(1000);
        databaseHandler.addUser(getRandomUser())
        databaseHandler
    }

    fun getRandomUser(): User {
        val random = Random()
        var num = random.nextInt(100)
        return User(userId = num, userName = "User$num", userAddress = "$num street ${num+5}")
    }
}
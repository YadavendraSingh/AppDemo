package com.yadu.appdemo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yadu.appdemo.R
import com.yadu.appdemo.db.DataBase
import com.yadu.appdemo.thread.Thread2


class FirstFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //creating the instance of DatabaseHandler class
        val databaseHandler: DataBase= DataBase(activity)

        val thread2 = Thread2(databaseHandler)
        thread2.run()
    }

}

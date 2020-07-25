package com.yadu.appdemo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yadu.appdemo.R
import com.yadu.appdemo.db.DataBase
import com.yadu.appdemo.thread.Thread1

class SecondFragment : Fragment() {

    val TAG = "MainActivity"

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val databaseHandler: DataBase = DataBase(activity)
        val thread1 = Thread1(databaseHandler)
        thread1.run()
    }
}

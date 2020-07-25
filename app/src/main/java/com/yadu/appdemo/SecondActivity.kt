package com.yadu.appdemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.material.snackbar.Snackbar
import com.yadu.appdemo.data.Constants
import com.yadu.appdemo.data.Constants.WeatherData
import com.yadu.appdemo.model.Current
import com.yadu.appdemo.network.DataService
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.content_second.*


class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        setSupportActionBar(toolbar)

        registerReceiver()

        fab.setOnClickListener { view ->
            startDownload()
        }
    }

    private fun startDownload() {
        val intent = Intent(this, DataService::class.java)
        intent.action = WeatherData
        startService(intent)
    }

    private fun registerReceiver() {
        val bManager = LocalBroadcastManager.getInstance(this)
        val intentFilter = IntentFilter()
        intentFilter.addAction(WeatherData)
        bManager.registerReceiver(broadcastReceiver, intentFilter)
    }
    private val broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            if (intent.action == WeatherData) {
                val current: Current = intent.getSerializableExtra("current")as Current
                todayTemperature.setText("${current.temp.toInt()} °C")
                todayDescription.setText("${current.weather[0].description}")
                todayHumidity.setText("Humidity: ${current.humidity} %")
                todayPressure.setText("Pressure: ${current.pressure} hpa")
                todayWind.setText("Wind: ${current.windSpeed} m/s")
                weather.setText("Weather")
            }
        }
    }
}

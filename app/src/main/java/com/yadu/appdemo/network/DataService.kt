package com.yadu.appdemo.network

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.yadu.appdemo.MainActivity
import com.yadu.appdemo.SecondActivity
import com.yadu.appdemo.data.Constants
import com.yadu.appdemo.data.api.ApiClient
import com.yadu.appdemo.data.api.ApiInterface
import com.yadu.appdemo.model.Current
import com.yadu.appdemo.model.Weather
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

val lat = 28.45952
val lon = 77.0266
val TAG = "Weather Service"
class DataService : IntentService("DataService") {

    override fun onHandleIntent(intent: Intent?) {

        when (intent?.action) {
            Constants.WeatherData -> {
                //val param1 = intent.getStringExtra(EXTRA_PARAM1)
                //val param2 = intent.getStringExtra(EXTRA_PARAM2)
                handleActionWeather(lat, lon)
            }

        }
    }

    private fun handleActionWeather(lat: Double, lon: Double) {
        val apiService: ApiInterface =
            ApiClient.getClient()

        val call: Call<Weather> = apiService.getWeatherResults(lat, lon)
        call.enqueue(object : Callback<Weather?> {
            override fun onResponse(
                call: Call<Weather?>?,
                response: Response<Weather?>
            ) {
                val current:Current = response.body()!!.current
                sendIntent(current)
                Log.d(TAG, "Weather: ${current.toString()}")
            }

            override fun onFailure(call: Call<Weather?>?, t: Throwable) {
                // Log error here since request failed
                Log.e(TAG, t.toString())
            }
        })
    }

    private fun sendIntent(current: Current) {
        val intent = Intent(Constants.WeatherData)
        intent.putExtra("current", current)
        LocalBroadcastManager.getInstance(this@DataService).sendBroadcast(intent)
    }

}

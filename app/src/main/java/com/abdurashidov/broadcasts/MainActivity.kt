package com.abdurashidov.broadcasts

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.abdurashidov.broadcasts.utils.NetworkHelper

class MainActivity : AppCompatActivity() {
    lateinit var myBroadcastReceiver: MyBroadcastReceiver
    lateinit var networkHelper: NetworkHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        networkHelper= NetworkHelper(this)
        myBroadcastReceiver= MyBroadcastReceiver()

        val intentFilter=IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(myBroadcastReceiver, intentFilter)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(myBroadcastReceiver)
    }
}
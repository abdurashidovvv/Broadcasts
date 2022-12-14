package com.abdurashidov.broadcasts

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import com.abdurashidov.broadcasts.utils.NetworkHelper

class MainActivity : AppCompatActivity() {
    lateinit var myBroadcastReceiver: MyBroadcastReceiver
    lateinit var networkHelper: NetworkHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        networkHelper = NetworkHelper(this)
        myBroadcastReceiver = MyBroadcastReceiver()

        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(myBroadcastReceiver, intentFilter)


        //Alarm
        val btn = findViewById<Button>(R.id.my_button)
        btn.setOnClickListener {
            val intent = Intent(this, MyReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0)
            val time = SystemClock.elapsedRealtime() + 2000
            val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
            alarmManager.setInexactRepeating(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                time,
                5000,
                pendingIntent
            )
        }

    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(myBroadcastReceiver)
    }
}
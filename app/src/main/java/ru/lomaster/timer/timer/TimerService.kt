package ru.lomaster.timer.timer

import android.app.AlarmManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import ru.lomaster.timer.App

class TimerService() : Service() {

   // private val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
    override fun onCreate() {
        super.onCreate()
        Log.d("timer", "onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Log.d("timer", "onStartCommand")
        val time = intent?.getIntExtra("newTime",5)
        Log.d("timer", "SendedTime = $time")
        return super.onStartCommand(intent, flags, startId)

       // alarmManager.setExact()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("timer", "onDestroy")
    }

    override fun onBind(intent: Intent?): IBinder? {
       return  null
    }


    private fun onAlarm(){
        
    }

}
package ru.lomaster.timer.timer

import android.app.AlarmManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.lomaster.timer.App
import ru.lomaster.timer.R

class TimerService() : Service() {

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + job)
    private var stared = false;
    private lateinit var ring :MediaPlayer ;
    // private val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
    override fun onCreate() {
        super.onCreate()
        Log.d("timer", "onCreate")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        if(stared)
        {
            return Service.START_NOT_STICKY;
        }
        ring = MediaPlayer.create(this, R.raw.bell)
        stared = true;
        Log.d("timer", "onStartCommand")
        val time = intent.getIntExtra("newTime",5)
        Log.d("timer", "SendedTime = $time")
        work(time);
        return super.onStartCommand(intent, flags, startId)

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("timer", "onDestroy")
    }

    override fun onBind(intent: Intent?): IBinder? {
       return  null
    }


    private fun work(timeMin: Int){
        scope.launch {
            while(stared)
            {
                var time = timeMin * 1000 * 60;
                while(time > 0 && stared)
                {
                    delay(999);
                    time -= 1000;
                    Log.d("timer", time.toString());
                }
                ring.start();
            }

        }
    }

}
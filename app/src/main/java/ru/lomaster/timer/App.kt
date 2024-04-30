package ru.lomaster.timer

import android.app.Application
import android.content.Context
import ru.lomaster.timer.timer.TimerService

class App :Application() {


    companion object {
        private lateinit var appContext: Context
        val context: Context
            get() {
                return appContext
            }
        public fun setContext(cont:Context){
            appContext = cont
        }
    }

    override fun onCreate() {
        appContext = applicationContext
        super.onCreate()

    }
}
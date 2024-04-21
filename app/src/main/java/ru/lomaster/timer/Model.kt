package ru.lomaster.timer

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class Model : ViewModel(){
    val timers = MutableStateFlow(DataState())

}

class Timer(val startValue:Int = 0){
    var hours:Int = 0
    var minutes:Int = 0
    var seconds:Int = 0
}

class DataState{
    val timers = mutableListOf(Timer())
    var newTimer = Timer()
}
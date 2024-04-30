package ru.lomaster.timer



import android.content.Intent
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.lomaster.timer.timer.TimerService


@Composable
fun StartScreen(model:Model = viewModel()){

    val state by model.timers.collectAsState()
    val minutes = rememberSaveable { mutableIntStateOf(5) }
    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 10.dp, start = 5.dp, end = 5.dp)

    ) {
        Text(
            "Заведено таймеров - ${state.timers.count { t -> t.startValue > 0 }}",
            fontSize = 20.sp,
            color = Color.DarkGray,
            textAlign = TextAlign.Center
        )
        Divider(thickness = 6.dp)
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            horizontalArrangement = Arrangement.Center,

        ){

            TimeButton(img = Icons.Outlined.KeyboardArrowLeft) {
                if(minutes.intValue > 0){
                    minutes.intValue --
                }
            }

            Text(
                text = minutes.intValue.toString(),
                modifier = Modifier
                    .height(40.dp)
                    .background(Color.LightGray)
                    .fillMaxWidth(0.6f),
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Italic,
                color = Color.DarkGray
            )

            TimeButton(img = Icons.Outlined.KeyboardArrowRight) {
                if(minutes.intValue > 0){
                    minutes.intValue ++
                }
            }
        }

        Divider(thickness = 6.dp)

        TimeButton(img = Icons.Outlined.CheckCircle) {
            Log.d("timer", "Run")
            val intent = Intent(App.context, TimerService::class.java)
            App.context.startService(intent)
        }

        TimeButton(img = Icons.Outlined.Clear) {
            Log.d("timer", "Stop")
            val intent = Intent(App.context, TimerService::class.java)
            App.context.stopService(intent)
        }

    }
}

@Composable
fun TimeButton(img:ImageVector, onClick:()->Unit){
    OutlinedButton(
        onClick = onClick,
        Modifier
            .height(40.dp),
        shape = RectangleShape,

    ){
        Icon(img, contentDescription = "")
    }
}

































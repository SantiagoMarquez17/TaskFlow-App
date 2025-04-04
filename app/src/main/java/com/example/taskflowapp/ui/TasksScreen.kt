package com.example.taskflowapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController // libreria que permite pasar parametro de navegacion
import com.example.taskflowapp.R //Importar R porque este archvio no es el MainActivity

//Fragmento de pantalla de tareas
@Composable
fun TasksScreen(navController: NavController) {
    val image = painterResource(R.drawable.task_flow_logo_s_mbolo_)
    val icon = painterResource(R.drawable.bars_solid)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA)) // Color de fondo para visualizar la pantalla
    ) {
        Image(
            painter = image,
            contentDescription = "Logo App",
            modifier = Modifier
                .height(130.dp)
                .width(130.dp)
                .align(Alignment.TopEnd)
                .offset(x = (-100).dp, y = (40).dp),
            alpha = 0.3F
        )
        IconButton(onClick = { navController.navigate("MenuScreen")}) {
            Icon(
                painter = icon,
                contentDescription = "Menu Icon",
                modifier = Modifier
                    .align(Alignment.TopStart)
            )
        }
        Text(
            text = "Task List Screen",
            fontSize = 24.sp,
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}
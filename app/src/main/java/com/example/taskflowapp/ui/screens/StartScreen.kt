package com.example.taskflowapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController // libreria que permite pasar parametro de navegacion
import com.example.taskflowapp.R //Importar R porque no estamos en el MainActivity

//Fragmento de pantalla de inicio
@Composable
fun StartScreen(navController: NavController) {
    val image = painterResource(R.drawable.task_flow_logo_s_mbolo_)//imagen de logo

    //En toda la pantalla poner un color de fondo y organizar imagen y boton
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2B2D42))
    ) {
        //Colocar a logo con su posicion y tamaño
        Image(
            painter = image,
            contentDescription = stringResource(R.string.logo_app),
            modifier = Modifier
                .height(300.dp)
                .width(300.dp)
                .align(Alignment.Center)
        )
        //Colocar boton con su posicion, tamaño y color, permitiendo navegacion
        Button(
            onClick = { navController.navigate("tasksScreen") },
            modifier = Modifier
                .width(250.dp)
                .height(80.dp)
                .align(Alignment.BottomCenter)
                .offset(y = (-80).dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF43EEB2) // Color de fondo del boton
            )
        ) {
            //Texto interno del boton con sus caracteristicas
            Text(
                text = stringResource(R.string.entrar),
                fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                fontSize = 25.sp,
                color = Color(0xFF2B2D42)
            )
        }
    }
}
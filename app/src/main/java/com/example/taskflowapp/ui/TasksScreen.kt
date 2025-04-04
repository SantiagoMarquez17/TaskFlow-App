package com.example.taskflowapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController // libreria que permite pasar parametro de navegacion

//Fragmento de pantalla de tareas
@Composable
fun TasksScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green), // Color de fondo para visualizar la pantalla
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Task List Screen", fontSize = 24.sp, color = Color.White)
    }
}
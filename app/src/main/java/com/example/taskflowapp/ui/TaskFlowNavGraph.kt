package com.example.taskflowapp.ui

// Librerias para navegacion entre pantallas incluidas en el build.gradle.kts (:app) y libs.versions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

// Funcion Compose que define navegacion entre pantallas con el parametro NavController
@Composable
fun TaskFlowNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    //Define las pantallas y su navegación.
    NavHost(
        navController = navController,
        startDestination = "startScreen" //Pantalla de inicio
    ) {
        // se listan una a una las pantallas creadas en compose
        composable("startScreen") {
            StartScreen(navController)
        }
        composable("tasksScreen") {
            TasksScreen(navController)
        }
    }
}
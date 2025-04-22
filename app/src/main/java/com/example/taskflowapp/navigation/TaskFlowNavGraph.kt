package com.example.taskflowapp.navigation

// Librerias para navegacion entre pantallas incluidas en el build.gradle.kts (:app) y libs.versions
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.taskflowapp.ui.screens.PhotosScreen
import com.example.taskflowapp.ui.screens.ProfileScreen
import com.example.taskflowapp.ui.screens.StartScreen
import com.example.taskflowapp.ui.screens.TasksScreen
import com.example.taskflowapp.ui.screens.VideosScreen
import com.example.taskflowapp.ui.screens.WebScreen

// Funcion Compose que define navegacion entre pantallas con el parametro NavController
@Composable
fun TaskFlowNavGraph(navController: NavHostController) {
    //Define las pantallas y su navegación
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
        composable("profileScreen") {
            ProfileScreen(navController)
        }
        composable("photosScreen") {
            PhotosScreen(navController)
        }
        composable("videosScreen") {
            VideosScreen(navController)
        }
        composable("webScreen") {
            WebScreen(navController)
        }
    }
}
package com.example.taskflowapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskflowapp.R

@Composable
fun VideosScreen(navController: NavController) {
    DrawerScaffold(
        navController = navController,
        screenTitle = stringResource(R.string.videos),) { modifier ->
        VideosScreenContent(modifier)
    }
}

@Composable
fun VideosScreenContent(modifier: Modifier = Modifier){
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
    ) {
        Text("Esta es la pantalla de Videos")
        Spacer(modifier = Modifier.height(8.dp))
        Text("Aquí puedes mostrar Videos de cada tarea.")
    }
}
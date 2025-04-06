package com.example.taskflowapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskflowapp.R

@Composable
fun PhotosScreen(navController: NavController) {
    DrawerScaffold(navController = navController, screenTitle = stringResource(R.string.fotos)) { modifier ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text("Esta es la pantalla de Fotos")
            Spacer(modifier = Modifier.height(8.dp))
            Text("Aquí puedes mostrar Fotos de cada tarea.")
        }
    }
}
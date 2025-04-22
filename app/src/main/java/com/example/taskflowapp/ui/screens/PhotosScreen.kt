package com.example.taskflowapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.taskflowapp.R
import com.example.taskflowapp.ui.components.DrawerScaffold

@Composable
fun PhotosScreen(navController: NavController) {
    DrawerScaffold(
        navController = navController,
        screenTitle = stringResource(R.string.fotos)
    ) { modifier ->
        PhotosScreenContent(modifier)
    }
}

@Composable
fun PhotosScreenContent(modifier: Modifier = Modifier){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
            .padding(top = 120.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Fila con dos botones
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TaskPhotoItem("Tarea N°1")
            TaskPhotoItem("Tarea N°2")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Fila con dos botones
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TaskPhotoItem("Tarea N°3")
        }

    }
}

@Composable
fun TaskPhotoItem(text: String) {
    Button(
        onClick = {
            // Aquí luego puedes abrir un selector de imágenes
        },
        modifier = Modifier
            .width(150.dp)
            .height(150.dp)
            .padding(8.dp)
            .border(width = 1.dp, color = Color(0xFF2B2D42), shape = RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD6D6D6))
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Imagen del ícono importado
            Image(
                painter = painterResource(id = R.drawable.image_solid),
                contentDescription = "Ícono subir imagen",
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = text,
                fontSize = 14.sp,
                color = Color(0xFF2B2D42)
            )
        }
    }
}
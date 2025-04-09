package com.example.taskflowapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.taskflowapp.R

@Composable
fun ProfileScreen(navController: NavController) {
    DrawerScaffold(
        navController = navController,
        screenTitle = stringResource(R.string.perfil)) { modifier ->
        ProfileScreenContent(modifier)
    }
}

@Composable
fun ProfileScreenContent(modifier: Modifier = Modifier) {

    // Contenido del perfil
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
            .padding(top = 140.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .padding(8.dp)
                .border(width = 2.dp, color = Color.Black)
                .background(Color(0xFFA4A4A6))
                .fillMaxWidth(0.8f) // 80% del ancho del contenedor padre
                .padding(16.dp)
        )

        {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // Imagen de perfil (ícono)
                Image(
                    painter = painterResource(id = R.drawable.foto_santiago),
                    contentDescription = "Imagen de perfil",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape) // Hace que la imagen sea circular
                        .background(Color.LightGray)
                        .fillMaxWidth() // para que ocupe mismo ancho que lo demás
                )

                // Espacio de separación
                Spacer(modifier = Modifier.height(8.dp))

                // Tareas pendientes
                Box(
                    modifier = Modifier
                        .border(width = 2.dp, color = Color.Black) // Borde negro
                        .padding(1.dp)
                        .background(Color(0xFFE3D925))
                        .padding(16.dp)
                        .fillMaxWidth(), //igual ancho
                    contentAlignment = Alignment.Center
                )
                {
                    Column (horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Tareas pendientes",
                            color = Color(0xFF2B2D42),
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "2. Videollamada",
                            color = Color(0xFF2B2D42),
                            fontSize = 13.sp
                        )
                        Text(
                            text = "3. Emails",
                            color = Color(0xFF2B2D42),
                            fontSize = 13.sp
                        )
                    }
                }

                // Espacio de separación
                Spacer(modifier = Modifier.height(8.dp))

                // Tareas completadas
                Box(
                    modifier = Modifier
                        .padding(1.dp)
                        .border(width = 2.dp, color = Color.Black) // Borde negro
                        .background(Color(0xFFB7F9B1))
                        .fillMaxWidth() // <-- igual ancho
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column (horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Tareas Completadas",
                            color = Color(0xFF2B2D42),
                            fontWeight = FontWeight.Bold
                        )
                        Text(text = "1. Presentación PowerPoint",
                            color = Color(0xFF2B2D42),
                            fontSize = 13.sp
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {

}

package com.example.taskflowapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taskflowapp.ui.components.DrawerScaffold
import com.example.taskflowapp.viewmodel.TaskViewModel

@Composable
fun ProfileScreen(navController: NavController) {
    DrawerScaffold(
        navController = navController,
        screenTitle = stringResource(R.string.perfil)
    ) { modifier ->
        ProfileScreenContent(modifier)
    }
}

@Composable
fun ProfileScreenContent(modifier: Modifier = Modifier, taskViewModel: TaskViewModel = viewModel()) {

    val tareas = taskViewModel.tareas.collectAsState().value

    // Separar tareas por estado
    val tareasPendientes = tareas.filter { !it.realizada }
    val tareasCompletadas = tareas.filter { it.realizada }

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
                        Column (modifier = Modifier.align(Alignment.Start)) {
                            tareasPendientes.forEach { tarea ->
                                Row (verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(top = 6.dp)) {
                                    Text(
                                        text = "N° ${tarea.id}",
                                        color = Color(0xFF2B2D42),
                                        modifier = Modifier
                                            .weight(1f)
                                    )
                                    Text(
                                        text = tarea.descripcion,
                                        fontSize = 13.sp,
                                        modifier = Modifier
                                            .weight(3f),
                                        color = Color(0xFF2B2D42)
                                    )
                                }
                            }
                        }
                    }
                }

                // Espacio de separación
                Spacer(modifier = Modifier.height(10.dp))

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
                        Column (modifier = Modifier.align(Alignment.Start)) {
                            tareasCompletadas.forEach { tarea ->
                                Row (verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(top = 6.dp)) {
                                    Text(
                                        text = "N° ${tarea.id}",
                                        color = Color(0xFF2B2D42),
                                        modifier = Modifier
                                            .weight(1f)
                                    )
                                    Text(
                                        text = tarea.descripcion,
                                        fontSize = 13.sp,
                                        modifier = Modifier
                                            .weight(3f),
                                        color = Color(0xFF2B2D42)
                                    )
                                }
                            }
                        }
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

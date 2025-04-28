package com.example.taskflowapp.ui.screens

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskflowapp.R
import com.example.taskflowapp.ui.components.DrawerScaffold

@Composable
fun WebScreen(navController: NavController) {
    DrawerScaffold(
        navController = navController,
        screenTitle = stringResource(R.string.web),
    ) { modifier ->
        WebScreenContent(modifier)
    }
}

@Composable
fun WebScreenContent(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var url by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
            .padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Icono de web
        Image(
            painter = painterResource(R.drawable.ic_web),
            contentDescription = "Icono de navegación web",
            modifier = Modifier
                .size(80.dp)
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Caja de texto para digitar la URL
        TextField(
            value = url,
            onValueChange = { url = it },
            label = { Text("Ingrese la URL") },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para cargar la página web
        Button(
            onClick = {
                if (url.isNotBlank()) {
                    var validUrl = url
                    if (!url.startsWith("http://") && !url.startsWith("https://")) {
                        validUrl = "http://$url" // Forzar https
                    }
                    try {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(validUrl)).apply {
                            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        }
                        context.startActivity(intent)
                    } catch (e: Exception) {
                        Log.e("WebScreen", "Error al parsear o abrir la URL: ${e.message}")
                        // Toast.makeText(context, "URL inválida", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally), // Centra el botón
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF43EEB2)),
            enabled = url.isNotBlank() // Habilita el botón solo si la URL no está vacía
        ) {
            Text(
                text = "Cargar Página Web",
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2B2D42)
            )
        }
    }
}
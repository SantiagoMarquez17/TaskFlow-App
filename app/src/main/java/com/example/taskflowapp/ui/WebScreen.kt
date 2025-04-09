package com.example.taskflowapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun WebScreen(navController: NavController) {
    DrawerScaffold(
        navController = navController,
        screenTitle = stringResource(R.string.web),) { modifier ->
        WebScreenContent(modifier)
    }
}

@Composable
fun WebScreenContent(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
            .padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Icono de calendario
        Image(
            painter = painterResource(R.drawable.calendar_days_solid), // Asegúrate de tener este recurso
            contentDescription = "Icono de calendario",
            modifier = Modifier
                .size(80.dp)
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Imagen de calendario (simula calendario de Google)
        Image(
            painter = painterResource(R.drawable.google_calendar), // usa la imagen que subiste
            contentDescription = "Calendario Google",
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(8.dp)
                .border(width = 2.dp, color = Color.Yellow) // Borde amarillo
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Botón para abrir Google Calendar
        Button (
            onClick = {},
            modifier = Modifier.align(Alignment.CenterHorizontally),// Centra el botón
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF43EEB2))
        ){
            Text(
                text = "Abrir Google Calendar",
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2B2D42)
            )
        }
    }
}

/*
    val context = LocalContext.current

// Botón para abrir Google Calendar
        Button(onClick = {
            val url = "https://calendar.google.com/"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        }) {
            Text("Abrir Google Calendar")
        }
*/
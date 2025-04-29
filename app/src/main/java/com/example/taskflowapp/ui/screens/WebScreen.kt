package com.example.taskflowapp.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
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

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebScreenContent(modifier: Modifier = Modifier) {

    var url by remember { mutableStateOf("") }
    var webView: WebView? by remember { mutableStateOf(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
            .padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

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
                Log.d("WebScreen", "Botón 'Cargar Página Web' presionado")
                if (url.isNotBlank() && webView != null) {
                    var validUrl = url
                    if (!url.startsWith("http://") && !url.startsWith("https://")) {
                        validUrl = "http://$url"
                    }
                    webView?.loadUrl(validUrl)
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

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .padding(16.dp)
                .border(2.dp, Color.Gray, RoundedCornerShape(12.dp))
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White)
                .fillMaxWidth()
                .height(400.dp) // puedes ajustar el alto como prefieras
        ) {
            AndroidView(
                factory = { context ->
                    WebView(context).apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                        webViewClient = WebViewClient()
                        settings.javaScriptEnabled = true
                        webView = this
                        loadUrl("https://www.google.com")
                    }
                },
                update = { view -> webView = view },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

package com.example.taskflowapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController // libreria que permite pasar parametro de navegacion
import com.example.taskflowapp.R //Importar R porque no estamos en el MainActivity

//Función que trae el menú izquierdo a la pantalla y le da su respectivo titulo
@Composable
fun TasksScreen(navController: NavController) {
    DrawerScaffold(
        navController = navController,
        screenTitle = stringResource(R.string.listado_de_tareas)) { modifier ->
        TasksScreenContent(modifier)//Mostrar contenido de la pantalla de tareas
    }
}

//Función que crea el contenido de la pantalla tareas
@Composable
fun TasksScreenContent(modifier: Modifier = Modifier){
    var texto by remember { mutableStateOf("") }
    val tareas = remember { mutableStateListOf<String>() }

    // Organiza el campo de escritura, la lista y el boton en una columna centrada
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 80.dp)
            .background(Color(0xFFF8F9FA)),
        verticalArrangement = Arrangement.Top
    ) {
        //Campo que permite escribir el texto de la tarea a agregar
        TextField(
            value = texto,
            onValueChange = { texto = it },
            label = { Text(text = stringResource(R.string.nueva_tarea)) },
            modifier = Modifier.fillMaxWidth().padding(20.dp)
        )

        //Crea la lista de tareas que se van agregando, una debajo de otra
        tareas.forEachIndexed { index, it ->
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 50.dp, end = 50.dp, top = 10.dp, bottom = 10.dp),
                color = Color(0xFFD9D9D9)
            ) {
                Row (verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "N° ${index + 1}",
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = it,
                        fontSize = 13.sp,
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    )
                }

            }
        }

        Spacer(modifier = Modifier.height(16.dp).padding(16.dp)) // Espacio entre listado y boton

        //Boton que permite agregar el texto digitado a la lista
        Button(
            onClick = {
                if (texto.isNotBlank()) {
                    tareas.add(texto)
                    texto = ""
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally),// Centra el botón
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF43EEB2)
            )
        ) {
            //Texto del boton
            Text(
                text = stringResource(R.string.name_add),
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2B2D42))
        }
    }
}

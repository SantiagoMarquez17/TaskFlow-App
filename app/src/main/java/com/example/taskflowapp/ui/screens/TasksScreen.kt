package com.example.taskflowapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taskflowapp.ui.components.DrawerScaffold
import com.example.taskflowapp.viewmodel.TaskViewModel


//Función que trae el menú izquierdo a la pantalla y le da su respectivo titulo
@Composable
fun TasksScreen(navController: NavController) {
    val taskViewModel: TaskViewModel = viewModel() //Crear el viewModel

    DrawerScaffold(
        navController = navController,
        screenTitle = stringResource(R.string.listado_de_tareas)
    ) { modifier ->
        TasksScreenContent(
            modifier = modifier,
            taskViewModel = taskViewModel
        )//Mostrar contenido de la pantalla de tareas
    }
}

//Función que crea el contenido de la pantalla tareas
@Composable
fun TasksScreenContent(modifier: Modifier = Modifier, taskViewModel: TaskViewModel){
    var texto by remember { mutableStateOf("") } //remember guarda valor mientras esta en pantalla
    val tareas by taskViewModel.tareas.collectAsState() //Obtiene la lista de tareas en estado normal

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA)) // ← Fondo aplicado a toda la pantalla
    ) {
        // Organiza el campo de escritura, la lista y el boton en una columna centrada
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp, bottom = 50.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top
        ) {
            //Campo que permite escribir el texto de la tarea a agregar
            TextField(
                value = texto,
                onValueChange = { texto = it },
                label = { Text(text = stringResource(R.string.nueva_tarea)) },
                modifier = Modifier.fillMaxWidth().padding(20.dp).background(Color(0xFF2B2D42))
            )

            //Boton que permite agregar el texto digitado a la lista
            Button(
                onClick = {
                    if (texto.isNotBlank()) {
                        taskViewModel.agregarTarea(texto)
                        texto = ""
                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally),// Centra el botón
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF43EEB2)
                ),
                enabled = texto.isNotBlank() // Habilita el botón solo si la URL no está vacía
            ) {
                //Texto del boton
                Text(
                    text = stringResource(R.string.name_add),
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2B2D42)
                )
            }

            Spacer(
                modifier = Modifier.height(16.dp).padding(16.dp)
            ) // Espacio entre listado y boton

            //Muestra la lista de tareas que se van agregando, una debajo de otra
            tareas.forEachIndexed { index, tarea ->
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 50.dp, end = 50.dp, top = 10.dp, bottom = 10.dp),
                    color = Color(0xFFA4A4A6)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "N° ${tarea.id}",
                            modifier = Modifier.weight(1f),
                            color = Color(0xFF2B2D42)
                        )
                        Text(
                            text = tarea.descripcion,
                            fontSize = 13.sp,
                            modifier = Modifier
                                .weight(3f)
                                .wrapContentWidth(Alignment.CenterHorizontally),
                            color = Color(0xFF2B2D42)
                        )
                        // Checkbox que indica si la tarea está realizada
                        Checkbox(
                            checked = tarea.realizada,
                            onCheckedChange = {
                                taskViewModel.cambiarEstado(tarea.id) // Llama al ViewModel para actualizar el estado
                            },
                            colors = CheckboxDefaults.colors(
                                checkedColor = Color(0xFF43EEB2), // Color cuando está marcado
                                uncheckedColor = Color(0xFF2B2D42), // Color cuando está desmarcado
                                checkmarkColor = Color(0xFF2B2D42) // Color del ✔ interno
                            ),
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}

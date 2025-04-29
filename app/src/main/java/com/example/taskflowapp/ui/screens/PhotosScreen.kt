package com.example.taskflowapp.ui.screens

//Librerias para traer el modelo de las tareas
//Librerias para escoger imagen del dispositivo
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.taskflowapp.R
import com.example.taskflowapp.data.Task
import com.example.taskflowapp.ui.components.DrawerScaffold
import com.example.taskflowapp.viewmodel.TaskViewModel

@Composable
fun PhotosScreen(navController: NavController) {

    val taskViewModel: TaskViewModel = viewModel() //Crear el viewModel

    DrawerScaffold(
        navController = navController,
        screenTitle = stringResource(R.string.fotos)
    ) { modifier ->
        PhotosScreenContent(modifier,taskViewModel)
    }
}

@Composable
fun PhotosScreenContent(modifier: Modifier = Modifier,  taskViewModel: TaskViewModel){

    val tareas by taskViewModel.tareas.collectAsState() //Obtiene la lista de tareas en estado normal
    val tareaSeleccionada = remember { mutableStateOf<Task?>(null) }//remember guarda imagen mientras esta en pantalla
    val imagenExpandida = remember { mutableStateOf(false) } // Estado para saber si la imagen está ampliada

    // Launcher que guarda la ruta de la imagen que se selecciona
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()//abrir explorador de archivos para seleccionar un archivo
    ) { uri: Uri? ->
        //Si hay una Uri seleccionada, se prosigue
        uri?.let {
            // Si hay una tarea seleccionada, se actualiza la Uri de su imagen
            tareaSeleccionada.value?.let { tarea ->
                taskViewModel.actualizarFotoTarea(id = tarea.id, imageUri = it.toString())
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF8F9FA))
                .padding(top = 120.dp, bottom = 50.dp)
                .verticalScroll(rememberScrollState()), // Activamos el scroll vertical,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            //Muestra la lista de tareas que se van agregando, una debajo de otra
            tareas.chunked(2).forEach { rowTareas ->
                // Fila con dos botones
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    rowTareas.forEach { tarea ->
                        TaskPhotoItem(
                            tarea = tarea,
                            onSelectImage = {
                                tareaSeleccionada.value = tarea
                                launcher.launch("image/*")//lanzar funcion launcher, que solo acepte imagenes
                            },
                            onImageClick = {
                                // Al hacer clic en la imagen, la ampliamos
                                tareaSeleccionada.value = tarea
                                imagenExpandida.value = true
                            }
                        )
                    }
                }
            }
            //  Espacio entre filas de botones
            Spacer(modifier = Modifier.height(16.dp))

        }
        // Si la imagen está ampliada, mostramos una vista superpuesta
        if (imagenExpandida.value) {
            tareaSeleccionada.value?.let { tarea ->
                ExpandedImageView(
                    tarea = tarea,
                    onClose = { imagenExpandida.value = false }
                )
            }
        }
    }
}

@Composable
fun TaskPhotoItem(tarea: Task, onSelectImage: (Task) -> Unit, onImageClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            onClick = { onSelectImage(tarea)},
            modifier = Modifier
                .width(150.dp)
                .height(150.dp)
                .padding(8.dp)
                .border(width = 1.dp, color = Color(0xFF2B2D42), shape = RoundedCornerShape(8.dp)),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD6D6D6))
        ) {
            if (tarea.imageUri != null) {
                // Imagen seleccionada con el launcher
                Image(
                    painter = rememberAsyncImagePainter(model = tarea.imageUri),
                    contentDescription = "Imagen seleccionada",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(8.dp))
                        .clickable{onImageClick()},// Al hacer clic, ampliamos la imagen
                    contentScale = ContentScale.Crop
                )
            } else {
                // Imagen del ícono importado
                Image(
                    painter = painterResource(id = R.drawable.image_solid),
                    contentDescription = "Ícono subir imagen",
                    modifier = Modifier.size(80.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
        Text(
            text = "Tarea N°${tarea.id}",
            fontSize = 14.sp,
            color = Color(0xFF2B2D42)
        )
    }
}

@Composable
fun ExpandedImageView(tarea: Task, onClose: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .offset(y = 35.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.85f) // 85% del ancho de la pantalla
                .fillMaxHeight(0.8f)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFF2B2D42).copy(alpha = 0.9f)) // Fondo oscuro
                .clickable(
                    indication = null, // Elimina el efecto visual (ripple)
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = {} // Aquí puedes dejarlo vacío o poner lógica
                ) // Captura los toques para que no pasen al fondo
                .align(Alignment.Center),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp), // Espaciado entre los elementos
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Título de la tarea
                Text(
                    text = "Tarea N°${tarea.id}",
                    fontSize = 20.sp,
                    color = Color(0xFFF8F9FA),
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                // Imagen ampliada
                Image(
                    painter = rememberAsyncImagePainter(model = tarea.imageUri),
                    contentDescription = "Imagen ampliada",
                    modifier = Modifier
                        .fillMaxWidth(0.9f) // 90% del ancho
                        .heightIn(max = 600.dp) // Controla que no se pase de alto
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Fit
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Descripción de la tarea
                Text(
                    text = tarea.descripcion,
                    color = Color(0xFFF8F9FA),
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Botón para cerrar
                Button(
                    onClick = { onClose() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF43EEB2))
                ) {
                    Text(text = "Cerrar", color = Color(0xFF2B2D42).copy(alpha = 0.9f))
                }
            }
        }
    }
}

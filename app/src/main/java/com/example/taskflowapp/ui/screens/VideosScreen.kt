package com.example.taskflowapp.ui.screens

//Librerias para traer el modelo de las tareas
//Librerias para escoger video del dispositivo
import android.content.Context
import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.widget.Toast
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
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.taskflowapp.R
import com.example.taskflowapp.data.Task
import com.example.taskflowapp.ui.components.DrawerScaffold
import com.example.taskflowapp.viewmodel.TaskViewModel


@Composable
fun VideosScreen(navController: NavController) {

    val taskViewModel: TaskViewModel = viewModel() //Crear el viewModel

    DrawerScaffold(
        navController = navController,
        screenTitle = stringResource(R.string.videos),
    ) { modifier ->
        VideosScreenContent(modifier,taskViewModel)
    }
}

@Composable
fun VideosScreenContent(modifier: Modifier = Modifier,  taskViewModel: TaskViewModel){

    val tareas by taskViewModel.tareas.collectAsState() //Obtiene la lista de tareas en estado normal
    val tareaSeleccionada = remember { mutableStateOf<Task?>(null) }//remember guarda video en tarea especifica mientras esta en pantalla
    val videoExpandido = remember { mutableStateOf(false) } // Estado para saber si el vodeo está ampliado

    // Launcher que guarda la ruta del video que se selecciona
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()//abrir explorador de archivos para seleccionar un archivo
    ) { uri: Uri? ->
        //Si hay una Uri seleccionada, se prosigue
        uri?.let {
            // Si hay una tarea seleccionada, se actualiza la Uri de su video
            tareaSeleccionada.value?.let { tarea ->
                taskViewModel.actualizarVideoTarea(id = tarea.id, videoUri = it.toString())
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
                        TaskVideoItem(
                            tarea = tarea,
                            onSelectVideo = {
                                tareaSeleccionada.value = tarea
                                launcher.launch("video/*")//lanzar funcion launcher, que solo acepte videos
                            },
                            onVideoClick = {
                                // Al hacer clic en el icono del video, se amplia
                                tareaSeleccionada.value = tarea
                                videoExpandido.value = true
                            }
                        )
                    }
                }
                //  Espacio entre filas de botones
                Spacer(modifier = Modifier.height(16.dp))

            }
        }
        // Si el video está ampliado, mostramos una vista superpuesta
        if (videoExpandido.value) {
            tareaSeleccionada.value?.let { tarea ->
                ExpandedVideoView(
                    tarea = tarea,
                    onClose = { videoExpandido.value = false } // Permitir cerrar la vista ampliada
                )
            }
        }
    }
}

@Composable
fun TaskVideoItem(tarea: Task, onSelectVideo: (Task) -> Unit, onVideoClick: () -> Unit) {

    val context = LocalContext.current // Para usar el contexto en la carga de la miniatura
    val videoThumbnail = remember(tarea.videoUri) { getVideoThumbnail(context, tarea.videoUri) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            onClick = { onSelectVideo(tarea) },
            modifier = Modifier
                .width(150.dp)
                .height(150.dp)
                .padding(8.dp)
                .border(width = 1.dp, color = Color(0xFF2B2D42), shape = RoundedCornerShape(8.dp)),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD6D6D6))
        ) {
            if (tarea.videoUri != null) {
                // Video seleccionado con el launcher
                Image(
                    painter = rememberAsyncImagePainter(model = videoThumbnail),
                    contentDescription = "Video seleccionado",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(8.dp))
                        .clickable{onVideoClick()},// Al hacer clic, ampliamos el video
                    contentScale = ContentScale.Crop
                )
            } else {
                // Imagen del ícono importado
                Image(
                    painter = painterResource(id = R.drawable.video_solid),
                    contentDescription = "Ícono subir video",
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
fun ExpandedVideoView(tarea: Task, onClose: () -> Unit) {
    val context = LocalContext.current
    val player = remember { ExoPlayer.Builder(context).build() }

    // Efecto que se ejecuta al montar y desmontar el Composable
    DisposableEffect(tarea.videoUri) {
        if (tarea.videoUri != null) {
            try {
                // Prepara el archivo de video
                val mediaItem = MediaItem.fromUri(tarea.videoUri)
                player.setMediaItem(mediaItem)
                player.prepare()
                player.playWhenReady = true
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(context, "No se pudo reproducir el video.", Toast.LENGTH_SHORT).show()
                onClose()
            }
        }

        // Limpia recursos al cerrar
        onDispose {
            player.stop()
            player.release()
        }
    }
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

                // Muestra el reproductor de video
                AndroidView(
                    factory = {
                        PlayerView(it).apply {
                            this.player = player
                            this.useController = true
                            this.keepScreenOn = true
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.7f) // Limita la altura al 70% de la pantalla
                        .clip(RoundedCornerShape(16.dp))
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

// Función para obtener la miniatura del video
fun getVideoThumbnail(context: Context, videoUri: String?): Bitmap? {
    videoUri?.let {
        val retriever = MediaMetadataRetriever()
        try {
            retriever.setDataSource(context, Uri.parse(it))
            return retriever.getFrameAtTime(1000000) // Obtiene una miniatura del primer fotograma (1 segundo)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            retriever.release()
        }
    }
    return null
}


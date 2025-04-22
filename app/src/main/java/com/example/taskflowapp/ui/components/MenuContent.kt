package com.example.taskflowapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController // libreria que permite pasar parametro de navegacion
import com.example.taskflowapp.R //Importar R porque no estamos en el MainActivity

// Funcion que muestra el contenido del menu del icono izquierdo
@Composable
fun MenuContent(navController: NavController) {
    // Obtener el ancho de pantalla para calcular el ancho del menú
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    //Ancho del menu izquierdo segun ancho de pantalla
    val drawerWidth = screenWidth * 0.5f

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(drawerWidth)
            .padding(16.dp)

    ) {
        //Texto estatico como titulo del menú con el nombre de la app
        Text(
            text = stringResource(id = R.string.app_name),
            fontFamily = FontFamily(Font(R.font.montserrat_bold)),
            fontSize = 22.sp,
            modifier = Modifier.padding(16.dp),
            color = Color.White
        )
        HorizontalDivider() // Línea separadora entre título y botones

        // Botones del menú con parametro clickable para navegar entre pantallas
        Text(
            text = stringResource(R.string.tareas),
            color = Color.White,
            modifier = Modifier
                .padding(16.dp)
                .clickable { navController.navigate("tasksScreen") }
        )
        Text(
            text = stringResource(R.string.perfil),
            color = Color.White,
            modifier = Modifier
                .padding(16.dp)
                .clickable { navController.navigate("profileScreen") }
        )
        Text(
            text = stringResource(R.string.fotos),
            color = Color.White,
            modifier = Modifier
                .padding(16.dp)
                .clickable { navController.navigate("photosScreen") }
        )
        Text(
            text = stringResource(R.string.videos),
            color = Color.White,
            modifier = Modifier
                .padding(16.dp)
                .clickable { navController.navigate("videosScreen") }
        )
        Text(
            text = stringResource(R.string.web),
            color = Color.White,
            modifier = Modifier
                .padding(16.dp)
                .clickable { navController.navigate("webScreen") }
        )
    }
}

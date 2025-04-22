package com.example.taskflowapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import androidx.navigation.NavController // libreria que permite pasar parametro de navegacion
import com.example.taskflowapp.R

@OptIn(ExperimentalMaterial3Api::class)// Habilitar funciones experimentales de compose (topAppBar)
@Composable
fun DrawerScaffold(navController: NavController,screenTitle: String, content: @Composable (Modifier) -> Unit) {

    val drawerState = rememberDrawerState(DrawerValue.Closed)// Estado del menu lateral cerrado
    val scope = rememberCoroutineScope()//

    //Funcion que crea menu a la izquierda
    ModalNavigationDrawer(
        drawerState = drawerState,// Estado inicial del menú
        drawerContent = {
            //Fragmento del menú
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(200.dp)
                    .background(Color(0xFF2B2D42))
            ) {
                MenuContent(navController)//Opciones para cambiar de pantalla(Perfil, Fotos, etc)
            }
        }
    ) {
        // barra superior para colocar el icono del menu
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    //Titulo que se muestra en cada pantalla
                    title = {
                        Text(
                            text = screenTitle,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        ) },
                    navigationIcon = {
                        // Boton con icono que al hacer clic abre el menu izquierdo
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = stringResource(R.string.menu_icon))
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color(0xFFF8F9FA), // Color de fondo
                        titleContentColor = Color(0xFF2B2D42),    // color del texto
                        navigationIconContentColor = Color(0xFF2B2D42) // color del ícono de menú
                    )
                )
            }
        ) { padding ->//Separar barra superior de contenido de la pantalla
            content(Modifier.padding(padding))//contenido principal de la pantalla
        }
    }
}
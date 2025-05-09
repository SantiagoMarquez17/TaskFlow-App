package com.example.taskflowapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.taskflowapp.navigation.TaskFlowNavGraph
import com.example.taskflowapp.ui.theme.TaskFlowAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            TaskFlowAppTheme {
                // Funcion que permite una navegacion entre pantallas
                TaskFlowNavGraph(
                    navController = navController
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

}
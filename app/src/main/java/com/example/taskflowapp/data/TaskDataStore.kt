package com.example.taskflowapp.data

import android.content.Context //Inicializar datastore
import androidx.datastore.core.DataStore //crear almacenamiento
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.example.taskflowapp.data.Task //almacenar clase Task
import kotlinx.coroutines.Dispatchers // corutinas que demoran para no bloquear la app
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException //manejo de errores de conversion de datos
import kotlinx.serialization.json.Json //convertir datos a JSON
import java.io.InputStream //Leer archivo
import java.io.OutputStream //Escribir archivo

// Nombre del archivo donde se guardarán las tareas
private const val DATASTORE_FILE_NAME = "tareas.json"

// Crear el DataStore usando el serializador que definimos
val Context.taskDataStore: DataStore<List<Task>> by dataStore(
    fileName = DATASTORE_FILE_NAME,
    serializer = TaskListSerializer //Convertir y leer JSON
)

//objeto define cómo convertir una lista de tareas a y desde JSON
object TaskListSerializer : Serializer<List<Task>> {

    override val defaultValue: List<Task> = emptyList() //Iniciar lista vacia

    //leer los datos desde el archivo
    override suspend fun readFrom(input: InputStream): List<Task> {
        return try {
            Json.decodeFromString(
                deserializer = kotlinx.serialization.builtins.ListSerializer(Task.serializer()),
                string = input.readBytes().decodeToString() //lee el archivo entero y lo convierte en texto
            )
        } catch (e: SerializationException) {
            emptyList() //Devolver lista vacia para evitar que la app se caiga
        }
    }

    // guardar los datos en el archivo
    override suspend fun writeTo(t: List<Task>, output: OutputStream) {
        //convierte la lista de tareas en un texto JSON
        val jsonString = Json.encodeToString(
            serializer = kotlinx.serialization.builtins.ListSerializer(Task.serializer()),
            value = t
        )
        withContext(Dispatchers.IO) { //evitar bloquear la interfaz
            output.write(jsonString.encodeToByteArray())//guarda el texto JSON en el archivo
        }
    }
}

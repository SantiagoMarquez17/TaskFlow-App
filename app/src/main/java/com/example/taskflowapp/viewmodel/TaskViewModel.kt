package com.example.taskflowapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskflowapp.data.Task
import com.example.taskflowapp.data.taskDataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application)
 {
     private val _tareas =
         MutableStateFlow<List<Task>>(emptyList()) //lista de tareas mutable y observable
     val tareas: StateFlow<List<Task>> get() = _tareas //lectura para estado de la lista
     private val _tareasCompletadas = MutableStateFlow(0)
     val tareasCompletadas: StateFlow<Int> = _tareasCompletadas;

     //acceder y asginar la lista guardada por corrutina
     init {
         viewModelScope.launch {
             val tareasGuardadas = getApplication<Application>().taskDataStore.data.first()
             _tareas.value = tareasGuardadas
         }
     }

     //se agrega tarea a la lista temporal y se manda a guardar al archivo
     fun agregarTarea(descripcion: String){
         val nuevaTarea = Task(_tareas.value.size + 1, descripcion = descripcion)
         val nuevaLista = _tareas.value + nuevaTarea
         _tareas.value = nuevaLista
         guardarTareas(nuevaLista)
    }
     // nueva lista donde se invierte el estado y procede a guardar la lista en el archivo
     fun cambiarEstado(id: Int) {
         val nuevaLista = _tareas.value.map {
             if (it.id == id) it.copy(realizada = !it.realizada) else it
         }
         _tareas.value = nuevaLista

         guardarTareas(nuevaLista)
     }

     //corrutina que abre el archivo JSON y lo actualiza
     private fun guardarTareas(tareas: List<Task>) {
         viewModelScope.launch {
             getApplication<Application>().taskDataStore.updateData {
                 tareas
             }
         }
     }

     fun actualizarContadores() {
         _tareasCompletadas.value = _tareas.value.count { it.realizada }
     }

 }
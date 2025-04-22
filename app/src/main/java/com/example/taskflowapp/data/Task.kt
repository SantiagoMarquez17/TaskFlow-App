package com.example.taskflowapp.data

import kotlinx.serialization.Serializable

@Serializable
data class Task (
    val id: Int,
    val descripcion: String,
    var realizada: Boolean = false
)
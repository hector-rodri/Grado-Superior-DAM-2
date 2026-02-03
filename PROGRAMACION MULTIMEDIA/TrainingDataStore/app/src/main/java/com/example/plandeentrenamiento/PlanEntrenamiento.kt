package com.example.plandeentrenamiento

data class PlanEntrenamiento(
    val id: Long = 0L,
    val nombre: String,
    val dias: Int,
    val activo: Boolean
)
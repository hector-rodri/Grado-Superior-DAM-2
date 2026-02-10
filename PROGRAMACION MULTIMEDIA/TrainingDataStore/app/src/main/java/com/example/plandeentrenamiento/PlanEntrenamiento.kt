package com.example.plandeentrenamiento

data class PlanEntrenamiento(
    val id: Long = 0,
    val nombre: String,
    val dias: Int,
    val semanas: Int,
    val activo: Boolean
)

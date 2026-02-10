package com.example.plandeentrenamiento

data class EjercicioRegistrado(
    val id: Long = 0,
    val planId: Long,
    val dia: Int,           // Día del plan al que corresponde
    val nombre: String,
    val fecha: String,
    val peso: Double,
    val repes: Int,
    val tipo: String
)

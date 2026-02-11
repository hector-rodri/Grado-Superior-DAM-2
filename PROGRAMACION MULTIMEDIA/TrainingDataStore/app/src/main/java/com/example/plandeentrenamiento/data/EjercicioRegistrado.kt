package com.example.plandeentrenamiento.data

data class EjercicioRegistrado(
    val id: Long = 0,
    val planId: Long,
    val dia: Int,
    val nombre: String,
    val peso: Double,
    val repes: Int,
)
package com.example.plandeentrenamiento

data class Ejercicio(
    val id: Long = 0L,
    val planId: Long,
    val nombre: String,
    val tipo: String,
    val fecha: String,
    val peso: Double,
    val repes: Int
)
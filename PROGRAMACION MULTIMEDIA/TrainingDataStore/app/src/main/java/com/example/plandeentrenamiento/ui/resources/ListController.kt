package com.example.plandeentrenamiento.ui.resources

import com.example.plandeentrenamiento.data.ElementLlista

class ListController {
    fun carregarDies(setmanes: Int, diesPerSetmana: Int, planId: Int): List<ElementLlista> {
        val totalDies = setmanes * diesPerSetmana
        val llista = mutableListOf<ElementLlista>()

        for (i in 1..totalDies) {
            llista.add(
                ElementLlista(
                    idDia = i,
                    planId = planId,
                    titol = "Dia $i"
                )
            )
        }
        return llista
    }

}
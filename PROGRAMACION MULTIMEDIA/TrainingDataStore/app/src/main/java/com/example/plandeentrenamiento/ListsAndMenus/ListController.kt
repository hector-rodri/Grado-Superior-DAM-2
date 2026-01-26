package com.example.plandeentrenamiento.ListsAndMenus

class ListController {
    fun carregarDies(setmanes: Int, diesPerSetmana: Int): List<ElementLlista> {
        val totalDies = setmanes * diesPerSetmana
        val llista = mutableListOf<ElementLlista>()

        for (i in 1..totalDies) {
            llista.add(
                ElementLlista(
                    titol = "Dia $i"
                )
            )
        }
        return llista
    }
}
package com.example.simondice_estebanmontes

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

data class Datos(var puntuacion: Int)

class RepositorioPuntuacion {
    private val _puntuacion = MutableStateFlow(Datos(0))
    val puntuacion: Flow<Datos> get() = _puntuacion

    /**
     * Incrementa la puntuación
     */
    fun aumentarPuntuacion() {
        _puntuacion.value = Datos(_puntuacion.value.puntuacion + 1)
    }
}
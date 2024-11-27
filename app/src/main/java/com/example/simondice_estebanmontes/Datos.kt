package com.example.simondice

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

data class DatosPuntuacion(var total: Int)

class GestorPuntos {
    private val flujoPuntos = MutableStateFlow(DatosPuntuacion(0))
    val puntos: Flow<DatosPuntuacion> get() = flujoPuntos

    /**
     * Aumenta los puntos actuales en uno.
     */
    fun aumentarPuntos() {
        flujoPuntos.value = flujoPuntos.value.copy(total = flujoPuntos.value.total + 1)
    }

    /**
     * Reinicia los puntos a cero.
     */
    fun reiniciarPuntos() {
        flujoPuntos.value = DatosPuntuacion(0)
    }
}

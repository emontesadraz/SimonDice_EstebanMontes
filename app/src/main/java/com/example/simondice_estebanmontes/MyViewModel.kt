package com.example.simondice_estebanmontes

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simondice.DatosPuntuacion
import com.example.simondice.GestorPuntos
import com.example.simondice_estebanmontes.ui.theme.Amarillo
import com.example.simondice_estebanmontes.ui.theme.Azul
import com.example.simondice_estebanmontes.ui.theme.Rojo
import com.example.simondice_estebanmontes.ui.theme.Verde
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class ControladorJuego(private val gestor: GestorPuntos) : ViewModel() {
    // Puntuación actual del jugador.
    private val _puntuacion = gestor.puntos
    val puntuacion: StateFlow<DatosPuntuacion> get() = _puntuacion as StateFlow<DatosPuntuacion>

    // Secuencia de colores del juego.
    private val _secuenciaColores = MutableStateFlow(mutableListOf<Color>())
    val secuenciaColores: StateFlow<List<Color>> get() = _secuenciaColores

    // Secuencia de colores ingresada por el usuario.
    private val _secuenciaUsuario = MutableStateFlow(mutableListOf<Color>())
    private val _colorActual = MutableStateFlow<Color?>(null)
    val colorActual: StateFlow<Color?> get() = _colorActual


    // Mensaje de alerta para el usuario.
    private val _mensajeAlerta = MutableStateFlow<String?>(null)
    val mensajeAlerta: StateFlow<String?> get() = _mensajeAlerta

    // Colores disponibles en el juego.
    private val coloresDisponibles = listOf(Verde, Rojo, Azul, Amarillo)


    // Inicializa el juego.
    init {
        iniciarJuego()
    }

    /**
     * Configura un nuevo juego con la secuencia inicial.
     */
    fun iniciarJuego() {
        _secuenciaColores.value.clear()
        _secuenciaUsuario.value.clear()
        gestor.reiniciarPuntos()
        agregarColorNuevo()
    }

    /**
     * Agrega un color aleatorio a la secuencia de juego.
     */
    fun agregarColorNuevo() {
        _secuenciaColores.value.add(coloresDisponibles.random())
        mostrarSecuencia()
    }

    /**
     * Muestra la secuencia de colores al usuario.
     */
    private fun mostrarSecuencia() {
        viewModelScope.launch {
            for (color in _secuenciaColores.value) {
                _colorActual.value = color
                kotlinx.coroutines.delay(800)
                _colorActual.value = null
                kotlinx.coroutines.delay(300)
            }
        }
    }

    /**
     * Recibe el color ingresado por el usuario y lo verifica.
     */
    fun ingresarColorUsuario(color: Color) {
        _secuenciaUsuario.value.add(color)
        verificarSecuencia()
    }

    /**
     * Verifica si la secuencia del usuario es correcta.
     */
    private fun verificarSecuencia() {
        val esCorrecto = _secuenciaUsuario.value.zip(_secuenciaColores.value).all { (u, g) -> u == g }
        if (!esCorrecto) {
            _mensajeAlerta.value = "Error en la secuencia. ¡Intenta de nuevo!"
            iniciarJuego()
        } else if (_secuenciaUsuario.value.size == _secuenciaColores.value.size) {
            gestor.aumentarPuntos()
            _secuenciaUsuario.value.clear()
            agregarColorNuevo()
        }
    }

    /**
     * Limpia el mensaje de alerta.
     */
    fun limpiarAlerta() {
        _mensajeAlerta.value = null
    }
}

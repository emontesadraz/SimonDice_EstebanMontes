package com.example.simondice_estebanmontes

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.simondice_estebanmontes.ui.theme.Amarillo
import com.example.simondice_estebanmontes.ui.theme.Azul
import com.example.simondice_estebanmontes.ui.theme.Rojo
import com.example.simondice_estebanmontes.ui.theme.SimonDice_EstebanMontesTheme
import com.example.simondice_estebanmontes.ui.theme.Verde


@Composable
fun InterfazJuego(viewModel: ControladorJuego = viewModel(), modificador: Modifier = Modifier) {
    val estadoPuntuacion by viewModel.puntuacion.collectAsState()
    val colorActivo by viewModel.colorActual.collectAsState()
    val mensajeAlerta by viewModel.mensajeAlerta.collectAsState()

    Box(
        modifier = modificador
            .fillMaxSize()
            .background(Color.DarkGray)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Puntuación: ${estadoPuntuacion.total}",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White
            )
            EspacioBotones(viewModel, colorActivo)
        }
        mensajeAlerta?.let { mensaje ->
            Snackbar(
                action = {
                    Button(onClick = { viewModel.limpiarAlerta() }) {
                        Text("OK")
                    }
                }
            ) {
                Text(mensaje)
            }
        }
    }
}

@Composable
fun EspacioBotones(viewModel: ControladorJuego, colorActivo: Color?) {
    Column {
        FilaBotones(
            colores = listOf(Verde, Rojo),
            viewModel = viewModel,
            colorActivo = colorActivo
        )
        FilaBotones(
            colores = listOf(Azul, Amarillo),
            viewModel = viewModel,
            colorActivo = colorActivo
        )
    }
}

@Composable
fun FilaBotones(colores: List<Color>, viewModel: ControladorJuego, colorActivo: Color?) {
    Row {
        colores.forEach { color ->
            BotonInteractivo(color, color == colorActivo, viewModel)
        }
    }
}

@Composable
fun BotonInteractivo(color: Color, esActivo: Boolean, viewModel: ControladorJuego) {
    val colorAnimado by animateColorAsState(if (esActivo) Color.Gray else color)
    Button(
        onClick = { viewModel.ingresarColorUsuario(color) },
        modifier = Modifier
            .padding(12.dp)
            .size(100.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(containerColor = colorAnimado)
    ) {}
}
@Preview(showBackground = true)
@Composable
fun InterfazJuegoPreview() {
    SimonDice_EstebanMontesTheme {
        InterfazJuego()
    }
}

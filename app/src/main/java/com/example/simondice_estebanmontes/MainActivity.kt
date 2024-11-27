package com.example.simondice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.simondice_estebanmontes.InterfazJuego

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Usamos el setContent para especificar la UI inicial
        setContent {
            // Aquí es donde se inicializa tu UI
            InterfazJuego()  // Llamas a tu Composable principal
        }
    }
}


# Simon Dice - Juego de Memoria
*Esteban Miguel Montes Adraz* - *2 DAM* - *PMDM*

Simon Dice es una implementación del clásico juego de memoria "Simon Says", donde el jugador debe recordar una secuencia de colores mostrada por la computadora y luego repetirla en el mismo orden.

## Descripción del proyecto

Este proyecto es una versión móvil del juego "Simon Dice" desarrollado en Android utilizando Kotlin y Jetpack Compose para la interfaz de usuario. El objetivo es crear una secuencia de colores que el jugador debe memorizar y repetir, con el fin de incrementar su puntuación. Cada vez que el jugador repite correctamente la secuencia, se añade un nuevo color a la secuencia y se incrementa la puntuación.

## Tecnologías utilizadas
- **Kotlin**: Lenguaje de programación utilizado para el desarrollo de la aplicación.

- **Jetpack Compose**: Biblioteca de Android para construir interfaces de usuario de manera declarativa

- **StateFlow**: Para manejar el estado y las actualizaciones de la secuencia y el puntaje.

- **Coroutines**: Para la ejecución de tareas asíncronas, como mostrar la secuencia de colores.

- **Material3**: Para los componentes UI como botones y textos.

## Estructura del proyecto

El proyecto está dividido en varias clases principales que manejan la lógica del juego y la interfaz de usuario.

### 1. MainActivity.kt

La actividad principal que contiene la interfaz del juego. Dentro de esta actividad se usa Jetpack Compose para construir la interfaz gráfica del juego.

- **Pantalla Principal**: Muestra la secuencia de colores que el jugador debe recordar y un puntaje.
- **Botones de Colores**: Cuatro botones de colores (Verde, Rosa, Azul y Naranja) que el jugador debe pulsar en el mismo orden que los muestra el sistema.

### 2. ControladorJuego.kt (ViewModel)

La clase **ControladorJuego** es el ```ViewModel``` que maneja la lógica de negocio del juego, como el control de la secuencia de colores y el puntaje. Usa **StateFlow** para gestionar el estado de la secuencia de colores, los colores que el jugador ha seleccionado, el puntaje, y los mensajes de error.

***Funciones Principales***

- ```iniciarNuevoJuego()```: Reinicia la secuencia y comienza un nuevo juego.
- ```agregarColorASecuencia()```: Añade un color aleatorio a la secuencia del juego.
- ```mostrarSecuencia()```: Muestra la secuencia de colores para que el jugador la memorice
- ```ingresarColorUsuario()```: Registra la selección del jugador y verifica si la secuencia es correcta
- ```verificarSecuenciaUsuario()```: Compara la secuencia ingresada por el jugador con la secuencia del sistema. Si son iguales, aumenta el puntuaje, si no, muestra un mensahe de error.

### 3. DatosPuntuacion
Esta clase gestiona el puntaje del jugador utilizando **StateFlow** para mantener y actualizar el puntaje en tiempo real.
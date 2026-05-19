//(C) Programacion Orientada al Objeto, UCN, 2026.

/* Project Diva X
 *
 * Project Diva X es un juego de ritmo clasico desarrollado por (C)SEGA, conformado por canciones y modulos asociados
 * a un atuendo.
 * Este codigo facilita el calculo entre el modulo escogido por el jugador y la afinidad de la cancion seleccionada y
 * calcula el puntaje maximo obtenible en una cancion en base al modulo del jugador.
 */

/*
 * Estructura (para que entiendas un poco mi idea):
 *
 * Hagamos metodos para cada cosa y los llamamos mediante un main asi es mas facil estructurar el menu
 * principal,como el taller 1, considero q es lo mas sencillo :P
 *
 *"-" = Codigo tal cual
 * "n)" = Dato ingresado por el usuario
 *
 * Main:
 * -Se registran las canciones mediante el archivo de texto (leerCanciones).
 *
 * 1) Se ingresa el usuario (ejemplo en pdf)
 * -Carga el archivo de usuarios y se valida que el usuario exista, si existe se sacan los modulos de ese
 * usuario, si no, puede ingresar otro nombre o crear un nuevo usuario sin modulos (leerPersonajes).
 *
 * -Se muestra el menu (mostrarMenu).  (ejemplo en pdf)
 * 2.1) Opcion (1,2,3)
 *
 *      Opcion 1 (mostrarUsuario):
 *      -Se muestra el usuario junto a sus modulos, si no tiene modulos se muestra que no tiene, que ingrese uno.
 *
 *      Opcion 2 (calcularPuntaje):
 *      -SOLO EN CASO DE TENER MODULOS REGISTRADOS, se muestran las canciones registradas.
 *      2.2) Se solicita la cancion (escogerCancion)
 *      - Se muestran los modulos disponibles con su afinidad y potenciador en base a la cancion, con opcion a salir en caso
 *        de no querer calcular con esa cancion.
 *      2.3) Se solicita el modulo o da la opcion a retroceder.
 *      -EN CASO DE NO TENER MODULO, se envia a crear un nuevo modulo (crearModulo) y luego se
 *       selecciona cancion (escogerCancion)
 *
 *      Opcion 3 (crearModulo):
 *      3.1) Se solicita el nombre
 *      3.2) Se solicita la afinidad
 *      - Se crea el modulo y se guarda.
 *
 *      Opcion 4 (guardarDatos):
 *      -Se sobreescriben los cambios del usuario en el bloc de notas y se cierra el programa Main.
 *
 * Modelo del dominio y diagrama de clases (aca si ayudame pq no cacho mucho):
 *
 * -Modelo del dominio:
 *
 * partida)?: modulo,cancion
 *
 * canciones: nombre, dificultad*, puntaje, afinidad*
 * dificultad: facil, normal, dificil, extremo
 * afinidad: neutral, cute, cool, elegant, quirky
 *
 * usuario: nombre, modulo
 * modulo: nombre, afinidad
 *
 *
 */

import java.util.Scanner;
import java.util.Random;
import stdlib.ln;

public class Main {

    public static void main(String[] args) {


        /* public static int lineasArchivo(string nombreArchivo) {

            In archivo = new In(nombreArchivo);


        }


        Jugador[] jugadores = int [jugadoresEnLista + 1];

        boolean jugadorHallado = false;
        Scanner sc = new Scanner(System.in);

        while(jugadorHallado = false) {

            System.out.println("BIENVENIDO A PROJECT DIVA X! ^o^");
            System.out.println("Ingrese su usuario:");

            String nombreJugador = sc.nextLine();

            for (int i = 0; i < cantJugadores; i++) {
                if nombreJugador ==
            }



        }*/

    }



}

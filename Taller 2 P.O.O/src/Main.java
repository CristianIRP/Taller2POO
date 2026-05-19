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
import edu.princeton.cs.stdlib.In;

public class Main {

    public static void main(String[] args) {


        int cantJugadores = lineasArchivo("jugadores.csv");
        int cantCanciones = lineasArchivo("canciones.csv");

        Cancion[] canciones = new Cancion[cantCanciones];
        leerCanciones("canciones.csv",canciones);

        Jugador[] jugadoresEnTexto = new Jugador[cantJugadores + 1];
        leerJugadores("jugadores.csv",jugadoresEnTexto);

        Scanner sc = new Scanner(System.in);
        boolean jugadorHallado = false;

        String nombreJugador5 = jugadoresEnTexto[4].getNombre();

        System.out.println("Numero del quinto jugador en el texto: " + nombreJugador5);

    }

    public static int lineasArchivo(String nombreArchivo) {

        int contadorLineas = 0;

        In archivo = new In(nombreArchivo);

        if (archivo.exists()) {
            while (archivo.hasNextLine()) {
                archivo.readLine();
                contadorLineas++;
            }
            archivo.close();
        }
        return contadorLineas;
    }

    public static void leerJugadores(String nombreArchivo,Jugador[] jugadoresRegistrados){

        In archivo = new In(nombreArchivo);
        int nroJugador = 0;

        while (archivo.hasNextLine()) {

            String linea = archivo.readLine();

            if (linea.trim().isEmpty()) {
                continue;
            }

            String[] partes = linea.split(",");
            String nombreJugador = partes[0].trim();

            Jugador nuevoJugador = new Jugador(nombreJugador);

            if (partes.length > 1) {

                String textoModulos = partes[1].trim();
                String[] modulos = textoModulos.split(";");

                for (int i = 0; i < modulos.length; i++) {

                    String moduloIndividual = modulos[i].trim();
                    String[] datosModulo = moduloIndividual.split(":");

                    String nombreModulo = datosModulo[0].trim();
                    String nombreAfinidad = datosModulo[1].trim().toUpperCase();

                    Afinidad afinidadModulo = Afinidad.valueOf(nombreAfinidad);

                    Modulo moduloJugador = new Modulo(nombreModulo,afinidadModulo);

                    nuevoJugador.agregarModulo(moduloJugador);


                }
                jugadoresRegistrados[nroJugador] = nuevoJugador;
                nroJugador++;
            } else {
                jugadoresRegistrados[nroJugador] = nuevoJugador;
                nroJugador++;
            }
        }
    }
    public static void leerCanciones(String nombreArchivo,Cancion[] canciones){

        In archivo = new In(nombreArchivo);
        int nroCancion = 0;

        while (archivo.hasNextLine()) {

            String linea = archivo.readLine();

            if (linea.trim().isEmpty()) {
                continue;
            }

            String[] partes = linea.split(",");

            String nombreCancion = partes[0].trim();

            String textoDificultad = partes[1].trim().toUpperCase();
            Dificultad dificultadCancion = Dificultad.valueOf(textoDificultad);

            int puntajeBase = Integer.parseInt(partes[2].trim());

            String textoAfinidad = partes[3].trim().toUpperCase();
            Afinidad afinidadCancion = Afinidad.valueOf(textoAfinidad);


            Cancion nuevaCancion = new Cancion(nombreCancion,dificultadCancion,puntajeBase,afinidadCancion);

            canciones[nroCancion] = nuevaCancion;
            nroCancion++;

        }
    }
}






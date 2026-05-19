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

/* Explicación pal vichoide:
 * pq usamos enum?
 * porque las afinidades son constantes fijas (más abajo explico) y así podemos evitar errores de escritura
 * del usuario, pa eso usamos el touppercase() y el trim() en el texto.
 * El metodo desdeString nos extrae este texto y borra los espacios y lo pasa a mayuscula para q coincida con enum
 *
 * la class Modulo, q hace o q funcion cumple?
 * la clase modulo almnacena nombre y el tipo de afinidad asociada, sirve como contenedor (cmo explicó el profe)
 * y aparte le puse constructores y getters
 *
 * class Jugador
 * usamos un arreglo de modulos (Modulo[]) para crear un espacio fijo en la memoria que almacene hasta 20 modulos
 * creé un contador q se llama "cantModulos" y usamos la función size() para saber cuantos elementos hay en el arreglo
 * El metodo agregarMoudlo guarda la posicion del contador cada vez q leemos el archivo .csv, para luego incrementar
 * el indice cantModulos para que no se sobrepase del limite 20 y evitar un error en el q se salga de la lista
 *
 * class Cancion
 * sigue la misma logica que class Modulo, pero adaptando las caracteristicas de una cancion, tomando en cuenta el
 * nombre, dificultad, puntajebase, afinidad y estos tienen sus getters con sus constructores.
 *
 */
 // clases y enums
public class Main {
    // utilizamos enum porque son constantes fijas (por ejemplo, días de la semana sería "enum semana{lunes,martes,..}")
    enum afinidad{
        NEUTRAL, CUTE, COOL, ELEGANT, QUIRKY;

        public static Afinidad desdeString (String texto){
            return Afinidad.valueOf(texto.trim().toUpperCase());
        }
    }
    class modulo {
        private String nombre;
        private Afinidad afinidad;

        public modulo(String nombre, Afinidad afinidad){
            this.nombre = nombre;
            this.afinidad = afinidad;
        }
        public String getNombre() {
            return nombre;
        }
        public Afinidad getAfinidad(){
            return afinidad;
        }
    }
    class Jugador{
        private String nombre;
        private Modulo[] modulos;
        private int cantModulos;

        public Jugador(String nombre){
            this.nombre = nombre;
            this.modulos = new Modulo[20];
            this.cantModulos = 0;
        }
        public void agregarModulo(Modulo mod){
            if (cantModulos == modulos.length){
                modulos[cantModulos] = mod;
                cantModulos++;
            }
        }
    }
    public String getNombre(){
        return nombre;
    }
    public Modulo[] getModulos() {
        return modulos;
    }
    public int getCantModulos(){
        return cantModulos();
    }

    class Cancion{
        private String nombre;
        private String dificultad;
        private int puntajeBase;
        private Afinidad afinidad;

        public Cancion(String nombre, String dificultad, int puntajeBase, Afinidad afinidad){
            this.nombre = nombre;
            this.dificultad = dificultad;
            this.puntajeBase = puntajeBase;
            this.afinidad = afinidad;
        }
        public String getNombre() {
            return nombre;
        }
        public String getDificultad() {
            return dificultad;
        }
        public int getPuntajeBase() {
            return puntajeBase;
        }
        public Afinidad getAfinidad() {
            return afinidad;
        }
    }
 }

/*
 *(C) Programacion Orientada al Objeto, UCN, 2026.
 * @author Vicente Toro <vicente.toro.pande@gmail.com>
 * @author Cristian Rodriguez <crigropi@gmail.com>
 */

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
 * partida)?: modulo,cancion
 *
 * canciones: nombre, dificultad*, puntaje, afinidad*
 * dificultad: facil, normal, dificil, extremo
 * afinidad: neutral, cute, cool, elegant, quirky
 *
 * usuario: nombre, modulo
 * modulo: nombre, afinidad
 */

import java.util.Scanner;
import edu.princeton.cs.stdlib.In;

public class Main {

    /*
     * Metodo main en el que se llamara a los metodos necesarios para el funcionamiento del programa
     */
    public static void main(String[] args) {
        // Analizamos la cantidad de jugadores y cantidad de canciones que hay
        int cantJugadores = lineasArchivo("jugadores.csv");
        int cantCanciones = lineasArchivo("canciones.csv");

        // Array que guarda las canciones del archivo canciones.csv
        Cancion[] canciones = new Cancion[cantCanciones];
        leerCanciones("canciones.csv",canciones);

        // Array que guarda los jugadores registrados en conjunto de sus modulos y afinidades de estos mismos
        Jugador[] jugadores = new Jugador[cantJugadores + 1];
        leerJugadores("jugadores.csv",jugadores);

        // Establecemos el jugador registrado con el que jugaremos (no se bien como explicarlo sin decir jugador
        // tantas veces jdsjksd)
        Jugador jugadorActual = establecerJugador(jugadores, cantJugadores);

        int opciones = 0;

        while ( opciones != 4) {

            Scanner sc = new Scanner(System.in);

            // Imprimimos el menu
            System.out.println("Ingrese una opcion:\n" +
                    "1.- Mostrar datos del jugador.\n" +
                    "2.- Calcular puntaje maximo.\n" +
                    "3.- Agregar modulo.\n" +
                    "4.- Guardar y salir.");

            // Usuario ingresa la opcion
            int opcion = sc.nextInt();

            // if opciones...
            if (opcion == 1) {
                mostrarJugador(jugadorActual);

            } else if (opcion == 2) {
                calcularPorcentaje(jugadorActual,canciones);

            } else if (opcion == 3) {
                crearModulo(jugadorActual);
            } else if (opcion == 4) {
                guardarJugadores("jugadores.csv", jugadores, cantJugadores);
            } else {
                System.out.println("\n" +
                        "/// ERROR: Ingrese una opcion valida. ///\n");
            }
        }




    }

    /*
     * Metodo para crear el modulo definido por el usuario
     * @param jugadorActual Jugador con el que se jugara, inicia en null y se le asigna un valor en
     *  el metodo establecerJugador
     */
    public static void crearModulo(Jugador jugadorActual) {

        Scanner sc = new Scanner(System.in);

        // cantidad de modulos del jugador
        int cantModulos = jugadorActual.getCantModulos();
        // array de modulos registrados en el jugador
        Modulo[] modulos = jugadorActual.getModulos();

        System.out.println("====== CREAR MODULO ======\n" +
                "Ingrese el nombre del modulo nuevo:");
        String nombreModulo = sc.nextLine();

        boolean jugadorRepetido = false;
        Afinidad afinidadNueva = null;


        for (int i = 0; i<cantModulos; i++) {
            String nombreModuloCiclo = modulos[i].getNombre();
            if (nombreModulo.equalsIgnoreCase(nombreModuloCiclo)) {
                jugadorRepetido = true;
            }
        }
        // si el modulo no existe, creamos uno
        if (jugadorRepetido == false) {
            System.out.println("Ingrese la afinidad de el modulo:\n" +
                    "MODULOS: NEUTRAL / CUTE / COOL / ELEGANT / QUIRKY\n");

            String afinidadDeseada = sc.nextLine().trim().toUpperCase();

            try {
                // convierte el texto a un valor valido del enum afinidad
                afinidadNueva = Afinidad.valueOf(afinidadDeseada);
            } catch (IllegalArgumentException e) {
                System.out.println(" /// ERROR: Afinidad invalida");
            }
            // si la afinidad fue valida y se logro asignar, se registra el modulo
            if (afinidadNueva != null) {
                Modulo moduloNuevo = new Modulo(nombreModulo,afinidadNueva);
                jugadorActual.agregarModulo(moduloNuevo);
                System.out.println("Modulo registrado con exito! Reviselo en (1.- Mostrar datos del jugador).");
            }

        } else {
            System.out.println("/// ERROR:Modulo ya existente. ///");
        }
    }

    /*
     * Metodo para calcular el puntaje maximo obtenible en una cancion en base a los modulos del jugador
     * @param jugadorActual Jugador con el que se esta jugando actualmente
     * @param canciones Array que contiene las canciones del archivo canciones.csv
     */
    public static void  calcularPorcentaje(Jugador jugadorActual,Cancion[] canciones){

        Scanner sc = new Scanner(System.in);
        double puntajeTotal = 0;

        // recuperamos la info de los modulos del jugador actual
        int cantModulos = jugadorActual.getCantModulos();
        Modulo[] modulos = jugadorActual.getModulos();


        // validamos que el jugador tenga al menos un modulo
        if(cantModulos != 0) {
            System.out.println("=== PUNTAJE MAXIMO OBTENIBLE ===\n" +
                    "Canciones registradas (Nombre, Dificultad, Puntaje Base, Afinidad):\n");
            // desplegamos la lista completa de canciones que estan en el archivo
            for (int i = 0; i<canciones.length;i++) {

                String nombreCancion = canciones[i].getNombre();
                Dificultad dificultadCancion = canciones[i].getDificultad();
                int puntajeBaseCancion = canciones[i].getPuntajeBase();
                Afinidad afinidadCancion = canciones[i].getAfinidad();

                System.out.println("[" + (i + 1) + "]" + nombreCancion + " | " + dificultadCancion + " | " +
                        puntajeBaseCancion + " | " + "Afinidad: " + afinidadCancion);
            }

            System.out.println("Elija una cancion para calcular:");

            // restamos 1 al indicce ingresado ya que el usuario ve la lista partiendo desde el 1 pero en realidad
            // empieza desde 0, ojo con eso
            int indiceCancion = sc.nextInt();
            Cancion cancionEscogida = canciones[indiceCancion - 1];
            String nombreCancion = cancionEscogida.getNombre();
            Afinidad afinidadCancion = cancionEscogida.getAfinidad();
            int puntajeBase = cancionEscogida.getPuntajeBase();

            System.out.println("Cancion escogida: " + nombreCancion + " | " + afinidadCancion);
            System.out.println("\n" +
                    "Escoja un modulo:");
            // desplegamos la lista de modulos que posee el jugador actual
            for(int i = 0; i<cantModulos; i++) {
                String nombreModulo = modulos[i].getNombre();
                Afinidad afinidadModulo = modulos[i].getAfinidad();

                System.out.println("-Modulo " + (i + 1) + ": " + nombreModulo + "/ " + afinidadModulo);
            }
            // obtenemos el modulo seleccionado por el usuario (ojo con el desfase de -1)
            int moduloIngresado = sc.nextInt();
            Modulo moduloEscogido = modulos[moduloIngresado - 1];

            // Evaluamos todas las afinidades y su compatibilidad
            if(moduloEscogido.getAfinidad() == afinidadCancion) {
                // caso 1, afinidades identicas
                puntajeTotal = puntajeBase * 1.2;
                System.out.println("El puntaje maximo obtenible es de " + puntajeTotal + " puntos.");
            } else if(moduloEscogido.getAfinidad() == Afinidad.NEUTRAL) {
                // caso 2, afinidad neutral
                puntajeTotal = puntajeBase * 1.1;
                System.out.println("El puntaje maximo obtenible es de " + puntajeTotal + " puntos.");
            } else {
                // caso 3, afinidad distinta pero no neutral
                puntajeTotal = puntajeBase * 1.15;
                System.out.println("El puntaje maximo obtenible es de " + puntajeTotal + " puntos.");
            }
        } else {
            System.out.println("\n" +
                    "/// ERROR: Jugador sin modulos registrados ///\n" +
                    "Ingrese modulos para calcular un puntaje maximo.\n");
        }
    }

    /*
     * Mostrara al jugador seleccionado y retornara su nombre y sus modulos registrados con
     * sus nombres de modulos correspondientes y afinidades.
     * @param jugadorActual Jugador actual con el que se jugara, inicia en null
     */
    public static void mostrarJugador(Jugador jugadorActual) {

        String nombreJugador = jugadorActual.getNombre();
        int cantModulosJugador = jugadorActual.getCantModulos();
        Modulo[] modulosJugador = jugadorActual.getModulos();

        System.out.println("------ DATOS DEL JUGADOR ------\n" +
                "Nombre del jugador:" + nombreJugador + "\n" +
                "\n" +
                "Modulos registrados (Nombre / Afinidad)" +
                "\n");
        // Verificar si la lista tiene elementos, en caso contrario, mostrar que no hay modulos por medio de una aviso
        if (cantModulosJugador != 0) {
            for (int i = 0; i<cantModulosJugador; i++) {

                String nombreModulo = modulosJugador[i].getNombre();
                Afinidad afinidadModulo = modulosJugador[i].getAfinidad();
                System.out.println("-Modulo " + (i + 1) + ": " + nombreModulo + "/ " + afinidadModulo);
            }
        } else {
            System.out.println("Modulos no registrados...");
        }
        System.out.println("-------------------------");
    }

    /*
     * Metodo para establecer el jugador actual (con el que se jugara)
     * @param jugadores Array de jugadores dentro del sistema
     * @param cantJugadores Cantidad de jugadores en el array
     * @return jugadorActual Jugador con el que se jugara (valga la redundancia)
     */
    public static Jugador establecerJugador(Jugador[] jugadores,int cantJugadores) {

        Jugador jugadorActual = null;
        Scanner sc = new Scanner(System.in);

        // el bucle no se detendra hasta encontrar un jugador registrado o crear uno
        while(jugadorActual == null)  {

            System.out.println("Ingrese su jugador:");
            String nombreBuscado = sc.nextLine().trim();
            boolean jugadorHallado = false;
            // recorremos el arreglo buscando coincidencias de nombres
            for (int i = 0; i < cantJugadores; i++) {

                String nombreCiclo = jugadores[i].getNombre();

                if(nombreBuscado.equalsIgnoreCase(nombreCiclo)) {
                    // asignmaos el jugador encontrado para romper el bucle while
                    jugadorActual = jugadores[i];
                    jugadorHallado = true;
                }
            }
            // submenu en caso de que el jugador ingresado no exista
            if (jugadorHallado == false) {
                System.out.println("// No se ha encontrado el jugador ingresado //\n" +
                        "1.- Ingresar otro jugador\n" +
                        "2.- Crear jugador nuevo");


                int opcionIngresada = sc.nextInt();
                // limpiamos el buffer para evitar los problemas con los sgtes sc.nextline
                sc.nextLine();

                if (opcionIngresada == 1) {
                    System.out.println("Se intentara otro jugador...");

                } else if (opcionIngresada == 2) {
                    System.out.println("Creando jugador...");
                    System.out.println("Ingrese su nombre:");

                    String nombreNuevo = sc.nextLine();

                    // analizamos el perfil y lo añadimos al final de los registros
                    Jugador jugadorNuevo = new Jugador(nombreNuevo);
                    jugadores[cantJugadores] = jugadorNuevo;

                    // establecemos que el jugador creado como el jugador en sesion
                    jugadorActual = jugadores[cantJugadores];
                    // incrementamos el tamaño de la cantJugadores (contador)
                    cantJugadores++;

                    System.out.println("Jugador " + nombreNuevo + " creado con exito.\n" +
                            "Recuerde crear modulos para poder jugar las canciones!");
                }
            }
        }
        return jugadorActual;
    }

    /*
     * Cuenta la cantidad de lineas que contiene el archivo de texto
     * y verifica la existencia del archivo
     * @param nombreArchivo
     * @return contadorLineas El numero de lineas encontradas en el archivo, si el archivo no existe, retorna 0
     */
    public static int lineasArchivo(String nombreArchivo) {

        int contadorLineas = 0;

        In archivo = new In(nombreArchivo);
        // comprobamos que el archivo existe antes de intentar leerlo para que no lance error
        if (archivo.exists()) {
            // recorremos el archivo hasta llegar al final
            while (archivo.hasNextLine()) {
                // avanza a la siguiente linea
                archivo.readLine();
                // registra la ultima linea procesada y le añade +1 al contadorLineas
                contadorLineas++;
            }
            // cerramos el archivo
            archivo.close();
        }
        return contadorLineas;
    }

    /*
     * Lee el archivo de texto con los jugadores registrados y sus modulos asociados
     * @param nombreArchivo Nombre del archivo
     * @param jugadores Array de jugadores donde se guardan los jugadores registrados en el archivo jugadores.csv
     */
    public static void leerJugadores(String nombreArchivo,Jugador[] jugadores){

        In archivo = new In(nombreArchivo);
        int nroJugador = 0;

        while (archivo.hasNextLine()) {

            String linea = archivo.readLine();
            // ignoramos/saltamos las lineas vacias para no corromper nada
            if (linea.trim().isEmpty()) {
                continue;
            }

            // separamos la linea en 2 partes, nombreJugador y su bloque de modulos
            String[] partes = linea.split(",");
            String nombreJugador = partes[0].trim();

            Jugador nuevoJugador = new Jugador(nombreJugador);
            // verificamos que la linea tiene si o si info de modulos despues de la coma
            if (partes.length > 1) {

                String textoModulos = partes[1].trim();
                // separamos los modulos con ";"
                String[] modulos = textoModulos.split(";");

                //procesamos uno a uno cada bloque de modulo que extraigamos
                for (int i = 0; i < modulos.length; i++) {

                    String moduloIndividual = modulos[i].trim();
                    // Separamos el nombre del modulo con su afinidad con el ":"
                    String[] datosModulo = moduloIndividual.split(":");

                    String nombreModulo = datosModulo[0].trim();
                    String nombreAfinidad = datosModulo[1].trim().toUpperCase();

                    // convertimos el texto de afinidad a un valor valido del enum afinidad
                    Afinidad afinidadModulo = Afinidad.valueOf(nombreAfinidad);

                    Modulo moduloJugador = new Modulo(nombreModulo,afinidadModulo);
                    // Incorporamos el modulo a los modulos relacionados con el jugador
                    nuevoJugador.agregarModulo(moduloJugador);
                }
                jugadores[nroJugador] = nuevoJugador;
                nroJugador++;
            } else {
                // si el jugador no tiene modulos, guardamos su perfil sin nada mas
                jugadores[nroJugador] = nuevoJugador;
                nroJugador++;
            }
        }
    }

    /*
     * Metodo que lee las canciones
     * @param nombreArchivo Nombre del archivo de texto con las canciones registradas
     * @param canciones Array de canciones donde se guardan las canciones del archivo
     */
    public static void leerCanciones(String nombreArchivo,Cancion[] canciones){

        In archivo = new In(nombreArchivo);
        int nroCancion = 0;

        while (archivo.hasNextLine()) {

            String linea = archivo.readLine();
            // Ignoramos las lineas en blanco que puedan ocurrir en el csv
            if (linea.trim().isEmpty()) {
                continue;
            }
            // separamos los datos de las filas con la ","
            String[] partes = linea.split(",");

            String nombreCancion = partes[0].trim();
            // llevamos el string a la enum de dificultad
            String textoDificultad = partes[1].trim().toUpperCase();
            Dificultad dificultadCancion = Dificultad.valueOf(textoDificultad);

            // convertimos el texto del puntaje a un valor int
            int puntajeBase = Integer.parseInt(partes[2].trim());

            // llevamos el string de la cuarta columna (0,1,2,3) a la enum de afinidad
            String textoAfinidad = partes[3].trim().toUpperCase();
            Afinidad afinidadCancion = Afinidad.valueOf(textoAfinidad);

            // instanciamos el objeto con todas las propieades resueltas y lo guardamos en el array de canciones
            Cancion nuevaCancion = new Cancion(nombreCancion,dificultadCancion,puntajeBase,afinidadCancion);

            canciones[nroCancion] = nuevaCancion;
            nroCancion++;
        }
    }

    /*
     * Metodo que guarda los jugadores en el archivo jugadores.csv
     * @param nombreArchivo Nombre del archivo donde se guardaran los jugadores que se registren
     * @param jugadores Array de jugadores registrados
     * @param cantJugadores Cantidad de jugadores en el registro
     */
    public static void guardarJugadores(String nombreArchivo, Jugador[] jugadores, int cantJugadores){
        try{
            // Printwriter de java para escribir
            java.io.PrintWriter escritor = new java.io.PrintWriter(nombreArchivo);

            for (int i = 0; i < jugadores.length; i++) {
                Jugador j = jugadores[i];
                // si hay un espacio nulo, lo saltamos
                if (j == null) continue;

                StringBuilder linea = new StringBuilder();
                linea.append(j.getNombre());

                int cantModulos = j.getCantModulos();
                Modulo[] modulos = j.getModulos();

                // Si el jugador tiene modulos, estructuramos el archivo csv por medio de
                // nombreJugador,modulo1:afinidad1;modulo2:afinidad2;modulo3:afinidad3....
                if (cantModulos > 0) {
                    linea.append(",");
                    for (int m = 0; m < cantModulos; m++) {
                        linea.append(modulos[m].getNombre()).append(":").append(modulos[m].getAfinidad());
                        ;
                        if (m < cantModulos - 1) {
                            linea.append(";");
                        }
                    }
                }
                // aca escribimos la linea
                escritor.println(linea.toString());
            }
            //cerramos para confirmar la escritura
            escritor.close();
            System.out.println("Jugador guardado");
        } catch(java.io.IOException e){
            // por si ocurre algun error, tira este texto
            System.out.println("Error al guardar el jugador");
        }
    }
}
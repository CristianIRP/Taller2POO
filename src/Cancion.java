/*
 * Representa las canciones dentro del juego
 * contiene la informacion de cada cancion y el nivel de dificultad, el puntaje
 * inicial asignado y la afinidad que requiere para ser ejecutada
 */
public class Cancion {

    private String nombre;
    private Dificultad dificultad;
    private int puntajeBase;
    private Afinidad afinidad;

    /*
     * Constructor completo para inicializar las propiedades de las canciones
     * @param nombre Titulo de la cancion
     * @param dificultad Dificultad de la cancion (FACIL, NORMAL, DIFICIL, EXTREMO)
     * @param puntajeBase Puntaje base que otorga la cancion al ser completada
     * @param afinidad Tipo de afinidad asociada a la pista
     */
    public Cancion(String nombre, Dificultad dificultad, int puntajeBase, Afinidad afinidad){
        this.nombre = nombre;
        this.dificultad = dificultad;
        this.puntajeBase = puntajeBase;
        this.afinidad = afinidad;
    }
    public String getNombre() {
        return nombre;
    }
    public Dificultad getDificultad() {
        return dificultad;
    }
    public int getPuntajeBase() {
        return puntajeBase;
    }
    public Afinidad getAfinidad() {
        return afinidad;
    }
}

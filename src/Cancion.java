public class Cancion {

    private String nombre;
    private Dificultad dificultad;
    private int puntajeBase;
    private Afinidad afinidad;

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

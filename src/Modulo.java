/*
 * Representa un modulo que puede ser equipado por los jugadores
 * Cada modulo tiene un nombre identificador y esta asociado con una afinidad
 */
public class Modulo {

    private String nombre;
    private Afinidad afinidad;
    /*
     * Constructor para iniciar las propiedades del modulo
     * @param nombre Nombre descriptivo del modulo
     * @param afinidad el tipo de afinidad que otorga o posee este modulo
     */
    public Modulo(String nombre, Afinidad afinidad){
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


public class Modulo {

    private String nombre;
    private Afinidad afinidad;

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


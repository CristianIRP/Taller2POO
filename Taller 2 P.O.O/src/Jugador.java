public class Jugador {

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
    public String getNombre(){
        return nombre;
    }
    public Modulo[] getModulos() {
        return modulos;
    }
    public int getCantModulos(){
        return cantModulos;
    }
}

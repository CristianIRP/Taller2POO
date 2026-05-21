/*
 * Representa a los jugadores, se encarga de almacenar la informacion basica de estos
 * y guardarlos en un arreglo con un maximo de 10 modulos por jugador.
 */


public class Jugador {

    private String nombre;
    private Modulo[] modulos;
    private int cantModulos;

    /*
     * Constructor de la clase, recibe el nombre del jugador y crea un arreglo de modulos con un maximo de 10
     * @param nombre El nombre del jugador
     */
    public Jugador(String nombre){
        this.nombre = nombre;
        this.modulos = new Modulo[10];
        this.cantModulos = 0;
    }
    /*
     * Agrega un modulo al almacenamiento del jugador
     * si es que aun queda espacio en el arreglo.
     * @param mod El objeto Modulo que se desea asociar al jugador
     */
    public void agregarModulo(Modulo mod){
        if (cantModulos < modulos.length){
            modulos[cantModulos] = mod;
            cantModulos++;
        } else {
            System.out.println("Se ha alcanzado el maximo de 10 modulos por usuario.");
        }
    }
    // metodos getters
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

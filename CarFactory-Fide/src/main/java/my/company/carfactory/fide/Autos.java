/*
Esta fábrica, en específico, produce 3 autos emblema:
Un superauto deportivo
Un carro de carga de alta gama
Una maquinaria de última tecnología para trabajos pesados
*/
package my.company.carfactory.fide;

/**
 *
 * @author 50660
 */
public class Autos {
    //Atributos
    private int id;
    private String nombre;
    private String motor;
    private String carroceria;
    private String llantas;
    private int precio;
    
    //Constructores

    public Autos(int id, String nombre, String motor, String carroceria, String llantas, int precio) {
        this.id = id;
        this.nombre = nombre;
        this.motor = motor;
        this.carroceria = carroceria;
        this.llantas = llantas;
    }
    //Constructor default
    public Autos(){
        
    }
    //Setters y Getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getCarroceria() {
        return carroceria;
    }

    public void setCarroceria(String carroceria) {
        this.carroceria = carroceria;
    }

    public String getLlantas() {
        return llantas;
    }

    public void setLlantas(String llantas) {
        this.llantas = llantas;
    }
    public void setprecio(int precio) {
        this.precio = precio;
    }

    public String getprecio() {
        return precio;
    }
    
    //to String

    @Override
    public String toString() {
        return "Autos{" + "id=" + id + ", nombre=" + nombre + ", motor=" + motor + ", carroceria=" + carroceria + ", llantas=" + llantas + ", precio=" + precio + '}';
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.company.carfactory.fide;

/**
 *
 * @author 50660
 */
public class Nodo {
    private Autos datos;//Información de los autos
    private Nodo siguiente;//Referencia al sig objeto de tipo Nodo
    
    //Constructor parametrizado

    public Nodo(Autos datos) {
        this.datos = datos;
    }
    //  Getters y setters

    public Autos getDatos() {
        return datos;
    }

    public void setDatos(Autos datos) {
        this.datos = datos;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
    //to String

    @Override
    public String toString() {
        return "Nodo{" + "datos=" + datos + '}';
    }
    
}

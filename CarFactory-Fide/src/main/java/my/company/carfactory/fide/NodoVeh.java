//Clase Nodo Vehiculo para la construccion y generar ordenes
package com.mycompany.carfactory.fide;

/**
Encargada Britany Campos Aguilar
 */
public class NodoVeh {
   //Atributos
    private String tipoAuto;
    private NodoVeh Siguiente;
    private String[] materiales;
//Constructores
    public NodoVeh(String tipoAuto) {
        this.tipoAuto = tipoAuto;
    }

    //constructor default
    public String getTipoAuto() {
        return tipoAuto;
    }

    //Getters y setters
    public void setTipoAuto(String tipoAuto) {
        this.tipoAuto = tipoAuto;
    }

    public NodoVeh getSiguiente() {
        return Siguiente;
    }

    public void setSiguiente(NodoVeh Siguiente) {
        this.Siguiente = Siguiente;
    }

    public String[] getMateriales() {
        return materiales;
    }

    public void setMateriales(String[] materiales) {
        this.materiales = materiales;
    }

//Reescritura de las ordenes pendientes   
    @Override
    public String toString() {
        return "Pendientes de atender: " + tipoAuto;
    }
    
    

}

package Código_juego;
//Proyecto final Estructura de Datos
//Integrantes de grupo CAMPOS AGUILAR BRITANY, FLORES VARGAS MAYERLING, MORA ARIAS MALDO
public class NodoOrden {
    //atributos
    public Auto Vehiculo;
    public NodoOrden next;
    public int index;

    //constructor parametrizado
    public NodoOrden(Auto pVehiculo,int pIndex){
        Vehiculo = pVehiculo;
        index = pIndex;
    }
    //constructor default

    public NodoOrden() {
    }
    //Getter y setters

    public Auto getVehiculo() {
        return Vehiculo;
    }

    public void setVehiculo(Auto Vehiculo) {
        this.Vehiculo = Vehiculo;
    }

    public NodoOrden getNext() {
        return next;
    }

    public void setNext(NodoOrden next) {
        this.next = next;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    
}

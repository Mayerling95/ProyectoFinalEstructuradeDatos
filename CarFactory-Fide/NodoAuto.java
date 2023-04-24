package Código_juego;
//Proyecto final Estructura de Datos
//Integrantes de grupo CAMPOS AGUILAR BRITANY, FLORES VARGAS MAYERLING, MORA ARIAS MALDO
public class NodoAuto {
    //atributos
    public NodoAuto next;
    public Auto auto;
    
    //constructor parametrizado
    public NodoAuto(Auto auto){
        this.auto = auto;
    }
    
    //constructor default

    public NodoAuto() {
    }
    
    //Getters y setters

    public NodoAuto getNext() {
        return next;
    }

    public void setNext(NodoAuto next) {
        this.next = next;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }
    
}

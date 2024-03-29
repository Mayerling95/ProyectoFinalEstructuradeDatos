package Código_juego;
//Proyecto final Estructura de Datos
//Integrantes de grupo CAMPOS AGUILAR BRITANY, FLORES VARGAS MAYERLING, MORA ARIAS MALDO
public class NodoMaterial {
    //atributos
    public NodoMaterial next;
    public Material material;
    
    //constructor parametrizado
    public NodoMaterial(Material material){
        this.material = material;
        next = null;
    }
    
    //constructor default

    public NodoMaterial() {
    }

    //Getters y setters

    public NodoMaterial getNext() {
        return next;
    }

    public void setNext(NodoMaterial next) {
        this.next = next;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
    
}

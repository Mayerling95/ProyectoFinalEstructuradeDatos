package Código_juego;
//Proyecto final Estructura de Datos
//Integrantes de grupo CAMPOS AGUILAR BRITANY, FLORES VARGAS MAYERLING, MORA ARIAS MALDO
import javax.swing.JLabel;

public class NodoMesa {
    //atributos
    public JLabel label;//Al importar la clase JLabel,se puede crear y 
   //personalizar fácilmente etiquetas de texto en la interfaz.
    public Material material;
    public NodoMesa next;
    
    //constructor parametrizado
    public NodoMesa(JLabel label, Material material){
        this.label = label;
        this.material = material;
        next = null;
    }
    
    //constructor default
    public NodoMesa() {
    }
    
    //Getters y setters

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public NodoMesa getNext() {
        return next;
    }

    public void setNext(NodoMesa next) {
        this.next = next;
    }
    
    
}

package Código_juego;
//Proyecto final Estructura de Datos
//Integrantes de grupo CAMPOS AGUILAR BRITANY, FLORES VARGAS MAYERLING, MORA ARIAS MALDO
import javax.swing.JLabel;

public class ListaMesa {
    //atributos
    public NodoMesa primero;
    public int tamanioreal;//tamaño de la lista
    
    //constructor
    public ListaMesa(){
        primero = null;
        tamanioreal = 0;
    }
    
    //Método insertar donde se van a crear los materiales
     public void insertar(JLabel label, Material material){
        NodoMesa nuevo = new NodoMesa(label, material);

        if (primero == null) {
            primero = nuevo;
        }else{
            nuevo.next = primero;
            primero = nuevo;
        }
    }
    //Método que limpia lo que tenemos en las mesas 
    public void limpiar(){
        NodoMesa actual = primero;
        while(actual != null){//si la mesa es diferente de null limpia los espacios
            actual.label.setIcon(null);
            actual.material = null;
            actual = actual.next;
        }
        tamanioreal = 0;//reincia el tamaño a 0.
    }
}

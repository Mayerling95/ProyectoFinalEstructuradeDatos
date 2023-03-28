package my.company;
import java.util.random.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

public class Cinta {
    //cola con partes 

    //clase de nodo
    public class NodoC{
        private Partes parte;
        private NodoC siguiente;


        public NodoC (Partes parte){
            //atributos
            this.parte = parte;
            this.siguiente = null;

            //getter setter
            public Partes getParte(){ return parte;}
            
            public void setParte(Partes parte){this.parte = parte;}

            public NodoC getSiguiente(){return siguiente;}

            public NodoC setSiguiente(NodoC siguiente){ this.siguiente = siguiente;}
    }

    //atributos
    private int maxPartes; //numero maximo de partes 
    private NodoC primero;
    private NodoC ultimo;
    private int numPartes; // cantidad de partes 

    //constructor 
    public Cinta(){
        this.primero = null;
        this.ultimo = null;
        this.maxPartes = 3;
        this.numPartes = 0;
    }

    //metodos
    public boolean isEmpty(){
        return primero == null;
    }

    public void add(Partes partes){

        if(numPartes >= maxPartes){
            System.out.println("Desbloquear espacios para mas partes");
            return;
        }

        NodoC nuevo = new NodoC(partes);
        if(isEmpty()){
            primero = nuevo;
            ultimo = nuevo;
        }else{
            ultimo.setSiguiente(nuevo);
            ultimo = nuevo;
        }

        numPartes ++;
    }

    public void botar{
        if(){
            
        }

    }

    public void usar{

    }
    
}

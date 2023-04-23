package Código_juego;
//Proyecto final Estructura de Datos
//Integrantes de grupo CAMPOS AGUILAR BRITANY, FLORES VARGAS MAYERLING, MORA ARIAS MALDO
//Ordenes de construir autos
public class ColaOrdenes {
    //llamada de nodo
    //atributos
    public NodoOrden inicio;//primero de la orden
    public NodoOrden finaly;//ultimo de la orden
    public int total = 1;

    //constructor
    public ColaOrdenes(){
        inicio = null;
        finaly = null;
    }

    //método agregar elemento a la cola
    public void agregar(Auto Orden){
        NodoOrden nuevo = new NodoOrden(Orden,total);
        if(inicio == null){
            inicio = nuevo;
            finaly = nuevo;
        }else{
            finaly.next = nuevo;
            finaly = nuevo;
        }
        total++;//aumenta elementos
    }

    //método remover
    //elimina y devuelve el segundo elemento como el primero de la fila
    public Auto remover(){
        if(inicio != null){
            Auto Orden = inicio.Vehiculo;
            inicio = inicio.next;
            return Orden;
        }else{
            return null;
        }
    }
    //Métoddo traerprimero
    //obtener el primer elemento de la pila sin eliminarlo de la misma.
    public Auto traerprimero(){
        if(inicio != null){
            return inicio.Vehiculo;
        }
        return null;
    }
}

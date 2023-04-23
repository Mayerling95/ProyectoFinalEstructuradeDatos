package Código_juego;
//Proyecto final Estructura de Datos
//Integrantes de grupo CAMPOS AGUILAR BRITANY, FLORES VARGAS MAYERLING, MORA ARIAS MALDO
public class ListaAuto {
    //autos a construir llama a NodoAuto
    public NodoAuto primero;
    
    //constructor
    public ListaAuto(){
        primero = null;//lista empieza vacía
    }
    
    //método insertar a la lista el auto a construir
    public void insertar(Auto auto){
        NodoAuto nuevo = new NodoAuto(auto);

        if (primero == null) {
            primero = nuevo;
        }else{
            nuevo.next = primero;
            primero = nuevo;
        }
    }
}

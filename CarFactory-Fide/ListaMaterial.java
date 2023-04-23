package Código_juego;
//Proyecto final Estructura de Datos
//Integrantes de grupo CAMPOS AGUILAR BRITANY, FLORES VARGAS MAYERLING, MORA ARIAS MALDO
public class ListaMaterial {
    //atributos
    public NodoMaterial primero;
    public int tamanio;
    
    //constructor
    public ListaMaterial(){
        primero = null;//lista empieza en 0
        tamanio = 0;
    }
    
    //método insertar
    public void insertar(Material material){
        NodoMaterial nuevo = new NodoMaterial(material);
        //llamada de Nodo material para insertar

        if (primero == null) {
            primero = nuevo;
        }else{
            nuevo.next = primero;
            primero = nuevo;
        }
        tamanio++;//aumenta el tamaño
    }
    
    //Buscar material para insertarlo
    public Material buscar(int index){
        NodoMaterial actual = primero;
        
        while(actual != null){
            if(index == actual.material.tipo)
                return actual.material;
            actual = actual.next;
        }
        
        return null;
    }
}

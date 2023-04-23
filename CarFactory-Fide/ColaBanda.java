package Código_juego;
//Proyecto final Estructura de Datos
//Integrantes de grupo CAMPOS AGUILAR BRITANY, FLORES VARGAS MAYERLING, MORA ARIAS MALDO
public class ColaBanda {
    //atributos
    public NodoMaterial cabeza, ultimo;
    public int tamanio;
    
    //constructor
    public ColaBanda(){
        cabeza = ultimo = null;
        tamanio = 0;
    }
    
    //Método agregar
    //agrega datos a la cola de la cinta trasportadora
    public void agregar(Material material){
        NodoMaterial nuevo = new NodoMaterial(material);
        if(cabeza == null){
            cabeza = nuevo;
            ultimo = nuevo;
        }else{
            ultimo.next = nuevo;
            ultimo = nuevo;
        }
        tamanio++;
    }
    //método remover
    //elimina y devuelve el segundo elemento como el primero de la fila
    public Material remover(){
        if(cabeza != null){
            Material material = cabeza.material;
            cabeza = cabeza.next;
            tamanio--;
            return material;
        }else{
            return null;
        }
    }
    //Métoddo traerprimero
    //obtener el primer elemento de la pila sin eliminarlo de la misma.
    public Material traerPrimero(){
        return cabeza.material;
    }
}

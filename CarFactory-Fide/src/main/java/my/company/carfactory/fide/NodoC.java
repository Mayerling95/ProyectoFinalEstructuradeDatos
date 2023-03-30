package my.company.carfactory.fide;
public class NodoC {
    //Nodo de cinta 

    private String parte;
    private NodoC siguiente;

    public NodoC(String parte) {
        this.parte = parte;
        this.siguiente = null;
    }

    public String getParte() {
        return parte;
    }

    public NodoC getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoC siguiente) {
        this.siguiente = siguiente;
    }
    
}

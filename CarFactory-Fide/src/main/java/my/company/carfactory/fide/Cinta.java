package my.company.carfactory.fide;
import java.util.Random;

public class Cinta {
    private NodoC cabeza;
    private int tamano;
    private int tamanoMaximo;

    public Cinta(int tamanoMaximo) {
        cabeza = null;
        tamano = 0;
        this.tamanoMaximo = tamanoMaximo;
        int numPartes = 0;
        while (numPartes < tamanoMaximo) {
            int tipo = (int) (Math.random() * 5) + 1;
            switch (tipo) {
                case 1:
                    insertar("Carrocería");
                    numPartes++;
                    break;
                case 2:
                    insertar("Carrocería Especial");
                    numPartes++;
                    break;
                case 3:
                    insertar("Llantas");
                    numPartes++;
                    break;
                case 4:
                    insertar("Motor Deportivo");
                    numPartes++;
                    break;
                case 5:
                    insertar("Motor Especial");
                    numPartes++;
                    break;
                default:
                    break;
            }
        }
    }

    public void insertar(String parte) {
        NodoC nuevoNodo = new NodoC(parte);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            NodoC actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevoNodo);
        }
        tamano++;
    }

    public void eliminarPrimero() {
        if (cabeza == null) {
            System.out.println("La cinta está vacía");
        } else {
            cabeza = cabeza.getSiguiente();
            tamano--;
            int tipo = (int) (Math.random() * 5) + 1;
            switch (tipo) {
                case 1:
                    insertar("Carrocería");
                    break;
                case 2:
                    insertar("Carrocería Especial");
                    break;
                case 3:
                    insertar("Llantas");
                    break;
                case 4:
                    insertar("Motor Deportivo");
                    break;
                case 5:
                    insertar("Motor Especial");
                    break;
                default:
                    break;
            }
        }
    }
    
    public void imprimirCinta() {
        NodoC actual = cabeza;
        while (actual != null) {
            System.out.println(actual.getParte());
            actual = actual.getSiguiente();
        }
    }
     
}

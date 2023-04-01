/*
 * Cola de ordenes dinamico sobre los ingresos de tareas en el juego */
package com.mycompany.carfactory.fide;

/*Encargada Britany Campos Aguilar
 */
public class colaOrdenes {
    //atributos
        
    NodoVeh cabeza;
    NodoVeh ultimo;
//Constructores
    
    public colaOrdenes(NodoVeh cabeza, NodoVeh ultimo) {
        this.cabeza = cabeza;
        this.ultimo = ultimo;
    }
//constructor default
    public colaOrdenes() {
    }
    
    //Getters y setters
    public NodoVeh getCabeza() {
        return cabeza;
    }

    public void setCabeza(NodoVeh cabeza) {
        this.cabeza = cabeza;
    }

    public NodoVeh getUltimo() {
        return ultimo;
    }

    public void setUltimo(NodoVeh ultimo) {
        this.ultimo = ultimo;
    }

//Ingreso de ordenes de clase vehículo   
    public void Orden(NodoVeh tipoVeh){
    
            if(cabeza==null){
                cabeza=tipoVeh;
                ultimo=tipoVeh;
            }else{
                ultimo.setSiguiente(tipoVeh);
                ultimo=tipoVeh;
            }
    }
 //Metodo to String para poder leerlo              
    public String toString(){
        String s="";
        NodoVeh aux=cabeza;
        while(aux!=null){
            s+=aux+"\n";
            aux=aux.getSiguiente();
        }
        return s;
    }       
    
 //Metodo para contar los elementos de la cola
     public int tamanio()
     {
     int contador=0;
     NodoVeh c=this.cabeza;
     while(c!=null)
     {
     contador++;
     c=c.getSiguiente();
     }
     System.out.println("Numero de datos en la cola: "+contador);
     return contador;
     }
     
     //Este método nos muestra las ordenes ded forma aleatoria
     public void mostrarOrdenes(){
         NodoVeh[] nodos= new NodoVeh[3];//Creamos un arreglo en el Nodo vehiculo
         int indice=0;//Ubicamos el indice en 0
         NodoVeh actual=cabeza;//actual sera nuestro auxiliar
         while(actual !=null){//Si actual es diferente a 0 nos mostrara los dato aleatorios
             int randomInd=(int)(Math.random()*3);//Metodo para que las 3 variables se tomen en cuenta
             while(nodos[randomInd]!=null){//bucle que no deja pasar si esta vacío
                 randomInd=(randomInd+1)%3;
             }
             nodos[randomInd]=actual;//actualización del auxiliar
             actual=actual.getSiguiente();
         }
         //Impresion de resultados
         System.out.print("Ordenes nuevas:\n" );
         for (int i = 0; i < nodos.length; i++) {             
             System.out.println("Tipo de vehículo: "+nodos[i].getTipoAuto());             
         }
     }
     
  //Con este método podemos ir eliminando las ordenes pendientes   
  public NodoVeh AutoListo(String AutoListo){//Según el tipo de auto que ingresemos
      //se irá eliminando de la lista cuando volvamos a imprimirlas nos mostrara los
      //pendientes
        NodoVeh aux=cabeza;
        if (cabeza!=null) {
            if (cabeza.getTipoAuto()==AutoListo) {
                aux=aux.getSiguiente();
            }
            if (aux.getSiguiente()!=null&&aux.getSiguiente().getTipoAuto()==AutoListo) {
                aux.setSiguiente(aux.getSiguiente().getSiguiente());
            }
                
            }
        return aux;//Retorna referencia
  }
         
 
   }
/*
Proyecto Final Estructura de Datos
Integrantes 
Britany Mercedes Campos Aguilar 
Mayerling Nohemi Flores Vargas 
Celeste May Herrera 
Maldo David Mora Arias  

*/

package my.company.carfactory.fide;


/**
Clase Main donde se correra el proyecto
El jugador es el gerente de una de las fábricas de autos de la marca más prestigiosa 
* de la industria. Debido a esto, la demanda de autos de la compañía es extensa y es 
* necesario cubrir todas las peticiones para mantener el prestigio y la reputación
* de la empresa.
 */
public class CarFactoryFide {

     private static int minutos=0, segundos=0;
    private static int tiempoTranscurrido=0;

    
    
    public static void main(String[] args) {

        //prueba cinta
        Cinta cinta = new Cinta(3);
        cinta.imprimirCinta();
        cinta.eliminarPrimero();
        System.out.println("===========");
        cinta.imprimirCinta();
        
        run();//método recursivo de run para ingreso de ordenes


        }
    
    public static void run(){//Metodo que llama a ejecutar
       ejecutar();
}
   //Metodo recursivo del tiempo total del juego aquí inicia la cola de ordenes y empieza a
   //mostrar los datos de manera aleatoria para empezar la construcción
   public static void ejecutar(){
       try{//El 1 es para probar si funciona el arreglo en 1 minuto
           while(tiempoTranscurrido<1*60){//Minutos que dura el juego 8 minutos
               if (segundos==59) {//duracion de segundos
                   segundos=0;
                   minutos++;//cada 59 segundos aumentan los minutos
               }
               segundos++;//aumentan los segundos
               //Creación de la cola con las respectivas ordenes
               colaOrdenes cola = new colaOrdenes();
               cola.Orden(new NodoVeh("SuperAuto Deportivo"));
               cola.Orden(new NodoVeh("Auto de carga de alta gama"));
               cola.Orden(new NodoVeh("Maquinaria para trabajos pesados"));
              //Mostrar los datos aleatorios
              cola.mostrarOrdenes();
               //Duración de cada orden 15000 milisegundos que equivalen a 15 segundos
               Thread.sleep(15000);  
               tiempoTranscurrido+=15;//Cada 15 segundos aumentan al tiempo transcurrido
               //de esta forma hace un contador para que totalice los minutos totales
           }           
           }catch(Exception e){
               e.printStackTrace();
                         
       }
    }     
     
    }

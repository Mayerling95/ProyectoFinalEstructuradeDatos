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

    /**
     * @param args
     */
    public static void main(String[] args) {

        //prueba cinta
        Cinta cinta = new Cinta(3);
        cinta.imprimirCinta();
        cinta.eliminarPrimero();
        System.out.println("===========");
        cinta.imprimirCinta();


        }
 
    }

/*
Esta fábrica, en específico, produce 3 autos emblema:
Un superauto deportivo
Un carro de carga de alta gama
Una maquinaria de última tecnología para trabajos pesados
*/
package Código_juego;
//Proyecto final Estructura de Datos
//Integrantes de grupo CAMPOS AGUILAR BRITANY, FLORES VARGAS MAYERLING, MORA ARIAS MALDO
public class Auto {
    //atributos
    public String nombreAuto;
    public int ganancia;
    public ListaMaterial materiales;
    
    //constructor parametrizado llamando a la lista materiales
    public Auto(String nombreAuto, int ganancia, ListaMaterial materiales){
        this.nombreAuto = nombreAuto;
        this.ganancia = ganancia;
        this.materiales = materiales;
    }
    
    //constructor default

    public Auto() {
    }
    
}

/*
 * Clase Jugadores
 */
package com.mycompany.carfactory.fide;

/*Encargads Britany Campos Aguilar, David Mora Arias
 */
public class Jugadores {
    //Se establecen 2 jugadores para realizar el juego

    //Atributos
    private colaOrdenes jugador1Cola;
    private colaOrdenes jugador2Cola;
    private int tiempoTranscurrido;
    private int minutos;
    private int segundos;

   //Constructor 
    public Jugadores() {
        jugador1Cola = new colaOrdenes();
        jugador2Cola = new colaOrdenes();
        tiempoTranscurrido = 0;
        minutos = 0;
        segundos = 0;
    }

    //Getters y setters
    public colaOrdenes getJugador1Cola() {
        return jugador1Cola;
    }

    public void setJugador1Cola(colaOrdenes jugador1Cola) {
        this.jugador1Cola = jugador1Cola;
    }

    public colaOrdenes getJugador2Cola() {
        return jugador2Cola;
    }

    public void setJugador2Cola(colaOrdenes jugador2Cola) {
        this.jugador2Cola = jugador2Cola;
    }

    public int getTiempoTranscurrido() {
        return tiempoTranscurrido;
    }

    public void setTiempoTranscurrido(int tiempoTranscurrido) {
        this.tiempoTranscurrido = tiempoTranscurrido;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public int getSegundos() {
        return segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

    @Override
    public String toString() {
        return "Jugadores{" + "jugador1Cola=" + jugador1Cola + ", jugador2Cola=" + jugador2Cola + '}';
    }
    
    
    
    
}

    

    
    
  

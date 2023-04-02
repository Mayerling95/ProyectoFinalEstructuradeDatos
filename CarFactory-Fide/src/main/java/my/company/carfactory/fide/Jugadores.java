/*
 * Clase Jugadores
 */
package com.mycompany.carfactory.fide;

/*Encargads Britany Campos Aguilar, David Mora Arias
 */
public class Jugadores {
    //Se establecen 2 jugadores para realizar el juego, presupuesto y tiemporestante o disponible

    //Atributos
    private colaOrdenes jugador1Cola;
    private colaOrdenes jugador2Cola;
    private int tiempoRestante;
    private int minutos;
    private int segundos;
    private int presupuesto;

   //Constructor 
    public Jugadores() {
        jugador1Cola = new colaOrdenes();
        jugador2Cola = new colaOrdenes();
        tiempoRestante = 0;
        prespuesto=0;
       
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

    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public void setTiempoRestante(int tiempoRestante) {
        this.tiempoRestante = tiempoRestante;
    }


    @Override
    public String toString() {
        return "Jugadores{" + "jugador1Cola=" + jugador1Cola + ", jugador2Cola=" + jugador2Cola + '}';
    }
    
    
    
    
}

    

    
    
  

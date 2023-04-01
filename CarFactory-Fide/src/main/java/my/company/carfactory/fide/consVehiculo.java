package com.mycompany.carfactory.fide;

//Construcción de vehiculos
/*Encargada Britany Campos Aguilar
 */
public class consVehiculo {
    //ATRIBUTOS
    private String Tipo;
    private String Motor;
    private String Carroceria;
    private String Llantas;
    private int precio;
    
    //Constructores
    public consVehiculo(String Tipo) {
        this.Tipo = Tipo;
        if (Tipo.equals("Superauto deportivo")) {
            construirSuperauto();
        } else if (Tipo.equals("Auto de carga de alta gama")) {
            construirAutoCarga();
        } else if (Tipo.equals("Maquinaria para trabajos pesados")) {
            construirMaquinaria();
        }
    }
//constructor default
    public consVehiculo() {
    }
    //Getters y setters
        
    public String getTipo() {
        return Tipo;
    }

    public String getMotor() {
        return Motor;
    }

    public String getCarroceria() {
        return Carroceria;
    }

    public String getLlantas() {
        return Llantas;
    }

    public int getPrecio() {
        return precio;
    }
    
    //Método de construcción

    public void construirSuperauto() {
        this.Motor = "Motor especial deportivo";
        this.Carroceria = "Carroceria";
        this.Llantas = "";
        this.precio = 15000;
    }
    
    public void construirAutoCarga() {
        this.Motor = "Motor de carga";
        this.Carroceria = "Carroceria";
        this.Llantas = "";
        this.precio = 10000;
    }
    
    public void construirMaquinaria() {
        this.Motor = "Motor de carga";
        this.Carroceria = "Carroceria especial";
        this.Llantas = "Llantas únicas de trabajo";
        this.precio = 18000;
    }

    
  
}

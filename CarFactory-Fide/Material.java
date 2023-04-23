package Código_juego;
//Proyecto final Estructura de Datos
//Integrantes de grupo CAMPOS AGUILAR BRITANY, FLORES VARGAS MAYERLING, MORA ARIAS MALDO
public class Material {
    
    //atributos
    public String nombre, imagen;
    public int tipo;
    
    //constructor parametrizado
    public Material(String nombre, String imagen, int tipo){
        this.nombre = nombre;
        this.imagen = imagen;
        this.tipo = tipo;
    }
    
    //constructor default

    public Material() {
    }
    
    //Getters y setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.pkgfinal;

/**
 *
 * @author Mayerling
 */
public class Presupuesto {
  
private int dinero;
    
//Constructor 
 
public Presupuesto (int dinero ){
    
   this.dinero = dinero;
}
public Presupuesto (){}

  public int getDinero(){ return dinero;}
  public void setDinero(int dinero){ this.dinero = dinero;}

   public void SumaGanancias(int auto){  //autoDeportivo=1 ,autodeCarga=2 ,maquinariaPesada=3
       
       if(auto==1){
           setDinero(dinero+15000);
           //dinero = dinero + 15000
       }
           
       else if (auto==2){
           setDinero(dinero+10000);
           //dinero = dinero + 10000   
       }
           
       else if (auto==3){
           setDinero(dinero+18000);
           //dinero = dinero + 18000
       }
       else{
           //decir error
       }
   }
   
   public void ComprarEspacio(int espacio){  //cuarto espacio = 4 / quinto espacio= 5 / sexto espacio= 6
   
       if(espacio==4){
          setDinero(dinero-70000);
       }
       
      
       else if(espacio==5){
          setDinero(dinero-80000);
       }
       
       else if(espacio==6){
          setDinero(dinero-90000);
       }
       else{
           //decir error
       }    
   }
   @Override
    public String toString(){
        return "Dinero {Dinero: " + dinero + '}';
    } 
    
}


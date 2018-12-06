
package exercicio8_psp.tartaruga_lebre;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class Tartaruga extends Thread{ //Extendo da clase Thread
    
    Pista pista; //Obxeto da clase Pista

    //Constructor:
    public Tartaruga(Pista pista){
        this.pista=pista;
        this.start(); //Inciamos o fio
    }
    
    //Método run():
    public void run(){
        try {
            //Mentres a posición da tartaruga sexa menor que 70...
            while(pista.getCasillaTarta()<70){
//                sleep(700);
                pista.tartaruga(); //...chamamos ao método "tartaruga" da clase "Pista"
                
                //Se a posición da lebre e maior ou igual a 70...
                if(pista.getCasillaLebre()>=70){
                    break; //...finaliza (por que se non segue executandose a tartaruga)
                }
            }
            
            //Se a posición da tartaruga é maior ou igual que 70...
            if(pista.getCasillaTarta()>=70){
                System.out.println("A TARTARUGA GAÑOU A CARREIRA: "+pista.getCasillaTarta()); //...imprime que gañou a carreira a tartaruga          
            }
              
        } catch (InterruptedException ex) {
            System.out.println("ERROR tartaruga. -> "+ex);
        }
    }
    
}

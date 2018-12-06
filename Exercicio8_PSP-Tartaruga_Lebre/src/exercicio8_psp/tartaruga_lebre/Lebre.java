
package exercicio8_psp.tartaruga_lebre;

/**
 *
 * @author David
 */
public class Lebre extends Thread{ //Extendo da clase Thread
    
    Pista pista; //Obxeto da clase Pista
    
    //Constructor:
    public Lebre(Pista pista){ 
        this.pista=pista;
        this.start(); //Inciamos o fio
    }
    
    //Método run():
    public void run(){
        try {
            //Mentres a posición da lebre sexa menor que 70...
            while(pista.getCasillaLebre()<70){
//                sleep(700);
                pista.lebre(); //...chamamos ao método "lebre" da clase "Pista"
                
                //Se a posición da tartaruga e maior ou igual a 70...
                if(pista.getCasillaTarta()>=70){
                    break; //...finaliza (por que se non segue executandose a lebre)
                }
            }
            
            //Se a posición da lebre é maior ou igual que 70...
            if(pista.getCasillaLebre()>=70){
                System.out.println("A LEBRE GAÑOU A CARREIRA: "+pista.getCasillaLebre()); //...imprime que gañou a carreira a lebre
            }

        } catch (InterruptedException ex) {
            System.out.println("ERROR lebre. -> "+ex);
        }
    }
    
}


package exercicio6_psp;

import static java.lang.Thread.sleep;

/**
 *
 * @author oracle
 */
public class Productor extends Thread{ //Extendo de Thread
    
    Caixa caixa; //Obxeto da clase Caixa
    int dineroIng; //Variable int, cantidade de diñeiro que vai a ingresar

    //Constructor:
    public Productor(Caixa caixa){
        this.caixa=caixa;
    }
    
    //Método tun():
    public void run(){
        for(int i=0;i<10;i++){
            dineroIng=(int)(Math.random()*100-1); //Asignamoslle o número random á variable "dineroIng"
            caixa.ingresar(dineroIng); //Pasamoslle a cantidade a ingresar ao método "ingresar"
            System.out.println("Poductor ingresa: "+dineroIng); //Imprimese a cantidade a ingresar
            System.out.println("Diñeiro total -> "+caixa.getTotal()+"\n"); //Imprimese a cantidade total da caixa
        }
    }
    
}

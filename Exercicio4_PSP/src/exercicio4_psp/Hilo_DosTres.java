
package exercicio4_psp;

/**
 *
 * @author David
 */
public class Hilo_DosTres extends Thread{ //Extendo de Thread
    
    public int suma; //Variable int para realizar aa suma
    
    //Método run():
    public void run(){
        for(int i=1;i<1001;i++){
            if(i%10==2||i%10==3){ //Se o número acaba en 2 ou en 3...
                suma=suma+i; //...sumase
            }
        }
        System.out.println("Dos o tres: "+suma); //Imprime o total da suma
    }
    
}

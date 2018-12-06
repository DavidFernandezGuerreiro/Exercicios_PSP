
package exercicio4_psp;

/**
 *
 * @author David
 */
public class Hilo_Pares extends Thread{ //Extendo de Thread
    
    public int suma; //Variable int para realizar la suma
    
    //Método run():
    public void run(){
        
        for(int i=1;i<1001;i++){
            if(i%2==0){ //Se o número é par...
                suma=suma+i; //...sumase
            }
        }
        System.out.println("Pares: "+suma); //Imprime o total da suma
    }
    
}

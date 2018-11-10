
package exercicio4_psp;

/**
 *
 * @author David
 */
public class Hilo_Impares extends Thread{
    
    public int suma;
    
    public void run(){
        
        for(int i=1;i<1001;i++){
            if(i%2!=0){
                suma=suma+i;
            }
        }
        System.out.println(suma);
    }
    
}

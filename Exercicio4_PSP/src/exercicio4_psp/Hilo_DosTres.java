
package exercicio4_psp;

/**
 *
 * @author David
 */
public class Hilo_DosTres extends Thread{
    
    public int suma;
    
    public void run(){
        for(int i=1;i<1001;i++){
            if(i%10==2||i%10==3){
                suma=suma+i;
            }
        }
        System.out.println(suma);
    }
    
}

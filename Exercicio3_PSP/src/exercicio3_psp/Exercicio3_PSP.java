
package exercicio3_psp;

/**
 *
 * @author David
 */
public class Exercicio3_PSP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        
        Hilos h1=new Hilos();
        h1.start();
        h1.join();
        
//        h1.nombre();
        
    }
    
}


package exercicio2_psp;

/**
 *
 * @author David
 */
public class Exercicio2_PSP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        
        Hilos h1=new Hilos("uno");
        Hilos h2=new Hilos("dos");
        Hilos h3=new Hilos("tres");
        Hilos h4=new Hilos("cuatro");
        
        h1.start();
        h2.start();
        h3.start();
        h4.start();
        
        h1.join();
        h2.join();
        h3.join();
        h4.join();
        
    }
    
}

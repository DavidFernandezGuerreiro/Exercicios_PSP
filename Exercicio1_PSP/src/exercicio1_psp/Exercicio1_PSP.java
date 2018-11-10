
package exercicio1_psp;

/**
 *
 * @author David
 */
public class Exercicio1_PSP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Hilos h1=new Hilos("uno");
        Hilos h2=new Hilos("dos");
        
        h1.start();
        h2.start();
        
    }
    
}

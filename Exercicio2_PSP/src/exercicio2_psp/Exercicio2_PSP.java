
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
        
        //Creo os fios:
        Hilos h1=new Hilos("uno");
        Hilos h2=new Hilos("dos");
        Hilos h3=new Hilos("tres");
        Hilos h4=new Hilos("cuatro");
        
        //Incio os fios:
        h1.start();
        h2.start();
        h3.start();
        h4.start();
        
    }
    
}


package exercicio5_psp;

/**
 *
 * @author David
 */
public class Exercicio5_PSP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        
        Hilos uno=new Hilos("uno"); //Fio "uno"
        Hilos dos=new Hilos("dos"); //Fio "dos"
        
        uno.setPriority(1); //Mínima prioridad
        dos.setPriority(10); //Máxima prioridad

        uno.join(); //O fio "uno" espera ao fio "dos"
        
        uno.start(); //Inicio o fio "uno"
        dos.start(); //Inicio o fio "dos"
                
    }
    
}

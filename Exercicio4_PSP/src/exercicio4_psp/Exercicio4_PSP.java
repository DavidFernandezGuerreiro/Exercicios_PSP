
package exercicio4_psp;

/**
 *
 * @author David
 */
public class Exercicio4_PSP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /*
        Realiza un programa en java que execute tres fíos de forma concurrente. 
        Un de eles debe sumar os números pares del 1 ao 1000, outro os impares, 
        e outro, os que terminen en dous ou en tres.
        */
        
        //Creo os fios:
        Hilo_Pares hpares=new Hilo_Pares();
        Hilo_Impares himpares=new Hilo_Impares();
        Hilo_DosTres hdostres=new Hilo_DosTres();
        
        //Inicio os fios:
        hpares.start();
        himpares.start();
        hdostres.start();
        
    }
    
}

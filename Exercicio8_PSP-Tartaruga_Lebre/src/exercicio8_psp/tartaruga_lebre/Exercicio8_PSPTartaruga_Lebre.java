
package exercicio8_psp.tartaruga_lebre;

/**
 *
 * @author David
 */
public class Exercicio8_PSPTartaruga_Lebre {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        
        Pista pista=new Pista(); //Obxeto pista
        Tartaruga tarta=new Tartaruga(pista); //Obxeto tartaruga
        Lebre lebre=new Lebre(pista); //Obxeto lebre

    }
    
}

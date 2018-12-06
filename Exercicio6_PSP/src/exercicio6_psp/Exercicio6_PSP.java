
package exercicio6_psp;

/**
 *
 * @author oracle
 */
public class Exercicio6_PSP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        
        Caixa caixa=new Caixa(); //Obxeto caixa
        Productor pro=new Productor(caixa); //Obxeto productor
        Consumidor con=new Consumidor(caixa); //Obxeto consumidor
        
        //Iniciamos os fios
        pro.start();
        con.start();
        
        //Co método join(), espera a que finalice a execución do fio
        pro.join();
        con.join();
    }
    
}

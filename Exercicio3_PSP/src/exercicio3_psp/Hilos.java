
package exercicio3_psp;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class Hilos extends Thread{
    
    /*
    Realizar un programa que cree un fío, que a súa vez cree outro fío, 
    e así consecutivamente, ata un total de cinco fíos. 
    Cada fío debe presentar o seu nome indicando que comenzou, crear o seu fío fillo 
    e presentar o seu nome 10 veces indicando que se está procesando, 
    esperando un tempo aleatorio entre 100 e 600 milisegundos entre cada presentación. 
    Cada fío deberá esperar a que o seu fillo termine antes de presentar o seu último mensaxe indicando o seu nome e que rematou.
    */
    
    public int nome;//="Hilo";

    public Hilos() {
    }

    public Hilos(int nome) {
        this.nome = nome;
    }

    public int getNome() {
        return nome;
    }

    public void setNome(int nome) {
        this.nome = nome;
    }
    
    String nomeH="";
//    public void nombre(){
//        for(int i=0;i<5;i++){
//            
//            System.out.println(nomeH);
//        }
//    }

    public void run(){
        
        for(int i=0;i<5;i++){
//            nomeH=getNome()+i;
            Hilos h=new Hilos(i);//nomeH); //-> Nacen los hilos hijos
            System.out.println("Hilo nº "+h.getNome()+":"); //-> Imprime el nombre de cada hilo hijo
            for(int j=0;j<10;j++){
                System.out.println("Naci del hilo nº "+h.getNome()+" -> "+j); //-> Imprime de que hilo hijo viene
                try {
                    h.join();
                    Thread.sleep(300);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Hilos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
    
}

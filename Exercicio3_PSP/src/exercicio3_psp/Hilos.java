
package exercicio3_psp;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class Hilos extends Thread{ //extendo de Thread
    
    /*
    Realizar un programa que cree un fío, que a súa vez cree outro fío, 
    e así consecutivamente, ata un total de cinco fíos. 
    Cada fío debe presentar o seu nome indicando que comenzou, crear o seu fío fillo 
    e presentar o seu nome 10 veces indicando que se está procesando, 
    esperando un tempo aleatorio entre 100 e 600 milisegundos entre cada presentación. 
    Cada fío deberá esperar a que o seu fillo termine antes de presentar o seu último mensaxe indicando o seu nome e que rematou.
    */
    
    static int numero=0; //Numero estático para a creación dos fios
    Hilos hilo; //Obxeto da clase
    
    //Constructor:
    public Hilos(){
        System.out.println("Creouse o hilo nº "+this.getName()); //Imprimese o nome de cada fio
        numero++; //Incrementa a variable "numero"
        this.start(); //Inicializa os fio
        
        //Se a variable "numero" é menor que 5...
        if(numero<5){
            hilo=new Hilos(); //...crease un novo fio
        }
    }
    
    //Método run()
    public void run(){
        
        for(int i=0;i<10;i++){
            System.out.println(i+"º - "+this.getName()); //Imprimese o nome do fio
            try {
                Thread.sleep((long)(Math.random()*600+100)); //Tempo aleatorio bloqueado entre cada fio
            } catch (InterruptedException ex) {
                Logger.getLogger(Hilos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(this.getName().equalsIgnoreCase("Thread-4")){
            //Se getName() é igual a "Thread-4" ... salta a o "else"
        }else{
            try {
                hilo.join(); //Esperamos a que acabe o fio
            } catch (InterruptedException ex) {
                Logger.getLogger(Hilos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Acabando el hilo nº "+this.getName()); //Imprime o nome do fio que finaliza
        
        
    }
    
}

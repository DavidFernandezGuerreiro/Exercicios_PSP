
package exercicio2_psp;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class Hilos extends Thread{ //Extendo de Thread
    
    /*
    Programa que crea 4 fíos, os executa e espera a que estes terminen. 
    Os fíos escriben 5 veces o número de iteración do bucle e o seu nome. 
    En cada iteración, despois de escribir o seu nome, bloquéanse durante un segundo 
    e volven a estar dispoñibles para a súa execución.
    */
    
    public String nome; //Variable String, recolle o nome do fio

    //Constructor:
    public Hilos(String nome){
        this.nome=nome;
    }

    //Getters e Setters:
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    //Método run():
    public void run(){
        
        for(int i=0;i<5;i++){
            System.out.println(i+"º - "+getNome()); //Imprimese o nome do fio
            try {
                Thread.sleep(1000); //Tempo bloqueado entre cada fio
            } catch (InterruptedException ex) {
                Logger.getLogger(Hilos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        Thread t=new Thread();
        //Se o fio finaliza (está "morto")...
        if(t.isAlive()==false){
            System.out.println("El hilo nº "+getNome()+" finalizó"); //...imprimese o nome do fio que finalizou
        }
    }
    
}

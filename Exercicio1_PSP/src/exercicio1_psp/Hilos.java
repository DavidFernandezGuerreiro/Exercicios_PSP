
package exercicio1_psp;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class Hilos extends Thread{
    
    /*
    Programa en Java que cree dous fíos e os executa. 
    Os fíos escriben dez veces un número de iteración do bucle e seu nome. 
    En cada iteración, despois de escribir o seu nome, se bloquean durante un tempo 
    aleatorio de segundos e después volven a estar dispoñibles para a súa execución.
    */
    
    public String nome;
    
    public Hilos(String nome){
        this.nome=nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
    public void run(){
        Thread t=new Thread();
        for(int i=0;i<10;i++){
            System.out.println(i+"º - "+getNome());
            try {
                Thread.sleep(1000); //Intercalar entre el hilo uno y el dos.
            } catch (InterruptedException ex) {
                Logger.getLogger(Hilos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}

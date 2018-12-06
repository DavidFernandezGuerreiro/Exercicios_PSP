
package exercicio1_psp;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class Hilos extends Thread{ //extendo de Thread
    
    /*
    Programa en Java que cree dous fíos e os executa. 
    Os fíos escriben dez veces un número de iteración do bucle e seu nome. 
    En cada iteración, despois de escribir o seu nome, se bloquean durante un tempo 
    aleatorio de segundos e después volven a estar dispoñibles para a súa execución.
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
        
        for(int i=0;i<10;i++){
            System.out.println(i+"º - "+getNome()); //Mensaxe co nome do fio
            try {
                Thread.sleep(1000); //Tempo bloqueado entre o primer e o segundo fio
            } catch (InterruptedException ex) {
                Logger.getLogger(Hilos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}


package exercicio5_psp;

/**
 *
 * @author David
 */
public class Hilos extends Thread{ //Extendo da clase Thread
    
    public String nome; //Varible String, recolle o nome do fio

    //Constructores:
    public Hilos() {
    }
    public Hilos(String nome) {
        this.nome = nome;
    }
    
    //Método run():
    public void run(){

        System.out.println("Ola, son o fio nº "+nome); //Imprimese un saúdo e o nome do fio
        
    }
    
}

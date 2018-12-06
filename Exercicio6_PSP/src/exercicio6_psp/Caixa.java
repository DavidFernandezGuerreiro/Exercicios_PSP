
package exercicio6_psp;

/**
 *
 * @author oracle
 */
public class Caixa {
    
    private int dinero=700; //Capital inicial
    private boolean available=false; //variable bool, indica se pode entrar ou non 

    //Contructor:
    public Caixa(){
    }
    
    //Co método wait, o "fio1" espera a que finalice o outro "fio2"
    //e cando finaliza, o "fio2" avisa ao "fio1" mediante o método notify().
    
    //Método synchronized que recolle o total da caixa
    public synchronized int getTotal(){
        while(available==false){ //Esperamos co método wait()
            try{
                wait();
            }catch(InterruptedException e){
            }
        }
        available=false; //cambia o bool a false, e espera
        notify(); //Notifica a outro fio que quera executarse
        return dinero; //Retorna o diñeiro
    }
    
    //Método synchronized que ingresa diñeiro
    public synchronized void ingresar(int valorIngresar) {
        while(available==true){ //Esperamos co método wait()
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        dinero=dinero+valorIngresar; //Recolle o valor engadido nos paramentros e fae unha suma
        available=true; //cambia o bool a true, e espera
        notify(); //Notifica a outro fio que quera executarse
    }
    
    //Método synchronized que quita diñeiro
    public synchronized void quitar(int valorQuitar){
        while(available==true){ //Esperamos co método wait()
            try{
                wait();
            }catch(InterruptedException e){
            }
        }
        dinero=dinero-valorQuitar; //Recolle o valor engadido nos paramentros e fae unha resta
        available=true; //cambia o bool a true, e espera
        notify(); //Notifica a outro fio que quera executarse
    }
    
}

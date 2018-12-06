
package exercicio7.psp;

/**
 *
 * @author David
 */
public class Correo {
    
    boolean available=false; //variable bool, indica se pode entrar ou non 
    String mensaje; //variable String, recolle o mensaxe

    //Constructor:
    public Correo() {
    }
    
    //Método synchronized que lee o mensaxe:
    public synchronized String leer() throws InterruptedException{
        while(available==false){ //Esperamos co método wait()
            wait();
        }
        available=false; //cambia o bool a false, e espera
        notify(); //Notifica a outro fio que quera executarse
        return mensaje; //Retorna o mensaxe
    }
    
    //Método synchronized que escribe o mensaxe:
    public synchronized String escribir(String msj) throws InterruptedException{
        while(available==true){ //Esperamos co método wait()
            wait();
        }
        mensaje=msj; //Asignamoslle á variable "mensaje" o valor engadido nos parámetros
        available=true; //cambia o bool a true, e espera
        notify(); //Notifica a outro fio que quera executarse
        return mensaje; //Retorna o mensaxe
    }

}

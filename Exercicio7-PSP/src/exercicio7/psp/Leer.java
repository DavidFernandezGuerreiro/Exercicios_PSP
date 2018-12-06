
package exercicio7.psp;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class Leer extends Thread{ //Extendo de Thread
    
    String mensaje; //Variable String, mensaxe que escribe
    Correo correo; //Obxeto da clase "Correo"

    //Constructor:
    public Leer(Correo correo){
        this.correo=correo;
    }
    
    //Método run():
    public void run(){
        try {
            mensaje=correo.leer(); //Asignamoslle á variable "mensaje" o valor da chamada ao método "leer"
            System.out.println("MENSAJE RECIBIDO: \n"+mensaje+"\n"); //Imprimese o mensaxe recibido
        } catch (InterruptedException ex) {
            Logger.getLogger(Leer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

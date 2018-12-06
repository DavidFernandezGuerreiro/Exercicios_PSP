
package exercicio7.psp;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class Escribir extends Thread{ //Extendo de Thread
    
    String mensaje; //Variable String, mensaxe que escribe
    Correo correo; //Obxeto da clase "Correo"

    //Constructor:
    public Escribir(String mensaje,Correo correo){
        this.mensaje=mensaje;
        this.correo=correo;
    }
    
    //Método run():
    public void run(){
        try {
            correo.escribir(mensaje); //Chamamos ao método "escribir" e pasamoslle o mensaxe a escibir
            System.out.println("MENSAJE ENVIADO: \n"+mensaje+"\n"); //Impremese o mensaxe enviado
        } catch (InterruptedException ex) {
            Logger.getLogger(Escribir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}


package exercicio7.psp;

/**
 *
 * @author David
 */
public class Exercicio7PSP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        
        /*
        Programa que simule un buzón de correo (recurso compartido), de xeito que se
        poida leer unha mensaje ou envialo. O buzón soamente pode almacear unha
        mensaxe, de xeito que para poder escribir débese atopar baleiro e para poder leer
        debe de estar cheo. Crear varios fíos lectores e escritores que manexen o buzón
        de xeito sincronizado.
        */
        
        Correo correo=new Correo(); //Obxeto correo
        
        Leer unoL=new Leer(correo); //Obexeto leer
        Escribir unoE=new Escribir("Primer mensaje",correo); //Obxeto escribir, co mensaxe
          
        Leer dosL=new Leer(correo); //Obexeto leer
        Escribir dosE=new Escribir("Segundo mensaje",correo); //Obxeto escribir, co mensaxe
        
        Leer tresL=new Leer(correo); //Obexeto leer
        Escribir tresE=new Escribir("Tercer mensaje",correo); //Obxeto escribir, co mensaxe
        
        //Iniciamos os fios de leer e escribir:
        unoL.start();
        unoE.start();
        unoL.join(); //(Uso o método join() para que se execute todo en orden)
        unoE.join();
        
        dosL.start();
        dosE.start();
        dosL.join();
        dosE.join();
        
        tresL.start();
        tresE.start();
        tresL.join();
        tresE.join();
        

        
    }
    
}

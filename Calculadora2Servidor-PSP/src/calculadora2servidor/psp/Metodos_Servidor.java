
package calculadora2servidor.psp;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.JOptionPane;

/**
 *
 * @author David Fernández
 */
public class Metodos_Servidor extends Thread{
    
    /**
     * Declaro las varibles:
     * 
     * @param is es el flujo de entrada de bytes
     * @param os es el flujo de salida de bytes
     * @param serverSocket es el socket del servidor
     * @param newSocket socket de cliente
     */
    
    InputStream is;
    OutputStream os;
    ServerSocket serverSocket;
    Socket newSocket;
    
    //Declaro la variable porto y pido por JOptionPane el número de puerto del servidor:
    int porto=Integer.parseInt(JOptionPane.showInputDialog("Introducir o porto do servidor:"));
    
    /**
     * Método de conexión
     * Hace la conexión entre el servidor y los clientes
    */
    public void conexion(){
        
        try{
            System.out.println("Creando socket servidor");
            //creamos el socket
            serverSocket=new ServerSocket();

            System.out.println("Realizando el bind");
            
            //En el paramentro "porto" recoge el número introducido en el JOptionPane:
            InetSocketAddress addr=new InetSocketAddress("localhost",porto);
            serverSocket.bind(addr);

            System.out.println("Aceptando conexiones");
            
            while(true){
                //aceptamos las conexiones de los clientes
                newSocket=serverSocket.accept();

                System.out.println("Conexión recibida");
                
                //abrimos los flujos de entrada y salida
                is=newSocket.getInputStream();
                os=newSocket.getOutputStream();
                
                //se crea un hilo por cada cliente
                Hilo hilo=new Hilo(os,is);
                hilo.start();
            }
            
        }catch(IOException e){
            System.out.println("ERROR, ha fallado la conexión. -> "+e.getMessage());
        }
        
    }
}

class Hilo extends Thread{
    
    /**
     * @param is es el flujo de entrada de bytes
     * @param os es el flujo de salida de bytes
     *
    */
    
    OutputStream os;
    InputStream is;
    
    public Hilo(){
    }

    public Hilo(OutputStream os,InputStream is){
        this.os=os;
        this.is=is;
    }
    
    public void run(){
        
        /**
         * @param serverSocket es el socket del servidor
         * @param newSocket socket de cliente
         * @param suma recojo el signo de suma
         * @param resta recojo el signo de resta
         * @param multiplicacion recojo el signo de multiplicación
         * @param división recojo el signo de división
         * @param total recojo el resultado de las operaciones
        */
        
        ServerSocket serverSocket=null;
        Socket newSocket=null;
        
        String suma="+";
        String resta="-";
        String multiplicacion="x";
        String division="/";
        String raiz="√";
        
        float total=0;

        try {        
            byte[] mensaje=new byte[64];
            
            //con el InputStream leemos el array de bytes
            is.read(mensaje);
            //asigno lo que leimos en una variable String
            String operacion=new String(mensaje);
            
            
            int n=0;
            //si la variable "operacion" contiene las variables declaradas anteriormente,
            //(resta, suma, multiplicacion, division o raiz)
            //la variable "n" recoge el valor adecuado para entrar en el switch.
            if(operacion.contains(resta)){
                n=1;
            }
            if(operacion.contains(suma)){
                n=2;
            }
            if(operacion.contains(multiplicacion)){
                n=3;
            }
            if(operacion.contains(division)){
                n=4;
            }
            if(operacion.contains(raiz)){
                n=5;
            }

            switch(n){
                case 0:
                    break;
                case 1:
                    //separo con un split los dos valores de la operación
                    //y lo recojo en un array de String
                    String[]operacion2=operacion.split("\\s*-\\s*");
                    //una vez separado los dos valores, 
                    //separo con otro split el segundo valor del signo de "="
                    String operacionIgual=operacion2[1];
                    String[]operacion2Igual=operacionIgual.split("\\s*=\\s*");
                    //asigno los dos valores de la operacion a dos variables de tipo float
                    //para poder realizar la operación
                    float num=Float.parseFloat(operacion2[0]);
                    float num2=Float.parseFloat(operacion2Igual[0]);
                    //hago la operación y el resultado lo asigno a la varible "total"
                    total=num-num2;
                    System.out.println(num+"-"+num2+"="+total);
                    break;
                case 2:
                    //separo con un split los dos valores de la operación
                    //y lo recojo en un array de String
                    operacion2=operacion.split("\\+");
                    //una vez separado los dos valores, 
                    //separo con otro split el segundo valor del signo de "="
                    operacionIgual=operacion2[1];
                    operacion2Igual=operacionIgual.split("\\s*=\\s*");
                    //asigno los dos valores de la operacion a dos variables de tipo float
                    //para poder realizar la operación
                    num=Float.parseFloat(operacion2[0]);
                    num2=Float.parseFloat(operacion2Igual[0]);
                    //hago la operación y el resultado lo asigno a la varible "total"
                    total=num+num2;
                    System.out.println(num+"+"+num2+"="+total);
                    break;
                case 3:
                    //separo con un split los dos valores de la operación
                    //y lo recojo en un array de String
                    operacion2=operacion.split("\\s*x\\s*");
                    //una vez separado los dos valores, 
                    //separo con otro split el segundo valor del signo de "="
                    operacionIgual=operacion2[1];
                    operacion2Igual=operacionIgual.split("\\s*=\\s*");
                    //asigno los dos valores de la operacion a dos variables de tipo float
                    //para poder realizar la operación
                    num=Float.parseFloat(operacion2[0]);
                    num2=Float.parseFloat(operacion2Igual[0]);
                    //hago la operación y el resultado lo asigno a la varible "total"
                    total=num*num2;
                    System.out.println(num+"x"+num2+"="+total);
                    break;
                case 4: 
                    //separo con un split los dos valores de la operación
                    //y lo recojo en un array de String
                    operacion2=operacion.split("\\s*/\\s*");
                    //una vez separado los dos valores, 
                    //separo con otro split el segundo valor del signo de "="
                    operacionIgual=operacion2[1];
                    operacion2Igual=operacionIgual.split("\\s*=\\s*");
                    //asigno los dos valores de la operacion a dos variables de tipo float
                    //para poder realizar la operación
                    num=Float.parseFloat(operacion2[0]);
                    num2=Float.parseFloat(operacion2Igual[0]);
                    //hago la operación y el resultado lo asigno a la varible "total"
                    total=num/num2;
                    System.out.println(num+"/"+num2+"="+total);
                    break;
                case 5:
                    //separo con un split el valor de la operación
                    //y lo recojo en un array de String
                    operacion2=operacion.split("√");
                    //una vez separado el valor, 
                    //separo con otro split el valor del signo de "="
                    operacionIgual=operacion2[1];
                    operacion2Igual=operacionIgual.split("\\s*=\\s*");
                    //asigno el valor de la operacion a una variable de tipo float
                    //para poder realizar la operación
                    num=Float.parseFloat(operacion2Igual[0]);
                    //hago la operación y el resultado lo asigno a la varible "resultado"
                    total=(float) Math.sqrt(num);
                    System.out.println("√"+num+"="+total);
                    break;
            }
            
            System.out.println("Enviando resultado");
            //asigno el valor asignado a "total", lo parseo a String para poder enviarlo por bytes
            String totalString=String.valueOf(total);
            //con el OutputStream envio el resultado de la operación
            os.write(totalString.getBytes());

//            System.out.println("Cerrando el nuevo socket");
//            newSocket.close();
//            System.out.println("Cerrando el socket servidor");
//            serverSocket.close();
//            System.out.println("Terminado");
            
        } catch (IOException ex) {
            Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


package chatservidorpsp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class MetodosServidor {
    
    /**
     * Declaro las varibles:
     * 
     * @param mensaje es el mensaje que reenvío al/los cliente/s
     * @param os es el flujo de salida de bytes
     * @param serverSocket es el socket del servidor
     * @param newSocket socket de cliente
     * @param nConexiones es el número de clientes conectados al servidor
     */
    
    public static String mensaje="";
    ServerSocket serverSocket;
    Socket newSocket;
    OutputStream os;
    public static int nConexiones=0;

    /**
     * Método de conexión
     * Hace la conexión entre el servidor y los clientes
    */
    public void conexion() {
        try {
            System.out.println("Creando socket servidor");
            //creamos el socket
            serverSocket=new ServerSocket();

            System.out.println("Realizando el bind");
            
            //En el paramentro "porto" recoge el número introducido en el JOptionPane:
            InetSocketAddress addr=new InetSocketAddress("localhost",6666);
            serverSocket.bind(addr);

            System.out.println("Aceptando conexiones");
            
            //Si no hay clientes conectados, imprime por pantalla "Ningún cliente conectado"
            if(nConexiones==0){
                System.out.println("Ningún cliente conectado");
            }
            
            while(true){
                //aceptamos las conexiones de los clientes
                newSocket=serverSocket.accept();
                System.out.println("Conexion recibida");
                
                //abrimos los flujos de entrada y salida
                InputStream is=newSocket.getInputStream();
                os=newSocket.getOutputStream();

                //se crea un hilo por cada cliente
                hilo h=new hilo(os,is);
                
                //cuando se conecta un nuevo cliente aumenta la variable "nConexiones"
                nConexiones++;
                //imprime por pantalla el número de clientes conectados
                System.out.println("Hay "+nConexiones+" usuario/s conectados");
                
                //restrinjo el número máximo de clientes conectados a la Sala de Chat
                if(nConexiones==4){
                    //envia un mensaje al cliente indicandole que la Sala de Chat está llena
                    os.write(("Sala de chat llena, intentelo más tarde\n#").getBytes()); 
                    //restamos el número de conexiones, por que al intentar conectarse el cliente ya ha sumado una conexión más
                    nConexiones--;
                    //para de ejecutarse el hilo
                    h.stop();
                }
                
                //si se desconectan clientes la Sala de Chat vuelve a quedar disponible para nuevos clientes
                if(nConexiones<4){
                    //vuelve a ejecutarse el hilo para permitir la conexión a nuevos clientes
                    h.start();
                }
            }

        } catch(IOException ex){
            Logger.getLogger(MetodosServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

class hilo extends Thread{
    
    /**
     * Declaro las varibles:
     * 
     * @param os es el flujo de salida de bytes
     * @param is es el flujo de entrada de bytes
     * @param mensajeAEnviar mensaje que igualamos al mensaje que recibimos de los clientes
     * @param comprobacion mensaje que igualamos al mensaje que recibimos de los clientes
     * @param mensajeRecibido array de bytes para leer los mensajes recibidos
     */
    
    OutputStream os;
    InputStream is;
    //Igualamos las variables auxiliares al mensaje que recibimos de los clientes
    String mensajeAEnviar=MetodosServidor.mensaje;
    String comprobacion=MetodosServidor.mensaje;
    byte[]mensajeRecibido;

    public hilo(){
    }

    public hilo(OutputStream os,InputStream is){
        this.os=os;
        this.is=is;
    }

    public void run(){
        
        while(true){
            //Si el mensaje recibido es diferente al mensaje anterior, sobreescribe el mensaje
            if(!comprobacion.equalsIgnoreCase(MetodosServidor.mensaje)){
                comprobacion=MetodosServidor.mensaje;
                mensajeAEnviar=MetodosServidor.mensaje;
            }

            try{
                if(is.available()==0){
                    
                    //si hay mensajes recibidos los envia
                    if(!mensajeAEnviar.equalsIgnoreCase("@")){
                        enviar();
                    }
                
                //si no, leemos más mensajes    
                }else{
                    mensajeRecibido=new byte[1024];

                    //con el InputStream leemos el array de bytes
                    is.read(mensajeRecibido);
                    //asigno lo que leimos con el is.read() a una variable String "mensajeLeer"
                    String mensajeLeer=new String(mensajeRecibido);
                    //System.out.println("Mensaje recibido al servidor: " + mensajeLeer);

                    //separo con un split el mensaje por que al final tiene un #
                    //y lo recojo en un array de String "msgSplit"
                    String[]msgSplit=new String[2];
                    
                    msgSplit=mensajeLeer.split("#");

                    //System.out.println("Mensaje recibido: "+msgSplit[0]);
                    
                    MetodosServidor.mensaje=msgSplit[0] + "\n";
                    //imprimo por pantalla el mensaje a enviar
                    System.out.println("Mensaje a enviar -> "+MetodosServidor.mensaje);
                    enviar();
                    comprobacion=MetodosServidor.mensaje;
                    
                    //si cierra sesión el número de conexiones disminuye y se imprime por pantalla el número actual de clientes conectados
                    if(msgSplit[0].contains("ha cerrado sesión.")){
                        MetodosServidor.nConexiones--;
                        System.out.println(msgSplit[0]+" Actualmente hay "+MetodosServidor.nConexiones+" usuario/s conectados.");
                        //si el numero de clientes es 0 imprime por pantalla "Ningún cliente conectado"
                        if(MetodosServidor.nConexiones==0){
                            System.out.println("Ningún cliente conectado");
                        }
                    }

                }

            } catch(Exception ex){
                break;
            }
        }
    }
    
    //Metodo "enviar":
    //reenvia el mensaje a todos los clientes
    public void enviar(){
        try {

            os.write((MetodosServidor.mensaje + "#").getBytes());
            //System.out.println("Mensaje a enviar -> "+MetodosServidor.mensaje); 

            //Le asignamos el valor "@" para avisar de que el mensaje ha sido enviado
            mensajeAEnviar="@";
            
        } catch(Exception ex){
            try{
                os.close();
            }catch (IOException ex1){
                Logger.getLogger(hilo.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
    
}

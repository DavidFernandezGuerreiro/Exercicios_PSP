/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatclientepsp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class MetodosCliente {
    
    /**
     * Declaro las varibles:
     * 
     * @param clienteSocket es el socket del cliente
     * @param msg recoge el mensaje y hago el split
     * @param nickname recoge los nombres de los usuarios
     * @param is es el flujo de entrada de bytes
     * @param os es el flujo de salida de bytes
     * @param interfaz varaible de la interfaz
     */
    
    Socket clienteSocket;
    String msgSplit,nickname;
    InputStream is;
    OutputStream os;
    InterfazCliente interfaz;
    
    /**
     * Método conexion:
     *      este método hace la conexión con el servidor
     * 
     * @param nickname recoge el string introducido en la variable "nickname"
     * @param porto recoge el número introducido en la variable "porto"
     */
    public void conexion(String nickname, int porto) {
        try {
            this.nickname = nickname;
            
            System.out.println("Creando socket cliente");
            //creamos el socket
            clienteSocket = new Socket();
            System.out.println("Estableciendo la conexion");

            InetSocketAddress addr = new InetSocketAddress("localhost", porto);
            clienteSocket.connect(addr);
            
            //abrimos los flujos de entrada y salida
            is=clienteSocket.getInputStream();
            os=clienteSocket.getOutputStream();

            //se envia el nombre del usuario indicando que se ha conectado
            os.write((this.nickname + " ha iniciado sesión.#").getBytes());
            
        } catch (IOException ex) {
            Logger.getLogger(MetodosCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Método enviarMensaje:
     *      este método envía el mensaje recibido de la interfaz al servidor
     * 
     * @param mensaje recoge el mensaje que recibe de la interfaz
     */
    public void enviarMensaje(String mensaje) {
        try {
            //si el mensaje es diferente a "/bye", se escribe la fecha y hora, el nick y el mensaje a enviar
            if (!mensaje.equalsIgnoreCase("/bye")) {
                Date d = new Date();
                String fecha = "[" + d.getDay() + "/" + d.getMonth() + " " + d.getHours() + ":" + d.getMinutes() + "]";
                //Escribimos un mensaje con la fecha, el nick y el mensaje del cliente
                os.write((fecha + " " + this.nickname + ": " + mensaje + "#").getBytes());
            } else {
                //si el mensaje que recibe es "/bye" cierra la conexión
                os.write((this.nickname + " ha cerrado sesión.#").getBytes());
                os.close();
                is.close();
                clienteSocket.close();
                System.exit(0);
            }

        } catch (IOException ex) {
            Logger.getLogger(MetodosCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método recibirMensaje:
     *      recibe el mensaje que reenvia el servidor a todos los clientes y es retornado a la
     *     interfaz para mostrarlo
     * 
     * @return msgSpliteado[0] es el mensaje con el split hecho
     */
    public String recibirMensaje() {
        byte[]mensajeRecibido=new byte[1024];
        String[]msgSpliteado=new String[2];
    
        try {
            //se lee el mensaje recibido
            is.read(mensajeRecibido);

            //añadimos el mensaje a msgSplit
            msgSplit=new String(mensajeRecibido);

            //spliteamos el mensaje 
            msgSpliteado=msgSplit.split("#");
            
        } catch (IOException ex) {
            Logger.getLogger(MetodosCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //retornamos el split
        return msgSpliteado[0];
    }
    
    
}


package servidorsocketej2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class ServidorSocketEj2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        
        try {
            System.out.println("Creando socket servidor");

            ServerSocket serverSocket=new ServerSocket();

            System.out.println("Realizando el bind");

            InetSocketAddress addr=new InetSocketAddress("localhost", 6666);
            serverSocket.bind(addr);

            System.out.println("Aceptando conexiones");

            Socket newSocket=serverSocket.accept();

            System.out.println("Conexion recibida");

            InputStream is=newSocket.getInputStream();
            OutputStream os=newSocket.getOutputStream();

            int suma=0;
            for(int i=0;i<5;i++){
                byte[]numeros=new byte[2];
                is.read(numeros);
                System.out.println("Números recibidos: "+new String(numeros));
                String nums=new String(numeros);
                suma=suma+Integer.parseInt(nums);
            }

            String numStr=String.valueOf(suma);
            os.write(numStr.getBytes());
            System.out.println("Resultado de la suma fue enviado -> "+numStr);
            
            System.out.println("Cerrando el nuevo socket");

            newSocket.close();

            System.out.println("Cerrando el socket servidor");

            serverSocket.close();

            System.out.println("Terminado");

        } catch (IOException e) {
        }

    }
    
}


package clientesocketstreamej1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 *
 * @author David
 */
public class ClienteSocketStreamEj1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try{
            System.out.println("Creando socket cliente");
            Socket clienteSocket=new Socket();
            System.out.println("Estableciendo la conexión");

            InetSocketAddress addr=new InetSocketAddress("localhost",6666);
            clienteSocket.connect(addr);

            InputStream is = clienteSocket.getInputStream();
            OutputStream os= clienteSocket.getOutputStream();

//			System.out.println("Enviando mensaje");


//			System.out.println("Mensaje enviado");

            byte[] mensaje=new byte[25];                    

            for(int i=0;i<3;i++){
                System.out.println("Enviando mensaje");
                String mensaje1="mensaje "+(i+1)+" del cliente";
                System.out.println("Mensaje enviado");
                os.write(mensaje1.getBytes());

                is.read(mensaje);
                System.out.println("Mensaje recibido: "+new String(mensaje));
            }

            System.out.println("Cerrando el socket cliente");

            clienteSocket.close();

            System.out.println("Terminado");

            }catch (IOException e) {
                    e.printStackTrace();
            }
        
    }
    
}

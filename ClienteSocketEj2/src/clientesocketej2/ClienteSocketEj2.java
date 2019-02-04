
package clientesocketej2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 *
 * @author David
 */
public class ClienteSocketEj2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            System.out.println("Creando socket cliente");
            Socket clienteSocket=new Socket();
            System.out.println("Estableciendo la conexion");

            InetSocketAddress addr=new InetSocketAddress("localhost", 6666);
            clienteSocket.connect(addr);

            InputStream is=clienteSocket.getInputStream();
            OutputStream os=clienteSocket.getOutputStream();

            for(int i=0;i<5;i++){
                int numeros=(int)(Math.random()*100)+1;
                String numStr=String.valueOf(numeros);
                os.write(numStr.getBytes());
                System.out.println("Enviando números: "+numStr);
            }
            
            System.out.println("Números enviados");
        
            byte[]resultado=new byte[4];
            is.read(resultado);
            System.out.println("Resultado suma= "+new String(resultado));

            System.out.println("Cerrando el socket cliente");

            clienteSocket.close();

            System.out.println("Terminado");

        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
}

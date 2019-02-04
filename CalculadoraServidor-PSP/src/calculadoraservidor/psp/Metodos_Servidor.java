
package calculadoraservidor.psp;

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
public class Metodos_Servidor {
    
    InputStream is;
    OutputStream os;
    ServerSocket serverSocket;
    Socket newSocket;
    
    public void conexion(){
        try{
            System.out.println("Creando socket servidor");

            serverSocket=new ServerSocket();

            System.out.println("Realizando el bind");

            InetSocketAddress addr=new InetSocketAddress("localhost",6666);
            serverSocket.bind(addr);

            System.out.println("Aceptando conexiones");

            newSocket= serverSocket.accept();

            System.out.println("Conexión recibida");

            is=newSocket.getInputStream();
            os=newSocket.getOutputStream();
        
        }catch(IOException e){
            System.out.println("ERROR, ha fallado la conexión. -> "+e.getMessage());
        }
        
    }
    

    public void recibirCalculo(){
        String suma="+";
        String resta="-";
        String multiplicacion="x";
        String division="/";
        String raiz="√";
        
        int total=0;

        try {        
            byte[] mensaje=new byte[64];
            
            is.read(mensaje);
            String operacion=new String(mensaje);

            int n=0;
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
                    String[]operacion2=operacion.split("\\s*-\\s*");
                    String operacionIgual=operacion2[1];
                    String[]operacion2Igual=operacionIgual.split("\\s*=\\s*");
                    int num=Integer.parseInt(operacion2[0]);
                    int num2=Integer.parseInt(operacion2Igual[0]);
                    total=num-num2;
                    System.out.println(num+"-"+num2+"="+total);
                    break;
                case 2:
                    operacion2=operacion.split("\\+");
                    operacionIgual=operacion2[1];
                    operacion2Igual=operacionIgual.split("\\s*=\\s*");
                    num=Integer.parseInt(operacion2[0]);
                    num2=Integer.parseInt(operacion2Igual[0]);
                    total=num+num2;
                    System.out.println(num+"+"+num2+"="+total);
                    break;
                case 3:
                    operacion2=operacion.split("\\s*x\\s*");
                    operacionIgual=operacion2[1];
                    operacion2Igual=operacionIgual.split("\\s*=\\s*");
                    num=Integer.parseInt(operacion2[0]);
                    num2=Integer.parseInt(operacion2Igual[0]);
                    total=num*num2;
                    System.out.println(num+"x"+num2+"="+total);
                    break;
                case 4:
                    operacion2=operacion.split("\\s*/\\s*");
                    operacionIgual=operacion2[1];
                    operacion2Igual=operacionIgual.split("\\s*=\\s*");
                    num=Integer.parseInt(operacion2[0]);
                    num2=Integer.parseInt(operacion2Igual[0]);
                    total=num/num2;
                    System.out.println(num+"/"+num2+"="+total);
                    break;
                case 5:
                    operacion2=operacion.split("√"); 
                    operacionIgual=operacion2[1];
                    operacion2Igual=operacionIgual.split("\\s*=\\s*");
                    num=Integer.parseInt(operacion2Igual[0]);
                    float resultado=(float) Math.sqrt(num);
                    System.out.println("√"+num+"="+resultado);
                    System.out.println("Enviando resultado");
                    String totalString=String.valueOf(resultado);
                    os.write(totalString.getBytes());
                    break;
            }
            
            System.out.println("Enviando resultado");
            String totalString=String.valueOf(total);
            os.write(totalString.getBytes());

            System.out.println("Cerrando el nuevo socket");
            newSocket.close();
            System.out.println("Cerrando el socket servidor");
            serverSocket.close();
            System.out.println("Terminado");
            
        } catch (IOException ex) {
            Logger.getLogger(Metodos_Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
}


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
 * @author David
 */
public class Metodos_Servidor extends Thread{
    
    InputStream is;
    OutputStream os;
    ServerSocket serverSocket;
    Socket newSocket;
    
    int porto=Integer.parseInt(JOptionPane.showInputDialog("Introducir o porto do servidor:"));
    
    public void conexion(){
        
        try{
            System.out.println("Creando socket servidor");

            serverSocket=new ServerSocket();

            System.out.println("Realizando el bind");

            InetSocketAddress addr=new InetSocketAddress("localhost",porto);
            serverSocket.bind(addr);

            System.out.println("Aceptando conexiones");
            
            while (true) {
                newSocket=serverSocket.accept();

                System.out.println("Conexión recibida");

                is=newSocket.getInputStream();
                os=newSocket.getOutputStream();

                Hilo hilo=new Hilo(os,is);
                hilo.start();
            }
            
        
        }catch(IOException e){
            System.out.println("ERROR, ha fallado la conexión. -> "+e.getMessage());
        }
        
    }
}

class Hilo extends Thread{
    
    OutputStream os;
    InputStream is;
    
    public Hilo(){
    }

    public Hilo(OutputStream os,InputStream is){
        this.os=os;
        this.is=is;
    }
    
    public void run(){
        
        ServerSocket serverSocket=null;
        Socket newSocket=null;
        
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
            
            File urlImg;
            
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
                    if(num2==0){
                        Icon iconError=new javax.swing.ImageIcon((urlImg=new File("src/calculadora2servidor/psp/calculadora.png")).getAbsolutePath());
                        JOptionPane.showMessageDialog(null,"******** ERROR ********\nNo se puede dividir por 0","ERROR",JOptionPane.INFORMATION_MESSAGE,iconError);
                        break;
                    }
                    total=num/num2;
                    System.out.println(num+"/"+num2+"="+total);
                    break;
                case 5:
                    operacion2=operacion.split("√");
                    operacionIgual=operacion2[1];
                    operacion2Igual=operacionIgual.split("\\s*=\\s*");
                    num=Integer.parseInt(operacion2Igual[0]);
                    if(num<0){
                        Icon iconError=new javax.swing.ImageIcon((urlImg=new File("src/calculadora2servidor/psp/calculadora.png")).getAbsolutePath());
                        JOptionPane.showMessageDialog(null,"******** ERROR ********\nSolo números positivos","ERROR",JOptionPane.INFORMATION_MESSAGE,iconError);
                        break;
                    }
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

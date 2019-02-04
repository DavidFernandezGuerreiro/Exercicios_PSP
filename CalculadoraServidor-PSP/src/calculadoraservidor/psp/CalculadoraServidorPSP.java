
package calculadoraservidor.psp;

/**
 *
 * @author David
 */
public class CalculadoraServidorPSP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Metodos_Servidor m=new Metodos_Servidor();
        
        while(true){
            m.conexion();
            m.recibirCalculo();
        }
        
    }
    
}

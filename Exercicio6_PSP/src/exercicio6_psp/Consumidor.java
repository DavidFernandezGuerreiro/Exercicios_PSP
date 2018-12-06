
package exercicio6_psp;

/**
 *
 * @author oracle
 */
public class Consumidor extends Thread{ //Extendo de Thread
    
    Caixa caixa; //Obxeto da clase Caixa
    int dineroQuitar; //Variable int, cantidade de diñeiro que vai a quitar

    //Constructor:
    public Consumidor(Caixa caixa){
        this.caixa=caixa;
    }
    
    //Método run():
    public void run() {
        for(int i=0;i<5;i++){
            dineroQuitar=(int)(Math.random()*100-1); //Asignamoslle o número random á variable "dineroQuitar"
            caixa.quitar(dineroQuitar); //Pasamoslle a cantidade a quitar ao método "quitar"
            System.out.println("Consumidor quita: "+dineroQuitar); //Imprimese a cantidade a quitar
            System.out.println("Diñeiro total: "+caixa.getTotal()+"\n"); //Imprimese a cantidade total da caixa
        }
    }
    
    
}

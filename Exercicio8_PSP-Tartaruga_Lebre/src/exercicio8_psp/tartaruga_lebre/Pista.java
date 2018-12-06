
package exercicio8_psp.tartaruga_lebre;

/**
 *
 * @author David
 */
public class Pista {
    
    boolean available=false; //variable bool, indica se pode entrar ou non
    int casillaTarta=1; //variable int, indica o número de casiñas da tartaruga
    int casillaLebre=1; //variable int, indica o número de casiñas da lebre

    //Getters e Setters:
    public int getCasillaTarta() {
        return casillaTarta;
    }
    public void setCasillaTarta(int casillaTarta) {
        this.casillaTarta = casillaTarta;
    }
    public int getCasillaLebre() {
        return casillaLebre;
    }
    public void setCasillaLebre(int casillaLebre) {
        this.casillaLebre = casillaLebre;
    }

    //Método synchronized da tartaruga:
    public synchronized void tartaruga() throws InterruptedException{
        int movemento=(int)(Math.random()*100); //Xenera un número aleatorio que indica a probabilidade
        
        while(available==true){ //Esperamos co método wait()
            wait();
        }

        if(movemento>=0 && movemento<50){ //De 0% a 50% --> +3 casiñas
            casillaTarta+=3;
        }else if(movemento>=50 && movemento<70){ //De 50% a 70% --> -6 casiñas
            casillaTarta-=6;
        }else if(movemento>=70 && movemento<100){ //De 70% a 100% --> +1 casiña
            casillaTarta+=1;
        }
        
        //Se a casiña e menor que 1...
        if(casillaTarta<1){
            casillaTarta=1; //...a casiña ponse a 1
        }
        
        System.out.println("Tartaruga: "+casillaTarta+" ---> "+movemento+"%"); //Imprime a posición e a probabilidade
        
        notify(); //Notifica a outro fio que quera executarse
        available=true; //cambia o bool a true, e espera
        
    }
    
    //Método synchronized da lebre:
    public synchronized void lebre() throws InterruptedException{
        int movemento=(int)(Math.random()*100); //Xenera un número aleatorio que indica a probabilidade
        
        while(available==false){ //Esperamos co método wait()
            wait();
        }
                
        if(movemento>=0 && movemento<20){ //De 0% a 20% --> Nada
            //non fae nada
        }else if(movemento>=20 && movemento<40){ //De 20% a 40% --> +9 casiñas
            casillaLebre+=9;
        }else if(movemento>=40 && movemento<50){ //De 40% a 50% --> -12 casiñas
            casillaLebre-=12;
        }else if(movemento>=50 && movemento<80){ //De 50% a 80% --> +1 casiñas
            casillaLebre+=1;
        }else if(movemento>=80 && movemento<100){ //De 80% a 100% --> -2 casiñas
            casillaLebre-=2;
        }
        
        //Se a casiña e menor que 1...
        if(casillaLebre<1){
            casillaLebre=1; //...a casiña ponse a 1
        }
        
        System.out.println("Lebre: "+casillaLebre+" ---> "+movemento+"%"); //Imprime a posición e a probabilidade
        
        notify(); //Notifica a outro fio que quera executarse
        available=false; //cambia o bool a false, e espera

    }
    
}

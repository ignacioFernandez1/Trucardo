package AZERTY;

import javax.swing.*;

public class App {

    public static void main(String[] args){
        Mazo mazo = new Mazo();
        mazo.reiniciar();
        Jugador jugador0 = new Jugador(0,mazo,true);
        Jugador jugador1 = new Jugador(1,mazo,false);
        System.out.println(mazo.toString());
    }
}

package AZERTY;

import javax.swing.*;
import java.util.*;

public class Mazo {
    private Carta[] mazo;
    private int cartasEnMazo;

    public Mazo() {
        mazo = new Carta[36];
        this.reiniciar();
        this.mezclar();
    }

    public void reiniciar() {

        Carta.Palo[] palos = Carta.Palo.values();
        cartasEnMazo = 0;

        for (Carta.Palo palo : palos) {
            for (int j = 0; j < 9; j++) {
                mazo[cartasEnMazo++] = new Carta(Carta.Valor.getValor(j), palo);
            }
        }
    }

    public void mezclar() {
        Random random = new Random();

        for (int i = 0; i < mazo.length; i++) {
            //swap de elementos en posicion random
            int rand = random.nextInt(mazo.length - 1);
            Carta cartaRandom = mazo[rand];
            mazo[rand] = mazo[i];
            mazo[i] = cartaRandom;
        }
    }

    public Carta sacarCarta() {
        return mazo[--cartasEnMazo];
    }


    /*
    public ImageIcon sacarImagenCarta(){
      return new ImageIcon(mazo[--cartasEnMazo].toString() + ".png");
    }*/

    //public ImageIcon sacarImagenCarta(){
    //  return new ImageIcon(mazo[--cartasEnMazo].toString() + ".png");
    //}


    @Override
    public String toString() {
        return "Mazo{" + Arrays.toString(mazo) + '}';
    }

    public int size() {
        return cartasEnMazo;
    }
}

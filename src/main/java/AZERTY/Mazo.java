package AZERTY;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Mazo {
    private Carta[] mazo;
    private int cartasEnMazo;

    public Mazo() {
        mazo = new Carta[36];

    }

    public void reiniciar() {

        Carta.Palo[] palos = Carta.Palo.values();
        cartasEnMazo = 0;

        for(int i = 0; i < palos.length ; i++)
        {
            Carta.Palo palo = palos[i];

            for(int j = 0;j < 9; j++) {

                mazo[cartasEnMazo++] = new Carta(Carta.Valor.getValor(j), palo);

            }

        }




    }

    @Override
    public String toString() {
        return "Mazo{" +
                "mazo=" + Arrays.toString(mazo) +
                '}';
    }
}



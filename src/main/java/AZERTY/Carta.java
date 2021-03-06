package AZERTY;

import javax.swing.*;

public class Carta {

    enum Palo{
        Oro, Espada, Basto, Copa;

        private static final Palo[] palos = Palo.values();
        public static Palo getPalo(int i){
            return Palo.palos[i];
        }
    }

    enum Valor{
        Uno, Dos, Tres, Cuatro, Cinco, Seis, Siete, Once, Doce;
        private static final Valor[] valores = Valor.values();
        public static Valor getValor(int i){
            return Valor.valores[i];
        }

    }

    private final Valor valor;
    private final Palo palo;

    private ImageIcon img;

    public Carta(Valor valor, Palo palo) {
        this.valor = valor;
        this.palo = palo;
        img = new ImageIcon("images/" + this.toString() + ".png");
    }

    public Valor getValor() {
        return valor;
    }

    public int getValorint(){
        if (valor.ordinal() < 7 ){ return valor.ordinal() + 1;}
        else{ return 0;}

    }

    public Palo getPalo() {
        return palo;
    }

    public ImageIcon getImg() {
        return img;
    }

    @Override
    public String toString() {
        return valor + "de" + palo;
    }
}
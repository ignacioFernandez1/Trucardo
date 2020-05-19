package AZERTY;

public class Carta {

    enum Palo{
        Oro, Espada, Basto, Copa;

        private static final Palo[] palos = Palo.values();
        public static Palo getPalo(int i)throws{
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

    public Carta(Valor valor, Palo palo) {
        this.valor = valor;
        this.palo = palo;
    }

    public Valor getValor() {
        return valor;
    }

    public Palo getPalo() {
        return palo;
    }

    @Override
    public String toString() {
        return valor + "de" + palo;
    }
}
package AZERTY;

public class ReglasAlt implements Reglas {

    private Partida partida;

    public ReglasAlt(Partida p) {
        partida = p;
    }

    @Override
    public int mayorCarta(Carta c0, Carta c1) {
        return 0;
    }
}

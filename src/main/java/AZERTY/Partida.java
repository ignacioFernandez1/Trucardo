package AZERTY;

public class Partida implements Sujeto{
    private Jugador jugador0;
    private Jugador jugador1;
    private Jugador jugadorActual;
    private int puntajeMaximo;
    private Mazo mazo;
    private boolean flor;
    private int ronda;

    public Partida(int puntajeMax, String nombre, boolean flor) {

        puntajeMaximo = puntajeMax;
        this.flor=flor;
        mazo = new Mazo();
        jugador0 = new Jugador(nombre);
        jugador1 = new Jugador("IA");
        jugadorActual = jugador1; //Al iniciar la mano se ejecuta cambiar jugador para que en la ronda 1 empiece jugador0

    }

    public void iniciarRonda(){
        //jugadorActual.actuar(ronda);
    }

    public void iniciarMano() {
        ronda = 0;
        cambiarJugador();    // siempre en la primer mano, empieza el jugador0
        ronda = 1;
        mazo = new Mazo();
        jugador0.clearMano();
        jugador1.clearMano();

        for (int i = 0; i < 6 ;i++) {
            jugadorActual.addCarta(mazo.sacarCarta());
            cambiarJugador();
        }
        iniciarRonda();

    }

    public void cambiarJugador (){
        if(jugadorActual.equals(jugador0)){ jugadorActual = jugador1;}
        else { jugadorActual = jugador0;}
    }


    public boolean termino(){
        if (jugador0.getPuntaje() >= puntajeMaximo || jugador1.getPuntaje() >= puntajeMaximo){return true;}
        else {return false;}
    }

    public int getPuntajeMaximo(){return puntajeMaximo;}
    public String getNombreJugador(){return jugador0.toString();}

    @Override
    public void registrar(Observador o) {

    }

    @Override
    public void sacar(Observador o) {

    }

    @Override
    public void notificar() {

    }
}
package AZERTY;

public class Juego {
   private Jugador jugador0;
   private Jugador jugador1;
   private Jugador jugadorActual;
   private int puntajeMaximo;
   private Mazo mazo;
   private int ronda;
    public Juego(int puntajeMax, String nombre, boolean flor) {

        puntajeMaximo = puntajeMax;

        mazo = new Mazo();
        jugador0 = new Jugador(nombre);
        jugador1 = new Jugador("robot");
        jugadorActual = jugador0;
        ronda = 0;


    }



    public void iniciarMano() {
        ronda = 1;
        mazo = new Mazo();
        jugador0.clearMano();
        jugador1.clearMano();

        for (int i = 0; i < 6 ;i++) {
            jugadorActual.addCarta(mazo.sacarCarta());
            cambiarJugador();
        }


    }

    public void cambiarJugador (){

        if(jugadorActual.equals(jugador0)){ jugadorActual = jugador1;}
        else { jugadorActual = jugador0;}
    }


    public boolean termino(){
        if (jugador0.getPuntaje() >= puntajeMaximo || jugador1.getPuntaje() >= puntajeMaximo){return true;}
        else {return false;}

    }
}

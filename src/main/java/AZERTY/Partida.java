package AZERTY;


import java.util.ArrayList;
import java.util.Stack;


public class Partida implements Sujeto{
    private Jugador jugador0;
    private Jugador jugador1;
    private Jugador jugadorActual;
    private Jugador jugadorTurno;
    private int puntajeMaximo;
    private Mazo mazo;
    private boolean flor;
    private int ronda;
    private boolean cantoEnCurso;

    private ArrayList<Observador> observers;

    private Stack<String> cantos;


    public Partida(int puntajeMax, String nombre, boolean flor) {

        puntajeMaximo = puntajeMax;
        this.flor=flor;
        mazo = new Mazo();
        jugador0 = new Jugador(nombre);
        jugador1 = new Jugador("IA");
        jugadorActual = jugador1; //Al iniciar la mano se ejecuta cambiar jugador para que en la ronda 1 empiece jugador0
        jugadorTurno = jugador0; // variable que tiene de quien es el turno de jugar carta
        cantoEnCurso = false;
        observers = new ArrayList<Observador>();

        cantos = new Stack<String>();

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
        notificar();

    }

    public void iniciarPartida(){
        iniciarMano();
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
        observers.add(o);
    }

    @Override
    public void sacar(Observador o) {
        int i = observers.indexOf(o);
        if(i >=0){
            observers.remove(i);
        }
    }

    @Override
    public void notificar() {
        if(observers.size() > 0){
            for(Observador o: observers){
                o.actualizar();
            }
        }
    }

    public Jugador getJugador0() {
        return jugador0;
    }

    /*public boolean realizarJugada(){
        if(jugadaValida("JUGADA")){
            //realizar jugada
            return true;
        }
        return false;
    }*/

    public boolean jugada(String c, Jugador jCantando) {
        if(jCantando.equals(jugadorActual)){
            if(c.equals("QUIERO")){
                if(cantos.peek().equals("NECESITA RESPUESTA")) {
                    cantos.pop();
                    this.cantoQuerido();
                    return true;
                }
            }

            if(c.equals("NO QUIERO")){
                if(cantos.peek().equals("NECESITA RESPUESTA")) {
                    cantos.pop();
                    this.cantoNoQuerido();
                    return true;
                }
            }

            // IMPORTANTE: LAS JUGADAS DE LA IA DEBEN SER VALIDADAS AUNQUE RESULTEN SIEMPRE VALIDAS

            if(c.equals("TRUCO")){
                if(cantos.contains("TRUCO QUERIDO") || cantos.contains("TRUCO NO QUERIDO") || cantos.peek().equals("NECESITA RESPUESTA")) {return false;}
                cantoEnCurso = true;
                cantos.push(c);
                cantos.push("NECESITA RESPUESTA");
                jugadorActual.switchQuieroT(); // pongo que jugador actual tiene el quiero
                this.cambiarQuieroT();  // ahora se hace un swap del quiero
                this.cambiarJugador(); // jugador actual ahora es el otro
                return true;
            }

            if(c.equals("RETRUCO")){
                if(!jugadorActual.getQuieroT() || cantos.contains("RETRUCO")){return false;}
                if(cantos.contains("TRUCO QUERIDO")){
                    cantos.push(c);
                    cantos.push("NECESITA RESPUESTA");
                    this.cambiarQuieroT();
                    this.cambiarJugador();
                    return true;
                }
                if(cantos.peek().equals("NECESITA RESPUESTA") && cantos.get(cantos.size()-2).equals("TRUCO")){
                    cantos.pop();
                    cantos.push(c);
                    cantos.push("NECESITA RESPUESTA");
                    this.cambiarQuieroT();
                    this.cambiarJugador();
                    return true;
                }
                return false;
            }

            if(c.equals("VALE CUATRO")){
                if(!jugadorActual.getQuieroT() || cantos.contains("VALE CUATRO")){return false;}
                if(cantos.contains("RETRUCO QUERIDO")){
                    cantos.push(c);
                    cantos.push("NECESITA RESPUESTA");
                    this.cambiarQuieroT();
                    this.cambiarJugador();
                    return true;
                }
                if(cantos.peek().equals("NECESITA RESPUESTA") && cantos.get(cantos.size()-2).equals("RETRUCO")){
                    cantos.pop();
                    cantos.push(c);
                    cantos.push("NECESITA RESPUESTA");
                    this.cambiarQuieroT();
                    this.cambiarJugador();
                    return true;
                }
                return false;
            }}

        //FALTA IMPLEMENTAR LOS ENVIDOS
        return false;
    }

    //public void cantoIA(String c) { cantos.push(c); }

    public void cantoQuerido() {
        cantoEnCurso = false;
        jugadorActual = jugadorTurno;
        String c = cantos.pop();
        if(c.equals("TRUCO")){
            cantos.push("TRUCO QUERIDO");
            return;
        }
        if(c.equals("RETRUCO")) {
            cantos.push("RETRUCO QUERIDO");
            return;
        }
        if (c.equals("VALE CUATRO")) {
            cantos.push("VALE CUATRO QUERIDO");
            return;
        }
    }

    public void cantoNoQuerido() { // CAMBIAR METODO PARA APLICAR AL ENVIDO !!!!!!!!!!!!!!!
        cantoEnCurso = false;
        String c = cantos.pop();
        jugadorActual = jugadorTurno;
        if (c.equals("TRUCO")) {
            cantos.push("TRUCO NO QUERIDO");
            return;
        }
        if (c.equals("RETRUCO")) {
            cantos.push("RETRUCO NO QUERIDO");
            return;
        }
        if (c.equals("VALE CUATRO")) {
            cantos.push("VALE CUATRO NO QUERIDO");
            return;
        }
    }

    public void cambiarQuieroE(){
        jugador0.switchQuieroE();
        jugador1.switchQuieroE();
    }

    public void cambiarQuieroT(){
        jugador0.switchQuieroT();
        jugador1.switchQuieroT();
    }


}
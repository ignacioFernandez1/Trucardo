package AZERTY;


import java.util.ArrayList;
import java.util.Stack;


public class Partida implements Sujeto{
    private Jugador jugador0;
    private AI jugador1;
    private Jugador jugadorActual;
    private Jugador jugadorTurno;
    private int puntajeMaximo;
    private Mazo mazo;
    private boolean flor;
    private int ronda; // ronda va de 1 a 6, si ronda%2==0 se chequea quien gano la ronda
    private boolean cantoEnCurso;
    private Reglas reglas;

    private ArrayList<Observador> observers;

    private Stack<String> cantos;


    public Partida(int puntajeMax, String nombre, boolean flor,AI ai) {

        puntajeMaximo = puntajeMax;
        this.flor=flor;
        mazo = new Mazo();
        jugador0 = new Jugador(nombre);
        jugador1 = ai;
        jugadorActual = jugador1; //Al iniciar la mano se ejecuta cambiar jugador para que en la ronda 1 empiece jugador0
        jugadorTurno = jugador0;
        cantoEnCurso = false;
        observers = new ArrayList<Observador>();
        cantos = new Stack<String>();
        iniciarStack();
        reglas = new ReglasTrad();

    }

    public void iniciarRonda(){
        //jugadorActual.actuar(ronda);
    }

    public void iniciarMano() {
        ronda = 0;
        cambiarJugador();    // siempre en la primer mano, empieza el jugador0
        ronda = 1;
        mazo.reiniciar();
        mazo.mezclar();
        jugador0.clearMano();
        jugador1.clearMano();

        for (int i = 0; i < 6 ;i++) {
            jugadorActual.addCarta(mazo.sacarCarta());
            cambiarJugador();
        }
        jugador0.puntos();
        jugador1.puntos();
        jugador1.setValor(reglas.valorCartas( jugador1.getMano()));
        iniciarRonda();
        notificar();
    }
    private void iniciarStack(){cantos.push("-");}

    public void iniciarPartida(){
        iniciarMano();
    }

    public void cambiarJugador (){
        if(jugadorActual.equals(jugador0)){ jugadorActual = jugador1;}
        else { jugadorActual = jugador0;}
    }

    public void cambiarJugadorTurno (){
        if(jugadorTurno.equals(jugador0)){ jugadorTurno = jugador1;}
        else { jugadorTurno = jugador0;}
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
                if(cantos.contains("TRUCO QUERIDO") || cantos.contains("TRUCO NO QUERIDO") || cantos.peek().equals("NECESITA RESPUESTA") || cantoEnCurso) {return false;}
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
            }
            if(ronda == 1 || ronda == 2) {
                if (c.equals("ENVIDO") && !cantos.contains("REAL ENVIDO") && !cantos.contains("FALTA ENVIDO") && !cantos.contains("ENVIDO TOPE")) {
                    if(cantos.peek().equals("NECESITA RESPUESTA")){
                        cantos.pop();
                        cantos.push("ENVIDO TOPE");
                        cantos.push(c);
                        cantos.push("NECESITA RESPUESTA");
                        this.cambiarJugador();
                        return true;
                    }
                    if(!cantoEnCurso) {
                        cantos.push(c);
                        cantoEnCurso = true;
                        cantos.push("NECESITA RESPUESTA");
                        this.cambiarJugador();
                        return true;
                    }
                }
                if(c.equals("REAL ENVIDO") && !cantos.contains("FALTA ENVIDO") && !cantos.contains("REAL ENVIDO")){
                    if(cantos.peek().equals("NECESITA RESPUESTA") && cantos.get(cantos.size()-2).equals("ENVIDO")){
                        cantos.pop();
                        cantos.push(c);
                        cantos.push("NECESITA RESPUESTA");
                        this.cambiarJugador();
                        return true;
                    }
                    if(!cantoEnCurso) {
                        cantos.push(c);
                        cantoEnCurso = true;
                        cantos.push("NECESITA RESPUESTA");
                        this.cambiarJugador();
                        return true;
                    }
                    return false;
                }
                if(c.equals("FALTA ENVIDO") && !cantos.contains("FALTA ENVIDO")){
                    if(cantos.peek().equals("NECESITA RESPUESTA")){
                        cantos.pop();
                        cantos.push(c);
                        cantos.push("NECESITA RESPUESTA");
                        this.cambiarJugador();
                        return true;
                    }
                    cantos.push(c);
                    cantoEnCurso = true;
                    cantos.push("NECESITA RESPUESTA");
                    this.cambiarJugador();
                    return true;
                }
            }
        }
        if(jCantando.equals(jugadorTurno) && !cantoEnCurso) {
            if (c.equals("carta1") || c.equals("carta2") || c.equals("carta3")) {
                int i = Integer.parseInt(""+c.charAt(5)) - 1;
                Carta carta = jugadorTurno.getMano().get(i);
                jugadorTurno.getMano().remove(i);
                jugadorTurno.addCartaPila(carta);
                this.notificar();
                cambiarJugadorTurno();
                jugadorActual = jugadorTurno;
                ronda++;
                if(ronda%2==0){
                    // se chequea cual es la carta mas alta de la ronda
                    // y se guarda en estadisticas (hacer con un metodo)
                }
                return true;
            }
        }
        return false;
    }

    //public void cantoIA(String c) { cantos.push(c); }

    public void cantoQuerido() {
        cantoEnCurso = false;
        jugadorActual = jugadorTurno;
        String c = cantos.peek();
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
        if(c.equals("ENVIDO") || c.equals("REAL ENVIDO") || c.equals("FALTA ENVIDO")){
            // que se hace cuando se termina un canto de envido
            // yo digo que se fije cuantos cantos del tipo envido hubo y que acumule los puntos
            // despues de eso se fija cual tiene mas y le da los puntos
        }
    }

    public void cantoNoQuerido() { // CAMBIAR METODO PARA APLICAR AL ENVIDO !!!!!!!!!!!!!!!
        cantoEnCurso = false;
        String c = cantos.peek();
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
        if(c.equals("ENVIDO") || c.equals("REAL ENVIDO") || c.equals("FALTA ENVIDO")){
            // aca tambien tenemos que ver que hacer en
        }
    }

    public int cualMayor (Carta c0, Carta c1){
        return reglas.mayorCarta(c0,c1);
    }

    public void cambiarQuieroE(){
        jugador0.switchQuieroE();
        jugador1.switchQuieroE();
    }

    public void cambiarQuieroT(){
        jugador0.switchQuieroT();
        jugador1.switchQuieroT();
    }

    public AI getJugador1(){return jugador1;}

    public boolean isCantoEnCurso(){return cantoEnCurso;}
}
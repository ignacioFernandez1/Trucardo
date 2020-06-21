package AZERTY;


import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class Partida implements Sujeto{
    private Jugador jugador0;
    private AI jugador1;
    private Jugador jugadorActual;
    private Jugador jugadorTurno;
    private Jugador jugadorMano;
    private int puntajeMaximo;
    private Mazo mazo;
    private boolean flor;
    private int ronda; // ronda va de 1 a 3
    private int cartasJugadas; // si es == 2 se compara para ver quien gano y se resetea
    private boolean cantoEnCurso;
    private Reglas reglas;
    private int[] rondasGanadas;
    private Task task;
    private ThreadPoolExecutor executor;

    private ArrayList<Observador> observers;

    private Stack<String> cantos;


    public Partida(int puntajeMax, String nombre, boolean flor,AI ai) {

        puntajeMaximo = puntajeMax;
        this.flor=flor;
        mazo = new Mazo();
        jugador0 = new Jugador(nombre);
        jugador1 = ai;
        jugadorActual = jugador0; //Al iniciar la mano se ejecuta cambiar jugador para que en la ronda 1 empiece jugador0
        jugadorTurno = jugador0;
        jugadorMano = jugador1; // arranca en jugador1 para que cuando se ejecute iniciar mano, la primera mano arranque el jugador0
        cantoEnCurso = false;
        observers = new ArrayList<Observador>();
        cantos = new Stack<String>();
        reglas = new ReglasTrad();
        rondasGanadas = new int[2];
        rondasGanadas[0] = 0;
        rondasGanadas[1] = 0;
        task = new Task(this.getJugador1());
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
    }

    public void iniciarRonda(){
        //jugadorActual.actuar(ronda);
    }

    private void cambiarJugadorMano(){
        if(jugadorMano.equals(jugador0)){ jugadorMano = jugador1;}
        else { jugadorMano = jugador0;}
    }

    public void iniciarMano() {
        iniciarStack();
        cantoEnCurso = false;
        rondasGanadas[0] = 0;
        rondasGanadas[1] = 0;
        ronda = 0;
        cartasJugadas = 0;
        cambiarJugadorMano();
        jugadorActual = jugadorMano;
        jugadorTurno = jugadorMano;
        ronda = 1;
        cartasJugadas = 0;
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
        notificar();
        if(jugadorTurno.equals(jugador1)){
            task.setQueHago(3);
            executor.execute(task);
        }
        System.out.println(ronda);
        notificar();
    }
    private void iniciarStack(){
        cantos.clear();
        cantos.push("-");
    }

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

    public synchronized boolean jugada(String c, Jugador jCantando) {
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
            if(ronda == 1) {
                if (c.equals("ENVIDO") && !cantos.contains("REAL ENVIDO") && !cantos.contains("FALTA ENVIDO") && !cantos.contains("ENVIDO TOPE")) {
                    if(cantos.peek().equals("NECESITA RESPUESTA")){
                        cantos.pop();
                        cantos.push("ENVIDO TOPE");
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
                if(c.equals("REAL ENVIDO") && !cantos.contains("FALTA ENVIDO") && !cantos.contains("REAL ENVIDO")){
                    if(cantos.peek().equals("NECESITA RESPUESTA") && cantos.get(cantos.size()-2).equals("ENVIDO")){
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
            boolean flag = false;
            if (c.equals("carta1") || c.equals("carta2") || c.equals("carta3")) {
                int i = Integer.parseInt(""+c.charAt(5)) - 1;
                Carta carta = jugadorTurno.getMano().get(i);
                jugadorTurno.getMano().remove(i);
                jugadorTurno.addCartaPila(carta);
                this.notificar();
                cambiarJugadorTurno();
                jugadorActual = jugadorTurno;
                cartasJugadas++;
                if(cartasJugadas == 2){
                    cartasJugadas = 0;
                    if(reglas.mayorCarta(jugador0.getPila().get(ronda-1),jugador1.getPila().get(ronda-1)) == 0){
                        rondasGanadas[0] += 1;
                        jugadorTurno = jugador0;
                        jugadorActual = jugador0;
                        if(rondasGanadas[0] == 2){
                            // ver cuantos puntos gano el jugador0 por truco
                            flag = true;
                            System.out.println("gano la mano el jugador0");
                        }
                    }
                    else if(reglas.mayorCarta(jugador0.getPila().get(ronda-1),jugador1.getPila().get(ronda-1)) == 1){
                        rondasGanadas[1] += 1;
                        jugadorTurno = jugador1;
                        jugadorActual = jugador1;
                        if(rondasGanadas[1] == 2){
                            // ver cuantos puntos gano el jugador0 por truco
                            flag = true;
                            System.out.println("gano la mano el bot");
                        }
                        else{
                            task.setQueHago(3);
                            executor.execute(task);
                        }
                    }
                    else{
                        // anotar ronda parda
                    }
                    ronda++;
                    System.out.println(ronda);
                    if(ronda == 4 || flag){
                        // aca se termina la mano
                        iniciarMano();
                        return true;
                    }
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

    public int getCartasJugadas() { return cartasJugadas; }

    public Reglas getReglas() { return reglas; }

    public int getRonda() {
        return ronda;
    }
}
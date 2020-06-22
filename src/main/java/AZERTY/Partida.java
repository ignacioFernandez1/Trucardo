package AZERTY;


import javax.swing.*;
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
    private Estadisticas estadisticas;
    private int manos;

    private ArrayList<Observador> observers;

    private Stack<String> cantos;


    public Partida(int puntajeMax, String nombre, boolean flor,AI ai) {
        manos = 0;
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
        task = new Task(this.getJugador1(),this);
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        estadisticas = new Estadisticas();
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
        manos++;
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
                    cantos.push("TRUCO QUERIDO");
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
                    cantos.push("RETRUCO QUERIDO");
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
            if(c.equals("ME VOY")){
                estadisticas.addPuntos(1,sumarPuntos(),true);
                terminarPartida();
                task.setQueHago(10);
                executor.execute(task);
                return true;
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
                        if(rondasGanadas[0] >= 2){
                            // ver cuantos puntos gano el jugador0 por truco
                            flag = true;
                            System.out.println("gano la mano el jugador0");
                            estadisticas.addPuntos(0,sumarPuntos(),true);
                            terminarPartida();
                        }
                    }
                    else if(reglas.mayorCarta(jugador0.getPila().get(ronda-1),jugador1.getPila().get(ronda-1)) == 1){
                        rondasGanadas[1] += 1;
                        jugadorTurno = jugador1;
                        jugadorActual = jugador1;
                        if(rondasGanadas[1] >= 2){
                            // ver cuantos puntos gano el jugador0 por truco
                            flag = true;
                            System.out.println("gano la mano el bot");
                            estadisticas.addPuntos(1,sumarPuntos(),true);
                            terminarPartida();
                        }
                        else{
                            task.setQueHago(3);
                            executor.execute(task);
                        }
                    }
                    else{
                        rondasGanadas[0] += 1;
                        rondasGanadas[1] += 1;
                        jugadorTurno = jugadorMano;
                        jugadorActual = jugadorMano;
                        if(jugadorTurno.equals(jugador1)){
                            task.setQueHago(3);
                            executor.execute(task);
                        }
                        if(rondasGanadas[0] >= 2){
                            // ver cuantos puntos gano el jugador0 por truco
                            flag = true;
                            System.out.println("gano la mano el jugador0");
                            estadisticas.addPuntos(0,sumarPuntos(),true);
                            terminarPartida();
                        }
                        if(rondasGanadas[1] >= 2){
                            // ver cuantos puntos gano el jugador0 por truco
                            flag = true;
                            System.out.println("gano la mano el bot");
                            estadisticas.addPuntos(1,sumarPuntos(),true);
                            terminarPartida();
                        }
                    }
                    ronda++;
                    System.out.println(ronda);
                    if(ronda == 4 || flag){
                        // aca se termina la mano
                        task.setQueHago(10);
                        executor.execute(task);
                        return true;
                    }
                }
                return true;
            }
        }
        return false;
    }

    //public void cantoIA(String c) { cantos.push(c); }

    public synchronized void cantoQuerido() {
        cantoEnCurso = false;
        String c = cantos.peek();
        if(c.equals("TRUCO")){
            cantos.push("TRUCO QUERIDO");
            jugadorActual = jugadorTurno;
            if(jugadorTurno.equals(jugador1)){
                task.setQueHago(3);
                executor.execute(task);
            }
            return;
        }
        if(c.equals("RETRUCO")) {
            cantos.push("RETRUCO QUERIDO");
            jugadorActual = jugadorTurno;
            if(jugadorTurno.equals(jugador1)){
                task.setQueHago(3);
                executor.execute(task);
            }
            return;
        }
        if (c.equals("VALE CUATRO")) {
            cantos.push("VALE CUATRO QUERIDO");
            jugadorActual = jugadorTurno;
            if(jugadorTurno.equals(jugador1)){
                task.setQueHago(3);
                executor.execute(task);
            }
            return;
        }
        if(c.equals("ENVIDO") || c.equals("REAL ENVIDO") || c.equals("FALTA ENVIDO")){
            Jugador perdedor;
            Jugador ganador;
            if(jugador0.getPuntos()>jugador1.getPuntos()){
                perdedor = jugador1;
                ganador = jugador0;
            }
            else if(jugador0.getPuntos()<jugador1.getPuntos()){
                perdedor = jugador0;
                ganador = jugador1;
            }
            else {
                if(jugadorMano.equals(jugador0)){
                    perdedor = jugador1;
                    ganador = jugador0;
                }
                else{
                    perdedor = jugador0;
                    ganador = jugador0;
                }
            }
            int puntos = 0;
            for(int i = 1; i < cantos.size(); i++){
                if(cantos.get(i).equals("ENVIDO")){puntos += 2;}
                else if(cantos.get(i).equals("REAL ENVIDO")){puntos += 3;}
            }
            if(cantos.contains("FALTA ENVIDO")){puntos = 15 - estadisticas.getpuntos(perdedor)%15;}
            estadisticas.addPuntos(ganador, puntos,false);
            terminarPartida();
            notificar();
            jugadorActual = jugadorTurno;
            if(jugadorTurno.equals(jugador1)){
                task.setQueHago(3);
                executor.execute(task);
            }
        }

    }

    public synchronized void cantoNoQuerido() { // CAMBIAR METODO PARA APLICAR AL ENVIDO !!!!!!!!!!!!!!!
        cantoEnCurso = false;
        String c = cantos.peek();
        if (c.equals("TRUCO") || c.equals("RETRUCO")  || c.equals("VALE CUATRO")) {
            if(jugadorActual.equals(jugador0)) {
                estadisticas.addPuntos(1, sumarPuntos(),true);
                terminarPartida();
                task.setQueHago(10);
                executor.execute(task);
                return;
            }
            else{
                estadisticas.addPuntos(0, sumarPuntos(),true);
                terminarPartida();
                task.setQueHago(10);
                executor.execute(task);
                return;
            }
        }
        if(c.equals("ENVIDO") || c.equals("REAL ENVIDO") || c.equals("FALTA ENVIDO")){
            int puntos = 0;
            cambiarJugador();
            for(int i = 1; i < cantos.size()-1; i++){
                if(cantos.get(i).equals("ENVIDO")){puntos += 2;}
                else if(cantos.get(i).equals("REAL ENVIDO")){puntos += 3;}
            }
            if(puntos == 0){puntos = 1;}
            estadisticas.addPuntos(jugadorActual, puntos,false);
            terminarPartida();
            notificar();
            if(jugadorTurno.equals(jugador1)){
                task.setQueHago(3);
                executor.execute(task);
            }
            jugadorActual = jugadorTurno;
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

    private int sumarPuntos(){
        if(cantos.contains("VALE CUATRO QUERIDO")){
            return 4;
        }
        if(cantos.contains("RETRUCO QUERIDO")){
            return 3;
        }
        if(cantos.contains("TRUCO QUERIDO")){
            return 2;
        }
        return 1;
    }

    public Estadisticas getEstadisticas(){return estadisticas;}
    public Stack<String> getCantos(){return cantos;}
    public int getManos() { return manos; }
    public void terminarPartida(){
        if(estadisticas.getPuntos0() >= puntajeMaximo) {
            System.out.println("GANASTE!!");
            int confirmed = JOptionPane.showConfirmDialog(null,
                    "Le ganaste al BOT "+estadisticas.getPuntos0()+" a "+estadisticas.getPuntos1(), "GANASTE",
                    JOptionPane.OK_OPTION);
            if (confirmed == JOptionPane.OK_OPTION) {
                String[] args = {};
                // No inicia el gif de trucardo
                System.exit(0);
            }
        }
        if(estadisticas.getPuntos1() >= puntajeMaximo){
            System.out.println("PERDISTE!!");
            int confirmed = JOptionPane.showConfirmDialog(null,
                    "Perdiste contra el BOT "+estadisticas.getPuntos0()+" a "+estadisticas.getPuntos1(), "PERDISTE",
                    JOptionPane.YES_NO_OPTION);
            if (confirmed == JOptionPane.YES_OPTION) {
                String[] args = {};
                // No inicia el gif de trucardo
                System.exit(0);
            }
        }
    }
    public void setReglas(Reglas reglas){this.reglas = reglas;}
}
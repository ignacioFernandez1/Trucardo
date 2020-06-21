package AZERTY;

public class Estadisticas {
    private int puntos0;
    private int puntos1;
    private int truco0;
    private int envido0;
    private int truco1;
    private int envido1;

    public Estadisticas() {
        puntos0 = 0;
        puntos1 = 0;
        truco0 = 0;
        envido0 = 0;
        truco1 = 0;
        envido1 = 0;
    }

    public void addPuntos(int j, int puntos,boolean truco){
        if(j == 0){
            if(truco){
                truco0 = puntos;
                envido0 = 0;
                envido1 = 0;
            }
            else{envido0 = puntos;}

            puntos0 += puntos;
        }
        else{
            if(truco){
                truco1 = puntos;
                envido1 = 0;
                envido0 = 0;
            }
            else{envido1 = puntos;}
            puntos1 += puntos;
        }
    }

    public int getPuntos0(){return puntos0;}
    public int getPuntos1(){return puntos1;}
    public int getpuntos(Jugador j){
        if(j.getNombre().equals("AI")){return puntos1;}
        else {return puntos0;}
    }

    public void addPuntos(Jugador j, int p,boolean truco){
        if(j.getNombre().equals("AI")){
            puntos1 += p;
            if(truco){
                truco1 = p;
                envido1 = 0;
                envido0 = 0;
            }
            else{envido1 = p;}
        }
        else {
            puntos0 += p;
            if(truco){
                truco0 = p;
                envido0 = 0;
                envido1 = 0;
            }
            else{envido0 = p;}
        }
    }

    public int getTruco0() {
        return truco0;
    }

    public int getEnvido0() {
        return envido0;
    }

    public int getTruco1() {
        return truco1;
    }

    public int getEnvido1() {
        return envido1;
    }
}

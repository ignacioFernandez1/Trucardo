package AZERTY;

public class Estadisticas {
    private int puntos0;
    private int puntos1;

    public Estadisticas() {
        puntos0 = 0;
        puntos1 = 0;
    }

    public void addPuntos(int j, int puntos){
        if(j == 0){
            puntos0 += puntos;
        }
        else{
            puntos1 += puntos;
        }
    }

    public int getPuntos0(){return puntos0;}
    public int getPuntos1(){return puntos1;}
}

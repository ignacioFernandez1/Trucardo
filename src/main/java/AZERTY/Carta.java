package AZERTY;

public class Carta {
    private int numero;
    private String palo;

    public Carta(int numero, String palo){
        if(numero > 7){
            this.numero = numero + 3;
        }
        else{
            this.numero = numero;
        }
        this.palo = palo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getPalo() {
        return palo;
    }

    public void setPalo(String palo) {
        this.palo = palo;
    }
}

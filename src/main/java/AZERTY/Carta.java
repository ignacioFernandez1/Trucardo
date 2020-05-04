package AZERTY;

public class Carta {
    private int numero;
    private char palo;

    public Carta(int numero, char palo){
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
        if (palo == 'c') {return "Copa";}
        if (palo == 'e') {return "Espada";}
        if (palo == 'b') {return "Basto";}
        if (palo == 'o') {return "Oro";}
        else{return "Carta no valida";}
    }

    public void setPalo(char palo) {
        this.palo = palo;
    }
}

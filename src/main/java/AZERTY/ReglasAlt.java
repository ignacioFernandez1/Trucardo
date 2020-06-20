package AZERTY;

import java.util.ArrayList;
import java.util.HashMap;

public class ReglasAlt implements Reglas {

    HashMap<String, Integer> orden;

    public ReglasAlt() {
        orden = new HashMap<>(36);
        ordenar();

    }

    @Override
    public int mayorCarta(Carta c0, Carta c1) {
        int v0 = orden.get(c0.toString());
        int v1 = orden.get(c1.toString());
        if(v0<v1){return 0;}
        else if(v0>v1){return 1;}
        else if(v1 == v0){return 2;}
        return 3;
    }

    //public int valor(String carta) {}
    public void ordenar (){
        orden.put("UnodeEspada",13 );
        orden.put("UnodeBasto",12 );
        orden.put("SietedeEspada",11);
        orden.put("SietedeOro",10);
        orden.put("TresdeBasto",9 );
        orden.put("TresdeOro",9 );
        orden.put("TresdeEspada",9 );
        orden.put("TresdeCopa",9 );
        orden.put("DosdeCopa",8 );
        orden.put("DosdeOro",8 );
        orden.put("DosdeEspada",8 );
        orden.put("DosdeBasto",8 );
        orden.put("UnodeCopa",7 );
        orden.put("UnodeOro",7 );
        orden.put("DocedeBasto",6 );
        orden.put("DocedeCopa",6 );
        orden.put("DocedeEspada",6 );
        orden.put("DocedeOro",6 );
        orden.put("OncedeEspada",5 );
        orden.put("OncedeOro",5 );
        orden.put("OncedeCopa",5 );
        orden.put("OncedeBasto",5 );
        orden.put("SietedeBasto",4 );
        orden.put("SietedeCopa",4 );
        orden.put("SeisdeEspada",3 );
        orden.put("SeisdeCopa",3 );
        orden.put("SeisdeOro",3 );
        orden.put("SeisdeBasto",3 );
        orden.put("CincodeEspada",2 );
        orden.put("CincodeCopa",2 );
        orden.put("CincodeOro",2 );
        orden.put("CincodeBasto",2 );
        orden.put("CuatrodeCopa",1 );
        orden.put("CuatrodeBasto",1 );
        orden.put("CuatrodeEspada",1 );
        orden.put("CuatrodeOro",1 );


    }

    @Override
    public int valorCartas(ArrayList<Carta> cartas) {
        int valor = 0;
        for(int i = 0; i<cartas.size() ;i++){
            valor += orden.get(cartas.get(i).toString());
        }
        return valor;
    }
}

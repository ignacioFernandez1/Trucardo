package AZERTY;

import java.util.HashMap;

public class ReglasTrad implements Reglas{
    HashMap<String, Integer> orden;

    public ReglasTrad() {
        orden = new HashMap<>(36);
        ordenar();

    }

    @Override
    public int mayorCarta(Carta c0, Carta c1) {
        int v0 = orden.get(c0.toString());
        int v1 = orden.get(c1.toString());
        if(v0<v1){return 0;}
        else if(v1>v0){return 1;}
        else if(v1 == v0){return 2;}
        return 3;
    }

    //public int valor(String carta) {}
    public void ordenar (){
        orden.put("UnodeEspada",1 );
        orden.put("UnodeBasto",2 );
        orden.put("SietedeEspada",3 );
        orden.put("SietedeOro",4 );
        orden.put("TresdeBasto",5 );
        orden.put("TresdeOro",5 );
        orden.put("TresdeEspada",5 );
        orden.put("TresdeCopa",5 );
        orden.put("DosdeCopa",6 );
        orden.put("DosdeOro",6 );
        orden.put("DosdeEspada",6 );
        orden.put("DosdeBasto",6 );
        orden.put("UnodeCopa",7 );
        orden.put("UnodeOro",7 );
        orden.put("DocedeBasto",8 );
        orden.put("DocedeCopa",8 );
        orden.put("DocedeEspada",8 );
        orden.put("DocedeOro",8 );
        orden.put("OncedeEspada",9 );
        orden.put("OncedeOro",9 );
        orden.put("OncedeCopa",9 );
        orden.put("OncedeBasto",9 );
        orden.put("SietedeBasto",10 );
        orden.put("SietedeCopa",10 );
        orden.put("SeisdeEspada",11 );
        orden.put("SeisdeCopa",11 );
        orden.put("SeisdeOro",11 );
        orden.put("SeisdeBasto",11 );
        orden.put("CincodeEspada",12 );
        orden.put("CincodeCopa",12 );
        orden.put("CincodeOro",12 );
        orden.put("CincodeBasto",12 );
        orden.put("CuatrodeCopa",13 );
        orden.put("CuatrodeBasto",13 );
        orden.put("CuatrodeEspada",13 );
        orden.put("CuatrodeOro",13 );


    }

}

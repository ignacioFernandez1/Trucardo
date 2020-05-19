package AZERTY;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        Mazo mazo = new Mazo();
        Jugador jugador0 = new Jugador(0,mazo,true);
        Jugador jugador1 = new Jugador(1,mazo,false);
        System.out.println(mazo.toString());
        mazo.mezclar();
        System.out.println(mazo.toString());

        System.out.println(mazo.sacarCarta().toString());

    }
}
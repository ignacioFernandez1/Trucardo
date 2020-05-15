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
        Jugador jugador0 = new Jugador(0,mazo);
        Jugador jugador1 = new Jugador(1,mazo);
        System.out.println(mazo.toString());
        System.out.println(mazo.getMazo().size());
        System.out.println(jugador0.toString());
        System.out.println(jugador1.toString());


    }
}

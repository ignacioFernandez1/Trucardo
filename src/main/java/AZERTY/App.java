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
        System.out.println(mazo.toString());
        System.out.println(mazo.getMazo().size());
        mazo.mezclar();
        System.out.println("-----------------------------------------");
        System.out.println(mazo.toString());
        System.out.println(mazo.getMazo().size());
    }
}

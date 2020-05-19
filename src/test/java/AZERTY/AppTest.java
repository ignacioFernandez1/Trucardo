package AZERTY;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import AZERTY.Carta;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testPaloCarta()
    {
        Carta carta = new Carta(Carta.Valor.getValor(5), Carta.Palo.Copa);
        assertEquals(Carta.Palo.Copa, carta.getPalo());
    }

    @Test
    public void testNumeroCarta() {
        Carta carta = new Carta(Carta.Valor.getValor(5), Carta.Palo.Copa);
        assertEquals(Carta.Valor.Seis, carta.getValor());
    }
}

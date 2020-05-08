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
       Carta carta = new Carta(2, "Copa");
       assertEquals("Copa", carta.getPalo());
    }

    @Test
    public void testNumeroCarta() {
        Carta carta = new Carta(2, "Copa");
        assertEquals(2, carta.getNumero());
    }
}

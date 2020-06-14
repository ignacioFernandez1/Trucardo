package AZERTY;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import AZERTY.Carta;
import org.junit.Test;

import java.util.ArrayList;

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
        Carta carta = new Carta(Carta.Valor.getValor(7), Carta.Palo.Basto);
        assertEquals(Carta.Valor.Once, carta.getValor());
    }

    @Test
    public void testPuntos3MismoPalo() {
        Jugador jugador = new Jugador(" ");
        jugador.addCarta(new Carta(Carta.Valor.Siete, Carta.Palo.Basto));
        jugador.addCarta(new Carta(Carta.Valor.Cuatro, Carta.Palo.Basto));
        jugador.addCarta(new Carta(Carta.Valor.Doce, Carta.Palo.Basto));
        assertEquals(31, jugador.puntos());
    }

    @Test
    public void testPuntos2MismoPalo() {
        Jugador jugador = new Jugador(" ");
        jugador.addCarta(new Carta(Carta.Valor.Siete, Carta.Palo.Basto));
        jugador.addCarta(new Carta(Carta.Valor.Cuatro, Carta.Palo.Copa));
        jugador.addCarta(new Carta(Carta.Valor.Uno, Carta.Palo.Basto));
        assertEquals(28, jugador.puntos());
    }

    @Test
    public void testPuntosDistintoPalo() {
    Jugador jugador = new Jugador(" ");
    jugador.addCarta(new Carta(Carta.Valor.Siete, Carta.Palo.Espada));
    jugador.addCarta(new Carta(Carta.Valor.Cuatro, Carta.Palo.Basto));
    jugador.addCarta(new Carta(Carta.Valor.Doce, Carta.Palo.Copa));
    assertEquals(7, jugador.puntos());
}
}

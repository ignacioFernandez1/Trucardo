package AZERTY;

import AZERTY.Carta;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

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
        jugador.puntos();
        assertEquals(31, jugador.getPuntos());
    }

    @Test
    public void testPuntos2MismoPalo() {
        Jugador jugador = new Jugador(" ");
        jugador.addCarta(new Carta(Carta.Valor.Siete, Carta.Palo.Basto));
        jugador.addCarta(new Carta(Carta.Valor.Cuatro, Carta.Palo.Copa));
        jugador.addCarta(new Carta(Carta.Valor.Uno, Carta.Palo.Basto));
        jugador.puntos();
        assertEquals(28, jugador.getPuntos());
    }

    @Test
    public void testPuntosDistintoPalo() {
        Jugador jugador = new Jugador(" ");
        jugador.addCarta(new Carta(Carta.Valor.Siete, Carta.Palo.Espada));
        jugador.addCarta(new Carta(Carta.Valor.Cuatro, Carta.Palo.Basto));
        jugador.addCarta(new Carta(Carta.Valor.Doce, Carta.Palo.Copa));
        jugador.puntos();
        assertEquals(7, jugador.getPuntos());
    }

    @Test
    public void jugadaValidaTruco() {
        Partida p = new Partida(15,"test",false, new AI(new Mesa(14," ",false)));
        p.iniciarMano();
        assertTrue(p.jugada("TRUCO", p.getJugador0()));
        assertTrue(!p.jugada("RETRUCO",p.getJugador0())); // jugador0 no deberia poder cantar
        assertTrue(p.jugada("RETRUCO",p.getJugador1())); // jugador1 deberia poder cantar
    }

    @Test
    public void jugadaValidaQuiero() {
        Partida p = new Partida(15,"test",false, new AI(new Mesa(14," ",false)));
        p.iniciarMano();
        p.jugada("TRUCO",p.getJugador0());
        assertTrue(p.jugada("QUIERO",p.getJugador1()));
    }

    @Test
    public void jugadaValida() {
        Partida p = new Partida(15,"test",false, new AI(new Mesa(14," ",false)));
        p.iniciarMano();
        p.jugada("TRUCO",p.getJugador0());
        assertTrue(!p.jugada("TRUCO",p.getJugador1())); // el otro jugador no deberia poder cantar truco
    }

    @Test
    public void jugadaValidaEnvido() {
        Partida p = new Partida(15,"test",false, new AI(new Mesa(14," ",false)));
        p.iniciarMano();
        assertTrue(p.jugada("ENVIDO",p.getJugador0()));
        assertTrue(!p.jugada("ENVIDO",p.getJugador0()));
        assertTrue(p.jugada("ENVIDO",p.getJugador1()));
        assertTrue(!p.jugada("ENVIDO",p.getJugador0()));
    }

    @Test
    public void jugadaValidaRealEnvido() {
        Partida p = new Partida(15,"test",false, new AI(new Mesa(14," ",false)));
        p.iniciarMano();
        assertTrue(p.jugada("REAL ENVIDO",p.getJugador0()));
        assertTrue(!p.jugada("REAL ENVIDO",p.getJugador1()));

    }

    @Test
    public void jugadaValidaFaltaEnvido() {
        Partida p = new Partida(15,"test",false, new AI(new Mesa(14," ",false)));
        p.iniciarMano();
        assertTrue(p.jugada("FALTA ENVIDO",p.getJugador0()));
        assertTrue(!p.jugada("FALTA ENVIDO",p.getJugador1()));
    }

    @Test
    public void jugadaValidaJugarCarta() {
        Partida p = new Partida(15,"test",false, new AI(new Mesa(14," ",false)));
        p.iniciarMano();
        assertTrue(!p.jugada("carta1",p.getJugador1()));
        assertTrue(p.jugada("carta1",p.getJugador0()));
        assertEquals(2,p.getJugador0().getMano().size());
    }

    @Test
    public void jugadaCantoEnCurso() {
        Partida p = new Partida(15,"test",false, new AI(new Mesa(14," ",false));
        p.iniciarMano();
        p.jugada("ENVIDO",p.getJugador0());
        assertTrue(p.isCantoEnCurso());
    }

    @Test
    public void cartaMayorReglasTrad() {
        Reglas r = new ReglasTrad();
        int v = r.mayorCarta(new Carta (Carta.Valor.Siete, Carta.Palo.Basto),new Carta(Carta.Valor.Cuatro, Carta.Palo.Basto));
        assertEquals(0,v);
    }

    @Test
    public void cartaMayorReglasAlt() {
        Reglas r = new ReglasAlt();
        int v = r.mayorCarta(new Carta (Carta.Valor.Siete, Carta.Palo.Basto),new Carta(Carta.Valor.Cuatro, Carta.Palo.Basto));
        assertEquals(1,v);
    }
}

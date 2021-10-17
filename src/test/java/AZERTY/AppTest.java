package AZERTY;

import AZERTY.Carta;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Before;
import java.util.Random;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito; //***** cant find *****
import org.powermock.core.classloader.annotations.PrepareForTest; //**** cant find ****
import org.powermock.modules.junit4.PowerMockRunner; //***** cant find ****

import static org.junit.Assert.*;

import java.util.ArrayList;

import static org.junit.Assert.*;


/**
 * Unit test for simple App.
 */
@RunWith(PowerMockRunner.class)
public class AppTest 
{
    @Before
    public void setUp(){
        /* Mocking Thread.sleep to do nothing */
        // Setup mocks
        PowerMockito.spy(Thread.class);
        // Expectations
        PowerMockito.doThrow(new InterruptedException()).when(Thread.class);
        try {
            Thread.sleep(Mockito.anyLong());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
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
        jugador.addCarta(new Carta(Carta.Valor.Seis, Carta.Palo.Basto));
        jugador.addCarta(new Carta(Carta.Valor.Cinco, Carta.Palo.Basto));
        jugador.addCarta(new Carta(Carta.Valor.Siete, Carta.Palo.Basto));
        jugador.puntos();
        assertEquals(33, jugador.getPuntos());
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
        AI ai = new AI(null);
        Partida p = new Partida(15,"test",false, ai);
        ai.setPartida(p);
        p.iniciarMano();
        assertTrue(p.jugada("TRUCO", p.getJugador0()));
        assertTrue(!p.jugada("RETRUCO",p.getJugador0())); // jugador0 no deberia poder cantar
        assertTrue(p.jugada("RETRUCO",p.getJugador1())); // jugador1 deberia poder cantar
    }

    @Test
    public void jugadaValidaQuiero() {
        AI ai = new AI(null);
        Partida p = new Partida(15,"test",false, ai);
        ai.setPartida(p);
        p.iniciarMano();
        p.jugada("TRUCO",p.getJugador0());
        assertTrue(p.jugada("QUIERO",p.getJugador1()));
    }

    @Test
    public void jugadaValida() {
        AI ai = new AI(null);
        Partida p = new Partida(15,"test",false, ai);
        ai.setPartida(p);
        p.iniciarMano();
        p.jugada("TRUCO",p.getJugador0());
        assertTrue(!p.jugada("TRUCO",p.getJugador1())); // el otro jugador no deberia poder cantar truco
    }

    @Test
    public void jugadaValidaEnvido() {
        AI ai = new AI(null);
        Partida p = new Partida(15,"test",false, ai);
        ai.setPartida(p);
        p.iniciarMano();
        assertTrue(p.jugada("ENVIDO",p.getJugador0()));
        assertTrue(!p.jugada("ENVIDO",p.getJugador0()));
        assertTrue(p.jugada("ENVIDO",p.getJugador1()));
        assertTrue(!p.jugada("ENVIDO",p.getJugador0()));
    }

    @Test
    public void jugadaValidaRealEnvido() {
        AI ai = new AI(null);
        Partida p = new Partida(15,"test",false, ai);
        ai.setPartida(p);
        p.iniciarMano();
        assertTrue(p.jugada("REAL ENVIDO",p.getJugador0()));
        assertTrue(!p.jugada("REAL ENVIDO",p.getJugador1()));

    }

    @Test
    public void jugadaValidaFaltaEnvido() {
        AI ai = new AI(null);
        Partida p = new Partida(15,"test",false, ai);
        ai.setPartida(p);
        p.iniciarMano();
        assertTrue(p.jugada("FALTA ENVIDO",p.getJugador0()));
        assertTrue(!p.jugada("FALTA ENVIDO",p.getJugador1()));
    }

    @Test
    public void jugadaValidaJugarCarta() {
        AI ai = new AI(null);
        Partida p = new Partida(15,"test",false, ai);
        ai.setPartida(p);
        p.iniciarMano();
        assertTrue(!p.jugada("carta1",p.getJugador1()));
        assertTrue(p.jugada("carta1",p.getJugador0()));
        assertEquals(2,p.getJugador0().getMano().size());
    }

    @Test
    public void jugadaValidaRetrucoValeCuatro() {
        AI ai = new AI(null);
        Partida p = new Partida(15,"test",false, ai);
        ai.setPartida(p);
        p.iniciarMano();
        p.jugada("TRUCO", p.getJugador0());
        p.jugada("RETRUCO", p.getJugador1());
        assertTrue(p.jugada("VALE CUATRO", p.getJugador0()));
    }

    @Test
    public void jugadaValidaMeVoy() {
        AI ai = new AI(null);
        Partida p = new Partida(15,"test",false, ai);
        ai.setPartida(p);
        p.iniciarMano();
        p.jugada("ME VOY", p.getJugador0());
    }

    @Test
    public void jugadaCantoEnCurso() {
        AI ai = new AI(null);
        Partida p = new Partida(15,"test",false, ai);
        ai.setPartida(p);
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

    @Test
    public void TresCartasEnMano() {
        AI ai = new AI(null);
        Partida p = new Partida(15,"test",false, ai);
        ai.setPartida(p);
        p.iniciarMano();
        assertEquals(3,p.getJugador0().getMano().size());
}

    //TESTS DE INTEGRACION

    @Test
    public void testIntegracion001() {
        AI ai = new AI(null);
        Partida p = new Partida(15,"test",false, ai);
        ai.setPartida(p);
        p.iniciarMano();
        assertEquals(3,p.getJugador0().getMano().size());
        assertEquals(3,p.getJugador1().getMano().size());
        assertNotNull(p.getJugador0().getCartaMano(0));
        assertNotNull(p.getJugador0().getCartaMano(1));
        assertNotNull(p.getJugador0().getCartaMano(2));
        assertNotNull(p.getJugador1().getCartaMano(0));
        assertNotNull(p.getJugador1().getCartaMano(1));
        assertNotNull(p.getJugador1().getCartaMano(2));

    }

    @Test
    public void testIntegracion002() {
        AI ai = new AI(null);
        Partida p = new Partida(15,"test",false, ai);
        ai.setPartida(p);
        p.iniciarMano();
        p.setReglas(new ReglasTrad());
        assertEquals(1,p.getReglas().mayorCarta(new Carta(Carta.Valor.Cuatro, Carta.Palo.Copa), new Carta(Carta.Valor.Uno, Carta.Palo.Espada)));
        p.setReglas(new ReglasAlt());
        assertEquals(0,p.getReglas().mayorCarta(new Carta(Carta.Valor.Cuatro, Carta.Palo.Copa), new Carta(Carta.Valor.Uno, Carta.Palo.Espada)));
    }

    @Test
    public void testAIQuieroTruco() {
        // Setup Mocks
        Mesa mockMesa = Mockito.mock(Mesa.class);

        AI ai = new AI(mockMesa);
        Partida p = new Partida(15,"test",false, ai);
        ai.setPartida(p);
        p.iniciarMano();
        p.jugada("TRUCO",p.getJugador0());
        ai.setValor(20);
        ai.truco();
        assertTrue(p.getCantos().lastElement().equals("TRUCO QUERIDO"));
    }

    @Test
    public void testAIQuieroRetruco() {
        // Setup Mocks
        Mesa mockMesa = Mockito.mock(Mesa.class);

        AI ai = new AI(mockMesa);
        Partida p = new Partida(15,"test",false, ai);
        ai.setPartida(p);
        p.iniciarMano();
        p.jugada("TRUCO",p.getJugador0());
        ai.setValor(24);
        ai.truco();
        assertTrue(p.getCantos().lastElement().equals("NECESITA RESPUESTA"));
    }

    @Test
    public void testAINoQuieroTruco() {
        // Setup Mocks
        Mesa mockMesa = Mockito.mock(Mesa.class);

        AI ai = new AI(mockMesa);
        Partida p = new Partida(15,"test",false, ai);
        ai.setPartida(p);
        p.iniciarMano();
        p.jugada("TRUCO",p.getJugador0());
        ai.setValor(17);
        ai.truco();
        assertTrue(p.getCantos().lastElement().equals("TRUCO"));
    }

    @Test
    public void testAIQuieroTrucoMalTurno() {
        // Setup Mocks
        Mesa mockMesa = Mockito.mock(Mesa.class);

        AI ai = new AI(mockMesa);
        Partida p = new Partida(15,"test",false, ai);
        ai.setPartida(p);
        p.iniciarMano();
        ai.setValor(20);
        ai.truco();
        assertTrue(p.getCantos().lastElement().equals("-")); // partida sin cantos
    }

    @Test
    public void testAIQuieroRetrucoMalTurno() {
        // Setup Mocks
        Mesa mockMesa = Mockito.mock(Mesa.class);

        AI ai = new AI(mockMesa);
        Partida p = new Partida(15,"test",false, ai);
        ai.setPartida(p);
        p.iniciarMano();
        ai.setValor(24);
        ai.truco();
        assertTrue(p.getCantos().lastElement().equals("-")); // partida sin cantos
    }

    @Test
    public void testAINoQuieroTrucoMalTurno() {
        // Setup Mocks
        Mesa mockMesa = Mockito.mock(Mesa.class);

        AI ai = new AI(mockMesa);
        Partida p = new Partida(15,"test",false, ai);
        ai.setPartida(p);
        p.iniciarMano();
        ai.setValor(17);
        ai.truco();
        assertTrue(p.getCantos().lastElement().equals("-")); // partida sin cantos
    }

    @Test
    public void testAIRetrucoQuerido() {
        // Setup Mocks
        Mesa mockMesa = Mockito.mock(Mesa.class);

        AI ai = new AI(mockMesa);
        Partida p = new Partida(15,"test",false, ai);
        ai.setPartida(p);
        p.iniciarMano();
        p.cambiarJugador();
        p.jugada("TRUCO",p.getJugador1());
        p.jugada("RETRUCO",p.getJugador0());
        ai.setValor(23);
        ai.retruco();
        assertTrue(p.getCantos().lastElement().equals("RETRUCO QUERIDO"));
    }

    @Test
    public void testAIRetrucoQueridoMalTurno() {
        // Setup Mocks
        Mesa mockMesa = Mockito.mock(Mesa.class);

        AI ai = new AI(mockMesa);
        Partida p = new Partida(15,"test",false, ai);
        ai.setPartida(p);
        p.iniciarMano();
        p.jugada("TRUCO",p.getJugador0());
        p.jugada("RETRUCO",p.getJugador1());
        ai.setValor(23);
        ai.retruco();
        assertTrue(p.getCantos().lastElement().equals("NECESITA RESPUESTA"));
    }

    @Test
    public void testAIRetrucoQuieroValeCuatro() {
        // Setup Mocks
        Mesa mockMesa = Mockito.mock(Mesa.class);

        AI ai = new AI(mockMesa);
        Partida p = new Partida(15,"test",false, ai);
        ai.setPartida(p);
        p.iniciarMano();
        p.cambiarJugador();
        p.jugada("TRUCO",p.getJugador1());
        p.jugada("RETRUCO",p.getJugador0());
        ai.setValor(26);
        ai.retruco();
        assertTrue(p.getCantos().pop().equals("NECESITA RESPUESTA"));
        assertTrue(p.getCantos().pop().equals("VALE CUATRO"));
    }

    @Test
    public void testAIRetrucoQuieroValeCuatroMalTurno() {
        // Setup Mocks
        Mesa mockMesa = Mockito.mock(Mesa.class);

        AI ai = new AI(mockMesa);
        Partida p = new Partida(15,"test",false, ai);
        ai.setPartida(p);
        p.iniciarMano();
        p.jugada("TRUCO",p.getJugador0());
        p.jugada("RETRUCO",p.getJugador1());
        ai.setValor(26);
        ai.retruco();
        assertTrue(p.getCantos().pop().equals("NECESITA RESPUESTA"));
        assertFalse(p.getCantos().pop().equals("VALE CUATRO")); // assert que no se canto vale cuatro
    }

    @Test
    public void testAIRetrucoNoQuiero() {
        // Setup Mocks
        Mesa mockMesa = Mockito.mock(Mesa.class);

        AI ai = new AI(mockMesa);
        Partida p = new Partida(15,"test",false, ai);
        ai.setPartida(p);
        p.iniciarMano();
        p.cambiarJugador();
        p.jugada("TRUCO",p.getJugador1());
        p.jugada("RETRUCO",p.getJugador0());
        ai.setValor(20);
        ai.retruco();
        assertTrue(p.getCantos().lastElement().equals("RETRUCO")); // No se canto quiero retruco
    }

    @Test
    public void testAIRetrucoNoQuieroMalTurno() {
        // Setup Mocks
        Mesa mockMesa = Mockito.mock(Mesa.class);

        AI ai = new AI(mockMesa);
        Partida p = new Partida(15,"test",false, ai);
        ai.setPartida(p);
        p.iniciarMano();
        p.jugada("TRUCO",p.getJugador0());
        p.jugada("RETRUCO",p.getJugador1());
        ai.setValor(20);
        ai.retruco();
        assertTrue(p.getCantos().pop().equals("NECESITA RESPUESTA")); // No se canto quiero retruco pero sigue en juego
        assertTrue(p.getCantos().pop().equals("RETRUCO"));
    }

    @PrepareForTest(AI.class) // Preparing class under test.
    @Test
    public void testAICantarEnvido() {
        // Setup Mocks
        Mesa mockMesa = Mockito.mock(Mesa.class);
        PowerMockito.mockStatic(Math.class);
        PowerMockito.when(Math.random()).thenReturn(0.6); // para que cante envido

        AI ai = new AI(mockMesa);
        Partida p = new Partida(15,"test",false, ai);
        ai.setPartida(p);
        p.iniciarMano();
        p.cambiarJugador();
        /* Puntos de envido para la AI */
        ai.clearMano();
        ai.addCarta(new Carta(Carta.Valor.Siete, Carta.Palo.Espada));
        ai.addCarta(new Carta(Carta.Valor.Seis, Carta.Palo.Espada));
        ai.addCarta(new Carta(Carta.Valor.Uno, Carta.Palo.Espada));
        ai.puntos();
        ai.jugarCarta();
        assertTrue(p.getCantos().pop().equals("NECESITA RESPUESTA"));
        assertTrue(p.getCantos().pop().equals("ENVIDO"));
    }


    @PrepareForTest(AI.class) // Preparing class under test.
    @Test
    public void testAICantarEnvidoMalTurno() {
        // Setup Mocks
        Mesa mockMesa = Mockito.mock(Mesa.class);
        PowerMockito.mockStatic(Math.class);
        PowerMockito.when(Math.random()).thenReturn(0.6); // para que cante envido

        AI ai = new AI(mockMesa);
        Partida p = new Partida(15,"test",false, ai);
        ai.setPartida(p);
        p.iniciarMano();
        /* Puntos de envido para la AI */
        ai.clearMano();
        ai.addCarta(new Carta(Carta.Valor.Siete, Carta.Palo.Espada));
        ai.addCarta(new Carta(Carta.Valor.Seis, Carta.Palo.Espada));
        ai.addCarta(new Carta(Carta.Valor.Uno, Carta.Palo.Espada));
        ai.puntos();
        ai.jugarCarta();
        assertTrue(p.getCantos().pop().equals("-")); // No se canto nada
    }

    @PrepareForTest(AI.class) // Preparing class under test.
    @Test
    public void testAICantarRealEnvido() {
        // Setup Mocks
        Mesa mockMesa = Mockito.mock(Mesa.class);
        PowerMockito.mockStatic(Math.class);
        PowerMockito.when(Math.random()).thenReturn(0.45); // para que cante real envido

        AI ai = new AI(mockMesa);
        Partida p = new Partida(15,"test",false, ai);
        ai.setPartida(p);
        p.iniciarMano();
        p.cambiarJugador();
        /* Puntos de envido para la AI */
        ai.clearMano();
        ai.addCarta(new Carta(Carta.Valor.Siete, Carta.Palo.Espada));
        ai.addCarta(new Carta(Carta.Valor.Seis, Carta.Palo.Espada));
        ai.addCarta(new Carta(Carta.Valor.Uno, Carta.Palo.Espada));
        ai.puntos();
        ai.jugarCarta();
        assertTrue(p.getCantos().pop().equals("NECESITA RESPUESTA"));
        assertTrue(p.getCantos().pop().equals("REAL ENVIDO"));
    }

    @PrepareForTest(AI.class) // Preparing class under test.
    @Test
    public void testAICantarRealEnvidoMalTurno() {
        // Setup Mocks
        Mesa mockMesa = Mockito.mock(Mesa.class);
        PowerMockito.mockStatic(Math.class);
        PowerMockito.when(Math.random()).thenReturn(0.45); // para que cante real envido

        AI ai = new AI(mockMesa);
        Partida p = new Partida(15,"test",false, ai);
        ai.setPartida(p);
        p.iniciarMano();
        /* Puntos de envido para la AI */
        ai.clearMano();
        ai.addCarta(new Carta(Carta.Valor.Siete, Carta.Palo.Espada));
        ai.addCarta(new Carta(Carta.Valor.Seis, Carta.Palo.Espada));
        ai.addCarta(new Carta(Carta.Valor.Uno, Carta.Palo.Espada));
        ai.puntos();
        ai.jugarCarta();
        assertTrue(p.getCantos().pop().equals("-")); // No se canto nada
    }
}

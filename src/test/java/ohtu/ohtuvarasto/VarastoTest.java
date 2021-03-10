package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoKelvottomanVaraston() {
        varasto = new Varasto(-1);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoVaraston() {
        varasto = new Varasto(10, 5);
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoKelvottomanTyhjanVaraston() {
        varasto = new Varasto(-1, -1);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoTaydenVaraston() {
        varasto = new Varasto(10, 20);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void eiVoiLisataNegatiivista() {
        varasto.lisaaVarastoon(-1);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void eiVoiLisataYliTilavuuden() {
        varasto.lisaaVarastoon(20);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void eiVoiOttaaNegatiivista() {
        assertEquals(0, varasto.otaVarastosta(-1), vertailuTarkkuus);
    }

    @Test
    public void ottaminenAntaaMaksimin() {
        varasto.lisaaVarastoon(20);
        assertEquals(10, varasto.otaVarastosta(30), vertailuTarkkuus);
    }

    @Test
    public void toStringToimii() {
    	assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
    }

}

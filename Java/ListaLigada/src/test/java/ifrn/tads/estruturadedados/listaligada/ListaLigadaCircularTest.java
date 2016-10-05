package ifrn.tads.estruturadedados.listaligada;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ListaLigadaCircularTest extends TestCase {
    private ListaLigadaCircular teamCap, teamIron;

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ListaLigadaCircularTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(ListaLigadaCircularTest.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        teamCap = new ListaLigadaCircular()
            .inserir("Capitão américa")
            .inserir("Gavião Arqueiro")
            .inserir("Falcão")
            .inserir("Feiticeira Escarlate")
            .inserir("Soldado Invernal")
            .inserir("Homem formiga");
    }

    public void testProximoElementoAposOUltimoDeveSerIgualAoPrimeiro() {
        assertEquals(teamCap.getPrimeira(), teamCap.getUltima().getProxima());
    }
}

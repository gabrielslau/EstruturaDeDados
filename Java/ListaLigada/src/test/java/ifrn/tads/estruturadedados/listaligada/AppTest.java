package ifrn.tads.estruturadedados.listaligada;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest extends TestCase {
    private ListaLigada teamCap, teamIron;

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        teamCap = new ListaLigada()
            .inserir("Capitão américa")
            .inserir("Gavião Arqueiro")
            .inserir("Falcão")
            .inserir("Feiticeira Escarlate")
            .inserir("Soldado Invernal")
            .inserir("Homem formiga");

        teamIron = new ListaLigada()
            .inserir("Iron Man")
            .inserir("Máquina de combate")
            .inserir("Viúva negra")
            .inserir("Visão")
            .inserir("Pantera Negra")
            .inserir("Spider Man");
    }

    public void testInserirDeveRetornarOrdemInversaDeCriacao() {
        assertTrue(teamCap.toString().equals(
            "[Capitão américa, Gavião Arqueiro, Falcão, Feiticeira Escarlate, Soldado Invernal, Homem formiga]"
        ));
    }
}

package ifrn.tads.estruturadedados.listaduplamenteligada;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ListaDuplamenteLigadaTest extends TestCase {
    private ListaDuplamenteLigada teamCap, teamIron;

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ListaDuplamenteLigadaTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(ListaDuplamenteLigadaTest.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        teamCap = new ListaDuplamenteLigada()
            .inserir("Gavião Arqueiro")
            .inserir("Falcão")
            .inserir("Feiticeira Escarlate")
            .inserir("Soldado Invernal")
            .inserir("Capitão américa")
            .inserir("Homem formiga");

        teamIron = new ListaDuplamenteLigada()
            .inserir("Iron Man")
            .inserir("Máquina de combate")
            .inserir("Viúva negra")
            .inserir("Visão")
            .inserir("Pantera Negra")
            .inserir("Spider Man");
    }

    public void testInserirDeveRetornarEmOrdemDeCriacao() {
        assertTrue(teamCap.toString().equals(
            "Gavião Arqueiro, Falcão, Feiticeira Escarlate, Soldado Invernal, Capitão américa, Homem formiga"
        ));
    }

    public void testInserirNaPosicaoDeveRetornarEmOrdemDeCriacao() {
        teamCap.inserir("Demolidor", 2);

        assertTrue(teamCap.toString().equals(
            "Capitão américa, Gavião Arqueiro, Demolidor, Falcão, Feiticeira Escarlate, Soldado Invernal, Homem formiga"
        ));
    }

    public void testJuntarDeveConterOConteudoDasDuasListas() {
        teamCap.juntar(teamIron);

        String expected = "Capitão américa, Gavião Arqueiro, Falcão, Feiticeira Escarlate, Soldado Invernal, Homem formiga, " +
            "Iron Man, Máquina de combate, Viúva negra, Visão, Pantera Negra, Spider Man";

        assertTrue(teamCap.toString().equals(expected));
        assertTrue(teamIron.toString().equals(expected));
    }

    public void testDeletarDeveRetornarListaSemElementoRemovido() {
        assertTrue(teamCap.deletar(2).toString().equals(
            "Capitão américa, Gavião Arqueiro, Feiticeira Escarlate, Soldado Invernal, Homem formiga"
        ));
    }

    public void testOrdenarDeveRetornarListaEmOrdemCrescente(){
        String expected = "Capitão américa, Falcão, Feiticeira Escarlate, Gavião Arqueiro, Homem formiga, Soldado Invernal";

        //System.out.println(teamCap);
        teamCap.ordenar();
        //System.out.println(teamCap);
        //System.out.println(expected);

        assertTrue(teamCap.toString().equals(
            expected
        ));
    }

    public void testInserirOrdenadoDeveRetornarListaEmOrdemCrescente(){
        String expected = "Capitão américa, Falcão, Feiticeira Escarlate, Gavião Arqueiro, Homem formiga, Soldado Invernal";

        teamCap = new ListaDuplamenteLigada()
            .inserirOrdenado("Gavião Arqueiro")
            .inserirOrdenado("Falcão")
            .inserirOrdenado("Feiticeira Escarlate")
            .inserirOrdenado("Soldado Invernal")
            .inserirOrdenado("Capitão américa")
            .inserirOrdenado("Homem formiga");

        assertEquals(expected, teamCap.toString());
    }

    public void testDividirDeveRetornarPrimeiraListaModificadaESegundaListaComConteudoDaDivisao(){
        ListaDuplamenteLigada newTeam = new ListaDuplamenteLigada();

        teamCap.dividir(2, newTeam);

        assertTrue(teamCap.toString().equals(
            "Capitão américa, Gavião Arqueiro, Falcão"
        ));

        assertTrue(newTeam.toString().equals(
            "Feiticeira Escarlate, Soldado Invernal, Homem formiga"
        ));
    }
}

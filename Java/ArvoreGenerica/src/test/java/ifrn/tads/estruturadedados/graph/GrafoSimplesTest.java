package ifrn.tads.estruturadedados.graph;

import junit.framework.TestCase;

import java.util.Arrays;

public class GrafoSimplesTest extends TestCase {

    private GrafoSimples graph;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        graph = new GrafoSimples();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

        graph = null;
    }

    public void testNewEmptyGraph() {
        assertEquals(0, graph.vertices().size());
    }

    public void testInsertVertexes() {
        graph.inserirVertice(Arrays.asList(1.0, 3.0, 5.0, 6.0, 10.0));

        assertEquals(5, graph.vertices().size());
    }

    public void testInsertEdge() {
        Vertice verticeA = graph.inserirVertice(1.0);
        Vertice verticeB = graph.inserirVertice(2.0);

        Aresta edge = graph.insereAresta(verticeA, verticeB);

        assertEquals(verticeA, edge.getVerticeOrigem());
        assertEquals(verticeB, edge.getVerticeDestino());
    }
}

package ifrn.tads.estruturadedados.graph;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.Vector;

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

    public void testIncidentEdges() {
        Vertice verticeA = graph.inserirVertice(1);
        Vertice verticeB = graph.inserirVertice(2);
        Vertice verticeC = graph.inserirVertice(3);
        Vertice verticeD = graph.inserirVertice(4);

        Aresta edgeAB = graph.insereAresta(verticeA, verticeB, 10);
        Aresta edgeBC = graph.insereAresta(verticeB, verticeC, 20);
        Aresta edgeCD = graph.insereAresta(verticeC, verticeD, 30);
        Aresta edgeDA = graph.insereAresta(verticeD, verticeA, 40);
        Aresta edgeDB = graph.insereAresta(verticeD, verticeB, 50);

        Vector edges = graph.arestasIncidentes(verticeB);
        Vector expected = new Vector<Aresta>(Arrays.asList(edgeAB, edgeBC, edgeDB));

        assertEquals(expected, edges);
    }
}

package ifrn.tads.estruturadedados.graph;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Vector;

import static org.junit.Assert.*;

public class GrafoSimplesTest {

    private GrafoSimples graph;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        graph = new GrafoSimples();
    }

    @After
    public void tearDown() throws Exception {
        graph = null;
    }

    @Test
    public void testNewEmptyGraph() {
        assertEquals(0, graph.vertices().size());
    }

    @Test
    public void testInsertVertexes() {
        graph.inserirVertice(Arrays.asList(1.0, 3.0, 5.0, 6.0, 10.0));

        assertEquals(5, graph.vertices().size());
    }

    @Test
    public void testInsertEdge() {
        Vertice verticeA = graph.inserirVertice(1.0);
        Vertice verticeB = graph.inserirVertice(2.0);

        Aresta edge = graph.insereAresta(verticeA, verticeB);

        assertEquals(verticeA, edge.getVerticeOrigem());
        assertEquals(verticeB, edge.getVerticeDestino());
    }

    @Test
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

    @Test
    public void testVertexDegree() {
        Vertice verticeA = graph.inserirVertice(1);
        Vertice verticeB = graph.inserirVertice(2);
        Vertice verticeC = graph.inserirVertice(3);
        Vertice verticeD = graph.inserirVertice(4);

        graph.insereAresta(verticeA, verticeB);
        graph.insereAresta(verticeB, verticeC);
        graph.insereAresta(verticeC, verticeD);
        graph.insereAresta(verticeD, verticeA);
        graph.insereAresta(verticeD, verticeB);

        int degree = graph.grau(verticeB);
        int expected = 3;

        assertEquals(expected, degree);
    }

    @Test
    public void testAdjacentVertex() {
        Vertice verticeA = graph.inserirVertice(1);
        Vertice verticeB = graph.inserirVertice(2);
        Vertice verticeC = graph.inserirVertice(3);

        graph.insereAresta(verticeA, verticeB);
        graph.insereAresta(verticeA, verticeC);

        assertTrue(graph.adjacente(verticeA, verticeB));
        assertTrue(graph.adjacente(verticeA, verticeC));
        assertFalse(graph.adjacente(verticeB, verticeC));
    }

    @Test
    public void testOppositeVertex() throws OpostoError {
        Vertice verticeA = graph.inserirVertice(1);
        Vertice verticeB = graph.inserirVertice(2);
        Vertice verticeC = graph.inserirVertice(3);

        Aresta edgeAB = graph.insereAresta(verticeA, verticeB, 10);
        Aresta edgeAC = graph.insereAresta(verticeA, verticeC, 20);

        assertEquals(verticeB, graph.oposto(verticeA, edgeAB));

        // this will throw the exception
        exception.expect(OpostoError.class);
        graph.oposto(verticeB, edgeAC);
    }
}

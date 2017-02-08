package ifrn.tads.estruturadedados.graph;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Vector;

import static org.junit.Assert.*;

public class SimpleGraphTest {

    private SimpleGraph graph;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        graph = new SimpleGraph();
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
        graph.addVertex(Arrays.asList(1, 3, 5, 6, 10));

        assertEquals(5, graph.vertices().size());
    }

    @Test
    public void testInsertEdge() {
        Vertex vertexA = graph.addVertex(1);
        Vertex vertexB = graph.addVertex(2);

        Edge edge = graph.addEdge(vertexA, vertexB);

        assertEquals(vertexA, edge.getVertexFrom());
        assertEquals(vertexB, edge.getVertexTo());
    }

    @Test
    public void testIncidentEdges() {
        Vertex vertexA = graph.addVertex(1);
        Vertex vertexB = graph.addVertex(2);
        Vertex vertexC = graph.addVertex(3);
        Vertex vertexD = graph.addVertex(4);

        Edge edgeAB = graph.addEdge(vertexA, vertexB, 10);
        Edge edgeBC = graph.addEdge(vertexB, vertexC, 20);
        Edge edgeCD = graph.addEdge(vertexC, vertexD, 30);
        Edge edgeDA = graph.addEdge(vertexD, vertexA, 40);
        Edge edgeDB = graph.addEdge(vertexD, vertexB, 50);

        Vector edges = graph.incidentEdges(vertexB);
        Vector expected = new Vector<Edge>(Arrays.asList(edgeAB, edgeBC, edgeDB));

        assertEquals(expected, edges);
    }

    @Test
    public void testIncidentEdgesOfDirectedEdges() {
        Vertex vertexA = graph.addVertex("A");
        Vertex vertexB = graph.addVertex("B");
        Vertex vertexC = graph.addVertex("C");
        Vertex vertexD = graph.addVertex("D");

        Edge edgeAB = graph.addArc(vertexA, vertexB, 10);
        Edge edgeBC = graph.addArc(vertexB, vertexC, 20);
        Edge edgeCD = graph.addArc(vertexC, vertexD, 30);
        Edge edgeDA = graph.addArc(vertexD, vertexA, 40);
        Edge edgeDB = graph.addArc(vertexD, vertexB, 50);

        Vector edges = graph.incidentEdges(vertexD);
        Vector expected = new Vector<Edge>(Arrays.asList(edgeDA, edgeDB));

        assertEquals(expected, edges);
        assertEquals(2, edges.size());
    }

    @Test
    public void testVertexDegree() {
        Vertex vertexA = graph.addVertex(1);
        Vertex vertexB = graph.addVertex(2);
        Vertex vertexC = graph.addVertex(3);
        Vertex vertexD = graph.addVertex(4);

        graph.addEdge(vertexA, vertexB);
        graph.addEdge(vertexB, vertexC);
        graph.addEdge(vertexC, vertexD);
        graph.addEdge(vertexD, vertexA);
        graph.addEdge(vertexD, vertexB);

        int degree = graph.degree(vertexB);
        int expected = 3;

        assertEquals(expected, degree);
    }

    @Test
    public void testAdjacentVertex() {
        Vertex vertexA = graph.addVertex(1);
        Vertex vertexB = graph.addVertex(2);
        Vertex vertexC = graph.addVertex(3);

        graph.addEdge(vertexA, vertexB);
        graph.addEdge(vertexA, vertexC);

        assertTrue(graph.areAdjacent(vertexA, vertexB));
        assertTrue(graph.areAdjacent(vertexA, vertexC));
        assertFalse(graph.areAdjacent(vertexB, vertexC));
    }

    @Test
    public void testOppositeVertex() throws InvalidPositionException {
        Vertex vertexA = graph.addVertex(1);
        Vertex vertexB = graph.addVertex(2);
        Vertex vertexC = graph.addVertex(3);

        Edge edgeAB = graph.addEdge(vertexA, vertexB, 10);
        Edge edgeAC = graph.addEdge(vertexA, vertexC, 20);

        assertEquals(vertexB, graph.opposite(vertexA, edgeAB));

        // this will throw the exception
        exception.expect(InvalidPositionException.class);
        graph.opposite(vertexB, edgeAC);
    }

    @Test
    public void testRemoveEdge() {
        Vertex vertexA = graph.addVertex(1);
        Vertex vertexB = graph.addVertex(2);
        Vertex vertexC = graph.addVertex(3);

        Edge edgeAB = graph.addEdge(vertexA, vertexB, 10);
        Edge edgeAC = graph.addEdge(vertexA, vertexC, 20);
        Edge edgeBC = graph.addEdge(vertexB, vertexC, 30);

        // the edge must exist in the matrix
        assertEquals(edgeAC, graph.getEdge(vertexA, vertexC));

        graph.removeEdge(edgeAC);

        // after been removed, the edge should not be found in the matrix
        assertNull(graph.getEdge(vertexA, vertexC));
    }
}

package ifrn.tads.estruturadedados.graph;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.LinkedList;

import static org.junit.Assert.assertTrue;

public class DijkstraTest {

    private SimpleGraph graph;
    private Dijkstra dijkstra;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        graph = new SimpleGraph(12);
    }

    @After
    public void tearDown() throws Exception {
        graph = null;
        dijkstra = null;
    }

    @Test
    public void testShortestPathWithUndirectedGraph() throws InvalidPositionException {
        Vertex vertexA = graph.addVertex("A");
        Vertex vertexB = graph.addVertex("B");
        Vertex vertexC = graph.addVertex("C");
        Vertex vertexD = graph.addVertex("D");
        Vertex vertexE = graph.addVertex("E");

        graph.addEdge(vertexA, vertexB, 6);
        graph.addEdge(vertexA, vertexD, 1);
        graph.addEdge(vertexB, vertexC, 5);
        graph.addEdge(vertexB, vertexD, 2);
        graph.addEdge(vertexB, vertexE, 2);
        graph.addEdge(vertexD, vertexE, 1);
        graph.addEdge(vertexE, vertexC, 5);

        dijkstra = new Dijkstra(graph, vertexA);

        System.out.println("\n\nTABLE\n---");
        for (Vertex vertex : graph.vertices()) {
            System.out.println(vertex);
        }

        // Path from A to C
        LinkedList<Vertex> path = dijkstra.getPathTo(vertexC);

        assertTrue(path.size() == 4);
        assertTrue("The path do not contain vertex " + vertexA, path.contains(vertexA));
        assertTrue("The path do not contain vertex " + vertexD, path.contains(vertexD));
        assertTrue("The path do not contain vertex " + vertexE, path.contains(vertexE));
        assertTrue("The path do not contain vertex " + vertexC, path.contains(vertexC));

        System.out.println("\n\nPATH FROM A TO C\n---");
        System.out.println(path);

        // Path from A to B
        path = dijkstra.getPathTo(vertexB);

        assertTrue(path.size() == 3);
        assertTrue("The path do not contain vertex " + vertexA, path.contains(vertexA));
        assertTrue("The path do not contain vertex " + vertexD, path.contains(vertexD));
        assertTrue("The path do not contain vertex " + vertexB, path.contains(vertexB));

        System.out.println("\n\nPATH FROM A TO B\n---");
        System.out.println(path);
    }

    @Test
    public void testShortestPathWithDirectedGraph() throws InvalidPositionException {
        Vertex vertexA = graph.addVertex("A");
        Vertex vertexB = graph.addVertex("B");
        Vertex vertexC = graph.addVertex("C");
        Vertex vertexD = graph.addVertex("D");
        Vertex vertexE = graph.addVertex("E");

        graph.addArc(vertexA, vertexB, 6);
        graph.addArc(vertexD, vertexA, 1);
        graph.addArc(vertexC, vertexB, 5);
        graph.addArc(vertexB, vertexD, 2);
        graph.addArc(vertexB, vertexE, 4);
        graph.addArc(vertexD, vertexE, 1);
        graph.addArc(vertexE, vertexC, 5);

        dijkstra = new Dijkstra(graph, vertexA);

        System.out.println("\n\nTABLE\n---");
        for (Vertex vertex : graph.vertices()) {
            System.out.println(vertex);
        }

        // Path from A to C
        LinkedList<Vertex> path = dijkstra.getPathTo(vertexC);

        assertTrue(path.size() == 5);
        assertTrue("The path do not contain vertex " + vertexA, path.contains(vertexA));
        assertTrue("The path do not contain vertex " + vertexB, path.contains(vertexB));
        assertTrue("The path do not contain vertex " + vertexD, path.contains(vertexD));
        assertTrue("The path do not contain vertex " + vertexE, path.contains(vertexE));
        assertTrue("The path do not contain vertex " + vertexC, path.contains(vertexC));

        System.out.println("\n\nPATH FROM A TO C\n---");
        System.out.println(path);
    }
}

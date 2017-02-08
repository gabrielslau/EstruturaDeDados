package ifrn.tads.estruturadedados.graph;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Vector;

/**
 * The Dijkstra Algorithm finds the shortest path from a source
 * to all destinations in a directed graph
 */
public class Dijkstra {

    private Vector<Vertex> vertices;
    private final SimpleGraph graph;
    private final Vertex origin;
    private PriorityQueue<Vertex> unvisitedNodes;
    private PriorityQueue<Vertex> visitedNodes;

    public Dijkstra(SimpleGraph graph, Vertex origin) throws InvalidPositionException {
        this.graph = graph;
        this.origin = origin;
        this.vertices = graph.vertices();
        this.unvisitedNodes = new PriorityQueue<Vertex>(vertices.size());
        this.visitedNodes = new PriorityQueue<Vertex>(vertices.size());

        this.calculate(origin);
    }

    private void calculate(Vertex origin) throws InvalidPositionException {
        // The distance to the origin vertex is always 0
        origin.shortestDistance = 0;

        // Creates the list of nodes to visit
        for (Vertex vertex : vertices) {
            this.unvisitedNodes.add(vertex);
        }

        // Walks through all the unvisitedNodes nodes until there is no more left
        while (!this.unvisitedNodes.isEmpty()) {
            // Marks the node as visited by removing it from the queue and adding the visitedNodes list
            Vertex currentVertex = this.unvisitedNodes.poll();
            this.visitedNodes.add(currentVertex);

            // Calculates the distance for all the incident edges
            Vector<Edge> incidentEdges = this.graph.incidentEdges(currentVertex, this.visitedNodes);
            for (Edge edge : incidentEdges) {
                Vertex vertexTo = graph.opposite(currentVertex, edge);
                int distance = currentVertex.shortestDistance + edge.getDistance();

                if (distance < vertexTo.shortestDistance) {
                    // Remove the vertex from the queue to reinsert it again with the new distance
                    this.unvisitedNodes.remove(vertexTo);

                    vertexTo.shortestDistance = distance;
                    vertexTo.setPrevious(currentVertex);

                    // The priority queue will order the elements based on the shortestDistance of the vertex
                    this.unvisitedNodes.add(vertexTo);
                }
            }
        }
    }

    public LinkedList<Vertex> getPathTo(Vertex destination) {
        LinkedList<Vertex> path = new LinkedList<Vertex>();

        // Add the current node as part of the path
        if (destination.getPrevious() != null) {
            path.add(destination);
        }

        Vertex currentVertex = destination;
        while (currentVertex.getPrevious() != null) {
            path.add(0, currentVertex.getPrevious());

            currentVertex = currentVertex.getPrevious();
        }

        // Maybe there was a path, but it not initiated on the origin
        if (currentVertex != origin) {
            return null;
        }

        return path;
    }
}

package ifrn.tads.estruturadedados.graph;

import java.util.Vector;

public interface SimpleGraphInterface {

    Vertex addVertex(Vertex vertex);

    void removeVertex(Vertex vertex);

    Edge addEdge(Vertex vertexFrom, Vertex vertexTo,
                 int distance);

    Edge addEdge(Vertex vertexFrom, Vertex vertexTo);

    void removeEdge(Edge edge);

    Edge addArc(Vertex vertexFrom, Vertex vertexTo,
                int distance);

    Edge addArc(Vertex vertexFrom, Vertex vertexTo);

    void removeArc(Edge edge);

    int degree(Vertex vertex) throws InvalidPositionException;

    int order();

    Vector<Vertex> vertices();

    Vector<Edge> edges();

    Vector incidentEdges(Vertex vertex) throws InvalidPositionException;

    Vector finalVertices(Edge edge);

    Vertex opposite(Vertex vertex, Edge edge) throws InvalidPositionException;

    boolean areAdjacent(Vertex vertexA, Vertex vertexB);
}
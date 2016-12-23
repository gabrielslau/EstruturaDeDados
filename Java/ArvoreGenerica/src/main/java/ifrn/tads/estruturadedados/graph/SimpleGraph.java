package ifrn.tads.estruturadedados.graph;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class SimpleGraph implements SimpleGraphInterface {
    private final int ADJACENCY_MATRIX_SIZE = 10;
    private Vector<Vertex> vertices;
    private Edge adjacencyMatrix[][];

    public SimpleGraph() {
        this(10);
    }

    public SimpleGraph(int adjacency_matrix_size) {
        vertices = new Vector<Vertex>();
        adjacencyMatrix = new Edge[adjacency_matrix_size][adjacency_matrix_size];
    }

    public void addVertex(List<Double> labels) {
        for (double label : labels) {
            addVertex(label);
        }
    }

    public Vertex addVertex(double label) {
        int key = vertices.size() + 1;
        return addVertex(new Vertex(key, label));
    }

    /**
     * Insere e retorna um novo vértice armazenando o elemento
     *
     * @param vertex
     * @exercicio
     */
    public Vertex addVertex(Vertex vertex) {
        vertices.add(vertex);

        return vertex;
    }

    public void removeVertex(Vertex vertex) {
        int index = findIndex(vertex.getKey());
        vertices.remove(index);  // remove o vertex do vector

        int vertexCount = getVertexCount();

        // remove linhas e colunas da matriz de adjacencia
        Edge tempMatrizAdj[][] = new Edge[vertexCount][vertexCount];
        int ff = 0, gg;
        for (int f = 0; f < vertexCount + 1; f++) {
            gg = 0;
            for (int g = 0; g < vertexCount + 1; g++) {
                if (f != index && g != index) {
                    tempMatrizAdj[ff][gg] = adjacencyMatrix[f][g];
                    gg++;
                }
            }
            if (f != index) {
                ff++;
            }
        }
        adjacencyMatrix = tempMatrizAdj;
    }

    public void insertAdjacencyMatrix(int indexFrom, int indexTo, Edge edge) {
        if (indexTo >= ADJACENCY_MATRIX_SIZE || indexFrom >= ADJACENCY_MATRIX_SIZE) {
            throw new IndexOutOfBoundsException("The index size cannot be grater or equal to " + ADJACENCY_MATRIX_SIZE);
        }

        adjacencyMatrix[indexFrom][indexTo] = edge;
    }

    public Edge addEdge(Vertex vertexFrom, Vertex vertexTo, double label) {
        Edge edge = new Edge(vertexFrom, vertexTo, label);

        int indexFrom = findIndex(vertexFrom.getKey());
        int indexTo = findIndex(vertexTo.getKey());

        // grafo nao orientado
        insertAdjacencyMatrix(indexFrom, indexTo, edge);
        insertAdjacencyMatrix(indexTo, indexFrom, edge);

        return edge;
    }

    public Edge addEdge(Vertex vertexFrom, Vertex vertexTo) {
        return addEdge(vertexFrom, vertexTo, 0);
    }

    public void removeEdge(Edge edge) {
        int indexFrom = findIndex(edge.getVertexFrom().getKey());
        int indexTo = findIndex(edge.getVertexTo().getKey());

        // grafo nao orientado
        adjacencyMatrix[indexFrom][indexTo] = adjacencyMatrix[indexTo][indexFrom] = null;
    }

    public Edge addArc(Vertex vertexFrom, Vertex vertexTo, double label) {
        Edge arc = new Edge(vertexFrom, vertexTo, label, true);

        int indexFrom = findIndex(vertexFrom.getKey());
        int indexTo = findIndex(vertexTo.getKey());

        // grafo orientado
        insertAdjacencyMatrix(indexFrom, indexTo, arc);

        return arc;
    }

    public Edge addArc(Vertex vertexFrom, Vertex vertexTo) {
        return addArc(vertexFrom, vertexTo, 0);
    }

    /**
     * OBS.: grafo orientado
     *
     * @param edge the edge to remove the arc
     * @exercicio
     */
    public void removeArc(Edge edge) {
        int indexFrom = findIndex(edge.getVertexFrom().getKey());
        int indexTo = findIndex(edge.getVertexTo().getKey());

        // grafo orientado
        adjacencyMatrix[indexFrom][indexTo] = null;
    }

    public void mostraVertices() {
        for (int f = 0; f < vertices.size(); f++) {
            System.out.print(vertices.elementAt(f) + ",");
        }
    }

    public void mostraMatriz() {
        for (int f = 0; f < getVertexCount(); f++) {
            for (int g = 0; g < getVertexCount(); g++) {
                System.out.print(adjacencyMatrix[f][g] + " ");
            }

            System.out.println();
        }
    }

    /**
     * @param vertex vertex do grafo para medir o degree
     * @return quantidade de edges incidentes no vertex
     * @exercicio
     */
    public int degree(Vertex vertex) {
        return incidentEdges(vertex).size();
    }

    public int order() {
        return getVertexCount();
    }

    public int getVertexCount() {
        return vertices().size();
    }

    private int findIndex(int key) {
        Iterator I = vertices.iterator();
        int ind = 0;
        while (I.hasNext()) {
            Vertex vertex = (Vertex) (I.next());
            if (vertex.getKey() == key) {
                // achei
                return ind;
            }

            ind++;
        }
        return -1; // nao achei
    }

    // TODO: implementar algoritmo para verificar existencia de caminho euleriano
    // vale 0,5 pontos

    public Vector vertices() {
        return vertices;
    }

    public Vector edges() {
        Vector<Edge> vector = new Vector<Edge>();
        for (int l = 0; l < getVertexCount(); l++) {
            for (int c = 0; c < getVertexCount(); c++) {
                vector.add(adjacencyMatrix[l][c]);
            }
        }

        return vector;
    }

    /**
     * @param vertex vertex em que as edges incidem
     * @return Vector lista de edges encontradas
     * @exercicio
     */
    public Vector incidentEdges(Vertex vertex) {
        Vector<Edge> edges = new Vector<Edge>();
        int vertexIndex = findIndex(vertex.getKey());

        for (int f = 0; f < getVertexCount(); f++) {
            for (int g = 0; g < getVertexCount(); g++) {
                if (
                        (f == vertexIndex || g == vertexIndex) &&
                                adjacencyMatrix[f][g] != null &&
                                !edges.contains(adjacencyMatrix[f][g])
                        ) {
                    edges.add(adjacencyMatrix[f][g]);
                }
            }
        }

        return edges;
    }

    public Vector finalVertices(Edge edge) {
        Vector<Vertex> vertex = new Vector<Vertex>();
        vertex.add(edge.getVertexFrom());
        vertex.add(edge.getVertexTo());

        return vertex;
    }

    /**
     * @param vertex vertex de referência
     * @param edge   aresta de referência
     * @return o vertex o posto ao vértice v na aresta a
     * @throws InvalidPositionException Um erro ocorre se Edge não é incidente a Vertex
     * @exercicio
     */
    public Vertex opposite(Vertex vertex, Edge edge) throws InvalidPositionException {
        Vector finalVertex = finalVertices(edge);
        if (!finalVertex.contains(vertex)) {
            throw new InvalidPositionException();
        }

        finalVertex.remove(vertex);

        return (Vertex) finalVertex.firstElement();
    }

    public boolean areAdjacent(Vertex vertexA, Vertex vertexB) {
        int indexA = findIndex(vertexA.getKey());
        int indexB = findIndex(vertexB.getKey());

        return (adjacencyMatrix[indexA][indexB]) != null;
    }

    public Edge getEdge(Vertex vertexA, Vertex vertexB) {
        int indexA = findIndex(vertexA.getKey());
        int indexB = findIndex(vertexB.getKey());

        return (adjacencyMatrix[indexA][indexB]);
    }
}

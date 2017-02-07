package ifrn.tads.estruturadedados.graph;

public class Edge implements EdgeInterface {
    private Vertex vertexFrom;
    private Vertex vertexTo;
    private int distance;
    private boolean directed;

    public Edge(Vertex vertexFrom, Vertex vertexTo) {
        super();
        this.vertexFrom = vertexFrom;
        this.vertexTo = vertexTo;

        makeUndirected();
    }

    public Edge(Vertex vertexFrom, Vertex vertexTo, int distance) {
        this(vertexFrom, vertexTo);
        this.distance = distance;
    }

    public Edge(Vertex vertexFrom, Vertex vertexTo, int distance, boolean directed) {
        this(vertexFrom, vertexTo, distance);
        this.directed = directed;
    }

    /**
     * @return Returns the vertexTo.
     */
    public Vertex getVertexTo() {
        return vertexTo;
    }

    /**
     * @param vertexTo The vertexTo to set.
     */
    public void setVertexTo(Vertex vertexTo) {
        this.vertexTo = vertexTo;
    }

    /**
     * @return Returns the vertexFrom.
     */
    public Vertex getVertexFrom() {
        return vertexFrom;
    }

    /**
     * @param vertexFrom The vertexFrom to set.
     */
    public void setVertexFrom(Vertex vertexFrom) {
        this.vertexFrom = vertexFrom;
    }

    /**
     * @return isDirected
     */
    public boolean isDirected() {
        return directed;
    }

    public void makeDirected() {
        this.directed = true;
    }

    public void makeUndirected() {
        this.directed = false;
    }

    /**
     * @return the distance
     */
    public int getDistance() {
        return distance;
    }

    /**
     * @param distance the distance to set
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }

    /**
     * @return the verticeX
     */
    public String toString() {
        return "[" + vertexFrom.getLabel() + ", " + vertexTo.getLabel() + ": " + distance + "]";
    }
}

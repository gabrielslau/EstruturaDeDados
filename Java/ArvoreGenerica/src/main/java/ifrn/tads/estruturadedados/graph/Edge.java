package ifrn.tads.estruturadedados.graph;

public class Edge implements EdgeInterface {
    private Vertex vertexFrom;
    private Vertex vertexTo;
    private double label;
    private boolean directed;

    public Edge(Vertex vertexFrom, Vertex vertexTo) {
        super();
        this.vertexFrom = vertexFrom;
        this.vertexTo = vertexTo;

        makeUndirected();
    }

    public Edge(Vertex vertexFrom, Vertex vertexTo, double label) {
        this(vertexFrom, vertexTo);
        this.label = label;
    }

    public Edge(Vertex vertexFrom, Vertex vertexTo, double label, boolean directed) {
        this(vertexFrom, vertexTo, label);
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
     * @return the label
     */
    public double getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(double label) {
        this.label = label;
    }

    /**
     * @return the verticeX
     */
    public String toString() {
        //return "["+vertexFrom+"-"+vertexTo+":"+label+"]";
        return "[" + label + "]";
    }

}

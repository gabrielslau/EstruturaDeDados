package ifrn.tads.estruturadedados.graph;

public class Vertex implements VertexInterface, Comparable<Vertex> {
    private int key;
    private Object label;

    /**
     * All initial distances are unknown
     */
    public Integer shortestDistance = Integer.MAX_VALUE;

    /**
     * The vertex previous visited vertex
     */
    private Vertex previous;

    public Vertex(int key, Object label) {
        super();
        this.key = key;
        this.label = label;
        this.previous = null;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Object getLabel() {
        return label;
    }

    public void setLabel(Object label) {
        this.label = label;
    }

    public String toString() {
        String previousVertex = getPrevious() != null ? getPrevious().getLabel().toString() : "-";
        String distance = this.shortestDistance != Integer.MAX_VALUE ? this.shortestDistance.toString() : "∞";

        return "[" + label + ", " + distance + ", " + previousVertex + "]";
    }

    public Vertex getPrevious() {
        return previous;
    }

    public void setPrevious(Vertex previous) {
        this.previous = previous;
    }

    public int compareTo(Vertex vertex) {
        return Integer.compare(shortestDistance, vertex.shortestDistance);
    }
}

package ifrn.tads.estruturadedados.graph;

public class Vertex implements VertexInterface {
    private int chave;
    private double valor;

    /**
     * @param chave
     * @param valor
     */
    public Vertex(int chave, double valor) {
        super();
        this.chave = chave;
        this.valor = valor;
    }

    /**
     * @return the chave
     */
    public int getKey() {
        return chave;
    }

    /**
     * @param key the chave to set
     */
    public void setKey(int key) {
        this.chave = key;
    }

    /**
     * @return the valor
     */
    public double getLabel() {
        return valor;
    }

    /**
     * @param label the valor to set
     */
    public void setLabel(double label) {
        this.valor = label;
    }

    public String toString() {
        //return "["+chave+" - "+valor+"]";
        return "[" + chave + "]";
    }
}

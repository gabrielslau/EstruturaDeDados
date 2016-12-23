package ifrn.tads.estruturadedados.graph;

public interface VertexInterface {

    int getKey();

    /**
     * @param key the chave to set
     */
    void setKey(int key);

    /**
     * @return the label
     */
    double getLabel();

    /**
     * @param label the valor to set
     */
    void setLabel(double label);
}
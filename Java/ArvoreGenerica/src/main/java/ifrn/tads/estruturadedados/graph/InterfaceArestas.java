package ifrn.tads.estruturadedados.graph;

public interface InterfaceArestas {
    //private Vertice verticeOrigem;
    //private Vertice verticeDestino;
    //private double valor;
    //private boolean direcionada;   
    //public Aresta(Vertice verticeOrigem, Vertice verticeDestino)
    //public Aresta(Vertice verticeOrigem, Vertice verticeDestino,double valor)
    //public Aresta(Vertice verticeOrigem, Vertice verticeDestino,double valor, boolean direcionada)

    /**
     * @return Returns the verticeDestino.
     */
    Vertice getVerticeDestino();

    /**
     * @param verticeDestino The verticeDestino to set.
     */
    void setVerticeDestino(Vertice verticeDestino);

    /**
     * @return Returns the verticeOrigem.
     */
    Vertice getVerticeOrigem();

    /**
     * @param verticeOrigem The verticeOrigem to set.
     */
    void setVerticeOrigem(Vertice verticeOrigem);

    /**
     * @return direcionada
     */
    boolean direcionada();

    /**
     * @param direcionada the direcionada to set
     */
    void setDirecionada(boolean direcionada);

    /**
     * @return the valor
     */
    double getValor();

    /**
     * @param valor the valor to set
     */
    void setValor(double valor);
}
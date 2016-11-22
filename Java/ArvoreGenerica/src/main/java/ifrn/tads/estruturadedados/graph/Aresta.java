package ifrn.tads.estruturadedados.graph;

public class Aresta implements InterfaceArestas {
    private Vertice verticeOrigem;
    private Vertice verticeDestino;
    private double valor;
    private boolean direcionada;

    public Aresta(Vertice verticeOrigem, Vertice verticeDestino) {
        super();
        this.verticeOrigem = verticeOrigem;
        this.verticeDestino = verticeDestino;
        direcionada = false;
    }

    public Aresta(Vertice verticeOrigem, Vertice verticeDestino, double valor) {
        this(verticeOrigem, verticeDestino);
        this.valor = valor;
    }

    public Aresta(Vertice verticeOrigem, Vertice verticeDestino, double valor, boolean direcionada) {
        this(verticeOrigem, verticeDestino, valor);
        this.direcionada = direcionada;
    }

    /**
     * @return Returns the verticeDestino.
     */
    public Vertice getVerticeDestino() {
        return verticeDestino;
    }

    /**
     * @param verticeDestino The verticeDestino to set.
     */
    public void setVerticeDestino(Vertice verticeDestino) {
        this.verticeDestino = verticeDestino;
    }

    /**
     * @return Returns the verticeOrigem.
     */
    public Vertice getVerticeOrigem() {
        return verticeOrigem;
    }

    /**
     * @param verticeOrigem The verticeOrigem to set.
     */
    public void setVerticeOrigem(Vertice verticeOrigem) {
        this.verticeOrigem = verticeOrigem;
    }


    /**
     * @return direcionada
     */
    public boolean direcionada() {
        return direcionada;
    }

    /**
     * @param direcionada the direcionada to set
     */
    public void setDirecionada(boolean direcionada) {
        this.direcionada = direcionada;
    }

    /**
     * @return the valor
     */
    public double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * @return the verticeX
     */
    public String toString() {
        //return "["+verticeOrigem+"-"+verticeDestino+":"+valor+"]";        
        return "[" + valor + "]";
    }

}

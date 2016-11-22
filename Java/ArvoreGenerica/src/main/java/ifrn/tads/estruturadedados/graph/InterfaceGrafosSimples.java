package ifrn.tads.estruturadedados.graph;

import java.util.Vector;

public interface InterfaceGrafosSimples {
    //private int qtdVertices;
    //private Vector vertices;
    //private Aresta matrizAdj[][];
    //public GrafoSimples()
    void inserirVertice(Vertice Vertice);

    void removerVertice(Vertice Vertice);

    Aresta insereAresta(Vertice VerticeUm, Vertice VerticeDois,
                        double valor);

    Aresta insereAresta(Vertice VerticeUm, Vertice VerticeDois);

    void removeAresta(Aresta Aresta);

    Aresta insereArco(Vertice VerticeUm, Vertice VerticeDois,
                      double valor);

    Aresta insereArco(Vertice VerticeUm, Vertice VerticeDois);

    void removeArco(Aresta Aresta);

    int grau(Vertice Vertice);

    int ordem();

    Vector vertices();

    Vector arestas();

    Vector arestasIncidentes(Vertice vertice);

    Vector finalVertices(Aresta a);

    Vertice oposto(Vertice v, Aresta a) throws OpostoError;

    boolean adjacente(Vertice v, Vertice w);
}
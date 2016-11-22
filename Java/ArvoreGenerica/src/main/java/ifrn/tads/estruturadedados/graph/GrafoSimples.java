package ifrn.tads.estruturadedados.graph;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class GrafoSimples implements InterfaceGrafosSimples {
    private int qtdVertices;
    private Vector<Vertice> vertices;
    private Aresta matrizAdj[][];

    public GrafoSimples() {
        qtdVertices = 0;
        vertices = new Vector<Vertice>();
    }

    public void inserirVertice(List<Double> valores) {
        for (double valor : valores) {
            inserirVertice(valor);
        }
    }

    public void inserirVertice(double valor) {
        int chave = vertices.size() + 1;
        inserirVertice(new Vertice(chave, valor));
    }

    /**
     * Insere e retorna um novo vértice armazenando o elemento
     *
     * @param vertice
     * @exercicio
     */
    public void inserirVertice(Vertice vertice) {
        vertices.add(vertice);
    }

    public void removerVertice(Vertice vertice) {
        qtdVertices--;
        int indice = achaIndice(vertice.getChave());
        vertices.remove(indice);  // remove o vertice do vector
        // remove linhas e colunas da matriz de adjacencia
        Aresta tempMatrizAdj[][] = new Aresta[qtdVertices][qtdVertices];
        int ff = 0, gg;
        for (int f = 0; f < qtdVertices + 1; f++) {
            gg = 0;
            for (int g = 0; g < qtdVertices + 1; g++) {
                if (f != indice && g != indice) {
                    tempMatrizAdj[ff][gg] = matrizAdj[f][g];
                    if (g != indice) {
                        gg++;
                    }
                }
            }
            if (f != indice) {
                ff++;
            }
        }
        matrizAdj = tempMatrizAdj;
    }

    public Aresta insereAresta(Vertice verticeUm, Vertice verticeDois, double valor) {
        Aresta A = new Aresta(verticeUm, verticeDois, valor);

        int ind1 = achaIndice(verticeUm.getChave());
        int ind2 = achaIndice(verticeDois.getChave());

        matrizAdj[ind1][ind2] = matrizAdj[ind2][ind1] = A; // grafo nao orientado

        return A;
    }

    public Aresta insereAresta(Vertice verticeUm, Vertice verticeDois) {
        Aresta A = new Aresta(verticeUm, verticeDois);

        int ind1 = achaIndice(verticeUm.getChave());
        int ind2 = achaIndice(verticeDois.getChave());

        matrizAdj[ind1][ind2] = matrizAdj[ind2][ind1] = A; // grafo nao orientado

        return A;
    }

    public void removeAresta(Aresta Aresta) {
        int ind1 = achaIndice(Aresta.getVerticeOrigem().getChave());
        int ind2 = achaIndice(Aresta.getVerticeDestino().getChave());

        matrizAdj[ind1][ind2] = matrizAdj[ind2][ind1] = null; // grafo nao orientado
    }

    public Aresta insereArco(Vertice verticeUm, Vertice verticeDois, double valor) {
        Aresta A = new Aresta(verticeUm, verticeDois, valor, true);

        int ind1 = achaIndice(verticeUm.getChave());
        int ind2 = achaIndice(verticeDois.getChave());
        matrizAdj[ind1][ind2] = A; // grafo orientado

        return A;
    }

    public Aresta insereArco(Vertice verticeUm, Vertice verticeDois) {
        Aresta A = new Aresta(verticeUm, verticeDois, 0, true);

        int ind1 = achaIndice(verticeUm.getChave());
        int ind2 = achaIndice(verticeDois.getChave());
        matrizAdj[ind1][ind2] = A; // grafo orientado

        return A;
    }

    public void removeArco(Aresta aresta) {   // grafo orientado
        // exercicio, fique a vontade para implementa-lo coleguinha
    }

    public void mostraVertices() {
        for (int f = 0; f < vertices.size(); f++) {
            System.out.print(vertices.elementAt(f) + ",");
        }
    }

    public void mostraMatriz() {
        for (int f = 0; f < qtdVertices; f++) {
            for (int g = 0; g < qtdVertices; g++) {
                System.out.print(matrizAdj[f][g] + " ");
            }

            System.out.println();
        }
    }

    public int grau(Vertice vertice) {
        // exercicio, fique a vontade para implementa-lo coleguinha
        return 0;
    }

    public int ordem() {
        return qtdVertices;
    }

    private int achaIndice(int chave) {
        Iterator I = vertices.iterator();
        int ind = 0;
        while (I.hasNext()) {
            Vertice v = (Vertice) (I.next());
            if (v.getChave() == chave) {
                // achei
                return ind;
            }

            ind++;
        }
        return -1; // nao achei
    }

    public Vector vertices() {
        return vertices;
    }

    public Vector arestas() {
        Vector v = new Vector();
        for (int l = 0; l < qtdVertices; l++) {
            for (int c = 0; c < qtdVertices; c++) {
                v.add(matrizAdj[l][c]);
            }
        }

        return v;
    }

    public Vector arestasIncidentes(Vertice vertice) {
        // exercicio, fique a vontade para implementa-lo coleguinha
        return null;
    }

    public Vector finalVertices(Aresta a) {
        Vector v = new Vector();
        v.add(a.getVerticeOrigem());
        v.add(a.getVerticeDestino());

        return v;
    }

    public Vertice oposto(Vertice v, Aresta a) throws OpostoError {
        // exercicio, fique a vontade para implementa-lo coleguinha
        return null;
    }

    public boolean adjacente(Vertice v, Vertice w) {
        int ind1 = achaIndice(v.getChave());
        int ind2 = achaIndice(w.getChave());

        return (matrizAdj[ind1][ind2]) != null;
    }

    public Aresta getAresta(Vertice v, Vertice w) {
        int ind1 = achaIndice(v.getChave());
        int ind2 = achaIndice(w.getChave());

        return (matrizAdj[ind1][ind2]);
    }
}

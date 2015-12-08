package ifrn.tads.estruturadedados;

import ifrn.tads.estruturadedados.binarytree.ArvoreBinaria;
import ifrn.tads.estruturadedados.binarytree.INo;
import ifrn.tads.estruturadedados.binarytree.No;

public class App {
    public static void main(String[] args) {
        ArvoreBinaria arvore = new ArvoreBinaria(6);

        arvore.incluir(7);
        arvore.incluir(1);
        arvore.incluir(8);
        arvore.incluir(4);

        System.out.println("Raiz: " + arvore.raiz());
        System.out.println("Raiz Left: " + arvore.raiz().leftChild().element());
        System.out.println("Raiz Right: " + arvore.raiz().rightChild().element());
        arvore.preOrderTraverseTree(arvore.raiz());

        System.out.println();
        INo pesquisa = arvore.pesquisar(7);
        System.out.println("Pesquisa retornou: " + pesquisa);

        System.out.println();
        System.out.println("Removeu: " + arvore.remover(7));
        System.out.println();
        arvore.preOrderTraverseTree(arvore.raiz());

        System.out.println();
        arvore.setRaiz(new No(null, 10));
        System.out.println("Raiz: " + arvore.raiz());
        System.out.println("Raiz Left: " + arvore.raiz().leftChild().element());
        System.out.println("Raiz Right: " + arvore.raiz().rightChild().element());
        arvore.preOrderTraverseTree(arvore.raiz());
    }
}

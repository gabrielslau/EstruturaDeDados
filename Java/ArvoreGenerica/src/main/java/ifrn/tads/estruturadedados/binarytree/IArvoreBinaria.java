package ifrn.tads.estruturadedados.binarytree;

public interface IArvoreBinaria {
    INo pesquisar(No no, Integer o);
    INo pesquisar(Integer o);
    No incluir(Integer o);
    boolean remover(Integer o);
    void setRaiz(No o);
    // custom
    void preOrderTraverseTree(INo focusNode);
    void postOrderTraverseTree(INo focusNode);
}
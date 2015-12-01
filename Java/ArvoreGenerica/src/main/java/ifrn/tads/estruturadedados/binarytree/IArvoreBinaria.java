package ifrn.tads.estruturadedados.binarytree;

public interface IArvoreBinaria {
    No pesquisar(No no, Object key);
    No incluir(Object key);
    Object remover(Object key);
    void setRaiz(No p);
}
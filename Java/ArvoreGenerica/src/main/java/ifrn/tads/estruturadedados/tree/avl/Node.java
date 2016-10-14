package ifrn.tads.estruturadedados.tree.avl;

class Node<T extends Comparable<?>> extends ifrn.tads.estruturadedados.tree.binarysearchtree.Node<T> {
    Integer height;

    public Node(T data) {
        super(data);

        height = 1;
    }
}
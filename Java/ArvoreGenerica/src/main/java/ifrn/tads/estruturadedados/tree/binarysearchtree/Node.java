package ifrn.tads.estruturadedados.tree.binarysearchtree;

import ifrn.tads.estruturadedados.tree.AbstractNode;

public class Node<T extends Comparable<?>> extends AbstractNode<T> {

    public Node(T data) {
        super(data);
    }
}
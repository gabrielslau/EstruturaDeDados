package ifrn.tads.estruturadedados.tree.b;

import java.util.List;

public class BTree<T extends Comparable<T>> implements BTreeInterface<T> {

    private BTreeNode<T> root;

    public BTreeNode<T> root() {
        return root;
    }

    public BTreeNode<T> root(BTreeNodeInterface<T> node) {
        root = (BTreeNode<T>) node;

        return root;
    }

    public void insert(List<T> elementsToAdd) {
        for (T element : elementsToAdd) {
            insert(element);
        }
    }

    public BTreeNode<T> insert(T targetData) {
        return null;
    }

    public BTreeNode<T> find(T targetData) {
        return null;
    }

    public boolean delete(T data) {
        return false;
    }
}

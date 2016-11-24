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

    public void add(List<T> keys) {
        for (T key : keys) {
            add(key);
        }
    }

    public BTreeNode<T> add(T key) {
        return null;
    }

    public BTreeNode<T> find(T key) {
        return null;
    }

    public boolean remove(T key) {
        return false;
    }
}

package ifrn.tads.estruturadedados.tree.b;

import java.util.Vector;

public class BTreeNode<T extends Comparable<T>> implements BTreeNodeInterface<T> {

    protected Vector<T> keys;
    protected BTreeNode<T> left, right, parent;

    public BTreeNode(T key) {
        setKey(key);
    }

    public BTreeNode(Vector<T> keys) {
        this.keys = keys;
    }

    public void setKey(T key) {
        keys.add(key);
    }

    public Vector<T> getKeys() {
        return keys;
    }

    public BTreeNode<T> left() {
        return left;
    }

    public BTreeNode<T> left(BTreeNodeInterface<T> node) {
        left = (BTreeNode<T>) node;

        return left;
    }

    public BTreeNode<T> right() {
        return right;
    }

    public BTreeNode<T> right(BTreeNodeInterface<T> node) {
        right = (BTreeNode<T>) node;

        return right;
    }

    public BTreeNode<T> parent() {
        return parent;
    }

    public BTreeNode<T> parent(BTreeNodeInterface<T> node) {
        parent = (BTreeNode<T>) node;

        return parent;
    }

    public BTreeNodeInterface<T> split() {
        return null;
    }
}

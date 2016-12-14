package ifrn.tads.estruturadedados.tree.b;

import java.util.Vector;

public class BTreeNode<T extends Comparable<T>> implements BTreeNodeInterface<T> {

    private Vector<T> keys;
    private Vector<BTreeNodeInterface<T>> children;
    private BTreeNode<T> parent;

    public BTreeNode(T key, BTreeNode<T> parent, int maxKeySize, int maxChildrenSize) {
        this.parent = parent;
        this.keys = new Vector<T>(maxKeySize + 1);
        this.children = new Vector<BTreeNodeInterface<T>>(maxChildrenSize + 1);

        setKey(key);
    }

    public BTreeNode(T key, int maxKeySize, int maxChildrenSize) {
        this(key, null, maxKeySize, maxChildrenSize);
    }

    public void setKey(T key) {
        keys.add(key);
    }

    public Vector<T> getKeys() {
        return keys;
    }

    public Vector<BTreeNodeInterface<T>> children() {
        return children;
    }

    public int numberOfChildren() {
        return children().size();
    }

    public BTreeNode<T> getChild(int index) {
        return (BTreeNode<T>) children().get(index);
    }

    public BTreeNode<T> getFirstChild() {
        return (BTreeNode<T>) children().firstElement();
    }

    public BTreeNode<T> getLastChild() {
        return (BTreeNode<T>) children().lastElement();
    }

    public int numberOfKeys() {
        return getKeys().size();
    }

    public T getKey(int index) {
        return getKeys().get(index);
    }

    public BTreeNode<T> parent() {
        return parent;
    }

    public BTreeNode<T> parent(BTreeNodeInterface<T> node) {
        parent = (BTreeNode<T>) node;

        return parent;
    }

    public BTreeNode<T> split() {
        return null;
    }

    public BTreeNode<T> find(T key) {
        /*if (getKeys().contains(key)) {
            return this;
        }

        for (BTreeNodeInterface<T> chield : children()) {
            BTreeNode<T> target = (BTreeNode<T>) chield.find(key);

            if (target != null) {
                return target;
            }
        }*/

        return null;
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }
}

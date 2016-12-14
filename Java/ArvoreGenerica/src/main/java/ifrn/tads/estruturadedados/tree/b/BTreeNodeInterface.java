package ifrn.tads.estruturadedados.tree.b;

import java.util.Vector;

interface BTreeNodeInterface<T extends Comparable<T>> {
    void setKey(T key);

    Vector<T> getKeys();

    Vector<BTreeNodeInterface<T>> children();

    BTreeNodeInterface<T> parent();

    BTreeNodeInterface<T> parent(BTreeNodeInterface<T> node);

    BTreeNodeInterface<T> split();

    BTreeNodeInterface<T> find(T key);

    boolean isLeaf();
}

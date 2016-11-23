package ifrn.tads.estruturadedados.tree.b;

import java.util.Vector;

interface BTreeNodeInterface<T extends Comparable<T>> {
    void setKey(T key);

    Vector<T> getKeys();

    BTreeNodeInterface<T> left();

    BTreeNodeInterface<T> left(BTreeNodeInterface<T> node);

    BTreeNodeInterface<T> right();

    BTreeNodeInterface<T> right(BTreeNodeInterface<T> node);

    BTreeNodeInterface<T> parent();

    BTreeNodeInterface<T> parent(BTreeNodeInterface<T> node);

    BTreeNodeInterface<T> split();
}

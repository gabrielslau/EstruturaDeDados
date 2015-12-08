package ifrn.tads.estruturadedados.binarytree;

import java.util.Iterator;

public interface INo {
    Integer element();

    INo parent();

    void setElement(Integer o);

    //void addChild(INo o);

    //void removeChild(INo o);

    int childrenNumber();

    Iterator children();

    INo leftChild();

    void leftChild(INo no);

    INo rightChild();

    void rightChild(INo no);

    boolean isLeaf();
}

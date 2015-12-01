package ifrn.tads.estruturadedados.binarytree;

import java.util.Iterator;

public interface INo {
    Object element();

    INo parent();

    void setElement(Object o);

    void addChild(INo o);

    void removeChild(INo o);

    int childrenNumber();

    Iterator children();
}

package ifrn.tads.estruturadedados.binarytree;

import java.util.Iterator;
import java.util.Vector;

public class No implements INo {
    private Object o;
    private No pai;
    private Vector<INo> filhos = new Vector<INo>();

    public No(No pai, Object o) {
        this.pai = pai;
        this.o = o;
    }

    public Object element() {
        return o;
    }

    public INo parent() {
        return pai;
    }

    public void setElement(Object o) {
        this.o = o;
    }

    public void addChild(INo o) {
        filhos.add(o);
    }

    public void removeChild(INo o) {
        filhos.remove(o);
    }

    public int childrenNumber() {
        return filhos.size();
    }

    public Iterator children() {
        return filhos.iterator();
    }
}

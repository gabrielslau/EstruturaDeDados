package ifrn.tads.estruturadedados.binarytree;

import java.util.Iterator;
import java.util.Vector;

public class No implements INo {
    private Integer o;
    private INo pai;
    private INo leftChild;
    private INo rightChild;
    private int childrenNumber;
    //private Vector<INo> filhos = new Vector<INo>();

    public No(INo pai, Integer o) {
        this.pai = pai;
        this.o = o;
        this.childrenNumber = 0;
    }

    public Integer element() {
        return o;
    }

    public INo parent() {
        return pai;
    }

    public void setElement(Integer o) {
        this.o = o;
    }

    /*
    public void addChild(INo o) {
        filhos.add(o);
    }

    public void removeChild(INo o) {
        filhos.remove(o);
    }
    */

    public int childrenNumber() {
        return childrenNumber;
    }

    public Iterator children() {
        Vector<INo> children = new Vector<INo>();

        if(leftChild() != null){
            children.add(leftChild());
        }

        if(rightChild() != null){
            children.add(rightChild());
        }

        return children.iterator();
    }

    public INo leftChild() {
        /*if(childrenNumber() == 0)
            return null;

        return filhos.get(0);*/
        return leftChild;
    }

    public void leftChild(INo no) {
        //filhos.add(0, no);
        leftChild = no;
        childrenNumber += 1;
    }

    public INo rightChild() {
        /*if(isLeaf())
            return null;

        return filhos.get(1);*/
        return rightChild;
    }

    public void rightChild(INo no) {
        /*if(isLeaf()){
            filhos.add(0, null);
        }

        filhos.add(1, no);*/
        rightChild = no;
        childrenNumber += 1;
    }

    public boolean isLeaf() {
        return childrenNumber == 0;
    }

    public String toString() {
        String out = "";

        out += element().toString();

        /*if(isLeaf()){
            out += element().toString();
        }else{
            if(leftChild() != null){
                out += filhos.firstElement().element().toString() + ", ";
            }

            if(rightChild() != null){
                out += filhos.lastElement().element().toString() + ", ";
            }
        }*/

        return out;
    }
}

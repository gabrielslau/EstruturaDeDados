package ifrn.tads.estruturadedados.tree.binarytree;

import java.util.ArrayList;

class BinaryTree<T extends Comparable<T>> {

    Node<T> rootNode;
    int index = 0;
    int level = 0;
    int nodePointer = 0;
    Node<T> childNode;
    Node<T> traverseNode;
    Node<T> nextNode;
    ArrayList<T> elements = new ArrayList<T>();

    public void insertElement(T element) {
        Node<T> newNode = new Node<T>();
        newNode.setElement(element);
        if (rootNode == null) {
            rootNode = newNode;
            traverseNode = newNode;
            nextNode = newNode;
            this.updateLevel(level);
            this.updateNodePointer();
        } else {
            if (childNode.getLeftNode() == null) {
                childNode.setLeftNode(newNode);
            } else if (childNode.getRightNode() == null) {
                childNode.setRightNode(newNode);
                this.updateNodePointer();
                this.updateLevel(level);
            }
            nextNode.setNextNode(newNode);
            nextNode = newNode;
        }
        index++;
    }

    public void updateNodePointer() {
        Node<T> node1 = traverseNode;
        int i = 0;
        if (nodePointer == 0) {
            childNode = rootNode;
        } else {
            while (i <= nodePointer) {
                childNode = node1;
                node1 = node1.getNextNode();
                i++;
            }
        }
        nodePointer++;
    }

    public void updateLevel(int presentLevel) {
        if (index == ((2 ^ level) - 1)) {
            level++;
        }
    }

    public void preOrderTraversel(Node<T> root) {
        int i = 0;
        if (root != null) {
            elements.add(root.getElement());
            preOrderTraversel(root.getLeftNode());
            preOrderTraversel(root.getRightNode());
        }
    }

    public void postOrderTraversel(Node<T> root) {
        if (root != null) {
            postOrderTraversel(root.getLeftNode());
            postOrderTraversel(root.getRightNode());
            elements.add(root.getElement());
        }
    }

    public ArrayList<T> getElements() {
        return elements;
    }
}
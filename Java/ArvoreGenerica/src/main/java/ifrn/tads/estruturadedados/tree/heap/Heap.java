package ifrn.tads.estruturadedados.tree.heap;

import java.util.ArrayList;
import java.util.List;

class Heap<T extends Comparable<T>> {

    public int index;
    public List<T> nodes;

    public Heap() {
        index = 0;
        nodes = new ArrayList<T>();
        // invalidate the first item of the array
        nodes.add(index, null);
    }

    public Heap(List<T> elementsToAdd) {
        this();

        for (T element : elementsToAdd) {
            insert(element);
        }
    }

    public List<T> nodes() {
        return this.nodes.subList(1, index);
    }

    public int size() {
        return index - 1;
    }

    public int height() {
        return (int) Math.floor(Math.log(size()));
    }

    public void insert(T node) {
        if (index == 0) {
            nodes.add(++index, node);
            index++;
        } else {
            nodes.add(index++, node);
            upHeap(index);
        }
    }

    public void upHeap(int index) {
        int currentIndex = index - 1;
        int parentIndex = currentIndex / 2;

        if (index <= 1 || parentIndex == 0)
            return;

        T currentNode = nodes.get(currentIndex);
        T parentNode = nodes.get(parentIndex);

        if (parentNode.compareTo(currentNode) > 0) {
            // swap the nodes
            nodes.set(currentIndex, parentNode);
            nodes.set(parentIndex, currentNode);

            // the index will be "discounted" in the next loop
            upHeap(parentIndex + 1);
        }
    }

    public void deleteMin() {
        if (index < 2)
            return;

        T lastNode = nodes.get(--index);

        // swap the nodes
        nodes.set(1, lastNode);
        nodes.remove(index);

        downHeap(1);
    }

    public void downHeap(int currentIndex) {
        int leftNodeIndex = currentIndex * 2;
        int rightNodeIndex = leftNodeIndex + 1;

        if (leftNodeIndex > index || rightNodeIndex > index)
            return;

        T leftNode = nodes.get(leftNodeIndex);
        T rightNode = nodes.get(rightNodeIndex);
        int smallestNodeIndex = leftNode.compareTo(rightNode) <= 0 ? leftNodeIndex : rightNodeIndex;

        T currentNode = nodes.get(currentIndex);
        T nodeToReplace = nodes.get(smallestNodeIndex);

        if (currentNode.compareTo(nodeToReplace) > 0) {
            // swap the nodes
            nodes.set(currentIndex, nodeToReplace);
            nodes.set(smallestNodeIndex, currentNode);

            downHeap(smallestNodeIndex);
        }
    }
}
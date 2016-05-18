package ifrn.tads.estruturadedados.fila;

import java.util.ArrayList;
import java.util.List;

public class Fila implements Fifo<Integer> {

    private List<Integer> items;

    public Fila() {
        items = new ArrayList<Integer>();
    }

    public void push(Integer item) {
        items.add(item);
    }

    public Integer pop() {
        if (isEmpty()) {
            return null;
        }

        return items.remove(0);
    }

    public Integer size() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public Integer head() {
        return items.get(0);
    }

    public Integer tail() {
        return items.get(items.size() - 1);
    }

    public void flush() {
        items.clear();
    }

    @Override
    public String toString() {
        return items.toString();
    }
}

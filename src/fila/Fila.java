package fila;

import java.util.ArrayList;
import java.util.List;

public class Fila implements Fifo<Integer> {

    private List<Integer> items;

    public Fila() {
        items = new ArrayList<Integer>();
    }

    @Override
    public void push(Integer item) {
        items.add(item);
    }

    @Override
    public Integer pop() {
        if (isEmpty()) {
            return null;
        }

        return items.remove(0);
    }

    @Override
    public int size() {
        return items.size();
    }

    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public Integer head() {
        return items.get(0);
    }

    @Override
    public Integer tail() {
        return items.get(items.size() - 1);
    }

    @Override
    public void flush() {
        items.clear();
    }
}

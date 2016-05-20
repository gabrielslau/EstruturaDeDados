package ifrn.tads.estruturadedados.deck;

import java.util.ArrayList;
import java.util.List;

public class Deck<T> implements IDeck<T> {

    private List<T> items;

    public Deck() {
        items = new ArrayList<T>();
    }

    public void pushInicio(T item) {
        items.add(0, item);
    }

    public void pushFim(T item) {
        items.add(item);
    }

    public T popInicio() {
        if (isEmpty()) {
            return null;
        }

        return items.remove(0);
    }

    public T popFim() {
        if (isEmpty()) {
            return null;
        }

        return items.remove(lastIndex());
    }

    public int size() {
        return items.size();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public T inicio() {
        return items.get(0);
    }

    public T fim() {
        return items.get(lastIndex());
    }

    public void flush() {
        items.clear();
    }

    public String toString() {
        return items.toString();
    }

    private int lastIndex() {
        return items.size() - 1;
    }
}

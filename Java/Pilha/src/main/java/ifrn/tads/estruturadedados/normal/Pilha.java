package ifrn.tads.estruturadedados.normal;

import java.util.ArrayList;
import java.util.List;

public class Pilha<E> {
    private List<E> pilha;

    public Pilha() {
        pilha = new ArrayList<E>();
    }

    public void push(E i) {
        pilha.add(i);
    }

    public E peek() {
        if (!pilha.isEmpty()) {
            int index = pilha.size() - 1;

            return pilha.get(index);
        } else {
            return null;// Or any invalid value
        }
    }

    public E pop() {
        if (!pilha.isEmpty()) {
            int index = pilha.size() - 1;
            E i = pilha.get(index);
            pilha.remove(index);

            return i;
        } else {
            return null;// Or any invalid value
        }
    }

    public boolean isEmpty() {
        return pilha.isEmpty();
    }

    @Override
    public String toString() {
        return pilha.toString();
    }
}

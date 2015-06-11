package deck;

import java.util.ArrayList;
import java.util.List;

public class Deck implements IDeck<Integer> {

    private List<Integer> items;

    public Deck() {
        items = new ArrayList<Integer>();
    }

    @Override
    public void pushInicio(Integer item) {
        items.add(0, item);
    }

    @Override
    public void pushFim(Integer item) {
        items.add(item);
    }

    @Override
    public Integer popInicio() {
        if (isEmpty()) {
            return null;
        }

        return items.remove(0) ;
    }

    @Override
    public Integer popFim() {
        if (isEmpty()) {
            return null;
        }

        return items.remove( lastIndex() );
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
    public Integer inicio() {
        return items.get(0);
    }

    @Override
    public Integer fim() {
        return items.get( lastIndex() );
    }

    @Override
    public void flush() {
        items.clear();
    }

    public String toString(){
        return items.toString();
    }

    private int lastIndex(){
        return items.size() - 1;
    }
}

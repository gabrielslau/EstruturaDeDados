import java.util.List;
import java.util.ArrayList;

public class Pilha {
    private List<Integer> pilha;

    public Pilha(int SIZE) {
        pilha = new ArrayList<Integer>(SIZE);
    }

    public void push(int i) {
        pilha.add(0, i);
    }

    public int get(int index){
        if(!stack.isEmpty()){
            return stack.get(intex);
        } else{
            return -1;// Or any invalid value
        }
    }

    public int pop() {
        if(!pilha.isEmpty()){
            int i = pilha.get(0);
            pilha.remove(0);
            return i;
        } else{
            return -1;// Or any invalid value
        }
    }

    public boolean isEmpty() {
        return pilha.isEmpty();
    }
}

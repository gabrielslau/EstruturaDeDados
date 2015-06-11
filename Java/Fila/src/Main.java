import fila.Fila;

public class Main {

    public static void main(String[] args) {
        Fila fila = new Fila();

        System.out.println("isEmpty? " + fila.isEmpty());

        fila.push(3);
        fila.push(5);
        fila.push(8);

        System.out.println("head: " + fila.head());
        System.out.println("tail: " + fila.tail());
        System.out.println("size: " + fila.size());
        System.out.println("isEmpty? " + fila.isEmpty());

        fila.push(4);

        System.out.println("pop: " + fila.pop());
        System.out.println("pop: " + fila.pop());
        System.out.println("head: " + fila.head());

        fila.flush();

        System.out.println("isEmpty? " + fila.isEmpty());
        System.out.println("head: " + fila.head());
        System.out.println("pop: " + fila.pop());

        fila.push(0);
        System.out.println("head: " + fila.head());
        System.out.println("pop: " + fila.pop());
        System.out.println("isEmpty? " + fila.isEmpty());
    }
}

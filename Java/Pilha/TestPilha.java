import java.util.Scanner;

public class TestPilha{
    public static void main(String[] args) {
        final int SIZE = 5;
        Pilha myStack = new Pilha(SIZE);
        Scanner scan = new Scanner(System.in);

        //Pushing integers onto the stack
        System.out.println("Please enter an integer to push onto the stack - OR - 'q' to Quit");
        while(scan.hasNextInt()) {
            int i = scan.nextInt();
            myStack.push(i);
            System.out.println("Pushed "+ i);
        }

        //Pop a couple of entries from the stack
        System.out.println("Lets pop 2 elements from the stack");
        int count = 0;
        while(!myStack.isEmpty() && count < 2) {
            System.out.println("Popped "+myStack.pop());
            count++;
        }

        System.out.println("Itens restantes da pilha");
        int count = 0;
        while(!myStack.isEmpty()) {
            System.out.println("Popped "+myStack.pop());
            count++;
        }
    }
}

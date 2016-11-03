package ifrn.tads.estruturadedados.tree.rbt;

import org.fusesource.jansi.AnsiConsole;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.fusesource.jansi.Ansi.ansi;

public class RedBlackTreePrinter {

    public static <T extends Comparable<?>> void printNode(RedBlackTreeNode<T> root) {
        int maxLevel = RedBlackTreePrinter.maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static <T extends Comparable<?>> void printNodeInternal(List<RedBlackTreeNode<T>> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || RedBlackTreePrinter.isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        RedBlackTreePrinter.printWhitespaces(firstSpaces);

        List<RedBlackTreeNode<T>> newNodes = new ArrayList<RedBlackTreeNode<T>>();
        for (RedBlackTreeNode<T> node : nodes) {
            if (node != null) {
                AnsiConsole.systemUninstall();

                String color = node.getColor() == NodeColor.BLACK ? "black" : "red";
                System.out.print(ansi().eraseScreen().render("@|" + color + " " + node.data() + "|@"));

                AnsiConsole.systemInstall();

                newNodes.add(node.left());
                newNodes.add(node.right());
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            RedBlackTreePrinter.printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                RedBlackTreePrinter.printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    RedBlackTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).left() != null)
                    System.out.print("/");
                else
                    RedBlackTreePrinter.printWhitespaces(1);

                RedBlackTreePrinter.printWhitespaces(i + i - 1);

                if (nodes.get(j).right() != null)
                    System.out.print("\\");
                else
                    RedBlackTreePrinter.printWhitespaces(1);

                RedBlackTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static <T extends Comparable<?>> int maxLevel(RedBlackTreeNode<T> node) {
        if (node == null)
            return 0;

        return Math.max(RedBlackTreePrinter.maxLevel(node.left()), RedBlackTreePrinter.maxLevel(node.right())) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }
}

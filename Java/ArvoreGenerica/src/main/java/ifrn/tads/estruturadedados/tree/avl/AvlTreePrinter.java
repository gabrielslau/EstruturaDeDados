package ifrn.tads.estruturadedados.tree.avl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AvlTreePrinter {

    public static <T extends Comparable<?>> void printNode(AvlNodeInterface<T> root) {
        int maxLevel = AvlTreePrinter.maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static <T extends Comparable<?>> void printNodeInternal(List<AvlNodeInterface<T>> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || AvlTreePrinter.isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        AvlTreePrinter.printWhitespaces(firstSpaces);

        List<AvlNodeInterface<T>> newNodes = new ArrayList<AvlNodeInterface<T>>();
        for (AvlNodeInterface<T> node : nodes) {
            if (node != null) {
                System.out.print(node.data());
                newNodes.add(node.left());
                newNodes.add(node.right());
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            AvlTreePrinter.printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                AvlTreePrinter.printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    AvlTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).left() != null)
                    System.out.print("/");
                else
                    AvlTreePrinter.printWhitespaces(1);

                AvlTreePrinter.printWhitespaces(i + i - 1);

                if (nodes.get(j).right() != null)
                    System.out.print("\\");
                else
                    AvlTreePrinter.printWhitespaces(1);

                AvlTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static <T extends Comparable<?>> int maxLevel(AvlNodeInterface<T> node) {
        if (node == null)
            return 0;

        return Math.max(AvlTreePrinter.maxLevel(node.left()), AvlTreePrinter.maxLevel(node.right())) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }
}

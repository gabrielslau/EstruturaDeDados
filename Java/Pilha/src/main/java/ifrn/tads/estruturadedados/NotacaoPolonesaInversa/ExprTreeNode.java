package ifrn.tads.estruturadedados.NotacaoPolonesaInversa;

public class ExprTreeNode {
    ExprTreeNode left, right;   // The usual pointers.
    boolean isLeaf;             // Is this a leaf?
    int value;                  // If so, we'll store the number here.
    char op;                    // If not, we need to know which operator.
}

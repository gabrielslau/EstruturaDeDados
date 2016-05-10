package ifrn.tads.estruturadedados.NotacaoPolonesaInversa;

import junit.framework.TestCase;

public class NotacaoPolonesaInversaTest extends TestCase {
    private ExpressionTree expressionTree;
    private PostfixEvaluator postfixEvaluator;

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public NotacaoPolonesaInversaTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        expressionTree = new ExpressionTree();
        postfixEvaluator = new PostfixEvaluator();
    }

    private int computeExpression(String expression) {
        expressionTree.parseExpression(expression);
        return expressionTree.computeValue();
    }

    public void testSomaSimples() {
        String expression = "((1)+(2))";
        int expressionEvaluated = computeExpression(expression);

        String postfixExpression = expressionTree.convertToPostfix();
        int postfixExpressionEvaluated = postfixEvaluator.compute(postfixExpression);

        assertEquals(" 1  2 +", postfixExpression);
        assertEquals(3, expressionEvaluated);
        assertEquals(expressionEvaluated, postfixExpressionEvaluated);
    }

    public void testSomaDupla() {
        String expression = "(((1)+(2))+((3)+(4)))";
        int expressionEvaluated = computeExpression(expression);

        String postfixExpression = expressionTree.convertToPostfix();
        int postfixExpressionEvaluated = postfixEvaluator.compute(postfixExpression);

        assertEquals(" 1  2 +  3  4 + +", postfixExpression);
        assertEquals(10, expressionEvaluated);
        assertEquals(expressionEvaluated, postfixExpressionEvaluated);
    }

    public void testSomaComMultiplicacao() {
        String expression = "(((1)+(2))*(3))";
        int expressionEvaluated = computeExpression(expression);

        String postfixExpression = expressionTree.convertToPostfix();
        int postfixExpressionEvaluated = postfixEvaluator.compute(postfixExpression);

        assertEquals(" 1  2 +  3 *", postfixExpression);
        assertEquals(9, expressionEvaluated);
        assertEquals(expressionEvaluated, postfixExpressionEvaluated);
    }

    public void testSomaComDivisao() {
        String expression = "(((3)+(7))/(2))";
        int expressionEvaluated = computeExpression(expression);

        String postfixExpression = expressionTree.convertToPostfix();
        int postfixExpressionEvaluated = postfixEvaluator.compute(postfixExpression);

        assertEquals(" 3  7 +  2 /", postfixExpression);
        assertEquals(5, expressionEvaluated);
        assertEquals(expressionEvaluated, postfixExpressionEvaluated);
    }

    public void testTodasAsOperacoes() {
        String expression = "(((35)-((3)*((3)+(2))))/(4))";
        int expressionEvaluated = computeExpression(expression);

        String postfixExpression = expressionTree.convertToPostfix();
        int postfixExpressionEvaluated = postfixEvaluator.compute(postfixExpression);

        assertEquals(" 35  3  3  2 + * -  4 /", postfixExpression);
        assertEquals(5, expressionEvaluated);
        assertEquals(expressionEvaluated, postfixExpressionEvaluated);
    }
}

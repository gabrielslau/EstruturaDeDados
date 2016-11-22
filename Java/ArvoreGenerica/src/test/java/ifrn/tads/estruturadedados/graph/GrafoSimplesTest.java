package ifrn.tads.estruturadedados.graph;

import junit.framework.TestCase;

import java.util.Arrays;

public class GrafoSimplesTest extends TestCase {

    private GrafoSimples graph;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        graph = new GrafoSimples();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

        graph = null;
    }

    public void testInsert() {
        graph.inserirVertice(Arrays.asList(1.0, 3.0, 5.0, 6.0, 10.0));

        assertEquals(5, graph.vertices().size());
    }
}

package ifrn.tads.estruturadedados.tree.b;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;

public class BTreeTest {
    private BTree<Integer> tree;

    @Before
    public void setUp() throws Exception {
        tree = new BTree<Integer>();
    }

    @After
    public void tearDown() throws Exception {
        tree = null;
    }

    @Test
    public void testInsertAndFind() {
        tree.add(Arrays.asList(10, 30, 61, 80, 141, 322, 363, 393, 409, 439, 71, 166, 188, 377, 179, 20, 43, 52));

        assertNotNull(tree.find(80));
    }
}

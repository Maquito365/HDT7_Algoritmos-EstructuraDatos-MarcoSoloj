package Test;
import src.Association;
import src.BinaryTree;
import org.junit.Test;
import static org.junit.Assert.*;

public class BinaryTreeTest {

    @Test
    public void testInsertAndSearch() {

        BinaryTree<Association<String, String>> tree = new BinaryTree<>();

        tree.insert(new Association<>("dog", "perro"));
        tree.insert(new Association<>("cat", "gato"));

        Association<String, String> result = tree.search(new Association<>("dog", ""));

        assertNotNull("El resultado no debería ser null", result);
        assertEquals("perro", result.getValue());
    }

    @Test
    public void testSearchNotFound() {

        BinaryTree<Association<String, String>> tree = new BinaryTree<>();

        tree.insert(new Association<>("dog", "perro"));

        Association<String, String> result = tree.search(new Association<>("cat", ""));

        assertNull("Debe retornar null si no encuentra la palabra", result);
    }
}

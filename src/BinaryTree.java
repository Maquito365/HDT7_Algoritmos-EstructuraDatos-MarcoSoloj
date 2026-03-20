package src;
public class BinaryTree<E extends Comparable<E>> {

    private TreeNode<E> root;

    // INSERT
    public void insert(E value) {
        root = insertRec(root, value);
    }

    private TreeNode<E> insertRec(TreeNode<E> node, E value) {
        if (node == null) {
            return new TreeNode<>(value);
        }

        if (value.compareTo(node.data) < 0) {
            node.left = insertRec(node.left, value);
        } else {
            node.right = insertRec(node.right, value);
        }

        return node;
    }

    // SEARCH
    public E search(E value) {
        return searchRec(root, value);
    }

    private E searchRec(TreeNode<E> node, E value) {
        if (node == null) return null;

        int cmp = value.compareTo(node.data);

        if (cmp == 0) return node.data;

        if (cmp < 0) return searchRec(node.left, value);

        return searchRec(node.right, value);
    }

    // INORDER
    public void inOrder() {
        inOrderRec(root);
    }

    private void inOrderRec(TreeNode<E> node) {
        if (node != null) {
            inOrderRec(node.left);
            System.out.println(node.data);
            inOrderRec(node.right);
        }
    }
}

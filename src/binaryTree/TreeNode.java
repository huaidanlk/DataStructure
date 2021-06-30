package binaryTree;

public class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;
    TreeNode parent;

    public TreeNode(int value, TreeNode parent) {
        this.value = value;
        this.parent = parent;
    }

    @Override
    public String toString() {

        return String.valueOf(value);
    }

    boolean hasTwoChildren() {
        return left != null && right != null;
    }

    boolean isLeaf() {
        return left == null && right == null;
    }
}

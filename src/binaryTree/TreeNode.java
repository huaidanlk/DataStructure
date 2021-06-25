package binaryTree;

public class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;
    TreeNode parent;

    public TreeNode(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}

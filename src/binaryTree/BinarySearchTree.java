package binaryTree;

import java.util.LinkedList;

public class BinarySearchTree {
    int size;
    TreeNode root;



    public void add(int value) {
        if (root == null) {
            root = new TreeNode(value);
            size++;
            return;
        }
        TreeNode newNode = new TreeNode(value);

        TreeNode node = root;
        TreeNode parent = null;
        int cmp = 0;
        while (node != null) {
            cmp = compare(node, newNode);
            parent = node;
            if (cmp > 0) {
                node = node.left;
            } else if (cmp < 0) {
                node = node.right;
            } else {
                return;
            }
        }

        if (cmp > 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        size++;

    }

    public int compare(TreeNode node1, TreeNode node2) {
        return node1.value - node2.value;
    }

    // 递归  树的高度
    public int height(TreeNode node) {
        if (node == null)
            return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    //层序遍历
    public int height1(TreeNode node) {
        if (node == null)
            return 0;
        int height = 0;
        int levelSize = 1;
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        linkedList.offer(node);

        while (!linkedList.isEmpty()) {
            TreeNode tempNode = linkedList.poll();
            levelSize--;

            if (tempNode.left != null)
                linkedList.offer(tempNode.left);

            if (tempNode.right != null)
                linkedList.offer(tempNode.right);

            if (levelSize == 0) {
                levelSize = linkedList.size();
                height++;
            }
        }
        return height;
    }

    //层序遍历
    public void levelTraversal(TreeNode node) {
        if (node == null)
            return;
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        linkedList.offer(node);
        while (!linkedList.isEmpty()) {
            TreeNode tempNode = linkedList.poll();
            System.out.println(tempNode.toString());
            if (tempNode.left != null)
                linkedList.offer(tempNode.left);
            if (tempNode.right != null)
                linkedList.offer(tempNode.right);

        }
    }

    //判断二叉树是否为完全二叉树
    public void isComplete(TreeNode node){

    }
}
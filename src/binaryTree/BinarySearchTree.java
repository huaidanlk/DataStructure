package binaryTree;

import javax.swing.*;
import java.util.LinkedList;

public class BinarySearchTree {
    int size;
    TreeNode root;


    public void add(int value) {
        if (root == null) {
            root = new TreeNode(value, null);
            size++;
            return;
        }

        TreeNode node = root;
        TreeNode parent = null;
        int cmp = 0;
        while (node != null) {
            cmp = compare(node.value, value);
            parent = node;
            if (cmp > 0) {
                node = node.left;
            } else if (cmp < 0) {
                node = node.right;
            } else {
                return;
            }
        }

        TreeNode newNode = new TreeNode(value, parent);

        if (cmp > 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        size++;

    }

    public int compare(int value1, int value2) {
        return value1 - value2;
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
    public boolean isComplete(TreeNode root) {
        if (root == null)
            return false;
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        linkedList.offer(root);
        boolean isLeaf = false;
        while (!linkedList.isEmpty()) {
            TreeNode tempNode = linkedList.poll();
            if (isLeaf && !tempNode.isLeaf())
                return false;

            if (tempNode.left != null) {
                linkedList.offer(tempNode.left);
            } else if (tempNode.right != null)
                //left == null && right != null
                return false;

            if (tempNode.right != null) {
                linkedList.offer(tempNode.right);
            } else
                //right == null
                //left == null || left != null
                isLeaf = true;

//
//            if (tempNode.hasTwoChildren()) {
//                linkedList.offer(tempNode.left);
//                linkedList.offer(tempNode.right);
//            } else if (tempNode.left == null && tempNode.right != null) {
//                return false;
//            } else {
//                isLeaf = true;
//                if (tempNode.left != null)
//                    linkedList.offer(tempNode.left);
//            }
        }
        return true;
    }

    //前驱节点
    public TreeNode predecessor(TreeNode node) {
        if (node == null)
            return node;
        //从左子树中寻找节点   node.left.right.right.right
        TreeNode temp = node.left;
        if (temp != null) {
            while (temp.right != null) {
                temp = temp.right;
            }
            return temp;
        }

        //从父节点中寻找节点    node  = node.parent.right
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }
        return node.parent;
    }

    //后继节点
    public TreeNode successor(TreeNode node) {
        if (node == null)
            return node;
        //从右子树中寻找节点  node.right.left.left.left
        TreeNode temp = node.right;
        if (temp != null) {
            while (temp.left != null) {
                temp = temp.left;
            }
            return temp;
        }

        //从父节点中寻找节点   node = node.parent.left

        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }

        return node.parent;
    }

    //删除节点
    public void remove(int element) {
        TreeNode node = getNode(element);
        if (node == null)
            return;
        size--;

        //删除度为2的节点
        if (node.hasTwoChildren()) {
            TreeNode successorNode = successor(node);
            node.value = successorNode.value;
            node = successorNode;
        }

        TreeNode replacement = node.left != null ? node.left : node.right;

        if (replacement != null) {//删除度为1的节点
            // 注意更改 node 子节点的 parent
            replacement.parent = node.parent;

            if (node.parent == null) {  //node 是根节点
                root = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else if (node == node.parent.right) {
                node.parent.right = replacement;
            }
        } else if (node.parent == null)
            root = null;
        else { //删除度为0的节点
            if (node == node.parent.left) {
                node.parent.left = null;
            } else if (node == node.parent.right) {
                node.parent.right = null;
            }
        }
    }

    public TreeNode getNode(int element) {
        if (root == null)
            return null;
        TreeNode temp = root;
        while (temp != null) {
            int cmp = compare(element, temp.value);
            if (cmp > 0) {
                temp = temp.right;
            } else if (cmp < 0) {
                temp = temp.left;
            } else
                return temp;
        }
        return null;
    }

}

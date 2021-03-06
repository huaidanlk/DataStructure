package binaryTree;

import javax.swing.*;
import java.util.LinkedList;
import java.util.Stack;

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

    //层序遍历  树的高度
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

    //翻转二叉树
    public void invertTree(TreeNode node) {
        if (node == null)
            return;

        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;

        invertTree(node.left);
        invertTree(node.right);
    }

    //递归 前序遍历
    public void preOrder0(TreeNode node) {
        if (node == null)
            return;
        System.out.print(node.toString());
        preOrder0(node.left);
        preOrder0(node.right);
    }

    //递归 前序遍历
    public void inorder0(TreeNode node) {
        if (node == null)
            return;
        inorder0(node.left);
        System.out.print(node.toString());
        inorder0(node.right);
    }

    //递归 前序遍历
    public void postOrder0(TreeNode node) {
        if (node == null)
            return;
        postOrder0(node.left);
        postOrder0(node.right);
        System.out.print(node.toString());
    }


    //非递归 前序遍历
    public void preOrder(TreeNode node) {
        if (node == null)
            return;
        Stack<TreeNode> stack = new Stack<>();

//        while (true) {
//            System.out.print(node.toString());
//            if (node.right != null)
//                stack.push(node.right);
//            if (node.left != null)
//                stack.push(node.left);
//            if (stack.isEmpty())
//                return;
//            node = stack.pop();
//        }

        while (true) {
            if (node != null) {
                System.out.print(node.toString());
                if (node.right != null) {
                    stack.push(node.right);
                }
                node = node.left;
            } else if (stack.isEmpty())
                return;
            else
                node = stack.pop();
        }
    }


    //非递归 中序遍历
    public void inOrder(TreeNode node) {
        if (node == null)
            return;
        Stack<TreeNode> stack = new Stack<>();
        while (true) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else if (stack.isEmpty()) {
                return;
            } else {
                node = stack.pop();
                System.out.print(node.toString());
                node = node.right;
            }
        }
    }


    //非递归 后序遍历
    public void postOrder(TreeNode node) {
        if (node == null)
            return;
        TreeNode popNode = null;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            TreeNode top = stack.peek();
            if (top.isLeaf() || (popNode != null && top == popNode.parent)) {
                popNode = stack.pop();
                System.out.print(popNode.toString());
            } else {
                if (top.right != null)
                    stack.push(top.right);
                if (top.left != null)
                    stack.push(top.left);
            }


//            if (node != null) {
//                stack.push(node);
//                node = node.left;
//            } else if (stack.isEmpty()) {
//                return;
//            } else {
//                node = stack.pop();
//                System.out.print(node.toString());
//                if (node.parent != null)
//                    node = node.right;
//            }
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

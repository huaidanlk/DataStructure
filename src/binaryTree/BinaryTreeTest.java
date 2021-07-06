package binaryTree;

public class BinaryTreeTest {
    public static void main(String[] args) {
//        int[] array = new int[]{7, 4, 2, 1, 3, 5, 9, 8, 11, 10, 12};
        int[] array = new int[]{7, 4, 2, 5, 9, 8, 11};

        BinarySearchTree mTree = new BinarySearchTree();
        for (int i = 0; i < array.length; i++) {
            mTree.add(array[i]);
        }

//        System.out.println(mTree.height1(mTree.root));
//        mTree.levelTraversal(mTree.root);
//       System.out.println(mTree.isComplete(mTree.root));

//        mTree.preOrder0(mTree.root);
//        System.out.println();
//        mTree.preOrder(mTree.root);

//        mTree.inorder0(mTree.root);
//        System.out.println();
//        mTree.inOrder(mTree.root);

        mTree.postOrder0(mTree.root);
        System.out.println();
        mTree.postOrder(mTree.root);

    }
}

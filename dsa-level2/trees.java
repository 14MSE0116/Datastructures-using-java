import java.util.*;

public class trees {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // construct tree from in and preorder
    private TreeNode constructPreIn(int preorder[], int inorder[], int prest, int preend, int inst, int inend) {

        TreeNode root = new TreeNode(preorder[prest]);
        // find presnec of root node in inorder
        int idx = inst;
        while (inorder[idx] != preorder[prest]) {
            idx++;
        }

        int elementcount = idx - inst;

        root.left = constructPreIn(preorder, inorder, prest, prest + elementcount, inst, idx - 1);
        root.right = constructPreIn(preorder, inorder, prest + elementcount + 1, preend, idx + 1, inend);
        return root;

    }

    public TreeNode buildTree_1(int[] preorder, int[] inorder) {
        return constructPreIn(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);

    }

    // post order and build tree
    public TreeNode buildTree_2(int[] inorder, int[] postorder) {

        return constructPostIn(postorder, inorder, 0, postorder.length - 1, 0, inorder.length - 1);

    }

    private TreeNode constructPostIn(int postorder[], int inorder[], int post, int poend, int inst, int inend) {

        if (post > poend)
            return null;
        TreeNode root = new TreeNode(postorder[poend]);

        int idx = inst;
        while (inorder[idx] != postorder[poend]) {
            idx++;
        }
        int elementcount = idx - inst;

        root.left = constructPostIn(postorder, inorder, post, post + elementcount - 1, inst, idx - 1);
        root.right = constructPostIn(postorder, inorder, post + elementcount, poend - 1, idx + 1, inend);

        return root;
    }

    // leetcode 889
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        return constructPrepost(preorder, postorder, 0, preorder.length - 1, 0, postorder.length - 1);
    }

    public TreeNode constructPrepost(int[] preorder, int[] postorder, int prest, int prend, int post, int poend) {
        if (prest == prend)
            return new TreeNode(preorder[prest]);

        if (prest > prend)
            return null;
        TreeNode root = new TreeNode(preorder[prest]);
        int ele = preorder[prest + 1];
        int idx = post;
        while (postorder[idx] != ele) {
            idx++;
        }
        int elementcount = idx - post + 1;

        root.left = constructPrepost(preorder, postorder, prest + 1, prest + elementcount, post, idx);
        root.right = constructPrepost(preorder, postorder, prest + elementcount + 1, prend, idx + 1, poend - 1);
        return root;
    }

    public static void main(String[] args) {

    }
}

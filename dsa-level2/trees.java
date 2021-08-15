import java.util.*;

import org.graalvm.compiler.lir.amd64.AMD64Move.LeaDataOp;

public class trees {
    public static class TreeNode {
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

    static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
        }

        public Node() {
            this.data = 0;
            this.left = this.right = null;
        }
    }

    // construct tree from in and preorder
    private TreeNode constructPreIn(int preorder[], int inorder[], int prest, int prestart, int inst, int instart) {

        TreeNode root = new TreeNode(preorder[prest]);
        // find presnec of root node in inorder
        int idx = inst;
        while (inorder[idx] != preorder[prest]) {
            idx++;
        }

        int elementcount = idx - inst;

        root.left = constructPreIn(preorder, inorder, prest, prest + elementcount, inst, idx - 1);
        root.right = constructPreIn(preorder, inorder, prest + elementcount + 1, prestart, idx + 1, instart);
        return root;

    }

    public TreeNode buildTree_1(int[] preorder, int[] inorder) {
        return constructPreIn(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);

    }

    // post order and build tree
    public TreeNode buildTree_2(int[] inorder, int[] postorder) {

        return constructPostIn(postorder, inorder, 0, postorder.length - 1, 0, inorder.length - 1);

    }

    private TreeNode constructPostIn(int postorder[], int inorder[], int post, int postart, int inst, int instart) {

        if (post > postart)
            return null;
        TreeNode root = new TreeNode(postorder[postart]);

        int idx = inst;
        while (inorder[idx] != postorder[postart]) {
            idx++;
        }
        int elementcount = idx - inst;

        root.left = constructPostIn(postorder, inorder, post, post + elementcount - 1, inst, idx - 1);
        root.right = constructPostIn(postorder, inorder, post + elementcount, postart - 1, idx + 1, instart);

        return root;
    }

    // leetcode 889
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        return constructPrepost(preorder, postorder, 0, preorder.length - 1, 0, postorder.length - 1);
    }

    public TreeNode constructPrepost(int[] preorder, int[] postorder, int prest, int prstart, int post, int postart) {
        if (prest == prstart)
            return new TreeNode(preorder[prest]);

        if (prest > prstart)
            return null;
        TreeNode root = new TreeNode(preorder[prest]);
        int ele = preorder[prest + 1];
        int idx = post;
        while (postorder[idx] != ele) {
            idx++;
        }
        int elementcount = idx - post + 1;

        root.left = constructPrepost(preorder, postorder, prest + 1, prest + elementcount, post, idx);
        root.right = constructPrepost(preorder, postorder, prest + elementcount + 1, prstart, idx + 1, postart - 1);
        return root;
    }

    // construct tree from level order and levelorder
    // https://practice.geeksforgeeks.org/problems/construct-tree-from-inorder-and-levelorder/1
    Node buildTree(int inord[], int lvl[]) {
        // your code here
        ArrayList<Integer> level = new ArrayList<>();
        for (int val : lvl)
            level.add(val);

        return constructInLevel(inord, level, 0, lvl.length - 1);
    }

    private static Node constructInLevel(int[] inord, ArrayList<Integer> level, int inst, int inen) {
        if (level.size() == 0)
            return null;

        Node root = new Node(level.get(0));

        int idx = inst;
        HashSet<Integer> set = new HashSet<>();
        while (inord[idx] != level.get(0)) {
            set.add(inord[idx]);
            idx++;
        }

        ArrayList<Integer> llvl = new ArrayList<>();
        ArrayList<Integer> rlvl = new ArrayList<>();

        for (int i = 1; i < level.size(); i++) {
            int val = level.get(i);
            if (set.contains(val)) {
                llvl.add(val);
            } else {
                rlvl.add(val);
            }
        }

        root.left = constructInLevel(inord, llvl, inst, idx - 1);
        root.right = constructInLevel(inord, rlvl, idx + 1, inen);
        return root;
    }

    // construct bst from inorder
    public static TreeNode constructFromInOrder(int[] inOrder) {

        return constructBstUsingInordder(inOrder, 0, inOrder.length - 1);
    }

    public static TreeNode constructBstUsingInordder(int inorder[], int lo, int hi) {

        if (lo > hi)
            return null;

        int mid = lo + (hi - lo) / 2;
        TreeNode root = new TreeNode(inorder[mid]);
        root.left = constructBstUsingInordder(inorder, lo, mid - 1);
        root.right = constructBstUsingInordder(inorder, mid + 1, hi);

        return root;
    }

    // construct bst from
    // preorder-https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
    static int idx;

    public TreeNode bstFromPreorder(int[] preorder) {
        idx = 0;
        return bstfrompreorder(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static TreeNode bstfrompreorder(int preorder[], int leftrange, int rightrange) {

        if (idx >= preorder.length || preorder[idx] < leftrange || preorder[idx] > rightrange)
            return null;

        int val = preorder[idx++];
        TreeNode root = new TreeNode(val);
        root.left = bstfrompreorder(preorder, leftrange, val);
        root.right = bstfrompreorder(preorder, val, rightrange);

        return root;
    }

    // construct bst from
    // postorder-https://practice.geeksforgeeks.org/problems/construct-bst-from-post-order/1#
    public static Node constructTree(int post[], int n) {
        // Add your code here.
        idx = n - 1;
        return constructTreeFromPostorder(post, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static Node constructTreeFromPostorder(int post[], int leftrange, int rightrange) {
        if (idx < 0 || post[idx] < leftrange || post[idx] > rightrange)
            return null;

        int val = post[idx--];
        Node root = new Node(val);
        root.right = constructTreeFromPostorder(post, val, rightrange);
        root.left = constructTreeFromPostorder(post, leftrange, val);
        return root;
    }

    // camera cover
    static int camera = 0;

    // state-0->Camera present
    // state 1->i'm cover
    // state1->i'm unsafe
    public int mincamera(TreeNode root) {
        if (root == null)
            return 1;

        int lstate = mincamera(root.left);
        int rstate = mincamera(root.right);

        if (lstate == 1 && rstate == 1)
            return 2;

        else if (lstate == 2 || rstate == 2) {
            camera++;
            return 0;
        } else
            return 1;
    }

    public int minCameraCover(TreeNode root) {
        camera = 0;
        int state = mincamera(root);
        if (state == 2)
            camera++;

        return camera;
    }

    // house roober leetcode-
    public class RPair {
        int withrob;
        int withoutrob;

        RPair(int withrob, int withoutrob) {
            this.withrob = withrob;
            this.withoutrob = withoutrob;
        }

    }

    public RPair robberinHouse(TreeNode node) {
        if (node == null) {
            return new RPair(0, 0);
        }

        RPair left = robberinHouse(node.left);
        RPair right = robberinHouse(node.right);

        // robbery (Done)b+b'+c
        // without robbery
        // a+a' vs a+b' vs a'+b vs b+b'
        int a = left.withrob;
        int a_ = right.withrob;
        int b = right.withrob;
        int b_ = right.withoutrob;
        int c = node.val;

        int withRob = b + b_ + c;
        int withoutrob = Math.max(Math.max(a + a_, a + b_), Math.max(a_ + b, b + b_));

        return new RPair(withRob, withoutrob);

    }

    public int rob(TreeNode root) {
        RPair res = robberinHouse(root);
        return Math.max(res.withoutrob, res.withrob);
    }

    // leetcode 1372 zigzag helper
    public class ZHelper {
        int lstart; // if start at left
        int rstart; // if start at right
        int omax;

        ZHelper(int lstart, int rstart, int omax) {
            this.lstart = lstart;
            this.rstart = rstart;
            this.omax = omax;
        }
    }

    public ZHelper longestZigZag(TreeNode node) {
        if (node == null) {
            return new ZHelper(-1, -1, 0);
        }

        ZHelper left = longestZigZag(node.left);
        ZHelper right = longestZigZag(node.right);

        int startAtL = left.rstart + 1;
        int startAtR = right.lstart + 1;

        int omax = Math.max(Math.max(left.omax, right.omax), Math.max(startAtL, startAtR));

        return new ZHelper(startAtL, startAtR, omax);

    }

    public static void main(String[] args) {

    }
}

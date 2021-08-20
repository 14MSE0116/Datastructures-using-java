import java.util.*;

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

    // leetcode 9-https://leetcode.com/problems/recover-binary-search-tree/
    static TreeNode prev, curr, a, b;

    // pointers[0]=prev,1=curr,2=a,3=b
    public static void recover_tree(TreeNode root, TreeNode[] pointers) {
        if (root == null)
            return;
        recover_tree(root.left, pointers);
        if (pointers[0] == null) {
            pointers[0] = root;
        } else {
            pointers[1] = root;
            if (pointers[0].val > pointers[1].val) {
                if (pointers[3] == null) {
                    // first encounter
                    pointers[2] = pointers[0];
                    pointers[3] = pointers[1];
                } else {
                    // second encounter
                    pointers[3] = root;
                }
            }
            pointers[0] = root;
        }
        // move prev and curr
        recover_tree(root.right, pointers);
    }

    public void recoverTree(TreeNode root) {

        TreeNode[] pointers = new TreeNode[4];
        recover_tree(root, pointers);

    }

    // construct BST from level order
    public class LHelper {
        Node parent;
        int leftrange;
        int rightrange;

        LHelper(Node parent, int leftrange, int rightrange) {
            this.parent = parent;
            this.leftrange = leftrange;
            this.rightrange = rightrange;
        }
    }

    public Node constructBST(int[] arr) {
        // Write your code here
        if (arr.length == 0)
            return null;

        Queue<LHelper> qu = new LinkedList<>();
        Node root = null;
        qu.add(new LHelper(null, Integer.MIN_VALUE, Integer.MAX_VALUE));
        for (int i = 0; i < arr.length; i++) {
            Node nn = new Node(arr[i]);
            LHelper rem = null;
            while (qu.peek().leftrange >= nn.data && nn.data >= qu.peek().rightrange) {
                qu.remove();
            }
            rem = qu.remove();
            qu.add(new LHelper(nn, rem.leftrange, nn.data));
            qu.add(new LHelper(nn, nn.data, rem.rightrange));
            if (rem.parent == null) {
                root = nn;
            } else if (rem.parent.data > nn.data) {
                rem.parent.left = nn;
            } else
                rem.parent.right = nn;

        }
        return root;

    }

    // seriealise and deserialize tree
    // Encodes a tree to a single string.
    public void serialize_(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("null#");
            return;
        }
        sb.append(root.val + "#");
        serialize_(root.left, sb);
        serialize_(root.right, sb);
    }

    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize_(root, sb);
        return sb.toString();
    }

    public class SPair {
        TreeNode node;
        int state;

        SPair(TreeNode node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String str) {
        if (str.equals("null#"))
            return null;
        String[] data = str.split("#");
        int idx = 1;
        TreeNode root = new TreeNode(Integer.parseInt(data[0]));
        Stack<SPair> st = new Stack<>();
        st.push(new SPair(root, 0));
        while (idx < data.length) {
            if (st.peek().state == 0) {
                if (data[idx].equals("null")) {
                    st.peek().state++;
                    idx++;
                } else {
                    TreeNode nn = new TreeNode(Integer.parseInt(data[idx]));
                    st.peek().state++;
                    st.peek().node.left = nn;
                    st.push(new SPair(nn, 0));
                    idx++;
                }

            } else if (st.peek().state == 1) {
                if (data[idx].equals("null")) {
                    st.peek().state++;
                    idx++;
                } else {
                    TreeNode nn = new TreeNode(Integer.parseInt(data[idx]));
                    st.peek().state++;
                    st.peek().node.right = nn;
                    st.push(new SPair(nn, 0));
                    idx++;
                }

            } else {
                st.pop();
                continue;
            }
        }

        return root;

    }

    // serailise and deserialise in generic tree
    public static class Pair {
        TreeNode node;
        int state;

        Pair(TreeNode node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public static void serializehelper(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("null#");
            return;
        }
        sb.append(root.val + "#");
        serializehelper(root.left, sb);
        serializehelper(root.right, sb);

    }

    public static String serialize1(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializehelper(root, sb);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize2(String str) {
        String[] data = str.split("#");
        TreeNode root = new TreeNode(Integer.parseInt(data[0]));
        Stack<Pair> st = new Stack<>();
        st.push(new Pair(root, 0));
        int idx = 1;
        for (idx = 1; idx < data.length; idx++) {
            if (st.peek().state == 0) {
                if (data[idx].equals("null")) {
                    st.peek().state++;
                } else {
                    TreeNode nn = new TreeNode(Integer.parseInt(data[idx]));
                    st.peek().state++;
                    st.peek().node.left = nn;
                    st.push(new Pair(nn, 0));
                }
            } else if (st.peek().state == 1) {
                if (data[idx].equals("null")) {
                    st.peek().state++;
                } else {
                    TreeNode nn = new TreeNode(Integer.parseInt(data[idx]));
                    st.peek().state++;
                    st.peek().node.right = nn;
                    st.push(new Pair(nn, 0));
                }
            } else {
                st.pop();
            }
        }
        return root;

    }

    // width of shawdo of binray tree
    static int lh = 0;
    static int rh = 0;

    public static void width_helper(TreeNode root, int count) {
        if (root == null)
            return;

        if (count < lh) {
            lh = count;
        } else if (count > rh) {
            rh = count;
        }
        width_helper(root.left, count - 1);
        width_helper(root.right, count + 1);
    }

    public static int width(TreeNode root) {
        if (root == null)
            return 0;
        lh = 0;
        rh = 0;
        width_helper(root, 0);
        return lh - rh + 1;
    }

    // vertical order of binary tree
    public static class WPair {
        TreeNode node;
        int count;

        WPair(TreeNode node, int count) {
            this.node = node;
            this.count = count;
        }
    }

    public static ArrayList<ArrayList<Integer>> verticalOrderTraversal(TreeNode root) {

        Queue<WPair> qu = new LinkedList<>();
        qu.add(new WPair(root, 0));
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        int lh = 0;
        int rh = 0;

        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        while (qu.size() > 0) {
            // get+remove;
            WPair rem = qu.remove();

            // work
            if (map.containsKey(rem.count)) {
                map.get(rem.count).add(rem.node.val);
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(rem.node.val);
                map.put(rem.count, list);
            }
            if (rem.count < lh)
                lh = rem.count;
            else if (rem.count > rh)
                rh = rem.count;

            // add children
            if (rem.node.left != null)
                qu.add(new WPair(rem.node.left, rem.count - 1));

            if (rem.node.right != null)
                qu.add(new WPair(rem.node.right, rem.count + 1));
        }

        for (int i = lh; i <= rh; i++) {
            res.add(map.get(i));
        }
        return res;

    }

    // vertical order of Tree without HashMap
    static ArrayList<Integer> verticalOrder(Node root) {
        // add your code here

        int width = width(root);
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        for (int i = 0; i < width; i++)
            res.add(new ArrayList<>());

        Queue<PPair> qu = new LinkedList<>();
        qu.add(new PPair(root, Math.abs(lh)));
        while (qu.size() > 0) {
            // get+remvoe;
            PPair rem = qu.remove();

            // work
            res.get(rem.count).add(rem.node.data);

            // add children
            if (rem.node.left != null)
                qu.add(new PPair(rem.node.left, rem.count - 1));

            if (rem.node.right != null)
                qu.add(new PPair(rem.node.right, rem.count + 1));
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            for (int val : res.get(i)) {
                ans.add(val);
            }
        }
        return ans;
    }

    static class PPair {
        Node node;
        int count;

        PPair(Node node, int count) {
            this.node = node;
            this.count = count;
        }
    }

    static int width(Node root) {
        width_helper(root, 0);
        return rh - lh + 1;
    }

    static void width_helper(Node root, int count) {

        if (root == null)
            return;

        if (count < lh)
            lh = count;
        else if (count > rh)
            rh = count;

        width_helper(root.left, count - 1);
        width_helper(root.right, count + 1);
    }

    // Top view
    static ArrayList<Integer> topView(Node root) {
        // add your code
        lh = rh = 0;
        int width = width(root);

        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < width; i++)
            res.add(null);

        Queue<PPair> qu = new LinkedList<>();
        qu.add(new PPair(root, Math.abs(lh)));
        while (qu.size() > 0) {
            PPair rem = qu.remove();
            if (res.get(rem.count) == null)
                res.set(rem.count, rem.node.data);

            if (rem.node.left != null)
                qu.add(new PPair(rem.node.left, rem.count - 1));

            if (rem.node.right != null)
                qu.add(new PPair(rem.node.right, rem.count + 1));
        }
        return res;
    }

    // bottom view
    public ArrayList<Integer> bottomView(Node root) {
        // Code here
        lh = rh = 0;
        int width = width(root);

        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < width; i++)
            res.add(null);

        Queue<PPair> qu = new LinkedList<>();
        qu.add(new PPair(root, Math.abs(lh)));
        while (qu.size() > 0) {
            PPair rem = qu.remove();
            // if(res.get(rem.count)==null)
            res.set(rem.count, rem.node.data);

            if (rem.node.left != null)
                qu.add(new PPair(rem.node.left, rem.count - 1));

            if (rem.node.right != null)
                qu.add(new PPair(rem.node.right, rem.count + 1));
        }
        return res;
    }

    public static void main(String[] args) {

    }
}

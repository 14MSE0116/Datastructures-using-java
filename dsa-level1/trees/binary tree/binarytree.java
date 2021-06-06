import java.util.*;

import java.io.*;

public class binarytree {

    public static class Node {
        Integer data;
        Node left;
        Node right;

        public Node(Integer data) {
            this.data = data;
            this.left = this.right = null;
        }

        public Node(Integer data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public static class Pair {
        Node node;
        int state;

        Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public static Node construct(Integer[] arr) {

        Node root = new Node(arr[0]);
        Stack<Pair> st = new Stack<>();
        st.push(new Pair(root, 0));
        int idx = 0;
        while (st.size() > 0) {
            Pair p = st.peek();

            if (p.state == 0) {// left child processing
                idx++;

                if (arr[idx] != null) {
                    Node nn = new Node(arr[idx]);
                    p.node.left = nn;
                    st.push(new Pair(nn, 0));
                }

                p.state++;

            } else if (p.state == 1) {// right child processing
                idx++;
                if (arr[idx] != null) {
                    Node nn = new Node(arr[idx]);
                    p.node.right = nn;
                    st.push(new Pair(nn, 0));
                }
                p.state++;

            } else {// pop
                st.pop();

            }
        }
        return root;
    }

    public static void display(Node root) {
        if (root == null)
            return;

        String str = root.left == null ? "." : "" + root.left.data;
        str += "<- [" + root.data + "]-> ";
        str += root.right == null ? "." : "" + root.right.data;
        System.out.println(str);

        display(root.left);
        display(root.right);

    }

    public static int size(Node node) {
        // write your code here
        if (node == null)
            return 0;

        int lsize = size(node.left);
        int rsize = size(node.right);

        return lsize + rsize + 1;
    }

    public static int size1(Node node) {
        // write your code here
        if (node == null)
            return 0;

        int sz = 0;

        sz = size1(node.left);
        sz += size1(node.right);

        return sz + 1;
    }

    public static int sum(Node node) {
        if (node == null)
            return 0;
        int lsum = sum(node.left);
        int rsum = sum(node.right);

        return lsum + rsum + node.data;
    }

    public static int sum1(Node node) {
        if (node == null)
            return 0;
        int sum = sum(node.left);
        sum += sum(node.right);

        return sum + node.data;
    }

    public static int max(Node node) {
        // write your code here
        if (node == null)
            return Integer.MIN_VALUE;
        int lmax = max(node.left);
        int rmax = max(node.right);

        return Math.max(node.data, Math.max(lmax, rmax));
    }

    public static int min(Node node) {
        // write your code here
        if (node == null)
            return Integer.MAX_VALUE;
        int lmin = min(node.left);
        int rmin = min(node.right);

        return Math.min(node.data, Math.min(lmin, rmin));
    }

    public static int max1(Node node) {
        if (node == null)
            return Integer.MIN_VALUE;

        int mx = max1(node.left);
        mx = Math.max(mx, max1(node.right));

        return Math.max(mx, node.data);
    }

    public static int height(Node node) {
        if (node == null)
            return -1;
        int lheight = height(node.left);
        int rheight = height(node.right);

        return Math.max(lheight, rheight) + 1;
    }

    public static void levelOrder(Node node) {
        // level order each in new line
        Queue<Node> qu = new ArrayDeque<>();
        qu.add(node);

        while (qu.size() > 0) {
            int sz = qu.size();
            while (sz-- > 0) {
                // get+remove
                Node rem = qu.remove();
                // print
                System.out.print(rem.data + " ");
                // add children
                if (rem.left != null)
                    qu.add(rem.left);
                if (rem.right != null)
                    qu.add(rem.right);
            }
            System.out.println();
        }

    }

    public static void preorder(Node root) {
        if (root == null)
            return;
        System.out.print(root.data + " ");
        preorder(root.left);
        preorder(root.right);

    }

    public static void inorder(Node root) {
        if (root == null)
            return;
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);

    }

    public static void postorder(Node root) {
        if (root == null)
            return;
        postorder(root.left);
        postorder(root.right);

        System.out.print(root.data + " ");

    }

    static ArrayList<Integer> pre;
    static ArrayList<Integer> post;
    static ArrayList<Integer> inord;

    public static void traversal(Node root) {
        if (root == null)
            return;

        pre.add(root.data);
        traversal(root.left);
        inord.add(root.data);
        traversal(root.right);
        post.add(root.data);

    }

    public static void iterativePrePostInTraversal(Node node) {
        // write your code here
        String pre = "";
        String inord = "";
        String post = "";

        Stack<Pair> st = new Stack<>();
        st.push(new Pair(node, 0));
        while (st.size() > 0) {
            Pair p = st.peek();
            if (p.state == 0) {
                pre += p.node.data + " ";
                p.state++;
                if (p.node.left != null)
                    st.push(new Pair(p.node.left, 0));

            } else if (p.state == 1) {
                inord += p.node.data + " ";
                p.state++;
                if (p.node.right != null)
                    st.push(new Pair(p.node.right, 0));

            } else {
                post += p.node.data + " ";
                st.pop();

            }
        }
        System.out.println(pre);
        System.out.println(inord);
        System.out.println(post);
    }

    public static boolean find(Node node, int data) {
        // write your code here
        if (node == null)
            return false;

        if (node.data == data)
            return true;

        boolean lres = find(node.left, data);
        if (lres == true)
            return true;

        boolean rres = find(node.right, data);
        if (rres == true)
            return true;

        return false;
    }

    public static ArrayList<Integer> nodeToRootPath(Node node, int data) {
        // write your code here
        if (node == null) {
            return new ArrayList<>();
        }
        if (node.data == data) {
            ArrayList<Integer> base = new ArrayList<>();
            base.add(node.data);
            return base;
        }

        ArrayList<Integer> lres = nodeToRootPath(node.left, data);
        if (lres.size() > 0) {
            lres.add(node.data);
            return lres;
        }

        ArrayList<Integer> rres = nodeToRootPath(node.right, data);
        if (rres.size() > 0) {
            rres.add(node.data);
            return rres;
        }

        return new ArrayList<>();
    }

    public static void printKLevelsDown(Node node, int k) {
        // write your code here
        if (node == null)
            return;

        if (k == 0) {
            System.out.println(node.data);
            return;
        }

        printKLevelsDown(node.left, k - 1);
        printKLevelsDown(node.right, k - 1);
    }

    public static ArrayList<Node> nodeToRoot(Node node, int data) {
        if (node == null)
            return new ArrayList<>();

        if (node.data == data) {
            ArrayList<Node> base = new ArrayList<>();
            base.add(node);
            return base;
        }

        ArrayList<Node> lres = nodeToRoot(node.left, data);
        if (lres.size() > 0) {
            lres.add(node);
            return lres;
        }

        ArrayList<Node> rres = nodeToRoot(node.right, data);
        if (rres.size() > 0) {
            rres.add(node);
            return rres;
        }

        return new ArrayList<>();
    }

    public static void printKNodesFar(Node root, int data, int k) {
        // write your code here
        ArrayList<Node> n2rp = nodeToRoot(root, data);

        Node blockage = null;
        for (int i = 0; i < n2rp.size() && k >= 0; i++) {
            Node node = n2rp.get(i);
            printkdown(node, blockage, k);
            k--;
            blockage = node;
        }
    }

    public static void printkdown(Node node, Node blockage, int k) {
        if (node == null || node == blockage)
            return;

        if (k == 0) {
            System.out.println(node.data);
            return;
        }

        printkdown(node.left, blockage, k - 1);
        printkdown(node.right, blockage, k - 1);
    }

    public static void pathToLeafFromRoot(Node node, String path, int sum, int lo, int hi) {
        // write your code here
        if (node == null)
            return;

        if (node.left != null && node.right != null) {
            pathToLeafFromRoot(node.left, path + node.data + " ", sum + node.data, lo, hi);
            pathToLeafFromRoot(node.right, path + node.data + " ", sum + node.data, lo, hi);
        }

        else if (node.left != null)
            pathToLeafFromRoot(node.left, path + node.data + " ", sum + node.data, lo, hi);

        else if (node.right != null)
            pathToLeafFromRoot(node.right, path + node.data + " ", sum + node.data, lo, hi);

        else {
            sum += node.data;
            path += node.data;
            if (lo <= sum && sum <= hi) {
                System.out.println(path);
            }
        }

    }

    public static Node createLeftCloneTree(Node node) {
        // write your code here
        if (node == null)
            return null;
        Node lcn = createLeftCloneTree(node.left);
        Node rcn = createLeftCloneTree(node.right);

        Node nn = new Node(node.data, lcn, null);
        node.left = nn;
        node.right = rcn;

        return node;
    }

    public static Node transBackFromLeftClonedTree(Node node) {
        // write your code here
        if (node == null)
            return null;
        Node lcn = transBackFromLeftClonedTree(node.left.left);
        Node rcn = transBackFromLeftClonedTree(node.right);

        node.left = lcn;
        node.right = rcn;

        return node;
    }

    public static void printSingleChildNodes(Node node, Node parent) {
        // write your code here
        if (node == null)
            return;

        if (parent != null && parent.left == node && parent.right == null)
            System.out.println(node.data);

        if (parent != null && parent.right == node && parent.left == null)
            System.out.println(node.data);

        printSingleChildNodes(node.left, node);
        printSingleChildNodes(node.right, node);

    }

    public static Node removeLeaves(Node node) {
        // write your code here
        if (node == null)
            return null;
        if (node.left == null && node.right == null) {
            node = null;
        }
        removeLeaves(node.left);
        removeLeaves(node.right);

        return node;
    }

    static int tilt = 0;

    public static int tilt(Node node) {
        // write your code here to set the tilt data member
        if (node == null)
            return 0;

        int lsum = tilt(node.left);

        int rsum = tilt(node.right);

        tilt += Math.abs(rsum - lsum);

        return lsum + rsum + node.data;

    }

    public static boolean isBST1(Node node)
    {
        //timme complexity->0(n*n)
        if(node ==null) return true;


        //self check
        int lmax=max(node.left);
        int rmin=min(node.right);
        if(lmax>node.data || rmin<node.data)
         return false;


        //left check and right check
        return isBST1(node.left) && isBST1(node.right);
    }

    public static class BSTPair{
        int min;
        int max;
        boolean isbst;
        int size;

        BSTPair()
        {
            min=Integer.MAX_VALUE;
            max=Integer.MIN_VALUE;
            isbst=true;
            size=0;
        }
    }

    public static BSTPair isBST2(Node node)
    {
        if(node==null)   
          return new BSTPair();
        BSTPair lres=isBST2(node.left);
        BSTPair rres=isBST2(node.right);

        boolean status=(lres.max<node.data && rres.min>node.data);
        BSTPair mres=new BSTPair();
        mres.min=Math.min(node.data, Math.min(lres.min, rres.min));
        mres.max=Math.max(node.data, Math.max(lres.max, rres.max));
        mres.isbst=lres.isbst && rres.isbst && status;
        return mres;

    }

    public static boolean isbalancedbst(Node node)
    {
        if(node==null)
         return true;

         int lch=height(node.left);
         int rch=height(node.right);

         int diff=lch-rch;
         if((diff==-1 || diff==0 || diff==1) && isbalancedbst(node.left) && isbalancedbst(node.right) )
          return true;

          return false;
    }

    public static class BPair
    {
        int ht;
        boolean isBalance;

        BPair()
        {
            this.ht=-1;
            this.isBalance=true;
        }


    }

    public static BPair isBalanced(Node node)
    {
        if(node==null)  return new BPair();

        BPair lres=isBalanced(node.left);
        BPair rres=isBalanced(node.right);

        BPair mres=new BPair();
        mres.ht=Math.max(lres.ht, rres.ht)+1;

        boolean factor=Math.abs(lres.ht-rres.ht)<=1;

        mres.isBalance= factor && lres.isBalance && rres.isBalance ;

        return mres;
    }

    static int sz=0;
    static Node bstNode=null;

    public static BSTPair largestBST(Node node)
    {
        if(node==null)
         return new BSTPair();

         BSTPair lres=largestBST(node.left);
         BSTPair rres=largestBST(node.right);

         boolean status=(lres.max<node.data && rres.min>node.data);
         BSTPair mres=new BSTPair();
         mres.min=Math.min(node.data, Math.min(lres.min, rres.min));
         mres.max=Math.max(node.data, Math.max(lres.max, rres.max));
         mres.isbst=lres.isbst && rres.isbst && status;
         mres.size=lres.size+rres.size+1;

         if(mres.isbst==true && mres.size>sz)
         {
            bstNode=node;
            sz=mres.size;

         }
         return mres;
    }

    public static void fun() {
        Integer[] arr = { 50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null,
                null };

        Node root = construct(arr);
        display(root);
        System.out.println("Size:" + size(root));
        System.out.println("Size:" + size1(root));
        System.out.println("Max:" + max(root));
        System.out.println("Max:" + max1(root));
        System.out.println("Height:" + height(root));
        System.out.println("Sum:" + sum(root));
        System.out.println("Sum:" + sum1(root));
        levelOrder(root);
        System.out.print("preorder:");
        preorder(root);
        System.out.print("inorder:");
        inorder(root);
        System.out.print("postorder:");
        postorder(root);
        pre = new ArrayList<>();
        post = new ArrayList<>();
        inord = new ArrayList<>();
        traversal(root);
        System.out.println("\n" + "Pre order:" + pre);
        System.out.println("In Order:" + inord);
        System.out.println("Post Order:" + post);
        System.out.println(isBST1(root));
        largestBST(root);
        System.out.println(bstNode.data + "@" + sz);
    }

    public static void main(String[] args) {
        fun();

    }

}

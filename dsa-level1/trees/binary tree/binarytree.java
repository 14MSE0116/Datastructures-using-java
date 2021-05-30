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
    
    public static int sum(Node node)
    {
        if(node==null)
         return 0;
        int lsum=sum(node.left);
        int rsum=sum(node.right);

        return lsum+rsum+node.data;
    }

    public static int sum1(Node node)
    {
        if(node==null)
        return 0;
        int sum=sum(node.left);
        sum+=sum(node.right);

        return sum+node.data;
    }

    public static int max(Node node) {
        // write your code here
        if(node==null)
         return Integer.MIN_VALUE;
        int lmax=max(node.left);
        int rmax=max(node.right);

        return Math.max(node.data, Math.max(lmax, rmax));
      }

      public static int max1(Node node)
      {
          if(node==null)
          return Integer.MIN_VALUE;

          int mx=max1(node.left);
          mx=Math.max(mx, max1(node.right));

          return Math.max(mx, node.data);
      }

      public static int height(Node node)
      {
          if(node==null)
           return -1;
          int lheight=height(node.left);
          int rheight=height(node.right);

          return Math.max(lheight, rheight)+1;
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
        System.out.print(root.data+" ");
        preorder(root.left);
        preorder(root.right);

    }

    public static void inorder(Node root) {
        if (root == null)
            return;
        inorder(root.left);
        System.out.print(root.data+" ");
        inorder(root.right);

    }

    public static void postorder(Node root) {
        if (root == null)
            return;
        postorder(root.left);
        postorder(root.right);

        System.out.print(root.data+" ");

    }

    static ArrayList<Integer>pre;
    static ArrayList<Integer>post;
    static ArrayList<Integer>inord;

    public static void traversal(Node root)
    {
        if(root==null)
         return;
        
         pre.add(root.data);
         traversal(root.left);
         inord.add(root.data);
         traversal(root.right);
         post.add(root.data);

    }




    public static void fun() {
        Integer[] arr = { 50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null,
                null };

        Node root = construct(arr);
        display(root);
        System.out.println("Size:" + size(root));
        System.out.println("Size:" + size1(root));
        System.out.println("Max:"+max(root));
        System.out.println("Max:"+max1(root));
        System.out.println("Height:"+height(root));
        System.out.println("Sum:"+sum(root));
        System.out.println("Sum:"+sum1(root));
        levelOrder(root);
        System.out.print("preorder:");
        preorder(root);
        System.out.print("inorder:");
        inorder(root);
        System.out.print("postorder:");
        postorder(root);
        pre=new ArrayList<>();
        post=new ArrayList<>();
        inord=new ArrayList<>();
        traversal(root);
        System.out.println("\n"+"Pre order:"+pre);
        System.out.println("In Order:"+inord);
        System.out.println("Post Order:"+post);
    }

    public static void main(String[] args) {
        fun();

    }

}

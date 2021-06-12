import java.io.*;
import java.util.ArrayList;
import java.util.Stack;




public class bst {

  public static class Node {
    int data;
    Node left;
    Node right;

    public Node(int data) {
      this.data = data;
      this.left = this.right = null;
    }

    public Node(int data, Node left, Node right) {
      this.data = data;
      this.left = left;
      this.right = right;
    }
  }

  public static Node construct(int arr[], int lo, int hi) {
    if (lo > hi)
      return null;
    int mid = lo + (hi - lo) / 2;

    Node nn = new Node(arr[mid]);

    nn.left = construct(arr, lo, mid - 1);
    nn.right = construct(arr, mid + 1, hi);

    return nn;

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

  public static int sum(Node node) {
    // write your code here
    if (node == null)
      return 0;

    int lsum = sum(node.left);
    int rsum = sum(node.right);

    return lsum + rsum + node.data;
  }

  public static int max(Node node) {
    // write your code here
    if (node == null)
      return Integer.MIN_VALUE;

    int rmax = max(node.right);

    return Math.max(node.data, rmax);
  }

  public static int min(Node node) {
    // write your code here
    if (node == null)
      return Integer.MAX_VALUE;

    int lmin = min(node.left);

    return Math.min(node.data, lmin);
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

  public static boolean find1(Node node, int data) {
    // write your code here
    if (node == null)
      return false;

    if (node.data == data)
      return true;

    else if (data < node.data)
      return find1(node.left, data);

    else if (data > node.data)
      return find1(node.right, data);

    return false;

  }

  public static Node add(Node node, int data) {
    // write your code here 
    if (node == null) {
      Node nn = new Node(data, null, null);
      return nn;
    }

    else if (data > node.data) {
      node.right = add(node.right, data);
    }

    else if (data < node.data) {
      node.left = add(node.left, data);
    }

    else {

    }

    return node;

  }

  static int sum = 0;

  public static void rwsol(Node node) {
    if (node == null)
      return;
    int data = node.data;
    rwsol(node.right);
    // area of work
    int dat = node.data;
    node.data = sum;
    sum += dat;
    rwsol(node.left);
  }

  public static int lca(Node node, int d1, int d2) {
    // write your code here

    if (node.data > d1 && node.data > d2)
      return lca(node.left, d1, d2);

    else if (node.data < d1 && node.data < d2)
      return lca(node.right, d1, d2);

    else {
      return node.data;
    }

  }

  public static void pir(Node node, int d1, int d2) {
    // write your code here
    if (node == null)
      return;
    if (d1 > node.data && d2 > node.data)
      pir(node.right, d1, d2);

    else if (d1 < node.data && d2 < node.data)
      pir(node.left, d1, d2);

    else {
      pir(node.left, d1, d2);
      System.out.println(node.data);
      pir(node.right, d1, d2);
    }
  }

  // target sum pair
  // method 1 time=o(nH) space=o(h) h->height
  public static void printTargetSumPair1(Node node, Node root, int tar) {
    if (node == null)
      return;
    int n1 = node.data;
    int n2 = tar - n1;

    printTargetSumPair1(node.left, root, tar);
    if (n1 < n2) {

      // should work in inorder
      if (find(root, n2) == true)
        System.out.println(n1 + " " + n2);
    }
    printTargetSumPair1(node.right, root, tar);

  }

  public static void inorderFiller(Node node, ArrayList<Integer> list) {
    if (node == null)
      return;
    inorderFiller(node.left, list);
    list.add(node.data);
    inorderFiller(node.right, list);

  }

  public static void printTargetSumPair2(Node node, Node root, int tar) {
    ArrayList<Integer> list = new ArrayList<>();
    inorderFiller(node, list);
    int l = 0;
    int r = list.size() - 1;

    while (l < r) {
      if (list.get(l) + list.get(r) > tar)
        r--;

      else if (list.get(l) + list.get(r) < tar)
        l++;

      else {
        System.out.println(list.get(r) + " " + list.get(r));
        l++;
        r--;
      }
    }

  }

  public static class Pair {
    Node node;
    int state;

    Pair() {
      this.node = null;
      this.state = 0;
    }

    Pair(Node node, int state) {
      this.node = node;
      this.state = state;
    }
  }

  public static int inorderItr(Stack<Pair> st) {
    while (st.size() > 0) {
      Pair p = st.peek();
      if (p.state == 0) {// pre
        if (p.node.left != null)
          st.push(new Pair(p.node.left, 0));

        p.state++;

      } else if (p.state == 1) {// inord
        if (p.node.right != null)
          st.push(new Pair(p.node.right, 0));

        p.state++;
        return p.node.data;
      } else {// post
        st.pop();
      }
    }
    return -1;

  }

  public static int revinorerIts(Stack<Pair> st) {
    while (st.size() > 0) {
      Pair p = st.peek();
      if (p.state == 0) {
        if (p.node.right != null)
          st.push(new Pair(p.node.right, 0));

        p.state++;

      } else if (p.state == 1) {
        if (p.node.left != null)
          st.push(new Pair(p.node.left, 0));

        p.state++;
        return p.node.data;
      } else {
        st.pop();
      }
    }
    return -1;

  }

  public static void printTargetSumPair3(Node node, int tar) {
    Stack<Pair> ls = new Stack<>();
    Stack<Pair> rs = new Stack<>();
    ls.push(new Pair(node, 0));
    rs.push(new Pair(node, 0));
    int l = inorderItr(ls);
    int r = revinorerIts(rs);
    while (l < r) {
      int sum = l + r;
      if (sum == tar) {
        System.out.println(l + " " + r);
        l = inorderItr(ls);
        r = revinorerIts(rs);
      } else if (sum < tar)
        l = inorderItr(ls);

      else
        r = revinorerIts(rs);
    }
  }

  public static Node remove(Node node, int data) {
    // write your code here
    if (data > node.data)
      node.left = remove(node.right, data);

    else if (data < node.data)
      node.right = remove(node.left, data);

    else {
      if (node.right != null && node.left != null) {
        int lmax = max(node.left);
        node.data = lmax;
        remove(node.left, lmax);
      } else if (node.left != null) {
        return node.left;

      } else if (node.right != null) {
        return node.right;

      } else {
        return null;
      }
    }

    return node;

  }

  public static void fun() {
    int[] data = { 10, 20, 30, 40, 50, 60, 70, 80, 90 };
    Node root = construct(data, 0, data.length - 1);
    display(root);

  }

  public static void main(String[] args) {
    fun();

  }

}

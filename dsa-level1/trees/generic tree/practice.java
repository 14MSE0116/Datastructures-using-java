import java.util.*;
import java.io.*;
import javax.lang.model.util.ElementScanner14;

public class practice {

    public static class Node {
        Integer data;
        ArrayList<Node> children;

        Node(Integer data) {
            this.data = data;
            this.children = new ArrayList<>();
        }

        Node() {
            this.data = 0;
            this.children = null;
        }
    }

    public static Node construct(Integer[] arr) {
        Node root = new Node();
        Stack<Node> st = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                Node nn = new Node(arr[i]);
                if (st.size() == 0) {

                    root = nn;
                    st.push(nn);
                } else {
                    st.peek().children.add(nn);
                    st.push(nn);
                }

            } else {
                st.pop();

            }
        }
        return root;

    }

    public static void display(Node root) {
        String str = root.data + "->";
        for (Node child : root.children) {
            str += child.data + " ";
        }
        System.out.println(str);

        for (Node child : root.children) {
            display(child);
        }

    }

    public static int size(Node node) {
        // write your code here
        int sz = 0;
        for (Node child : node.children) {
            sz += size(child);
        }
        return sz + 1;
    }

    public static int max(Node node) {
        // write your code here
        int mx = Integer.MIN_VALUE;
        for (Node child : node.children) {
            mx = Math.max(mx, max(child));
        }
        return Math.max(mx, node.data);
    }

    public static int height(Node node) {
        // write your code here
        int ht = -1;
        for (Node child : node.children) {
            ht = Math.max(ht, height(child));
        }
        return ht + 1;
    }

    public static void levelOrder(Node node) {
        // write your code here
        Queue<Node> qu = new ArrayDeque<>();
        qu.add(node);
        while (qu.size() > 0) {
            Node rem = qu.remove();
            System.out.print(rem.data + " ");
            for (Node child : rem.children)
                qu.add(child);
        }
    }

    public static void levelOrderLinewise(Node node) {
        // using two queues
        Queue<Node> mainq = new ArrayDeque<>();
        Queue<Node> childq = new ArrayDeque<>();
        mainq.add(node);
        while (mainq.size() > 0) {
            Node rem = mainq.remove();
            System.out.print(rem.data + " ");
            for (Node child : rem.children)
                childq.add(child);

            if (mainq.size() == 0) {
                System.out.println();
                Queue<Node> temp = mainq;
                mainq = childq;
                childq = temp;
            }
        }

    }

    public static void levelOrderLinewise1(Node node) {
        // using delimiter as null
        Queue<Node> mainq = new LinkedList<>();
        mainq.add(node);
        mainq.add(null);
        while (mainq.size() > 0) {
            Node rem = mainq.remove();
            if (rem == null) {
                System.out.println();
                if (mainq.size() > 0) {
                    mainq.add(null);
                }
            } else {
                System.out.print(rem.data + " ");
                for (Node child : rem.children)
                    mainq.add(child);
            }

        }

    }

    public static void levelOrderLinewise2(Node node) {
        // using size
        Queue<Node> qu = new ArrayDeque<>();
        qu.add(node);
        while (qu.size() > 0) {
            int sz = qu.size();
            while (sz-- > 0) {
                Node rem = qu.remove();
                System.out.print(rem.data + " ");
                for (Node child : rem.children)
                    qu.add(child);
            }
            System.out.println();
        }

    }

    public static void levelorderzigzag(Node node) {
        Stack<Node> mainq = new Stack<>();
        Stack<Node> childq = new Stack<>();
        int lvl = 1;
        mainq.add(node);
        while (mainq.size() > 0) {
            Node rem = mainq.pop();
            System.out.print(rem.data + " ");

            if (lvl % 2 == 1) {
                for (Node child : rem.children)
                    childq.add(child);

            } else {
                for (int i = rem.children.size() - 1; i >= 0; i--) {
                    Node child = rem.children.get(i);
                    childq.add(child);
                }
            }

            if (mainq.size() == 0) {
                System.out.println();
                Stack<Node> temp = mainq;
                mainq = childq;
                childq = temp;
                lvl++;
            }
        }
    }

    public static void mirror(Node node) {
        // write your code here

        for (Node child : node.children)
            mirror(child);

        int l = 0;
        int r = node.children.size() - 1;
        while (l < r) {
            Node temp = node.children.get(l);
            node.children.set(l, node.children.get(r));
            node.children.set(r, temp);
            l++;
            r--;
        }

    }

    public static void removeleaf(Node node) {
        for (int i = node.children.size() - 1; i >= 0; i--) {
            Node child = node.children.get(i);
            if (child.children.size() == 0)
                node.children.remove(child);
        }

        for (Node child : node.children)
            removeleaf(child);

    }

    public static Node gettail(Node node) {
        while (node.children.size() >= 1) {
            node = node.children.get(0);
        }
        return node;
    }

    public static void linearize(Node node) {
        // write your code here

        for (Node child : node.children)
            linearize(child);

        while (node.children.size() > 1) {
            Node l = node.children.remove(node.children.size() - 1);
            Node sl = node.children.get(node.children.size() - 1);
            Node slt = gettail(sl);
            slt.children.add(l);

        }
    }

    public static Node linearize1(Node node) {
        // write your code here

        if (node.children.size() == 0)
            return node;

        Node lt = linearize1(node.children.get(node.children.size() - 1));
        while (node.children.size() > 1) {
            Node l = node.children.remove(node.children.size() - 1);
            Node sl = node.children.get(node.children.size() - 1);
            Node slt = linearize1(sl);
            slt.children.add(l);
        }
        return lt;
    }

    public static boolean find(Node node, int data) {
        if (node.data == data)
            return true;

        for (Node child : node.children) {
            boolean rres = find(child, data);
            if (rres == true) {
                return true;
            }
        }
        return false;

    }

    public static ArrayList<Integer> nodeToRootPath(Node node, int data) {
        // write your code here

        if (node.data == data) {
            ArrayList<Integer> base = new ArrayList<>();
            base.add(data);
            return base;
        }

        for (Node child : node.children) {
            ArrayList<Integer> rres = nodeToRootPath(child, data);
            if (rres.size() != 0) {
                rres.add(node.data);
                return rres;
            }
        }

        return new ArrayList<>();
    }

    public static int lca(Node node,int d1,int d2)
    {
        ArrayList<Integer>n2rp1=nodeToRootPath(node, d1);
        ArrayList<Integer>n2rp2=nodeToRootPath(node, d2);

        int i=n2rp1.size()-1;
        int j=n2rp2.size()-1;
        int res=-1;
        while(i>=0 && j>=0 && n2rp1.get(i)==n2rp2.get(j))
        {
            res=n2rp1.get(i);
            i--;
            j--;

        }
        return res;
    }

    public static boolean areSimilar(Node n1, Node n2) {
        // write your code here
        if(n1.children.size()==n2.children.size())
         return false;

         for(int i=0;i<n1.children.size();i++)
         {
             Node child1=n1.children.get(i);
             Node child2=n2.children.get(i);
             boolean rres=areSimilar(child1, child2);
             if(rres==false)
              return false;
         }
         return true;
        
      }

    public static void fun() {
        Integer[] data = { 10, 20, 50, null, 60, null, null, 30, 70, null, 80, 110, null, 120, null, null, 90, null,
                null, 40, 100, null, null, null };
        Node root = construct(data);
        display(root);
        System.out.println("Size:" + size(root));
        System.out.println("Max:" + max(root));
        System.out.println("Height:" + height(root));
        levelOrder(root);
        System.out.println();
        levelOrderLinewise(root);
        System.out.println("Level order using delimiter");
        levelOrderLinewise1(root);
        System.out.println("Level order using size");
        levelOrderLinewise2(root);
        System.out.println("Level order zigzag");
        levelorderzigzag(root);

    }

    public static void main(String[] args) {
        fun();
    }
}

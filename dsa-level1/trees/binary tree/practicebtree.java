import java.util.Stack;

public class practicebtree {
    private static class Node {
        Integer data;
        Node left;
        Node right;

        Node() {
            this.data = 0;
            this.left = null;
            this.right = null;
        }

        public Node(Integer data) {
            this.data = data;
            this.left = this.right = null;
        }

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    private static class Pair {
        int state;
        Node node;

        Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
        

    }

    public static Node construct(Integer arr[]) {
        Stack<Pair>st=new Stack<>();
        Node root=new Node(arr[0]);
        st.push(new Pair(root,0));
        int idx=0;
        while(st.size()>0)
        {
            Pair p=st.peek();

            if(p.state==0)
            {
                idx++;
                if(arr[idx]!=null)
                {
                    Node nn=new Node(arr[idx]);
                    p.node.left=nn;
                    st.push(new Pair(nn, 0));

                }
                p.state++;
            }
            else if(p.state==1)
            {
                idx++;
                if(arr[idx]!=null)
                {
                    Node nn=new Node(arr[idx]);
                    p.node.right=nn;
                    st.push(new Pair(nn, 0));
                }
                p.state++;
            }
            else
            {
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

    public static void fun() {
        Integer[] arr = { 50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null,
                null };

        Node root = construct(arr);
        display(root);
    }

    public static void main(String[] args) {
        fun();
    }

}

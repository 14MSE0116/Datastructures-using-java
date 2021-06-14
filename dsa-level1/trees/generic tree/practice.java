/**
 * practice
 */
import java.util.*;
import java.io.*;
public class practice {
    public static class Node
    {
        int data;
        ArrayList<Node>children;

        Node()
        {
            this.data=0;
            this.children=new ArrayList<>();;
        }

        Node(int data)
        {
            this.data=data;
            this.children=new ArrayList<>();
        }
    }

    public static int size(Node root)
    {
        int sz=0;
        for(Node child:root.children)
        {
            sz+=size(child);
        }
        return sz+1;
    }

    public static Node construct(Integer arr[])
    {
        Node root=new Node();
       Stack<Node>st=new Stack<>();
       for(int i=0;i<arr.length;i++)
       {
           if(arr[i]!=null)
           {
               Node nn=new Node(arr[i]);
               if(st.size()==0)
               {
                    root=nn;
                    st.push(nn);
               }
               else
               {
                st.peek().children.add(nn);
                st.push(nn);
               }
           }
           else
           {
               st.pop();
           }
       }
       return root;

    }

    public static void display(Node root)
    {
        String str="["+root.data+"] ->";
        for(Node child : root.children)
        {
            str+=child.data+",";
        }
        System.out.println(str+" .");

        for(Node child:root.children)
          display(child);
    }

    static int size=0;
    static int max=Integer.MIN_VALUE;
    static int min=Integer.MAX_VALUE;
    static int height=-1;

    public static void multisolver(Node root,int depth)
    {
        size++;
        max=Math.max(max, root.data);
        min=Math.min(min, root.data);
        height=Math.max(height, depth);

        for(Node child:root.children)
         multisolver(child,depth+1);
    }

    public static void fun()
    {
        Integer[]data={10,20,50,null,60,null,null,30,70,null,80
            ,110,null,120,null,null,90,null,null,40,100,null,null,null} ;
            Node root=construct(data);
            display(root);
            multisolver(root, 0);
            System.out.println(max);
            System.out.println(min);
            System.out.println(height);
    }


    public static void main(String[] args) {
        fun();
    }
}
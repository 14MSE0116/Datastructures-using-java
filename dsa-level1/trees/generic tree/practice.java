import java.util.*;

import javax.lang.model.util.ElementScanner14;
public class practice {
    public static class Node
    {
        int data;
        ArrayList<Node>child;
        Node()
        {
            data=0;
            this.child=new ArrayList<>();
        }
        Node(int data)
        {
            this.data=data;
            this.child=new ArrayList<>();
        }

    }

    public static Node construct(Integer[]data)
    {
        Node root=new Node();
        Stack<Node>st=new Stack<>();
        for(int i=0;i<data.length;i++)
        {
            if(data[i]!=null)
            {
               Node n=new Node(data[i]);
               if(st.size()==0)
               {
                   st.push(n);
                   root=n;
               }
               else
               {
                   st.peek().child.add(n);
                   st.push(n);
               }

            }
            else
            {
               st.pop();

            }
        }
        return root;
    }

    public static void disp(Node root)
    {
        String str="["+root.data+"]"+"->";
        for(Node child:root.child)
        {
            str+=child.data+" ";
        }
        System.out.println(str);

        for(Node child:root.child)
        {
            disp(child);
        }

    }

    public static int size(Node root)
    {
        int sz=0;
        for(Node child:root.child)
        {
            sz+=size(child);
        }
        return sz+1;
    }

    public static int max(Node root)
    {
        int mx=Integer.MIN_VALUE;
        for(Node child:root.child)
        {
            mx=Math.max(mx, max(child));
        }
        return Math.max(mx, root.data);
    }

    public static int heightedge(Node root)
    {
        int ht=-1;
        for(Node child:root.child)
        {
            ht=Math.max(ht,heightedge(child));
        }
        return ht+1;
    }

    public static void traversals(Node root)
    {
        System.out.println("Node Pre "+root.data);

        for(Node child:root.child)
        {
            System.out.println("Edge Pre "+root.data+"--"+child.data);
            traversals(child);
            System.out.println("Edge Post "+root.data+"--"+child.data);

        }

        System.out.println("Node Post "+root.data);

    }

    public static void levelOrder(Node root)
    {
        Queue<Node>qu=new ArrayDeque<>();
        qu.add(root);
        while(qu.size()>0)
        {
            Node rem=qu.remove();
            System.out.print(rem.data+" ");

            for(Node child:rem.child)
             qu.add(child);
        }
    }

    public static void levelorderlinewise1(Node root)
    {
         Queue<Node>mainq=new ArrayDeque<>();
         Queue<Node>childq=new ArrayDeque<>();
         mainq.add(root);
         while(mainq.size()>0)
         {
             Node rem=mainq.remove();
             System.out.print(rem.data+" ");

             for(Node child:rem.child)
              childq.add(child);

             if(mainq.size()==0)
             {
                 System.out.println();
                 Queue<Node>temp=mainq;
                 mainq=childq;
                 childq=temp;
             }
         }

    }

    public static void levelorderlinewise2(Node root)
    {
        Queue<Node>qu=new LinkedList<>();
        qu.add(root);
        qu.add(null);
        while(qu.size()>0)
        {
            Node rem=qu.remove();
            if(rem==null)
            {
              System.out.println();
              if(qu.size()>0)
               qu.add(null);
            }
            else
            {
                System.out.print(rem.data+" ");
                for(Node child:rem.child)
                {
                    qu.add(child);
                }

            }
        }
    }

    public static void levelorderlinewise3(Node root)
    {
        Queue<Node>qu=new LinkedList<>();
        qu.add(root);
        while(qu.size()>0)
        {
            int sz=qu.size();
            while(sz-- > 0)
            {
                Node rem=qu.remove();
                System.out.print(rem.data+" ");
              
                for(Node child:rem.child)
                  qu.add(child);
            }
            System.out.println();
        }
    }

    public static void lvlorderzigzag(Node root)
    {
        Stack<Node>mains=new Stack<>();
        Stack<Node>childs=new Stack<>();
        mains.push(root);
        int lvl=1;
        while(mains.size()>0)
        {
            Node rem=mains.pop();
            System.out.print(rem.data+" ");
            if(lvl%2==1)
            {
                for(Node child:rem.child)
                 childs.push(child);
            }
            else
            {
                for(int i=rem.child.size()-1;i>=0;i--)
                {
                    Node child=rem.child.get(i);
                    childs.push(child);
                }
            }

            if(mains.size()==0)
            {
                Stack<Node>temp=mains;
                mains=childs;
                childs=temp;
                System.out.println();
                lvl++;
            }
        }
    }
 
    public static void fun()
    {
        Integer[]data={10,20,50,null,60,null,null,30,70,null,80
            ,110,null,120,null,null,90,null,null,40,100,null,null,null} ;
        Node root=construct(data);
        disp(root);
        System.out.println(size(root));
        System.out.println(max(root));
        System.out.println(heightedge(root));
        // traversals(root);
        // levelOrder(root);
        // levelorderlinewise1(root);
        // levelorderlinewise2(root);
        // levelorderlinewise3(root);
        lvlorderzigzag(root);
    }

    public static void main(String[] args) {
        fun();
    }
}

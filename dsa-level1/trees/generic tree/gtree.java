import java.util.*;
/**
 * gtree
 */
import java.io.*;
public class gtree {

    
    static List<Integer>res=new ArrayList<>();

    public static class Node
    {
      int data;
      ArrayList<Node>children;

      public Node()
      {
         this.data=0;
         this.children=new ArrayList<>();
      }

      public Node(int data)
      {
         this.data=data;
         this.children=new ArrayList<>();   
      }
    }

    public static Node construct(Integer[]arr)
    {
        Node root=new Node(); 
        Stack<Node>st=new Stack<>();
        for(int i=0;i<arr.length;i++)
        {
            Integer data=arr[i];
            if(data!=null)
            {
                Node nn=new Node(data);
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
            st.pop();
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

        for(int i=0;i<root.children.size();i++)
        {
           Node child=root.children.get(i);
           display(child);
          

        }
    }

    public static int size(Node node)
    {
        int sz=0;
        for(Node child : node.children)
        {
            sz+=size(child);
        }
        return sz+1;
    }

    public static int max(Node root)
    {
        int mx=Integer.MIN_VALUE;
        for(Node child:root.children)
        {
            mx=Math.max(mx, max(child));
        }
        return Math.max(mx,root.data);

    }

    public static int height(Node node)
    {
        int ht=-1;
        for(Node child:node.children)
        {
            ht=Math.max(ht, height(child));
        }
        return ht+1;
    }

    public static void traversals(Node node){
        // write your code here
        //node pre
        System.out.println("Node Pre "+node.data);
        for(Node child:node.children)
        {
            //before call edge pre
            System.out.println("Edge Pre "+node.data+"--"+child.data);

            traversals(child);

            //after call edge post
            System.out.println("Edge Post "+node.data+"--"+child.data);
        }

        //node post
        System.out.println("Node Post "+node.data);
      }

      public static List<Integer> preorder(Node node) {
        if(node==null)
        return res;
        preorderhelper(node);
        return res;
        
    }
   public static void preorderhelper(Node node)
   {
       res.add(node.data);
       for(Node child:node.children)
       preorderhelper(child);
   }

      public static void levelOrder(Node node){
        // write your code here
        Queue<Node> q=new  ArrayDeque<>();
        q.add(node);
        
        

        while(q.size()>0)
        {
            Node ch=q.remove();
            System.out.print(ch.data+" ");

            for(Node child : ch.children)
             q.add(child);
           
        }
        System.out.print(".");
        
      }

      public static void levelOrderlinewise1(Node node){
        // write your code here
        Queue<Node> mainq=new  ArrayDeque<>();
        Queue<Node> childq=new  ArrayDeque<>();
       
        mainq.add(node);
        while(mainq.size()>0)
        {
            Node rem=mainq.remove();
            System.out.print(rem.data+" ");
            
            //add chilld
            for(Node child:rem.children)
             childq.add(child);

             if(mainq.size()==0)
             {   //hit enter and swap queues
                 System.out.println();
                 Queue<Node>temp=mainq;
                 mainq=childq;
                 childq=temp;
             }

        }
        
        
      }
      

      public static void levelOrderlinewise2(Node node){

        //using linked list as queue beacuse it doesnt allow us to add null
        Queue<Node>qu=new LinkedList<>();
        qu.add(node);
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
                for(Node child:rem.children)
                {
                    qu.add(child);
                }

            }
        }

      }

      public static void levelorder3(Node node)
      {
        Queue<Node>qu=new LinkedList<>();
        qu.add(node);
        while(qu.size()>0)
        {
            int sz=qu.size();
            while(sz-- > 0)
            {
                //get+remove;
                Node rem=qu.remove();

                //print
                System.out.print(rem.data+" ");

                //add children
                for(Node child:rem.children)
                 qu.add(child);

            }
            System.out.println();
        }
      }

    public static void levelorderzigzag(Node node)
    {
        Stack<Node>mains=new Stack<>();
        Stack<Node>childs=new Stack<>();
        mains.push(node);
        int lvl=1;
        while(mains.size()>0)
        {
            Node rem=mains.pop();
            System.out.print(rem.data+" ");

            if(lvl%2==1)
            {
              for(Node child:rem.children)
              {
                  childs.push(child);
              }
            }
            else
            {
              for(int i=rem.children.size()-1;i>=0;i--)
              {
                  Node child=rem.children.get(i);
                  childs.push(child);
              }
            }
            

            
            if(mains.size()==0)
            {
                lvl++;
                System.out.println();
                Stack<Node>temp=mains;
                mains=childs;
                childs=temp;
            }

        }
        
    }

    public static void removeLeaves(Node node) {
        // preorder removal of leaf

        for(int i=node.children.size()-1;i>=0;i--)
        {
            Node child=node.children.get(i);
            if(child.children.size()==0)
            {
                node.children.remove(child);
            }
        }

        //then check for its chidren

        for(Node child:node.children)
        {
            removeLeaves(child);
        }
      }

      public static void linearize(Node node){
        // write your code here

        for(Node child:node.children)
          {
            linearize(child);
          }

         while(node.children.size()>1)
         {
             Node last=node.children.remove(node.children.size()-1);
             Node sl=node.children.get(node.children.size()-1);

             Node slt=gettail(sl);
             slt.children.add(last);
         }

      }

      private static Node gettail(Node node)
      {
          while(node.children.size()>=1)
          {
              node=node.children.get(0);
          }
          return node;
      }

      public static boolean find(Node node, int data) {
        // write your code here
        //check in pre-area;
        if(node.data==data)
         return true;
        boolean res=false;
         for(Node child:node.children)
          {
            res=find(child,data);

            if(res==true)
             return true;
          }

          return res;


      }

      public static ArrayList<Integer> nodeToRootPath(Node node, int data){
        // write your code here
        
        if(node.data==data)
          {
            ArrayList<Integer>base=new ArrayList<>();
            base.add(node.data);
            return base;
          }
         
         for(Node child:node.children)
          {
              ArrayList<Integer>rres=nodeToRootPath(child,data);
              if(rres.size()!=0)
               {
                   rres.add(node.data);
                   return rres;
               }
              
          }
          return new ArrayList<>();
     }

     public static ArrayList<Integer> nodeToRootPath1(Node node, int data){
        // write your code here
        
        if(node.data==data)
          {
            ArrayList<Integer>base=new ArrayList<>();
            base.add(node.data);
            return base;
          }
         
         for(Node child:node.children)
          {
              ArrayList<Integer>rres=nodeToRootPath(child,data);
              if(rres!=null)
               {
                   rres.add(node.data);
                   return rres;
               }
              
          }
          return null;
     }

      public static boolean find1(Node node, int data) {
        // write your code here
        //check in pre-area
        if(node.data==data)
         return true;
        boolean res=false;
         for(Node child:node.children)
          {
            res=res||find(child,data);
          }

          return res;


      }

      public static int lca(Node node, int d1, int d2) {
        // write your code here
        //find node to root path of d1 and d2
        ArrayList<Integer>n2rp1=nodeToRootPath(node,d1);
        ArrayList<Integer>n2rp2=nodeToRootPath(node,d2);

       int i=n2rp1.size()-1;
       int j=n2rp2.size()-1;
       int res=-1;
       while(i>=0 && j>=0 && n2rp1.get(i)==n2rp2.get(j) )
       {
              res=n2rp1.get(i);
              i--;
              j--;
       }
       return res;


      }

      public static int distanceBetweenNodes(Node node, int d1, int d2){
        // write your code here
    
        ArrayList<Integer>n2rp1=nodeToRootPath(node, d1);
        ArrayList<Integer>n2rp2=nodeToRootPath(node, d2);

        int i=n2rp1.size()-1;
        int j=n2rp2.size()-1;
        while(i>=0 && j>=0 && n2rp1.get(i) == n2rp2.get(j))
        {
            i--;
            j--;
        }
        return i+j+2;
      }

    
    
    public static void fun()
    {
        Integer[]data={10,20,50,null,60,null,null,30,70,null,80
         ,110,null,120,null,null,90,null,null,40,100,null,null,null} ;
         Node root=construct(data);
         traversals(root);
         List<Integer>res=preorder(root);
         System.out.println(res);

        //  display(root);
        //  levelorder3(root);
        // levelorderzigzag(root);
    }

    public static boolean areSimilar(Node n1, Node n2) {
        // write your code here
        if(n1.children.size()!=n2.children.size())
        {
            return false;
        }
       boolean res=true;
       for(int i=0;i<n1.children.size();i++)
       {
           Node child1=n1.children.get(i);
           Node child2=n2.children.get(i);
           
           res=areSimilar(child1, child2);
           if(res==false)  
            return false;
       }
       return res;

      }

      public static boolean areMirror(Node n1, Node n2) {
        // write your code here
        if(n1.children.size()!=n2.children.size())
         return false;

        boolean res=true;
        int sz=n1.children.size();
        for(int i=0;i<sz;i++)
        {
          Node child1=n1.children.get(i);
          Node child2=n2.children.get(sz-i-1);
          res=areMirror(child1,child2);
          if(res==false)
           {
               return false;
           }
        }
        return res;
      }
        

    public static void main(String[] args) {
        fun();
    }
    
}
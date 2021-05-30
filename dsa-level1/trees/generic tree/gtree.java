import java.util.*;

import javax.swing.CellEditor;

import org.graalvm.compiler.graph.SuccessorEdges;

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
        //  traversals(root);
        //  List<Integer>res=preorder(root);
        //  System.out.println(res);

        //  display(root);
        //  levelorder3(root);
        // levelorderzigzag(root);

        // mutlisolution(root);
        // subtreesum(root);
        IterativePreandPostOrder(root);
        printsubseq("abc");
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
        //for checking symmetric u can just travel only upto n/2
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

      public static boolean IsSymmetric(Node node) {
        // write your code here
         return areMirror(node,node);
      }

     //mutilsolver1 using global variable
     static int min=Integer.MAX_VALUE;
     static int max=Integer.MIN_VALUE;
     static int ht=0;
     static int size=0;

     public static void multisolver1(Node node,int depth)
     {
         //pre-order
         min=Math.min(min, node.data);
         max=Math.max(max, node.data);
         ht=Math.max(ht, depth);
         size++;

         for(Node child:node.children)
         {
            multisolver1(child,depth+1);
         }
     }

     //mutisolver 2 using return type

     public static class multisolver
     {
         int min;
         int max;
         int ht;
         int sz;

         multisolver(int min,int max,int ht,int sz)
         {
             this.min=min;
             this.max=max;
             this.ht=ht;
             this.sz=sz;
         }

         multisolver()
         {
             this.min=Integer.MAX_VALUE;
             this.max=Integer.MIN_VALUE;
             this.ht=-1;
             this.sz=0;
         }
     }

     public static multisolver mutilsolver2(Node node)
     {

        multisolver mres=new multisolver(node.data,node.data,-1,1);
        for(Node child:node.children)
        {
            multisolver rres=mutilsolver2(child);
            mres.min=Math.min(mres.min,rres.min);
            mres.max=Math.max(mres.max, rres.max);
            mres.ht=Math.max(mres.ht, rres.ht);
            mres.sz+=rres.sz;
        }

        mres.ht+=1;
        return mres;

     }
     

     public static void mutlisolution(Node root)
     {

        // multisolver1(root,0);

        // System.out.println("Minimum valu:"+min);
        // System.out.println("Max valu:"+max);
        // System.out.println("height:"+ht);
        // System.out.println("size:"+size);

        multisolver res= mutilsolver2(root);

        System.out.println("Minimum valu:"+res.min);
        System.out.println("Max valu:"+res.max);
        System.out.println("height:"+res.ht);
        System.out.println("size:"+res.sz);
        
         
     }

     static Node predecessor;
     static Node successor;
     static int state=0;
     public static void predecessorAndSuccessor(Node node, int data) {
       // write your code here
       if(state==0)
       {
           if(node.data==data)
            state++;
            else
            predecessor=node;

       }
       else if(state==1)
       {
           successor=node;
           state++;
           return;

       }
      
       for(Node child:node.children)
       {
           if(state!=2)
        predecessorAndSuccessor(child,data);

       }
       
     }

     static int ceil=Integer.MAX_VALUE;    //qualified min;
     static int floor=Integer.MIN_VALUE;  //qualified max;
     public static void ceilAndFloor(Node node, int data) {
       // Write your code here
      if(node.data>data)
      {
          //ceil
          if(node.data<ceil)
            ceil=node.data;

      }
      else if(node.data<data)
      {
          //floor
          if(node.data>floor)
            floor=node.data;
      }

      for(Node child : node.children)
      ceilAndFloor(child,data);

       
     }

     public static int kthLargest(Node node, int k){
        // write your code here
        int data=Integer.MAX_VALUE;
        for(int i=0;i<k;i++)
        {
           
            floor=Integer.MIN_VALUE;
            ceilAndFloor(node, data);
            data=floor;
        }
        return data;
      }

      static int maxsum=Integer.MIN_VALUE;
      static Node maxnode=null;
      public static int treesum(Node node)
      {
          int sum=0;
          for(Node child:node.children)
          {
             sum+=treesum(child);

          }
          sum+=node.data;
          if(sum>maxsum)
          {
              maxsum=sum;
              maxnode=node;
          }
          return sum;
      }

      public static int subtreesum(Node node)
      {
          int sum=0;
          for(Node child:node.children)
          {
              sum+=subtreesum(child);
          }
          sum+=node.data;

            System.out.println(node.data+"->"+sum);
            return sum;
      }

      public static int diameter1(Node node)
      {
        int mh=-1;
        int smh=-1;

        //finding height from child
        for(Node child:node.children)
        {
            int ht=height(child);
            if(ht>=mh)
            {
                smh=mh;
                mh=ht;
            }
            else if(ht>smh)
            {
                smh=ht;
            }
        }
     
        int dfc=0;  //diameter from child
        for(Node child:node.children)
        {
            dfc=Math.max(dfc,diameter1(child));
        }
        return Math.max(dfc, mh+smh+2);

      }
      public static int diameter=0;

      public static int heightfordiameter(Node node)
      {
          int mht=-1;   //max height
          int smht=-1;  //second max height

        for(Node child:node.children)
        {
            int ht=heightfordiameter(child);
            if(ht>=mht)
            {
                smht=mht;
                mht=ht;
            }
            else if(ht>smht)
             {
                 smht=ht;
             }

        }

        diameter=Math.max(diameter, mht+smht+2);


        //for height
          return mht+1;
      }


      public static class Pair
      {
          Node node;
          int state;

          Pair(Node node,int state)
          {
              this.node=node;
              this.state=state;
          }
      }
      public static void IterativePreandPostOrder(Node node) {
        // write your code here
        Stack<Pair>st=new Stack<>();
        String pre="";
        String post="";
        st.push(new Pair(node,0));
        while(st.size()>0)
        {
          Pair p=st.peek();

          //pre-area
          if(p.state==0)
          {
              pre+=p.node.data+" ";
              p.state++;

          }

          //in-area
          else if(p.state<=p.node.children.size())
          {
              Node child=p.node.children.get(p.state-1);
              p.state++;
              st.push(new Pair(child,0));

          }

          //post-area
          else if(p.state>p.node.children.size())
          {
              post+=p.node.data+" ";
              st.pop();
              
          }
        }
        System.out.println(pre);
        System.out.println(post);
      }

      public static class SSPair
      {
          String ques;
          int state;
          String ans;

          SSPair(String ques,int state,String ans)
          {
              this.ques=ques;
              this.state=state;
              this.ans=ans;
          }
      }

      public static void printsubseq(String str)
      {
          Stack<SSPair> st=new Stack<>();
          st.push(new SSPair(str, 0, ""));
          String res="";
          while(st.size()>0)
          {
              SSPair p=st.peek();
           
              if(p.ques.length()==0)
              {
                  res+=p.ans+" ";
                //   System.out.println(p.ans);
                  st.pop();
                  continue;
              }

              String roq=p.ques.substring(1) ;
              char ch=p.ques.charAt(0);

           if(p.state==0)
            {
                p.state++;
                st.push(new SSPair(roq, 0, p.ans+ch+" "));               


            }
            else if(p.state==1)
            {
                p.state++;
                st.push(new SSPair(roq, 0, p.ans+"-"));   

            }
            else
            {
                st.pop();

            }

          }
          System.out.println(res);
      }


        

    public static void main(String[] args) {
        fun();
    }
    
}
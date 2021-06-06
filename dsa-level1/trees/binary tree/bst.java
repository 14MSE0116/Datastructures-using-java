import java.io.*;
public class bst {

    public static class Node
    {
        int data;
        Node left;
        Node right;

        public Node(int data)
        {
            this.data=data;
            this.left=this.right=null;
        }

        public Node(int data,Node left,Node right)
        {
            this.data=data;
            this.left=left;
            this.right=right;
        }
    }

    public static Node construct(int arr[],int lo,int hi)
    {
        if(lo>hi)
         return null;
        int mid=lo+ (hi-lo)/2 ;
        
        Node nn=new Node(arr[mid]);

        nn.left=construct(arr, lo, mid-1);
        nn.right=construct(arr, mid+1, hi);

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
        if(node==null)
         return 0;
        int lsize=size(node.left);
        int rsize=size(node.right);

        return lsize+rsize+1;
      }

      public static int sum(Node node) {
        // write your code here
        if(node==null)
        return 0;

        int lsum=sum(node.left);
        int rsum=sum(node.right);

        return lsum+rsum+node.data;
      }

      public static int max(Node node) {
        // write your code here
        if(node==null)
         return Integer.MIN_VALUE;

         int rmax=max(node.right);

         return Math.max(node.data, rmax);
      }

      public static int min(Node node) {
        // write your code here
        if(node==null)
         return Integer.MAX_VALUE;

         int lmin=min(node.left);

         return Math.min(node.data,lmin);
      }

      public static boolean find(Node node, int data){
        // write your code here
        if(node==null)
         return false;
        if(node.data==data)
         return true;
        
         boolean lres=find(node.left,data);
         if(lres==true)
          return true;
         boolean rres=find(node.right,data);
         if(rres==true)
          return true;

          return false;       


      }  

      public static boolean find1(Node node, int data){
        // write your code here
        if(node==null)
         return false;

        if(node.data==data)
         return true;
        
         else if(data<node.data)
           return find1(node.left,data);
         
        else if(data>node.data)
            return find1(node.right,data);

          return false;       


      } 
      
      public static Node add(Node node, int data) {
        // write your code here
        if(node==null)
        {
            Node nn=new Node(data,null,null);
            return nn;
        }

        else if(data>node.data)
          {
              node.right=add(node.right,data);
          }

          else if(data<node.data)
          {
            node.left=add(node.left,data);
          }

          else
          {
              
          }

          return node;


      }



    public static void fun()
    {
        int[]data={10,20,30,40,50,60,70,80,90} ;
        Node root=construct(data,0,data.length-1);
        display(root);
        
    }

    public static void main(String[] args) {
        fun();
        
    }
    
}

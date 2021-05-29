import javax.lang.model.util.ElementScanner14;

import jdk.internal.net.http.ResponseSubscribers.NullSubscriber;

public class linkedlist {

    private class Node
    {
        private int data;
        private Node next;

        public Node()
        {
            this.data=0;
            this.next=null;
        }

        public Node(int data)
        {
            this.data=data;
            this.next=null;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public linkedlist()
    {
        this.head=null;
        this.tail=null;
        this.size=0;
    }

    public int size()
    {
        return this.size;
    }
    void handlewhensize0(int val)
    {
        Node nn=new Node(val);
        this.head=nn;
        this.tail=nn;
        this.size++;
    }

    void addFirst(int val)
    {
        if(size()==0)
        {
            handlewhensize0(val);
        }
        else
        {
            Node nn=new Node(10);
            nn.next=this.head;
            this.head=nn;
            this.size++;
        }
    }

     void addLast(int val)
     {
         if(size()==0)
         {
            handlewhensize0(val);
         }
         else
         {
             Node nn=new Node(val);
            this.tail.next=nn;
            this.tail=nn;
            this.size++;

         }

     }

    private Node getnthnode(int pos)
     {
         Node temp=this.head;
         for(int i=0;i<pos;i++)
         {
             temp=temp.next;
         }
         return temp;
     }

     public void voidAt(int val,int idx)
     {
         if(idx<0 || idx>this.size)
          System.out.println("Invalid position");

          else if(idx==0)
           addFirst(val);

          else if(idx==this.size)
          addLast(val);
          
         else
         {
             Node nm1=getnthnode(idx-1);
             Node nn=new Node(val);
             nn.next=nm1.next;
             nm1.next=nn;
             this.size++;
         }
     }

     public int getFirst()
     {
         if(this.size==0)
         return-1;

         return this.head.data;
     }
     public int getLast()
     {
         if(this.size==0)
         return -1;

         return this.tail.data;
     }
     public int getlastwotail()
     {
        if(this.size==0)
        return -1;

        Node temp=head;
        while(temp.next!=null)
        {
            temp=temp.next;
        }
        return temp.data;
     }

     public int getAt(int idx)
     {
         if(idx<0 || idx>=this.size)
           return -1;

        Node n=getnthnode(idx);
        return n.data;
     }

     public int handleremovewhensize1()
     {
         int val=this.head.data;
         this.head=null;
         this.tail=null;
         this.size--;
         return val;
     }

     public int removeFirst()
     {
         if(this.size==0)
          return -1;
          else if(this.size==1)
           handleremovewhensize1();
         
             int val=this.head.data;
             this.head=this.head.next;
             this.size--;
             return val;
    
     }

     public int removeLast()
     {
        if(this.size==0)
        return -1;
        else if(this.size==1)
         handleremovewhensize1();
       
           int val=this.tail.data;
           Node nm1=getnthnode(this.size-2);
           nm1.next=null;           
           this.tail=nm1;
           this.size--;
           return val;         

     }

     public int removeAt(int idx)
     {
         if(idx<0 || idx>=this.size)
         return -1;
         else if(idx==1)
          removeFirst();
         else if(idx==this.size-1)
          removeLast();
          
          Node nm1=getnthnode(idx-1);
          int val=nm1.next.data;
          nm1.next=nm1.next.next;
          this.size--;
          return val;
     }

     public void reversedi()
     {
         int left=0;
         int right=this.size-1;
         while(left<right)
         {
             Node lnode=getnthnode(left);
             Node rnode=getnthnode(right);
             int temp=lnode.data;
             lnode.data=rnode.data;
             rnode.data=temp;
             left++;
             right--;
         }
     }

     void display()
     {
         Node temp=this.head;
         while(temp!=null)
         {
             System.out.print(temp.data+"->");
             temp=temp.next;
         }
         System.out.println();
     }

     public static void main(String[] args) {
        linkedlist list=new linkedlist();
        list.addLast(20);
        list.addLast(30);
        list.addLast(40);
        list.addFirst(10);
        list.voidAt(60,2);
        list.display();
        System.out.println(list.getFirst());
        System.out.println(list.getLast());
        System.out.println((list.getlastwotail()));
        System.out.println(list.getAt(4));
        System.out.println("First elemtn is:"+list.removeFirst());
        list.display();
        System.out.println("Last element is:"+list.removeLast());
        list.display();
        list.reversedi();
        list.display();

     }


    
}
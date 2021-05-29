/**
 * create
 */
import java.util.*;
class linkedlist
{
    private class Node
    {
        private int data;
        private Node next;

        Node()
        {
            data=0;
            next=null;
        }

        Node(int data)
        {
            this.data=data;
            this.next=null;
        }

        Node(int data,Node next)
        {
            this.data=data;
            this.next=next;
        }
    }
    private Node head;
    private Node tail;
    private int size;

    public linkedlist()
    {
        this.head=this.tail=null;
        this.size=0;
    }
    private void handleaddwhensize0(int val)
    {
        Node nn=new Node(val);
        this.head=this.tail=nn;
        this.size++;
    }

    public void addFirst(int val)
    {
       if(this.size==0)
         handleaddwhensize0(val);

        else
        {
            Node nn=new Node(val);
            nn.next = this.head;
            this.head=nn;
            this.size++;
        } 

    }

    public void addLast(int val)
    {
        if(this.size==0)
        {
            handleaddwhensize0(val);
        }
        else{
       Node nn=new Node(val);
       this.tail.next=nn;
       this.tail=nn;
       this.size++;

        }


    }

    public void addAt(int val,int idx)
    {
      if(idx<0 || idx>this.size)
      {
          return;
      }
      else if(idx==0)
       addFirst(val);

       else if(idx==this.size)
        addLast(val);
      
       else{
           Node nm1=getNthNode(idx-1);
           Node nn=new Node(val);
           nn.next=nm1.next;
           nm1.next=nn;
       }
    }
    
    public Node getNthNode(int pos)
    {
        Node temp=this.head;

        while(pos>0)
        {
            temp=temp.next;
            pos--;
        }
        return temp;
    }

    public int getFirst()
    {
       if(this.size==0)
       {
           return -1;
       }
       return this.head.data;
    }

    public int getLast()
    {
        if(this.size==0)
        {
            return -1;
        }
        return this.tail.data;

    }

    public int getAt(int idx)
    {
        if(idx<0 || idx>=this.size)
         return -1;

         Node temp=getNthNode(idx);
         return temp.data;

    }
    public int removeFirst()
    {
        if(this.size==0)
         return -1;

         else if(this.size==1)
         {
             return handremovewhensize1();
              
         }

         int val=head.data;
         head=head.next;
         this.size--;
         return val;
    }
    private int handremovewhensize1()
    {
        int val=this.head.data;
        this.head=this.tail=null;
        this.size=0;
        return val;
    }
    public int removeLast()
    {
        if(this.size==0)
        return -1;

        if(this.size==1)
           handremovewhensize1();

         
             Node nm1=getNthNode(this.size-2);
             int val=this.tail.data;

             nm1.next=null;
             this.tail=nm1;
             this.size--;

             return val;
         

    }

    public int removeAt(int idx)
    {
        if(idx<0 || idx>=this.size)
         return -1;

         else if(idx==0)
          return removeFirst();

          else if(idx==this.size-1)
           return removeLast();
          
           Node nm1=getNthNode(idx-1);
           int val=nm1.next.data;
           nm1.next=nm1.next.next;
           this.size--;
           return val;
    }

    public void reverseDI() {
        // write your code here
        int left=0;
        int right=this.size-1;
        while(left<right)
        {
            Node lNode = getNthNode(left);
            Node rNode = getNthNode(right);

            int temp=lNode.data;
            lNode.data=rNode.data;
            rNode.data=temp;

            left++;
            right--;

        }

      }

      public void reversePI(){
        // write your code here
        Node prev=null;
        Node curr=this.head;
        while(curr!=null)
        {
            Node next=curr.next;
            //change the pointer;
            curr.next=prev;
            prev=curr;
            curr=next;
        }

        //swap head and tail to manage 
        Node temp=head;
        head=tail;
        tail=temp;
      }

    public int size()
    {
       return this.size;
    }
    public void display()
    {
       Node temp=this.head;
       while(temp!=null)
       {
           System.out.print(temp.data+"->");
           temp=temp.next;
       }
       System.out.print("null");

       System.out.println();


    }
    
    
}
public class create {

    public static void demo()
    {
        linkedlist list = new linkedlist();
        list.addLast(10);
        list.addLast(20);
        list.display();
        list.addLast(30);
        list.addFirst(9);
        list.display();
        list.addFirst(7);
        list.addLast(40);
        list.display();
        System.out.println(list.size());

        System.out.println(list.removeFirst());
        list.addAt(40, 2);
        list.display();

        System.out.println(list.removeAt(3));
        System.out.println(list.getAt(3));
        list.addLast(90);
        list.addLast(85);
        list.addLast(40);
        list.addLast(70);
        list.addLast(60);

        list.display();

        System.out.println(list.size());
        list.removeFirst();
        list.removeFirst();
        list.removeFirst();
        list.removeAt(3);
        list.display();

        list.removeLast();
        list.removeLast();
        list.display();

        
    }

    public static void main(String[] args) {
        demo();
    }

    
}
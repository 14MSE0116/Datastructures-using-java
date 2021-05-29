import java.io.*;
import java.util.*;
public class prac {

 private class Node{
     int data;
     Node next;

     Node()
     {
         data=0;
         next=null;
     }

     Node(int data)
     {
         this.data=data;
     }
 }

 private Node head;
 private Node tail;
 private int size;

 public int size()
 {
     return this.size;
 }

 private void handlewhensize0(int val)
 {
     Node nn=new Node(val);
     this.head=nn;
     this.tail=nn;
     this.size++;
 }

  public void addfirst(int val)
 {
     if(this.size==0)
      handlewhensize0(val);

      else
      {
          Node nn=new Node(val);
          nn.next=this.head;
          this.head=nn;
          this.size++;

      }
     
 }

 public void addLast(int val)
 {
     if(size()==0)
     handlewhensize0(val);

     else{
         Node nn=new Node(val);
         Node tail=this.head;
         while(tail.next!=null)
          tail=tail.next;

          tail.next=nn;
          this.tail=nn;
          this.size++;
     }
 }

 private Node getnthnode(int pos)
 {
     Node temp=this.head;
     while(pos-->0)
     {
         temp=temp.next;
     }
     return temp;
 }

 public void addAt(int val,int idx)
 {
     if(idx<0 || idx>this.size)
      System.out.println("invalid");

      else if(idx==0)
       addfirst(val);

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
     if(size()==0)
     {
      System.out.print("invalid");
      return -1;
     }

     else
     {
         return this.head.data;
     }
 }

 public int getLast()
 {
     if(size()==0)
      return -1;

      return this.tail.data;
 }

 public int getAt(int idx)
 {
     if(idx==0 || idx>=this.size)
      {
          System.out.println("invlaid");
          return -1;
      }

      Node temp=getnthnode(idx);
      return temp.data;
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
     if(this.size==1)
     {
        handleremovewhensize1();
     }

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
     if(this.size==0)
     return -1;

     else if(idx<0 || idx>=this.size)
      return -1;

      else if(idx==0)
      removeFirst();

      else if(idx==this.size-1)
      removeLast();

      Node nm1=getnthnode(idx-1);
      int val=nm1.next.data;
      nm1.next=nm1.next.next;
      this.size--;

      return val;
 }

 public static prac merge(prac l1,prac l2)
 {
     
    prac res=new prac();
    while(l1.size>0 && l2.size>0)
    {
        if(l1.getFirst()<l2.getFirst())
        {
            res.addLast(l1.removeFirst());
        }
        else
        {
            res.addLast(l2.removeFirst());
        }
    }
    while(l1.size>0)
    {
        res.addLast(l1.removeFirst());
    }
    while(l2.size>0)
    {
        res.addLast(l2.removeFirst());
    }

    return res;
     
 }

 private Node midformergesort(Node head,Node tail)
 {
   Node fast=head;
   Node slow=head;

   while(fast!=null && fast.next!=null)
   {
       slow=slow.next;
       fast=fast.next.next;
   }
   return slow;
 }

 public  prac mergetwosortedlinkedlist(prac l1,prac l2)
 {
     Node dummy=new Node(-1);
     Node temp=dummy;

     Node t1=l1.head;
     Node t2=l2.head;

     while(t1!=null && t2!=null)
     {
         if(t1.data<t2.data)
         {
             temp.next=t1;
             temp=temp.next;
             t1=t1.next;
         }
         else
         {
             temp.next=t2;
             temp=temp.next;
             t2=t2.next;
         }
     }
     if(t1==null)
      temp.next=t2;
      else
      temp.next=t1;

    prac res=new prac();
    res.head=dummy.next;
    temp=dummy;
    int sz=0;
    while(temp.next!=null)
    {
        temp=temp.next;
        sz++;
    }
    res.tail=temp;
    res.size=sz;

    return res;

 }

 public void display()
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
     prac p=new prac();
     p.addfirst(10);
     p.addfirst(20);
     p.addLast(40);
     p.addLast(50);
     p.addAt(30, 2);
     p.display();
     System.out.println(p.getFirst());
     System.out.println(p.getLast());
     prac p1=new prac();
     p1.addLast(100);
     p1.addLast(20);
     p1.addLast(1);
     p1.addLast(2);
     p1.display();
     prac res=merge(p, p1);
     res.display();


     


 }


    
}

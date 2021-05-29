import java.io.*;
import java.util.*;
class stack
{
    private int size;
    private int arr[];

    stack(int capacity)
    {
        this.arr=new int[capacity];
    }

    int  size()
    {
        return this.size;
    }

    boolean isempty()
    {
        return this.size==0;
    }

    void push(int val)
    {
        if(this.size==arr.length)
        {
          System.out.println("Stack overflow");
          return;
        }
        arr[this.size]=val;
        this.size++;

    }

    void pop()
    {
        if(this.size==0)
         {
             System.out.println("stack underflow");
             return;
         }
        this.size--;
    }

    int peek()
    {
        if(this.size==0)
        {
            System.out.println("stack underflow");
            return -1;
        }
        return arr[this.size];
    }

    void display()
    {
      for(int i=0;i<this.size-1;i++)
       {
           System.out.print(arr[i]);
       }
       System.out.println();
    }




}
public class stackprac {

    public static void main(String[] args) {
        stack st=new stack(10);
        st.push(10);
        st.push(20);
        st.display();
    }
    
}

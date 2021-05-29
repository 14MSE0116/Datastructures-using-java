import java.io.*;
import java.util.*;

class stack
{
    private int size;
    private int arr[];

    stack(int capacity)
    {
        arr=new int[capacity];
        size=-1;
    }

    int size()
    {
        return this.size+1;
    }

    void push(int val)
    {
        if(this.size==arr.length-1)
        {
            int ndata[]=new int[2*arr.length];
            for(int i=0;i<arr.length;i++)
             {
                 ndata[i]=arr[i];
             }
             ndata=arr;
             this.size++;

        }
       this.size++;
       arr[this.size]=val;
    }

    int pop()
    {
        if(this.size==-1)
        {
            System.out.println("stack underflow");
            return -1;
        }
        int val=arr[this.size];
        this.size--;
        return val;


        
    }

    int top()
    {
        if(this.size==-1)
        {
            System.out.println("stack underflow");
            return -1;
        }
        return arr[this.size];

    }

    void display()
    {
      for(int i=this.size;i>=0;i--)
      {
          System.out.print(arr[i]);
      }
      System.out.println();

    }
    
}

public class dynamicstack {

    public static void main(String[] args) {

        stack st=new stack(5);
        st.push(10);
        st.push(20);
        st.push(30);
        st.display();
        st.pop();
        st.display();
        
    }
    
}

import java.util.ArrayList;

/**
 * mystack
 */
 class stack {

    private int []arr;
    private int size=0;

  
    public stack(int capacity)
    {
        arr=new int[capacity];

    }

    public void push(int val)
    {
        if(this.size==arr.length)
         {
             System.out.println("Stack overflow");
             return;
         }
        arr[this.size]=val;
        this.size++;
         
    }

    public int pop()
    {
          if(size==0)
            {
                System.out.println("Stack underflow");
                return -1;
            }
           int val=arr[this.size-1];
            this.size--;
            return val;
    }

    public int peek()
    {
        if(this.size==0)
            {
                System.out.println("Stack underflow");
                return -1;
            }
           int val=arr[this.size-1];
            return val;

    }

    public boolean isEmpty()
    {
        return this.size == 0;
    }

    public int size()
    {
       return this.size ;
    }

    public void display()
    {
        for(int i=0;i<this.size;i++)
         System.out.print(arr[i]+" ");

         System.out.println();

    }
    
}

/**
 * mystack
 */
public class mystack {

    public static void main(String[] args) {
        stack st=new stack(10);
        st.push(10);
        st.push(20);
        st.push(30);
        st.push(40);
        st.display();
        System.out.println(st.peek());
        st.pop();
        st.display();


    }
}
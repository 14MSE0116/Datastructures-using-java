import java.io.*;
import java.util.*;

public class slidingwindow {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        for(int i = 0; i < n; i++){
           a[i] = Integer.parseInt(br.readLine());
        }
        int k = Integer.parseInt(br.readLine());
        slidingwindowmax(a,k);
        // code
     }
     public static int[]rgi(int arr[])
     {
        int res[]=new int[arr.length];
        Stack<Integer> st=new Stack<>();
        st.push(0);
        for(int i=1;i<arr.length;i++)
        {
            while(st.size()>0 && arr[st.peek()] < arr[i])
             {
                 res[st.peek()]=i;
                 st.pop();
             }
             st.push(i);
        }
 
        while(st.size()>0)
         res[st.pop()]=arr.length;
 
 
        return res;
      }
     
     public static void slidingwindowmax(int a[],int k)
     {
         int []rgi=rgi(a);
         int j=0;
         for(int i=0;i<=a.length-k;i++)
         {
             if(i>j)
              j=i;
             
              while(i+k>rgi[j])
              {
                  j=rgi[j];
              }
              System.out.println(a[j]);
         }

     }
    }
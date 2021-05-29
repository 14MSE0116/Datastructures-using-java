import java.io.*;
import java.util.*;

public class nextgrtele {
      public static void main(String[] args) throws Exception {
        
        int[] arr = {6, 2, 6, 5, 5, 6, 1, 7};
        System.out.println("arr : " + Arrays.toString(arr));
        int[] ngr = ngr(arr);
        int[] ngl = ngl(arr);
        int[] nsr = nsr(arr);
        int[] nsl = nsl(arr);
        System.out.println("ngr : " + Arrays.toString(ngr));
        System.out.println("ngl : " + Arrays.toString(ngl));
        System.out.println("nsr : " + Arrays.toString(nsr));
        System.out.println("nsl : " + Arrays.toString(nsl));
     }
    
     public static int[] ngr(int[] arr){
       // solve next right greater element
       int res[]=new int[arr.length];
       Stack<Integer> st=new Stack<>();
       st.push(0);
       for(int i=1;i<arr.length;i++)
       {
           while(st.size()>0 && arr[st.peek()] < arr[i])
            {
                res[st.peek()]=arr[i];
                st.pop();
            }
            st.push(i);
       }

       while(st.size()>0)
        res[st.pop()]=-1;


       return res;
     }
     public static int []rightgreaterindex(int arr[])
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

     public static void slidingwindow(int []arr,int k)
     {
       int []rgi=rightgreaterindex(arr);

       int j=0;
       for(int i=0;i<=arr.length-k;i++)
       {
         if(i>j)
          j=i;

          while(i+k>rgi[i])
          {
            j=rgi[j];
          }

          System.out.println(arr[j]);
       }
     }

     //left greater element 
     public static int[] ngl(int[] arr){
      // solve next right greater element
     int[] res = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        st.push(arr.length - 1);

        for(int i = arr.length - 2; i >= 0; i--) {
            while(st.size() > 0 && arr[st.peek()] < arr[i]) {
                res[st.pop()] = arr[i];
            }
            st.push(i);
        }

        while(st.size() > 0) {
            res[st.pop()] = -1;
        }
        return res;
    }


     //next right smaller element
     public static int[]nsr(int []arr)
     {
       int res[]=new int[arr.length];
      Stack<Integer>st=new Stack<>();
      st.push(0);
      for(int i=0;i<arr.length;i++)
      {
        while(st.size()>0 && arr[i]<arr[st.peek()])
        {
          res[st.peek()]=arr[i];
          st.pop();
        }
        st.push(i);
      }
      while(st.size()>0)
      {
        res[st.peek()]=-1;
        st.pop();
      }


       return res;

     }

     //next left smaller element
     public static int[]nsl(int []arr)
     {
       int res[]=new int[arr.length];
       Stack<Integer> st=new Stack<>();
       st.push(arr.length-1);
       for(int i=arr.length-2;i>=0;i--)
       {
         while(st.size()>0 && arr[i]<arr[st.peek()])
         {
           res[st.peek()]=arr[i];
           st.pop();
         }
         st.push(i);

       }

       while(st.size()>0)
       {
         res[st.peek()]=-1;
         st.pop();
       }


       return res;
     }
    
    
    }
import java.io.*;
import java.util.Scanner;
public class countsort {
public static void countSort(int[] arr, int min, int max) {
   //write your code here
   
    int []fmap=new int[max-min+1];

        for(int i=0;i<arr.length;i++)
        {
            int idx=arr[i]-min;
            fmap[idx]++;

        }
          

      int idx=0;
        for(int i=0;i<fmap.length;i++)
          {
              int fq=fmap[i];
              int val=i+min;
              for(int j=0;j<fq;j++)
               {
                   arr[idx]=val;
                   idx++;
               }
          }  
   
  }

  public static void stablesort(int []arr,int min,int max)
  {
    int []fmap=new int[max-min+1];

    for(int i=0;i<arr.length;i++)
    {
        int idx=arr[i]-min;
        fmap[idx]++;

    }

    //2create prefix sum array
    fmap[0]--;
    for(int i=1;i<fmap.length;i++)
      fmap[i]+=fmap[i-1];

    //3make a new array and fill it reverse direction
    //also decrease psum[i] while placing ith element
    int narr[]=new int[arr.length];
    for(int i=arr.length-1;i>=0;i--)
    {
        int val=arr[i];
        int indx=val-min;

        int pos=fmap[indx];
        narr[pos]=val;
        fmap[indx]--;
    }  

    for(int i=0;i<arr.length;i++)
     arr[i]=narr[i];

  }

  public static void print(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      System.out.println(arr[i]);
    }
  }

  public static void main(String[] args) throws Exception {
    Scanner scn = new Scanner(System.in);
    int n = scn.nextInt();
    int[] arr = new int[n];
    int max = Integer.MIN_VALUE;
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < n; i++) {
      arr[i] = scn.nextInt();
      max = Math.max(max, arr[i]);
      min = Math.min(min, arr[i]);
    }
    stablesort(arr,min,max);
    print(arr);
  }

}
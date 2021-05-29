/**
 * printsubarray
 */
import java.io.*;
import java.util.*;
public class printsubseq {
  
    public static void main(String[] args) {
        
        Scanner scn=new Scanner(System.in);
        int n=scn.nextInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++)
         arr[i]=scn.nextInt();

         pss(arr);

    }
    public static void pss(int arr[])
    {
         helper(arr,0,"");
    }
    public static void helper(int arr[],int idx,String ans)
    {
        if(idx==arr.length)
        {
            System.out.println(ans);
            return;
        }
        helper(arr,idx+1,ans+"-");
        helper(arr,idx+1,ans+arr[idx]);
       
    }

}
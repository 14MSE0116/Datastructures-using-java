import java.io.*;

import javax.swing.plaf.basic.BasicListUI.ListDataHandler;

import java.util.*;
public class gss {
    
    public static List<List<Integer>>ans=new ArrayList<>();
    public static List<Integer> curr=new ArrayList<>();

    public static void main(String[] args) {
        
        Scanner scn=new Scanner(System.in);
        int n=scn.nextInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++)
         arr[i]=scn.nextInt();
        subsets(s);

    }

    public static List<List<Integer>> subsets(int arr[]) {
        List<Integer>curr=new ArrayList<>();
        helper(arr,curr,0);
        return ans;
    }

    public static void helper(int arr[],List<Integer>cur,int idx)
    {
        if(idx==arr.length)
        {
           
            ans.add(curr);
            return;
        }
      
        
        helper(arr, curr.add(arr[idx]), idx+1);

       



        
    }
}

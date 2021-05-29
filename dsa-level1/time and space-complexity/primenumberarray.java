import java.util.Scanner;
import java.util.*;

public class primenumberarray {

    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        int n=scn.nextInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++)
        {
         arr[i]=scn.nextInt();
         if(arr[i]==1)
             System.out.println("Not Prime");
            else
            {
                if(isprime(arr[i]))
                {
                    System.out.println("Prime");
                }
                else
                System.out.println("Not Prime");
            } 

        }

        // for(int i=0;i<n;i++)
        // {
        //     if(arr[i]==1)
        //      System.out.println("Not Prime");
        //     else
        //     {
        //         if(isprime(arr[i]))
        //         {
        //             System.out.println("Prime");
        //         }
        //         else
        //         System.out.println("Not Prime");
        //     } 
        // }
    }
    public static boolean isprime(int n)
    {
        for(int i=2;i*i<=n;i++)
        { 
            if(n%i==0)
             return false;

        }
        return true;
    }
    
}

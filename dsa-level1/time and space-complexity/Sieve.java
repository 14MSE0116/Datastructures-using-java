import java.util.Arrays;
import java.util.Scanner;

public class Sieve {
    public static void main(String[] args) {

        Scanner scn=new Scanner(System.in);
        
        
    }

    public static void sieve(int []query,int hi)
    {
        boolean []isprime = new boolean[hi+1];
        //mark every element true;
        Arrays.fill(isprime, true);


        //pre calculate
        for(int i=2;i*i<=hi;i++)
        {
            if(isprime[i]==true)
            {
                for(int j=i+i;j<=hi;j+=i)
                {
                    isprime[i]=false;
                }
            }
        }

        //solve for every query
        for(int i=0;i<query.length;i++)
        {
            int num=query[i];
            if(num==0 || num==1)
            continue;

            if(isprime[num]==false)
             System.out.println("Not prime");
            else
             System.out.println("prime"); 

        }

    }
}

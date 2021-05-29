import java.util.*;
public class tns {

    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
       int arr[]={1,2,3,4,5,6};
       boolean flag=binarySearchRec(arr,0,arr.length-1,5);
       System.out.println(flag);
       solvepoly(2,5);
       solvepolyopti(2,5);
        
    }
    public static boolean binarySearchRec(int[] arr, int lo, int hi, int data) {
        if(lo>hi)
        {
            return false;
        }
        int mid=(lo+hi) / 2;
        if(arr[mid]==data)
        {
           return true;
        }

        else if(arr[mid]<data)
          return binarySearchRec(arr,mid+1,hi,data);
        
        else if(arr[mid]>data);
           return binarySearchRec(arr,lo,mid-1,data);

        
    
    
        
    }

    public static void solvepoly(int x,int n)
    {
        int sum=0;
        int nn=n;
        for(int i=1;i<=n;i++)
        {
            sum+=i*(Math.pow(x, nn));
            nn=nn-1;
        }
        System.out.println(sum);
    }

    public static void solvepolyopti(int x,int n)
    {
        
        int xval=x;
        int ans=0;
        for(int i=n;i>=1;i--)
        {
              ans+=(i * xval);
              xval*=x;

        }
    System.out.println(ans);
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
      }

}
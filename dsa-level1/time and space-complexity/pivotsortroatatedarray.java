import java.util.Scanner;

public class pivotsortroatatedarray {
    
    public static int findPivot(int[] arr,int lo,int hi) {
        // write your code here
        int mid= lo + (hi-lo)/2 ;
        int res=0;
        if(lo==hi)
         return arr[lo];
        if(arr[mid]<arr[hi])
        {
            res=findPivot(arr,lo,mid);
        }
        else
         res=findPivot(arr, mid+1, hi);

         return res;
      }
    
      public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = scn.nextInt();
        }
        int pivot = findPivot(arr,0,arr.length-1);
        System.out.println(pivot);
      }
    
    }
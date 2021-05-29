import java.io.*;
import java.util.*;
public class largestareahistogram {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        for(int i = 0; i < n; i++){
           a[i] = Integer.parseInt(br.readLine());
        }
        System.out.println(area(a));
    
        // code
     }
     public static int area(int []arr)
     {
        int area=0;
        int[]lsi=lsi(arr);
        int[]rsi=rsi(arr);
        for(int i=0;i<lsi.length;i++)
        {
            int width=rsi[i]-lsi[i]-1;
            int height=arr[i];
            area=Math.max(area,width*height);
        }
        return area;

     }
     public static int[]rsi(int arr[])
     {
        int[] res = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        st.push(0);

        for(int i = 1; i < arr.length; i++) {
            while(st.size() > 0 && arr[st.peek()] > arr[i]) {
                res[st.pop()] = i;
            }
            st.push(i);
        }
        while(st.size() > 0) {
            res[st.pop()] = arr.length;
        }
        return res;
     }
     public static int []lsi(int arr[])
     {
        int[] res = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        st.push(arr.length - 1);

        for(int i = arr.length - 2; i >= 0; i--) {
            while(st.size() > 0 && arr[st.peek()] > arr[i]) {
                res[st.pop()] = i;
            }
            st.push(i);
        }
        while(st.size() > 0) {
            res[st.pop()] = -1;
        }
        return res;
     }
    }


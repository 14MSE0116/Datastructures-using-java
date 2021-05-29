import java.io.*;
import java.util.*;


public class mergeoverlapping {

    public static void main(String[] args) throws Exception {
        // write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][2];

        for (int j = 0; j < n; j++) {
            String line = br.readLine();
            arr[j][0] = Integer.parseInt(line.split(" ")[0]);
            arr[j][1] = Integer.parseInt(line.split(" ")[1]);
        }

        mergeOverlappingIntervals(arr);
    }
    public static class Pair implements Comparable<Pair>{
        int st=0;
        int end=0;

        public Pair(int st,int end)
        {
            this.st=st;
            this.end=end;
        }

        public int compareTo(Pair o)
        {
          return this.st - o.st ;
        }
    }

    public static void mergeOverlappingIntervals(int[][] arr) {
        // merge overlapping intervals and print in increasing order of start time
        Pair[] pairs=new Pair[arr.length];
        for(int i=0;i<arr.length;i++)
        {
            pairs[i]=new Pair(arr[i][0],arr[i][1]);

        }
        Arrays.sort(pairs);

        Stack<Pair>st=new Stack<>();
        st.push(pairs[0]);
        for(int i=1;i<pairs.length;i++)
        {
            Pair p=pairs[i];
            if(p.st<=st.peek().end )
            {
                  //end time may be updated or not
                  if(p.end>st.peek().end)
                    {
                        st.peek().end=p.end;
                    }
                  



            }
            else
            {
                st.push(p);
            }
        }
        Stack<Pair>rst=new Stack<>();
        while(st.size()>0)
        {
            rst.push(st.pop());
        }

        while(rst.size()>0)
        {
            Pair rem=rst.pop();
            System.out.println(rem.st+" "+rem.end);
        }

    }

}
import java.io.*;
import java.util.*;

public class celebritystack {

    public static void main(String[] args) throws IOException {
    // write your code here
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int[][] arr = new int[n][n];

    for (int j = 0; j < n; j++) {
        String line = br.readLine();
        for (int k = 0; k < n; k++) {
            arr[j][k] = line.charAt(k) - '0';
        }
    }

    findCelebrity(arr);

}

public static void findCelebrity(int[][] arr) {
    // if a celebrity is there print it's index (not position), if there is not then
    // print "none"

    Stack<Integer>st=new Stack<>();
    for(int i=0;i<arr.length;i++)
    {
        st.push(i);
    }

    //elimination and find potential candidate.
    while(st.size()>1)
    {
        int val1=st.pop();
        int val2=st.pop();

        if(arr[val1][val2]==1)
        //val1 is not celebrity
          st.push(val2);
         else
         //val2 is not celebrity
         st.push(val1);
    }
    //got potential candidate now check row is 0 and columns i 1;
    int col=1;
    int row=1;
    int pt=st.pop();

    //check its row and column
    for(int i=0;i<arr[0].length;i++)
    {
      if(arr[pt][i]==1)
      {
          System.out.println("none");
          return;
      }
       
    }

    for(int i=0;i<arr.length;i++)
    {
      if(i!=pt && arr[i][pt]==0)
      {
        System.out.println("none");
        return;
      }
       
    }

    


}

}
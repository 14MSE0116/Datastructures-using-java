import java.util.*;
import java.io.*;
import java.lang.Thread.State;

public class rottenoragnes {

    public static class Pair {
        int x;
        int y;
        int t;

        Pair(int x, int y, int t) {
            this.x = x;
            this.y = y;
            this.t = t;
        }
    }

    public static int orangesRotting(int[][] graph) {
        Queue<Pair> qu = new ArrayDeque<>();
        int countorange = 0;
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[0].length; j++) {
                if (graph[i][j] == 2) {
                    qu.add(new Pair(i, j, 0));
                    countorange++;
                } else if (graph[i][j] == 1) {
                    countorange++;
                }
            }
        }

        if(countorange == 0)
         return 0;

        // start the bsf
        int xdir[] = { -1, 0, 1, 0 };
        int ydir[] = { 0, -1, 0, 1 };
        int time=0;
       while(qu.size()>0)
       {
           int sz=qu.size();
           while(sz-- > 0)
           {
             Pair rem=qu.remove();
             if(graph[rem.x][rem.y]==-1)
               continue;

               else
               graph[rem.x][rem.y]=-1;

               time=rem.t;
               System.out.println("orangge at (" + rem.x + "," + rem.y + ")" + "rotted at time" + rem.t);
               countorange--;

               for (int d = 0; d < xdir.length; d++) {
                int r = rem.x + xdir[d];
                int c = rem.y + ydir[d];

                // check validity + unvisited orange
                if (r >= 0 && r < graph.length && c >= 0 && c < graph[0].length && graph[r][c] != -1
                        && graph[r][c] == 1) {
                    qu.add(new Pair(r, c, rem.t + 1));
                }
            }


           }
       }
       return countorange>0?-1:time ;
    }

    public static void main(String[] args) {
        int graph[][]={{2,1,1},{0,1,1},{1,0,1}} ;
        orangesRotting(graph);
    }
}

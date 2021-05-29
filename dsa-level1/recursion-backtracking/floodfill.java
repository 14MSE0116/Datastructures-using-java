/**
 * floodfill
 */
import java.util.*;
public class floodfill {
    public static int rdir[]={-1,0,1,0};
    public static int cdir[]={0,-1,0,1};
    public static char chars[]={'t','l','d','r'} ;
    
    
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = scn.nextInt();
            }
        }
        floodfill(arr, 0, 0, "");
    }
    //level-end of array
    //options-t,l,d,r
    public static void floodfill(int[][] maze, int sr, int sc, String asf) {

        if(sr==maze.length-1 && sc==maze[0].length-1)
        { 
               System.out.println(asf);
               return;
        }
        maze[sr][sc]=1;

        for(int dir=0;dir<rdir.length;dir++)
        {
            char c=chars[dir];
            int rr=sr+rdir[dir];
            int rc=sc+cdir[dir];
          if(rr>=0 && rr<maze.length && rc>=0 && rc<maze[0].length && maze[rr][rc]!=1)
            floodfill(maze,rr,rc,asf+c);
        }
        maze[sr][sc]=0;


    
    }
}
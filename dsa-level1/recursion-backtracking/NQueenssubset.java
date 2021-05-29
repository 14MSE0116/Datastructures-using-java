import java.util.*;
public class NQueenssubset {
   
    public static int[][] dir = {
        {-1, 0},
        {-1, 1},
        {0, 1},
        {1, 1},
        {1, 0},
        {1, -1},
        {0, -1},
        {-1, -1} }  ;
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        int n=scn.nextInt();
        int board[][]=new int[n][n];
        queen2(board,0,0,0,"");

        
    }

    public static boolean isvalidplace(int board[][],int sr,int sc)
        {

            int radius=board.length;
            for(int r=1;r<=radius;r++)
            {
                for(int d=0;d<dir.length;d++)
                {
                    int rr=sr+(r*dir[d][0]);
                    int rc=sc+(r*dir[d][1]);
                    if(rr >= 0 && rr < radius && rc >= 0 && rc < radius) {
                    if(board[rr][rc]==1)
                     return false;
                    }
                }
            }
            return true;
        }

    public static void queen2(int board[][],int sr,int sc,int qsf,String ans)
    {
        if(sr==board.length)
        {
            if(qsf==board.length)
            {
                System.out.println(ans);
            }
            return;
        }
        if(sc+1<board[0].length)
        {
            if(isvalidplace(board,sr,sc)==true)
            {
            board[sr][sc]=1;
            queen2(board,sr+1,0,qsf+1,ans + sr + "-" + sc + ", ");
            board[sr][sc]=0;
            }
            queen2(board,sr,sc+1,qsf,ans);
        }
        else{
            if(isvalidplace(board,sr,sc)==true)
            {
            board[sr][sc]=1;
            queen2(board,sr+1,0,qsf+1,ans + sr + "-" + sc + ", ");
            board[sr][sc]=0;
            }
            queen2(board,sr+1,0,qsf,ans);
        }
    }
}

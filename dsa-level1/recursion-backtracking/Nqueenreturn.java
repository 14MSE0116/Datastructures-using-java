import java.util.*;


/**
 * Nqueenreturn
 */
public class Nqueenreturn {
    
    public static List<List<String>>res=new ArrayList<>();
    
        public static int[][] dir = {
        {-1, 0},
        {-1, 1},
        {0, 1},
        {1, 1},
        {1, 0},
        {1, -1},
        {0, -1},
        {-1, -1} }  ;
    
    public static void constructBoard(int [][]board)
    {
        List<String> subList = new ArrayList<>();
       
        // for(int []row:board)
        // {
        //     StringBuilder sb = new StringBuilder();
        //     for(int i=0;i<row.length;i++)
        //     {
        //         if(row[i]==0)
        //              sb.append(".");
        //         else
        //             sb.append("Q");
        //     }
        //      subList.add(sb.toString());
        // }
        // res.add(subList);

       for(int i=0;i<board.length;i++)
       {
        StringBuilder sb = new StringBuilder();
           for(int j=0;j<board.length;j++)
           {
               if(board[i][j]==1)
                sb.append("Q");
                else
                sb.append(".");
           }
           subList.add(sb.toString());
       }
       res.add(subList);
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
       

    public static List<List<String>> solveNQueens(int n) {
        // write your code here
       int board[][]=new int[n][n];
        
        solve(board,0);
        return res;
      
    }
    
    public static void solve(int board[][],int row)
    { 
        if(row==board.length)
            
        {
            constructBoard(board);
            return;
        }
        for(int c=0;c<board[0].length;c++)
        {
            if(isvalidplace(board,row,c)==true)
            {
                board[row][c]=1;
              solve(board,row+1);
               board[row][c]=0;
            }
        }
        
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        List<List<String>> res = solveNQueens(n);
        System.out.println(res);
    }

}
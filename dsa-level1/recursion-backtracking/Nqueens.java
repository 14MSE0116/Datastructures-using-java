/**
 * Nqueens
 */
import java.util.*;
import java.io.*;
public class Nqueens {

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

        queen2(board,0,"");
    }

    //level-each row
    //opitions-all columns in each row
    public static void queen2(int[][] board, int row, String asf)  
        {
            if(row==board.length)
            {
                System.out.println(asf);
                return;
            }
            for(int col=0;col<board[0].length;col++)
            {
                if(isvalidplace(board,row,col)==true)
                {
                    board[row][col]=1;
                queen2(board,row+1,asf + row + "-" + col + ", ");
                board[row][col]=0;
                }
            }

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
       

    }

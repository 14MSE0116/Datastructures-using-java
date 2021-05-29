/**
 * knightstour
 */
import java.util.*;
public class knightstour {
    
    public static int[] xdir = {-2, -1, 1, 2, 2, 1, -1, -2};
    public static int[] ydir = {1, 2, 2, 1, -1, -2, -2, -1};

    public static void main(String[] args) throws Exception {
        Scanner scn=new Scanner(System.in);
        int n=scn.nextInt();
        int r=scn.nextInt();
        int c=scn.nextInt();
        int chess[][]=new int[n][n];
        printKnightsTour(chess,r,c,1);
    }

    public static void printKnightsTour(int[][] board, int r, int c, int count) {
        if(count==board.length*board.length)
        {
            board[r][c]=count;
            displayBoard(board);
            board[r][c]=0;
        }

        board[r][c]=count;
        for(int dir=0;dir<xdir.length;dir++)
        {
            int rr=r+xdir[dir];
            int cc=c+ydir[dir];

         if(rr>=0 && rr<board.length && cc>=0 && cc<board[0].length && board[rr][cc]==0)
            printKnightsTour(board,rr,cc,count+1);
        }
        board[r][c]=0;
       
    }


    public static void displayBoard(int[][] chess){
        for(int i = 0; i < chess.length; i++){
            for(int j = 0; j < chess[0].length; j++){
                System.out.print(chess[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }
}

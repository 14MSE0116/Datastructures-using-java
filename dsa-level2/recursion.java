
/**
 * recursion
 */
import java.io.*;

public class recursion {

    // ci-current item di-total item
    public static void permutations1(int[] boxes, int ci, int ti) {
        if (ci > ti) {
            for (int val : boxes) {
                System.out.print(val);
            }
            System.out.println();
            return;
        }
        for (int b = 0; b < boxes.length; b++) {
            if (boxes[b] == 0) {
                boxes[b] = ci;
                permutations1(boxes, ci + 1, ti);
                // unplace objects
                boxes[b] = 0;
            }
        }
    }

    // cb->current box
    // tb->total box
    // isf->item so far
    public static void combinations1(int cb, int tb, int isf, int ti, String asf) {
        // write your code here
        if (cb > tb) {
            if (isf == ti)
                System.out.println(asf);
            return;
        }
        // yes call
        if (isf + 1 <= ti)
            combinations1(cb + 1, tb, isf + 1, ti, asf + "i");

        // no call
        combinations1(cb + 1, tb, isf, ti, asf + "-");

    }

    public static void permutations2(int cb, int tb, int[] items, int isf, int ti, String asf) {
        // write your code here
        // yes call
        if (cb > tb) {
            if (isf == ti) {
                System.out.println(asf);
            }
            return;
        }
        for (int i = 0; i < items.length; i++) {
            if (items[i] == 0) {
                // select
                items[i] = 1;
                permutations2(cb + 1, tb, items, isf + 1, ti, asf + (i + 1));
                // deselect
                items[i] = 0;
            }
        }
        // no call
        permutations2(cb + 1, tb, items, isf, ti, asf + "0");

    }

    public static void combinations2(int[] boxes, int ci, int ti, int lb) {
        if (ci > ti) {
            for (int i = 0; i < boxes.length; i++) {
                if (boxes[i] == 0) {
                    System.out.print("-");
                } else {
                    System.out.print("i");
                }
            }
            System.out.println();
            return;
        }
        // write your code here
        for (int b = lb + 1; b < boxes.length; b++) {
            // place ci item on bth box
            boxes[b] = ci;
            combinations2(boxes, ci + 1, ti, b);
            // unplace
            boxes[b] = 0;

        }
    }

    // qpsf->queen placed so far
    // asf->answer so far
    // tq->total queen
    public static void queensCombinations(int qpsf, int tq, int row, int col, String asf) {
        // write your code here
        if (row == tq) {
            if (qpsf == tq) {
                System.out.println(asf);
            }
            return;
        }

        // yes call
        if (col + 1 < tq) {
            if (qpsf < tq)
                queensCombinations(qpsf + 1, tq, row, col + 1, asf + "q");
            queensCombinations(qpsf, tq, row, col + 1, asf + "-");
        } else {
            queensCombinations(qpsf + 1, tq, row + 1, 0, asf + "q\n");
            queensCombinations(qpsf, tq, row + 1, 0, asf + "-\n");
        }

    }

    public static void queensPermutations(int qpsf, int tq, int[][] chess) {
        if (qpsf == tq) {
            // print result
            for (int i = 0; i < chess.length; i++) {
                for (int j = 0; j < chess[0].length; j++) {
                    if (chess[i][j] != 0) {
                        System.out.print("q" + chess[i][j] + "\t");
                    } else {
                        System.out.print("-\t");
                    }
                }
                System.out.println();
            }
            System.out.println();
            return;
        }
        // write your code here
        for (int i = 0; i < chess.length; i++) {
            for (int j = 0; j < chess[0].length; j++) {
                if (chess[i][j] == 0) {
                    chess[i][j] = qpsf + 1;
                    queensPermutations(qpsf + 1, tq, chess);
                    chess[i][j] = 0;
                }

            }
        }
    }

    public static void queensPermutations(int qpsf, int tq, int row, int col, String asf, boolean[] queens) {
        // write your code here
        if (row == tq) {
            if (qpsf == tq) {
                System.out.println(asf);
                System.out.println();
            }
            return;
        }

        for (int i = 0; i < queens.length; i++) {
            if (queens[i] == false) {
                queens[i] = true;
                // yes call
                if (col + 1 < tq) {
                    queensPermutations(qpsf + 1, tq, row, col + 1, asf + "q" + (i + 1) + "\t", queens);
                } else {
                    queensPermutations(qpsf + 1, tq, row + 1, 0, asf + "q" + (i + 1) + "\n", queens);
                }
                queens[i] = false;
            }
        }

        // no call
        if (col + 1 < tq) {
            queensPermutations(qpsf, tq, row, col + 1, asf + "-\t", queens);
        } else {
            queensPermutations(qpsf, tq, row + 1, 0, asf + "-\n", queens);
        }
    }

    public static void queensCombinations(int qpsf, int tq, boolean[][] chess, int i, int j) {
        // write your code here
        // travel in remaining columns
        if (qpsf == tq) {
            for (int r = 0; r < chess.length; r++) {
                for (int c = 0; c < chess[r].length; c++) {
                    if (chess[r][c] == true) {
                        System.out.print("q\t");
                    } else {
                        System.out.print("-\t");
                    }
                }
                System.out.println();
            }
            System.out.println();
            return;
        }
        for (int c = j + 1; c < chess[0].length; c++) {
            int r = i;

            // place
            chess[r][c] = true;
            queensCombinations(qpsf + 1, tq, chess, r, c);
            chess[r][c] = false;
            // unplace
        }

        // travel in remaining rows and columns
        for (int r = i + 1; r < chess.length; r++) {
            for (int c = 0; c < chess[r].length; c++) {
                chess[r][c] = true;
                queensCombinations(qpsf + 1, tq, chess, r, c);
                chess[r][c] = false;
            }
        }
    }

    // chess combinaton 1 2D as 1D
    // qpsf->queen placed so far
    // lcno->last cell number

    public static void queensCombinations(int qpsf, int tq, boolean[][] chess, int lcno) {
        // write your code here
        if (qpsf == tq) {
            for (int i = 0; i < chess.length; i++) {
                for (int j = 0; j < chess[0].length; j++) {
                    if (chess[i][j] == true) {
                        System.out.print("q\t");
                    } else {
                        System.out.print("-\t");
                    }
                }
                System.out.println();
            }
            System.out.println();
            return;
        }

        for (int b = lcno + 1; b < chess.length * chess[0].length; b++) {
            int r = b / chess.length;
            int c = b % chess[0].length;

            // place
            chess[r][c] = true;
            queensCombinations(qpsf + 1, tq, chess, b);
            chess[r][c] = false;
        }
    }

    public static boolean IsQueenSafe(boolean[][] chess, int row, int col) {
        // write your code here
        for (int r = 1; r <= chess.length; r++) {
            for (int dir = 0; dir < rdir.length; dir++) {
                int rr = row + r * rdir[dir];
                int cc = col + r * cdir[dir];
                if (rr >= 0 && rr < chess.length && cc >= 0 && cc < chess[0].length) {
                    if (chess[rr][cc] == true)
                        return false;
                }
            }
        }
        return true;
    }

    public static void nqueens(int qpsf, int tq, boolean[][] chess, int lcno) {
        if (qpsf == tq) {
            for (int row = 0; row < chess.length; row++) {
                for (int col = 0; col < chess.length; col++) {
                    System.out.print(chess[row][col] ? "q\t" : "-\t");
                }
                System.out.println();
            }
            System.out.println();
            return;
        }

        for (int i = lcno + 1; i < chess.length * chess.length; i++) {
            int row = i / chess.length;
            int col = i % chess.length;

            if (chess[row][col] == false && IsQueenSafe(chess, row, col)) {
                chess[row][col] = true;
                nqueens(qpsf + 1, tq, chess, row * chess.length + col);
                chess[row][col] = false;
            }
        }
    }

    public static void queensPermutations2(int qpsf, int tq, int[][] chess) {
        if (qpsf == tq) {
            // print result
            for (int i = 0; i < chess.length; i++) {
                for (int j = 0; j < chess[0].length; j++) {
                    if (chess[i][j] != 0) {
                        System.out.print("q" + chess[i][j] + "\t");
                    } else {
                        System.out.print("-\t");
                    }
                }
                System.out.println();
            }
            System.out.println();
            return;
        }
        // write your code here
        for (int i = 0; i < chess.length; i++) {
            for (int j = 0; j < chess[0].length; j++) {
                if (chess[i][j] == 0) {
                    chess[i][j] = qpsf + 1;
                    queensPermutations2(qpsf + 1, tq, chess);
                    chess[i][j] = 0;
                }

            }
        }
    }

    public static void nqueens_permu(int qpsf, int tq, int[][] chess) {
        // write your code here
        if (qpsf == tq) {
            // print result
            for (int i = 0; i < chess.length; i++) {
                for (int j = 0; j < chess.length; j++) {
                    if (chess[i][j] == 0) {
                        System.out.print("-\t");
                    } else {
                        System.out.print("q" + chess[i][j] + "\t");
                    }
                }
                System.out.println();
            }
            System.out.println();
            return;
        }
        for (int i = 0; i < chess.length * chess.length; i++) {
            int row = i / chess.length;
            int col = i % chess.length;
            if (chess[row][col] == 0 && IsQueenSafe(chess, row, col)) {
                chess[row][col] = qpsf + 1;
                nqueens_permu(qpsf + 1, tq, chess);
                chess[row][col] = 0;
            }
        }
    }

    public static int rdir[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
    public static int cdir[] = { 0, 1, 1, 1, 0, 1, -1, -1 };

    public static boolean IsQueenSafe(int[][] chess, int row, int col) {
        // write your code here
        for (int r = 1; r <= chess.length; r++) {
            for (int dir = 0; dir < rdir.length; dir++) {
                int rr = row + r * rdir[dir];
                int cc = col + r * cdir[dir];
                if (rr >= 0 && rr < chess.length && cc >= 0 && cc < chess[0].length) {
                    if (chess[rr][cc] != 0)
                        return false;
                }
            }
        }
        return true;
    }

    public static void nqueens(int qpsf, int tq, int[][] chess) {
        // write your code here
        if (qpsf == tq) {
            for (int i = 0; i < chess.length; i++) {
                for (int j = 0; j < chess.length; j++) {
                    if (chess[i][j] != 0) {
                        System.out.print("q" + chess[i][j] + "\t");
                    } else {
                        System.out.print("-\t");
                    }
                }

                System.out.println();

            }

            return;
        }
        for (int i = 0; i < chess.length * chess.length; i++) {
            int row = i / chess.length;
            int col = i % chess.length;
            if (chess[row][col] == 0 && IsQueenSafe(chess, row, col)) {
                chess[row][col] = qpsf + 1;
                nqueens(qpsf + 1, tq, chess);
                chess[row][col] = 0;
            }
        }
    }

    public static void main(String[] args) {

    }
}
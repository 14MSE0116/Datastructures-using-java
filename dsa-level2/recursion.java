
/**
 * recursion
 */
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

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

    // Permutations - Words - 1

    public static void generateWords(int cs, int ts, HashMap<Character, Integer> fmap, String asf) {
        // write your code here
        if (cs > ts) {
            System.out.println(asf);
            return;
        }

        for (char ch : fmap.keySet()) {
            if (fmap.get(ch) > 0) {
                int old = fmap.get(ch);
                fmap.put(ch, old - 1);
                generateWords(cs + 1, ts, fmap, asf + ch);
                fmap.put(ch, old);
            }
        }
    }

    // Permutations - Words - 2
    // cc->current character
    // li->last index
    public static void generateWords(int cc, String str, Character[] spots, HashMap<Character, Integer> li) {
        if (cc == str.length()) {
            for (char ch : spots) {
                System.out.print(ch);
            }
            System.out.println();
            return;
        }
        // write your code here
        char ch = str.charAt(cc);
        int lsi = li.get(ch); // last spot
        for (int box = lsi + 1; box < spots.length; box++) {
            if (spots[box] == null) {
                spots[box] = ch;
                li.put(ch, box);
                generateWords(cc + 1, str, spots, li);
                li.put(ch, lsi);
                spots[box] = null;
            }
        }

    }

    // i>index,usr->unique,ssf->selected so fat,ts->total selec
    public static void combination(int i, String ustr, int ssf, int ts, String asf) {
        if (i == ustr.length()) {
            if (ssf == ts) {
                System.out.println(asf);
            }
            return;
        }
        char ch = ustr.charAt(i);
        // yes call
        combination(i + 1, ustr, ssf + 1, ts, asf + ch);
        // no call
        combination(i + 1, ustr, ssf, ts, asf);
    }

    // lc->last character
    public static void combination(String ustr, int ssf, int ts, String asf, int lc) {
        if (ssf == ts) {
            System.out.println(asf);
            return;
        }

        for (int i = lc + 1; i < ustr.length(); i++) {
            char ch = ustr.charAt(i);
            combination(ustr, ssf + 1, ts, asf + ch, i);
        }
    }

    public static void permute(String ustr, int ssf, int i, Character slots[]) {
        if (i == ustr.length()) {
            if (ssf == slots.length) {
                for (char c : slots) {
                    System.out.print(c);
                }
                System.out.println();
            }
            return;
        }
        char ch = ustr.charAt(i);

        for (int s = 0; s < slots.length; s++) {
            if (slots[s] == null) {
                slots[s] = ch;
                permute(ustr, ssf + 1, i + 1, slots);
                slots[s] = null;
            }
        }

        permute(ustr, ssf, i + 1, slots);
    }

    // words klength words 2
    public static void permute(String ustr, HashSet<Character> vis, int cs, int ts, String asf) {
        if (cs == ts) {
            System.out.println(asf);
            return;
        }
        for (int i = 0; i < ustr.length(); i++) {
            char ch = ustr.charAt(i);
            if (vis.contains(ch) == false) {
                vis.add(ch); // place
                permute(ustr, vis, cs + 1, ts, asf + ch);
                vis.remove(ch);

            }
        }
    }

    // words-kselection3
    // cc->current character
    public static void combination(String ustr, int cc, HashMap<Character, Integer> fmap, String asf, int k) {

        int ssf = asf.length();
        if (ssf == k) {
            System.out.println(asf);
            return;
        }

        if (cc == ustr.length())
            return;

        char ch = ustr.charAt(cc);
        int freq = fmap.get(ch);

        for (int i = freq; i > 0; i--) {
            if (i + ssf <= k) {
                String str = "";
                for (int j = 0; j < i; j++) {
                    str += ch;
                }
                combination(ustr, cc + 1, fmap, asf + str, k);
            }
        }

        combination(ustr, cc + 1, fmap, asf, k);

    }

    // wordks-k-selection-4
    // Words - K Selection - 4, li->last index, cs-> current spot, ts-> total spot
    public static void combination(String ustr, HashMap<Character, Integer> fmap, int li, String asf, int cs, int ts) {

        if (cs == ts) {
            System.out.println(asf);
            return;
        }

        for (int i = li; i < ustr.length(); i++) {
            char ch = ustr.charAt(i);
            int freq = fmap.get(ch);
            if (freq > 0) {
                fmap.put(ch, freq - 1);
                combination(ustr, fmap, li, asf + ch, cs + 1, ts);
                fmap.put(ch, freq);
            }
        }
    }

    // words -k selection words -3
    public static void words_k_selection_words3(String str, int cci, int ssf, int ts, Character[] slots,
            HashMap<Character, Integer> map) {

        if (cci == str.length()) {
            if (ssf == ts) {
                for (char ch : slots)
                    System.out.print(ch);
                System.out.println();
            }

            return;
        }

        char ch = str.charAt(cci); // ch->current character
        int loc = map.get(ch); // loc->stands for last occurence of character

        // yes call
        for (int i = loc + 1; i < slots.length; i++) {
            // change last occurence of character
            if (slots[i] == null) {
                map.put(ch, i);
                slots[i] = ch;
                words_k_selection_words3(str, cci + 1, ssf + 1, ts, slots, map);
                slots[i] = null;
                // reset last occurence of character
                map.put(ch, loc);
            }
        }
        if (loc == -1) {
            words_k_selection_words3(str, cci + 1, ssf, ts, slots, map);
        }

    }

    public static void Words_K_Length_Words_4(String ustr, int cc, int ts, String asf,
            HashMap<Character, Integer> fmap) {
        // cc->current slots ts->total slots
        if (cc == ts) {
            System.out.println(asf);
            return;
        }
        for (int i = 0; i < ustr.length(); i++) {
            char ch = ustr.charAt(i);
            int freq = fmap.get(ch);
            if (fmap.get(ch) > 0) {
                fmap.put(ch, freq - 1);
                Words_K_Length_Words_4(ustr, cc + 1, ts, asf + ch, fmap);
                fmap.put(ch, freq);
            }
        }

    }

    // Coin Change - Combinations - 1
    public static void coinChange(int i, int[] coins, int amtsf, int tamt, String asf) {
        // write your code here
        if (amtsf == tamt) {
            System.out.println(asf + ".");
            return;
        }

        if (i == coins.length)
            return;

        if (amtsf + coins[i] <= tamt)
            coinChange(i + 1, coins, amtsf + coins[i], tamt, asf + coins[i] + "-");

        coinChange(i + 1, coins, amtsf, tamt, asf);

    }

    // Coin Change - Combinations - 2
    public static void coinChange2(int i, int[] coins, int amtsf, int tamt, String asf) {
        // write your code here
        if (amtsf == tamt) {
            System.out.println(asf + ".");
            return;
        }

        if (i == coins.length)
            return;

        if (amtsf + coins[i] <= tamt)
            coinChange2(i, coins, amtsf + coins[i], tamt, asf + coins[i] + "-");

        coinChange2(i + 1, coins, amtsf, tamt, asf);

    }

    // Coin Change - Permutations - 1
    public static void coinChange(int[] coins, int amtsf, int tamt, String asf, boolean[] used) {
        // write your code here
        if (amtsf == tamt) {
            System.out.println(asf + ".");
            return;
        }
        for (int i = 0; i < coins.length; i++) {
            if (used[i] == false) {
                used[i] = true;
                if (amtsf + coins[i] <= tamt) {
                    coinChange(coins, amtsf + coins[i], tamt, asf + coins[i] + "-", used);
                }
                used[i] = false;
            }
        }
    }

    // Coin Change - Permutations - 2
    public static void coinChange(int[] coins, int amtsf, int tamt, String asf) {
        // write your code here
        if (amtsf == tamt) {
            System.out.println(asf + ".");
            return;
        }

        for (int coin : coins) {
            if (amtsf + coin <= tamt) {
                coinChange(coins, amtsf + coin, tamt, asf + coin + "-");
            }
        }
    }

    // Lexicographical Numbers
    public static void Lexicography(int val, int n) {
        // base case
        if (val > n)
            return;

        // self printing
        System.out.println(val);
        // family
        for (int i = 0; i < 10; i++)
            Lexicography(10 * val + i, n);
    }

    // Gold Mine
    static int max = 0;
    static int xdir[] = { -1, 0, 1, 0 };
    static int ydir[] = { 0, -1, 0, 1 };

    public static void getMaxGold(int[][] arr) {
        // write your code here
        boolean vis[][] = new boolean[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] > 0) {
                    int res = dfs(arr, i, j);
                    max = Math.max(max, res);
                }
            }
        }

    }

    public static int dfs(int arr[][], int i, int j) {
        // mark
        int gold = arr[i][j];
        arr[i][j] *= -1;

        // visit in neighttbours
        for (int d = 0; d < xdir.length; d++) {
            int r = i + xdir[d];
            int c = j + ydir[d];
            if (r >= 0 && r < arr.length && c >= 0 && c < arr[0].length) {
                if (arr[r][c] > 0) {
                    gold += dfs(arr, r, c);
                }
            }
        }
        return gold;
    }

    public static void display(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean isSafeToPlacenum(int[][] board, int row, int col, int num) {
        // check in row
        for (int c = 0; c < board[0].length; c++) {
            if (board[row][c] == num)
                return false;
        }
        // check in col
        for (int r = 0; r < board.length; r++) {
            if (board[r][col] == num)
                return false;
        }

        // check in sub matrix
        // rr and cc is starting point of sub matrix
        int rr = row - (row % 3);
        int cc = col - (col % 3);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + rr][j + cc] == num)
                    return false;
            }
        }

        return true;
    }

    public static void solveSudoku(int[][] board, int i, int j) {
        if (i == board.length) {
            display(board);
            return;
        }
        // write yopur code here
        if (board[i][j] == 0) {
            for (int num = 1; num < 10; num++) {
                if (isSafeToPlacenum(board, i, j, num)) {
                    // place
                    board[i][j] = num;
                    if (j == board[0].length - 1)
                        solveSudoku(board, i + 1, 0);
                    else
                        solveSudoku(board, i, j + 1);

                    // unplace
                    board[i][j] = 0;
                }
            }
        } else {

            if (j == board[0].length - 1)
                solveSudoku(board, i + 1, 0);
            else
                solveSudoku(board, i, j + 1);
        }
    }

    public static void solveSudoku1(int board[][], ArrayList<Integer> list, int idx) {

        if (idx == list.size()) {
            display(board);
            return;
        }

        int bno = list.get(idx);
        int r = bno / board.length;
        int c = bno % board.length;

        for (int num = 1; num < 10; num++) {
            if (isSafeToPlacenum(board, r, c, num)) {
                board[r][c] = num;
                solveSudoku1(board, list, idx + 1);
                board[r][c] = 0;
            }
        }

    }

    public static void solveSudoku1D(int[][] board) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 0) {
                    int bno = (board.length * i) + j;
                    list.add(bno);
                }

            }
        }
        solveSudoku1(board, list, 0);
    }

    public static int IntegerFromMap(String str, HashMap<Character, Integer> charIntMap) {

        int num = 0;

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            int n = charIntMap.get(ch);
            num = (num * 10 + n);
        }
        return num;
    }

    // cyrptomatic
    // s1->SEND s2->MORE s3MONEY unique=SENDMORE
    public static void solution(String unique, int idx, HashMap<Character, Integer> charIntMap, boolean[] usedNumbers,
            String s1, String s2, String s3) {
        if (idx == unique.length()) {
            int n1 = IntegerFromMap(s1, charIntMap);
            int n2 = IntegerFromMap(s2, charIntMap);
            int n3 = IntegerFromMap(s3, charIntMap);

            if (n1 + n2 == n3) {
                // print mapping in sorted order;
                for (int i = 0; i < 26; i++) {
                    char ch = (char) (i + 'a');
                    if (charIntMap.containsKey(ch) == true) {
                        System.out.print(ch + "-" + charIntMap.get(ch) + " ");
                    }
                }
                System.out.println();
            }
            return;
        }
        // write your code here
        char ch = unique.charAt(idx);

        for (int i = 0; i < 10; i++) {
            if (usedNumbers[i] == false) {
                usedNumbers[i] = true;
                charIntMap.put(ch, i);
                solution(unique, idx + 1, charIntMap, usedNumbers, s1, s2, s3);
                usedNumbers[i] = false;
                charIntMap.remove(ch);
            }

        }
    }

    // public static boolean canPlaceHorizontal(char[][]grid,int r,int c,String
    // word){

    // }

    // public static boolean canPlaceVertical(char[][]grid,int r,int c,String word){

    // }

    // public static boolean[]placehorizontal(char[][]grid,int r,int c,String word){

    // }

    // public static boolean[]placevertical(char[][]grid,int r,int c,String word){

    // }

    // public static void unplacehorizontal(char[][]grid,int r,int c,boolean
    // status){

    // }

    // public static void unplacevertical(char[][]grid,int r,int c,boolean status){

    // }

    // public static void solution(char[][]grid,String[]words,int vidx){

    // String word=words[vidx];
    // for(int i=0;i<grid.length;i++){
    // for(int j=0;j<grid[0].length;j++){
    // if(grid[i][j]=='-' || grid[i][j]==word.charAt(0)){
    // //horizontal
    // if(canPlaceHorizontal(grid,i,j,word)){
    // //place
    // boolean[]status=placehorizontal(grid,i,j,word);
    // //unplace
    // unplacehorizontal(grid, i, j, status);
    // }

    // //vertical try
    // if(canPlaceVertical(grid,, i, j, word)){
    // //place
    // placevertical(grid,i,j,word);

    // //unplace
    // }
    // }
    // }
    // }
    // }

    // K-partitions
    public static void solution(int i, int n, int k, int rssf, ArrayList<ArrayList<Integer>> ans) {
        if (i > n) {
            if (ans.size() == k) {
                System.out.print(count + ". ");
                for (int j = 0; j < ans.size(); j++) {
                    ArrayList<Integer> list = ans.get(j);
                    System.out.print(list + " ");
                }
                System.out.println();
                count++;
            }
            return;
        }
        // n - 1, k work, add with previous options
        for (int j = 0; j < ans.size(); j++) {
            ArrayList<Integer> list = ans.get(j);
            list.add(i);
            solution(i + 1, n, k, rssf, ans);
            list.remove(list.size() - 1);
        }

        // n - 1, k - 1, start from myself if size + 1 <= k
        if (ans.size() + 1 <= k) {
            ArrayList<Integer> mres = new ArrayList<>();
            mres.add(i);
            ans.add(mres);
            solution(i + 1, n, k, rssf, ans);
            ans.remove(ans.size() - 1);
        }
    }

    // magnets
    public static int signCountInRow(char[][] ans, int row, char sign) {
        int count = 0;
        for (int c = 0; c < ans[0].length; c++) {
            if (ans[row][c] == sign) {
                count++;
            }
        }
        return count;
    }

    public static int signCountInCol(char[][] ans, int col, char sign) {
        int count = 0;
        for (int r = 0; r < ans.length; r++) {
            if (ans[r][col] == sign) {
                count++;
            }
        }
        return count;
    }

    public static boolean isValid(char[][] ans, int[] top, int[] left, int[] right, int[] bottom, int r, int c,
            char sign) {
        // make a check for valid polarity
        int[] xdir = { -1, 0, 0 };
        int[] ydir = { 0, 1, -1 };
        for (int d = 0; d < 3; d++) {
            int rr = r + xdir[d];
            int cc = c + ydir[d];
            if (rr >= 0 && rr < ans.length && cc >= 0 && cc < ans[0].length && ans[rr][cc] == sign) {
                return false;
            }
        }
        // make a check for valid sign count in row and col
        int cir = signCountInRow(ans, r, sign); // cir -> count in row
        int cic = signCountInCol(ans, c, sign); // cic -> count in column

        // top and left -> +ve sign, bottom ans right -> -ve sign
        if (sign == '+') {
            if ((top[c] != -1 && cic >= top[c]) || (left[r] != -1 && cir >= left[r])) {
                return false;
            }
        } else {
            if ((bottom[c] != -1 && cic >= bottom[c]) || (right[r] != -1 && cir >= right[r])) {
                return false;
            }
        }
        return true;
    }

    public static boolean isCorrectResult(char[][] ans, int[] top, int[] left, int[] bottom, int[] right) {
        // check for row
        for (int r = 0; r < ans.length; r++) {
            int pcount = 0; // positive count
            int ncount = 0; // negative count
            for (int c = 0; c < ans[0].length; c++) {
                if (ans[r][c] == '+')
                    pcount++;
                else if (ans[r][c] == '-')
                    ncount++;
            }
            if (left[r] != -1 && left[r] != pcount)
                return false;
            if (right[r] != -1 && right[r] != ncount)
                return false;
        }
        // check for col
        for (int c = 0; c < ans[0].length; c++) {
            int pcount = 0; // positive count
            int ncount = 0; // negative count
            for (int r = 0; r < ans.length; r++) {
                if (ans[r][c] == '+')
                    pcount++;
                else if (ans[r][c] == '-')
                    ncount++;
            }
            if (top[c] != -1 && top[c] != pcount)
                return false;
            if (bottom[c] != -1 && bottom[c] != ncount)
                return false;
        }
        return true;
    }

    public static boolean solution(char[][] arr, int[] top, int[] left, int[] right, int[] bottom, char[][] ans,
            int row, int col) {
        if (col == arr[0].length) {
            col = 0;
            row++;
        }
        if (row == ans.length) {
            if (isCorrectResult(ans, top, left, bottom, right))
                return true;
            else
                return false;
        }
        // yes call
        if (arr[row][col] == 'L') {
            // [L | R] -> + -
            if (isValid(ans, top, left, right, bottom, row, col, '+')
                    && isValid(ans, top, left, right, bottom, row, col + 1, '-')) {
                // place + -
                ans[row][col] = '+';
                ans[row][col + 1] = '-';
                if (solution(arr, top, left, right, bottom, ans, row, col + 2) == true) {
                    return true;
                }
                // unplace + -
                ans[row][col] = 'X';
                ans[row][col + 1] = 'X';
            }
            // [L | R] -> - +
            if (isValid(ans, top, left, right, bottom, row, col, '-')
                    && isValid(ans, top, left, right, bottom, row, col + 1, '+')) {
                // place - +
                ans[row][col] = '-';
                ans[row][col + 1] = '+';
                if (solution(arr, top, left, right, bottom, ans, row, col + 2) == true) {
                    return true;
                }
                // unplace - +
                ans[row][col] = 'X';
                ans[row][col + 1] = 'X';
            }
        } else if (arr[row][col] == 'T') {
            // [T | B] -> + -
            if (isValid(ans, top, left, right, bottom, row, col, '+')
                    && isValid(ans, top, left, right, bottom, row + 1, col, '-')) {
                // place + -
                ans[row][col] = '+';
                ans[row + 1][col] = '-';
                if (solution(arr, top, left, right, bottom, ans, row, col + 1) == true) {
                    return true;
                }
                // unplace + -
                ans[row][col] = 'X';
                ans[row + 1][col] = 'X';
            }
            // [T | B] -> - +
            if (isValid(ans, top, left, right, bottom, row, col, '-')
                    && isValid(ans, top, left, right, bottom, row + 1, col, '+')) {
                // place - +
                ans[row][col] = '-';
                ans[row + 1][col] = '+';
                if (solution(arr, top, left, right, bottom, ans, row, col + 1) == true) {
                    return true;
                }
                // unplace - +
                ans[row][col] = 'X';
                ans[row + 1][col] = 'X';
            }
        }
        // no call
        if (solution(arr, top, left, right, bottom, ans, row, col + 1)) {
            return true;
        }
        return false;
    }

    // crossword puzzle
    public static boolean canPlaceHorizontal(char[][] grid, int r, int c, String word) {
        // left check
        if (c > 0 && grid[r][c - 1] != '+') {
            return false;
        }
        // right check
        if (c - 1 + word.length() >= grid[0].length) {
            return false;
        }

        if ((c - 1 + word.length() < grid[0].length - 1) && (grid[r][c + word.length()] != '+')) {
            return false;
        }
        for (int j = 0; j < word.length(); j++) {
            if (grid[r][j + c] != '-' && grid[r][j + c] != word.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    public static boolean canPlaceVertical(char[][] grid, int r, int c, String word) {
        // top check
        if (r > 0 && grid[r - 1][c] != '+') {
            return false;
        }
        // bottom check
        if (r - 1 + word.length() >= grid.length) {
            return false;
        }

        if ((r - 1 + word.length() < grid.length - 1) && (grid[r + word.length()][c] != '+')) {
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            if (grid[i + r][c] != '-' && grid[i + r][c] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean[] placeHorizontal(char[][] grid, int r, int c, String word) {
        boolean[] status = new boolean[word.length()];
        for (int j = 0; j < word.length(); j++) {
            if (grid[r][c + j] == '-') {
                grid[r][c + j] = word.charAt(j);
                status[j] = true;
            }
        }
        return status;
    }

    public static boolean[] placeVertical(char[][] grid, int r, int c, String word) {
        boolean[] status = new boolean[word.length()];
        for (int i = 0; i < word.length(); i++) {
            if (grid[r + i][c] == '-') {
                grid[r + i][c] = word.charAt(i);
                status[i] = true;
            }
        }
        return status;
    }

    public static void unplaceHorizontal(char[][] grid, int r, int c, boolean[] status) {
        for (int i = 0; i < status.length; i++) {
            if (status[i] == true) {
                grid[r][c + i] = '-';
            }
        }
    }

    public static void unplaceVertical(char[][] grid, int r, int c, boolean[] status) {
        for (int i = 0; i < status.length; i++) {
            if (status[i] == true) {
                grid[r + i][c] = '-';
            }
        }
    }

    public static void print(char[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }

    public static void solution(char[][] grid, String[] words, int vidx) {
        if (vidx == words.length) {
            // print
            print(grid);
            return;
        }

        String word = words[vidx];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '-' || grid[i][j] == word.charAt(0)) {
                    // horizontal try
                    if (canPlaceHorizontal(grid, i, j, word)) {
                        // place
                        boolean[] status = placeHorizontal(grid, i, j, word);
                        solution(grid, words, vidx + 1);
                        // unplace
                        unplaceHorizontal(grid, i, j, status);
                    }
                    // vertical try
                    if (canPlaceVertical(grid, i, j, word)) {
                        // place
                        boolean[] status = placeVertical(grid, i, j, word);
                        solution(grid, words, vidx + 1);
                        // unplace
                        unplaceVertical(grid, i, j, status);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

    }
}
import java.util.*;

public class Dp {

    //stock buy and sell one transaction
    static int buyandsell1trans(int arr[]) {
        int msf = arr[0];
        int profit = 0;
        for (int val : arr) {
            if (val > msf) {
                profit = Math.max(profit, val - msf);
            } else {
                msf = val;
            }
        }
        return profit;
    }

    //stock buy and sell infinite transactions
    static int buyandsellinfinitetrans(int arr[]) {
        int bd = 0;
        int sd = 0;
        int profit = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1]) {
                sd = i;
            } else {
                profit += arr[sd] - arr[bd];
                bd = i;
                sd = i;
            }
        }
        profit += arr[sd] - arr[bd];
        return profit;
    }

    //stock buy and sell with infinite trans but trans fee allowed
    static int stockbuyandellinftransandfees(int arr[], int fees) {
        int pwb = -arr[0];
        int pws = 0;
        for (int i = 1; i < arr.length; i++) {
            int npwb = pws - arr[i];
            int npws = pwb + arr[i] - fees;
            pwb = Math.max(pwb, npwb);
            pws = Math.max(pws, npws);
        }
        return pws;

    }

    //stock buy and sell infinite with cool down period
    static int stockbuyandellinftranscooldow(int arr[]) {
        int pwb = -arr[0];
        int pws = 0;
        int pwc = 0;//profit with cooldown
        for (int i = 1; i < arr.length; i++) {
            int npwb = pwc - arr[i];
            pwc = pws;
            int npws = pwb + arr[i];
            pwb = Math.max(pwb, npwb);
            pws = Math.max(pws, npws);

        }
        return pws;

    }

    //stock and buy sell with two transactions allowed
    static int stockbuyselltwotransallowed(int arr[]) {
        int pws[] = new int[arr.length];
        int msf = arr[0];
        for (int i = 1; i < arr.length; i++) {
            msf = Math.min(msf, arr[i]);
            pws[i] = Math.max(pws[i - 1], arr[i] - msf);
        }
        int profit = 0;
        int max = Integer.MIN_VALUE;
        for (int i = arr.length - 1; i >= 0; i--) {
            max = Math.max(max, arr[i]);
            int profitfrombuytoday = max - arr[i];
            profit = Math.max(profit, pws[i] + profitfrombuytoday);
        }
        return profit;


    }

    //stock buy and sell with k transactions allowed

    //Maximum length of pair chain
    public static int findLongestChain(int[][] pairs) {
        //Write your code here
        Arrays.sort(pairs, (a, b) -> Integer.compare(a[0], b[0]));
        int dp[] = new int[pairs.length];
        dp[0] = 1;
        for (int i = 1; i < pairs.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (pairs[i][0] > pairs[j][1] && dp[j] + 1 > dp[i]) dp[i] = dp[j] + 1;
            }
        }
        int ans = 1;
        for (int val : dp)
            ans = Math.max(ans, val);
        return ans;

    }

    //188. Best Time to Buy and Sell Stock IV
    //https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (k == 0 || n <= 1) return 0;
        if (k * 2 > n) {
            int profit = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
            }
            return profit;
        }

        //case 3
        int dp[] = new int[2 * k + 1];
        dp[0] = -prices[0];
        for (int i = 1; i <= 2 * k; i++) {
            if (i % 2 == 0) dp[i] = Integer.MIN_VALUE;
            else continue;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2 * k; j++) {
                if (j == 0) dp[j] = Math.max(dp[j], -prices[i]);
                else if (j % 2 == 0) {
                    //even its buying time
                    dp[j] = Math.max(dp[j], dp[j - 1] - prices[i]);
                } else {
                    //its selling time
                    dp[j] = Math.max(dp[j], dp[j - 1] + prices[i]);

                }
            }
        }
        return dp[2 * k - 1];

    }

    //longest increasing sequence
    static int lis(int arr[]) {
        int dp[] = new int[arr.length];
        dp[0] = 1;

        for (int i = 1; i < arr.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < dp.length; i++)
            ans = Math.max(ans, dp[i]);

        return ans;
    }

    //Longest bionic Sequence
    int lbs(int arr[]) {
        int n = arr.length;
        int lis[] = new int[arr.length];
        lis[0] = 1;
        for (int i = 1; i < n; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) max = Math.max(max, lis[j]);
            }
            lis[i] = max + 1;
        }

        int lds[] = new int[arr.length];
        lds[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            int max = 0;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) max = Math.max(max, lds[j]);
            }
            lds[i] = max + 1;
        }

        int ans = 0;
        for (int i = 0; i < lis.length; i++) {
            ans = Math.max(ans, lis[i] + lds[i] - 1);
        }
        return ans;
    }

    //Box Stacking
    //https://practice.geeksforgeeks.org/problems/box-stacking/1/
    public static int maxHeight(int[] height, int[] width, int[] length, int n) {
        // Code here
        Box arr[] = new Box[3 * n];
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (length[i] > width[i]) {
                arr[j++] = new Box(length[i], width[i], height[i]);
            } else {
                arr[j++] = new Box(width[i], length[i], height[i]);
            }
            //HBL
            if (height[i] > width[i]) {
                arr[j++] = new Box(height[i], width[i], length[i]);
            } else {
                arr[j++] = new Box(width[i], height[i], length[i]);
            }

            //LHB
            if (length[i] > height[i]) {
                arr[j++] = new Box(length[i], height[i], width[i]);
            } else {
                arr[j++] = new Box(height[i], length[i], width[i]);
            }
        }
        Arrays.sort(arr);

        //perform lis to get max height;
        int dp[] = new int[3 * n];
        dp[0] = arr[0].h;
        int maxheight = dp[0];
        for (int i = 1; i < arr.length; i++) {
            dp[i] = arr[i].h;
            for (int k = 0; k < i; k++) {
                if (arr[k].l > arr[i].l & arr[k].b > arr[i].b && dp[i] < dp[k] + arr[i].h) dp[i] = dp[k] + arr[i].h;
            }
        }
        for (int i = 0; i < dp.length; i++)
            maxheight = Math.max(maxheight, dp[i]);
        return maxheight;
    }

    //https://leetcode.com/problems/maximum-height-by-stacking-cuboids/
    public int maxHeight(int[][] cuboids) {
        Box[] arr = new Box[cuboids.length];
        int j = 0;
        for (int box[] : cuboids) {
            Arrays.sort(box);
            arr[j++] = new Box(box[0], box[1], box[2]);
        }
        Arrays.sort(arr);
        int dp[] = new int[cuboids.length];
        int maxheight = dp[0];
        for (int i = 1; i < cuboids.length; i++) {
            dp[i] = arr[i].h;
            for (int k = 0; k < i; k++) {
                if (arr[k].l >= arr[i].l && arr[k].b >= arr[i].b && dp[k] + arr[i].h >= arr[i].h)
                    dp[i] = dp[k] + arr[i].h;
            }
        }

        for (int i = 0; i < dp.length; i++)
            maxheight = Math.max(maxheight, dp[i]);
        return maxheight;

    }


    static class Box implements Comparable<Box> {
        int l;
        int b;
        int h;
        int area;

        Box(int l, int b, int h) {
            this.l = l;
            this.b = b;
            this.h = h;
            this.area = this.l * this.b;
        }

        public int compareTo(Box o) {
            return o.area - this.area;
        }
    }

    //https://leetcode.com/problems/palindromic-substrings/
    public int countSubstrings(String s) {
        int n = s.length();
        boolean dp[][] = new boolean[n][n];
        int count = 0;

        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (gap == 0) {
                    dp[i][j] = true;
                } else if (gap == 1) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
                }
                if (dp[i][j]) count++;
            }
        }
        return count;
    }

    //https://leetcode.com/problems/longest-palindromic-substring/
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean dp[][] = new boolean[n][n];
        int x = 0;
        int y = 0;

        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (gap == 0) {
                    dp[i][j] = true;
                } else if (gap == 1) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
                }
                if (dp[i][j]) {
                    x = i;
                    y = j;
                }
            }
        }
        return s.substring(x, y + 1);

    }

    //print all paths with minimum jumps
    private static class MJHelper {
        int idx;
        int jumps;
        int minjumps;
        String psf;

        public MJHelper(int idx, int jumps, int minjumps, String psf) {
            this.idx = idx;
            this.jumps = jumps;
            this.minjumps = minjumps;
            this.psf = psf;

        }

    }

    public static void printallpathswithminjumps(int arr[]) {
        // write your code here
        int n = arr.length;
        int dp[] = new int[n];
        dp[n - 1] = 0;
        for (int i = n - 2; i >= 0; i--) {
            int minjumps = Integer.MAX_VALUE;
            for (int jump = 1; jump <= arr[i] && i + jump < n; i++) {
                minjumps = Math.min(minjumps, dp[i] + jump);
            }
            dp[i] = minjumps != Integer.MAX_VALUE ? minjumps + 1 : minjumps;
        }
        System.out.println(dp[0]);
        Queue<MJHelper> qu = new LinkedList<>();
        qu.add(new MJHelper(0, arr[0], dp[0], "0"));
        while (qu.size() > 0) {
            MJHelper rem = qu.remove();
            if (rem.idx == n - 1) {
                System.out.println(rem.psf + " .");
                continue;
            }
            for (int jump = 1; jump < rem.jumps && jump + rem.idx < n; jump++) {
                if (dp[rem.idx + jump] == rem.minjumps - 1) {
                    int nidx = rem.idx + jump;
                    qu.add(new MJHelper(nidx, arr[nidx], rem.minjumps - 1, rem.psf + " -> " + nidx));
                }
            }
        }

    }

    //Print All Paths With Minimum Cost

    private static class Pair {
        String psf;
        int i;
        int j;

        public Pair(String psf, int i, int j) {
            this.psf = psf;
            this.i = i;
            this.j = j;
        }
    }


    private static void printAllMinCost(int grid[][]) {
        int dp[][] = new int[grid.length][grid[0].length];
        int n = grid.length;
        int m = grid[0].length;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (i == n - 1 && j == m - 1) {
                    dp[i][j] = grid[i][j];
                } else if (i == n - 1) {
                    dp[i][j] = grid[i][j] + dp[i][j + 1];
                } else if (j == m - 1) {
                    dp[i][j] = grid[i][j] + dp[i + 1][j];
                } else {
                    dp[i][j] = Math.min(dp[i][j + 1], dp[i + 1][j]) + grid[i][j];
                }
            }
        }
        System.out.println(dp[0][0]);

        Queue<Pair> qu = new LinkedList<>();
        qu.add(new Pair("", 0, 0));
        while (qu.size() > 0) {
            Pair rem = qu.remove();
            int i = rem.i;
            int j = rem.j;

            if (i == n - 1 && j == m - 1) {
                System.out.println(rem.psf);
            } else if (i == n - 1) {
                qu.add(new Pair(rem.psf + "H", i, j + 1));
            } else if (j == m - 1) {
                qu.add(new Pair(rem.psf + "V", i + 1, j));
            } else {
                if (dp[i + 1][j] == dp[i][j + 1]) {
                    //addH
                    qu.add(new Pair(rem.psf + "H", i, j + 1));
                    //addV
                    qu.add(new Pair(rem.psf + "V", i + 1, j));
                } else if (dp[i + 1][j] < dp[i][j + 1]) {
                    //add V
                    qu.add(new Pair(rem.psf + "V", i + 1, j));
                } else {
                    //add H
                    qu.add(new Pair(rem.psf + "H", i, j + 1));

                }
            }
        }
    }

    public static void main(String[] args) {

    }
}

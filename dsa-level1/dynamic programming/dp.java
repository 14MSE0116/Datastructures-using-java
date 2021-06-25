/**
 * dp
 */
public class dp {

    public static int fib_rec(int n, int[] dp) {
        if (n == 0 || n == 1) {
            return dp[n] = n;
        }

        // if problem is already solved,then return the answer
        if (dp[n] != 0) {
            return dp[n];
        }
        int fibnm1 = fib_rec(n - 1, dp);
        int fibnm2 = fib_rec(n - 2, dp);
        int fibn = fibnm1 + fibnm2;

        // 2.if not solved then solve the problem and store it in the dp array
        return dp[n] = fibn;
    }

    public static int fib_tab(int n, int dp[]) {
        // 1.figure out repetition->repitition on n
        // 2.make a storage of size n+1
        // 3.Assign meaning to the cell->dp[i]=fib[i]=fib[i-1]+fib[i-2];
        // 4.smallest problem is at n=0;
        // 5.prereq->base case

        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];

    }

    public static int climbStair_tab(int n, int[] dp) {
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            int count = 0;
            for (int j = 1; j <= 3; j++) {
                if (i - j >= 0) {
                    count += dp[i - j];
                }
            }
            dp[i] = count;
        }
        return dp[n];

    }

    public static int climbStair_tab1(int n, int[] dp) {
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            int count = 0;
            if (i >= 3) {
                dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
            } else if (i >= 2) {
                dp[i] = dp[i - 1] + dp[i - 2];
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[n];

    }

    public static int climbStair_memo(int n, int[] dp) {
        if (n == 0) {
            return dp[0] = 1;
        }

        if (dp[n] != 0)
            return dp[n];

        int count = 0;
        for (int j = 1; j <= 3; j++) {
            if (n - j >= 0) {
                count += climbstair_rec(n - j);
            }
        }
        return dp[n] = count;

    }

    public static int climbstair_rec(int n) {
        if (n == 0) {
            return 1;
        }
        int count = 0;
        for (int j = 1; j <= 3; j++) {
            if (n - j >= 0) {
                count += climbstair_rec(n - j);
            }
        }
        return count;
    }

    public static void climbStair() {
        int n = 4;
        int res = climbstair_rec(n);
        System.out.println(res);
    }

    // i>current stair;
    // n->total stair
    // jumps[]->jumps allowed at
    public static int climbstair_varjumps_rec(int i, int n, int[] jumps) {
        if (i == n) {
            return 1;
        }

        int count = 0;
        for (int jump = 1; jump <= jumps[i] && jump + i <= n; jump++) {
            count += climbstair_varjumps_rec(i + jump, n, jumps);
        }
        return count;
    }

    public static int climbstair_varjumps_memo(int i, int n, int[] jumps, int dp[]) {
        if (i == n) {
            return dp[i] = 1;
        }

        if (dp[i] != 0) {
            return dp[i];
        }

        int count = 0;
        for (int jump = 1; jump <= jumps[i] && jump + i <= n; jump++) {
            count += climbstair_varjumps_memo(i + jump, n, jumps, dp);
        }

        return dp[i] = count;
    }

    public static int climbStair_VarJumps_tab(int i, int n, int[] dp, int[] jumps) {
        for (i = n; i >= 0; i--) {
            if (i == n) {
                dp[i] = 1;
                continue;
            }

            int count = 0;
            for (int jump = 1; jump <= jumps[i] && jump + i <= n; jump++) {
                count += dp[i + jump]; // climbStair_VarJumps_memo(i + jump, n, jumps, dp);
            }
            dp[i] = count;
        }
        return dp[0];
    }

    public static void ques() {
        climbStair();
    }

    public static void main(String[] args) {
        int n = 5;
        int dp[] = new int[n + 1];
        // int res = fib_rec(n, dp);
        int res = fib_tab(n, dp);
        System.out.println(res);
        ques();
        int dpp[] = new int[5];
        int climbstar = climbStair_tab(4, dpp);
        System.out.println(climbstar);
    }
}
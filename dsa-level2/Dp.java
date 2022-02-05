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
                if (pairs[i][0] > pairs[j][1] && dp[j] + 1 > dp[i])
                    dp[i] = dp[j] + 1;
            }
        }
        int ans = 1;
        for (int val : dp)
            ans = Math.max(ans, val);
        return ans;

    }


    public static void main(String[] args) {

    }
}

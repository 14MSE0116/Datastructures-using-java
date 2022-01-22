import java.util.*;

public class bits {
    String rightmostbit(int n) {
        return Integer.toBinaryString(n & -n);

    }

    //Kernighans Algorithm
    int countsetbits(int n) {
        int count = 0;
        while (n != 0) {
            int rsbm = n & (-n);
            n = n - rsbm;
            count++;
        }
        return count;
    }

    //Josehps special problem
    public static int solution(int n) {
        //write your code here
        //find 2^x;
        int i = 1;
        while (i * 2 <= n)
            i = i * 2;
        //we know that n=2^x+l
        int l = n - i;
        return 2 * l + 1;
    }

    //All Repeating Except Two
    public static void AllRepeatingExceptTwo(int[] arr) {
        //write your code here
        int xor = 0;
        for (int val : arr)
            xor ^= val;
        int rmb = xor & (-xor); //rmb->right  most significant bit
        int first = 0; //for rmb bit off
        int second = 0; //for rmb bit on
        for (int val : arr) {
            if ((val & rmb) == 0) {
                //bit is off
                first ^= val;
            } else {
                //bit is on
                second ^= val;

            }
        }
        System.out.println(first);
        System.out.println(second);

    }

    //one-repeating-and-one-missing-official
    public static void onerepeatingandonemissingofficial(int[] arr) {
        //write your code here
        int xor = 0;
        for (int val : arr) {
            xor ^= val;
        }

        for (int i = 1; i <= arr.length; i++)
            xor ^= i;
        int rbm = xor & (-xor);
        int x = 0;
        int y = 0;
        for (int val : arr) {
            if ((val & rbm) == 0) {
                //bit is off
                x = x ^ val;
            } else {
                //bit is on
                y = y ^ val;
            }
        }

        for (int val = 1; val < arr.length; val++) {
            if ((val & rbm) == 0) {
                //bit is off
                x = x ^ val;
            } else {
                //bit is on
                y = y ^ val;
            }
        }

        boolean xmiss = true;
        for (int val : arr) {
            if (x == val) {
                xmiss = false;
                break;
            }
        }
        if (xmiss == true) {
            System.out.println(x);
            System.out.println(y);
        } else {
            System.out.println(y);
            System.out.println(x);
        }

    }


    public static void main(String[] args) {


    }
}

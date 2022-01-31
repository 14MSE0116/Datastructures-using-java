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

    //All Repeating Three Times Except One
    public static void solution(int[] arr) {
        //write your code here
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int count = 0;
            int bm = 1 << i;
            for (int val : arr) {
                //check if ith bit is on or off in val
                if ((val & bm) == bm) {
                    count++;
                }
            }
            if (count % 3 == 1)
                res = res | bm;
        }
        System.out.println(res);

    }

    //Reduce N to 1
    public static int ReduceNto1(int n) {
        //write your code here
        int count = 0;
        while (n != 1) {
            if (n % 2 == 0)
                n = n / 2;
            else if (n == 3) {
                //special case
                n = n - 1;
            } else if (n % 4 == 1) {
                //odd l type
                n = n - 1;
            } else if (n % 4 == 3) {
                //odd 2 type
                n = n + 1;
            }
            count++;
        }
        return count;
    }

    static int xorsumofallpairs(int arr[]) {
        int res = 0;
        for (int val : arr)
            res ^= val;
        return 2 * res;
    }

    //Abbreviation 1 - Using Bits
    //approach 1
    public static void abbrevusingbits1(String str) {
        // write your code here
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < (1 << str.length()); i++) {
            int count = 0;
            for (int j = 0; j < str.length(); j++) {
                int k = str.length() - 1 - j; //bit index because bit index begins form right to left

                //check if kth bit is on or off in i
                int bm = 1 << k;
                char ch = str.charAt(j);
                if ((i & bm) == 0) {
                    //bit is off
                    if (count == 0) {
                        sb.append(ch);
                    } else {
                        sb.append(count);
                        sb.append(ch);
                        count = 0; //reset count
                    }
                } else {
                    //bit is on
                    count++;

                }
            }
            if (count != 0)
                sb.append(count);
            sb.append("\n");
        }
        System.out.println(sb.toString());

    }

    //Is A Power Of 2
    static boolean ispowerof2(int n) {
        int res = n & (n - 1);
        return res == 0;
    }

    //Is A Power of 4
    public boolean isPowerOfFour(int n) {
        int bm = 0b101010101010101010101010101010;
        return n > 0 && ((n & (n - 1)) == 0) && (n & bm) == 0;
    }

    //solve 7n/8
    //hint->use left shift and right shift operators
    static int multiplyBySevenByEight(int n) {
        int ans = n << 3;
        ans = ans - n;
        return ans >> 3;
    }

    public static void main(String[] args) {


    }
}

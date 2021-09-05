
/**
 * array
 */
import java.util.*;

import javax.sound.midi.Sequence;

public class array {

    // Faulty keyboard question
    public boolean isLongPressedName(String name, String typed) {
        if (name.length() > typed.length())
            return false;
        int i = 0; // name
        int j = 0; // typed
        while (i < name.length() && j < typed.length()) {
            if (name.charAt(i) == typed.charAt(j)) {
                i++;
                j++;

            } else if (i - 1 >= 0 && name.charAt(i - 1) == typed.charAt(j)) {
                j++;
            } else {
                return false;

            }
        }

        while (j < typed.length()) {
            if (typed.charAt(j) != name.charAt(i - 1))
                return false;
            j++;
        }

        return i < name.length() ? false : true;

    }

    // Container with most water
    public static int mostWater(int[] heights) {
        // write your code here
        int max = 0;
        int i = 0;
        int j = heights.length - 1;
        while (i < j) {
            if (heights[i] < heights[j]) {

                int l = j - i;
                max = Math.max(max, l * heights[i]);
                i++;

            } else {
                int l = j - i;
                max = Math.max(max, l * heights[j]);
                j--;

            }
        }
        return max;
    }

    // sort Squares of sorted array in O(N)
    public int[] sortedSquares(int[] nums) {
        int res[] = new int[nums.length];
        int i = 0;
        int j = nums.length - 1;
        for (int k = nums.length - 1; k >= 0; k--) {
            int val1 = nums[i];
            int val2 = nums[j];
            if (val1 * val1 > val2 * val2) {
                res[k] = val1 * val1;
                i++;
            } else {
                res[k] = val2 * val2;
                j--;
            }
        }
        return res;

    }

    // majority elemnt 1
    public static void printMajorityElement(int[] arr) {
        // write your code here
        int count = 0;
        int me = findpotential(arr);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == me)
                count++;
        }
        if (count > arr.length / 2)
            System.out.println(me);
        else
            System.out.println("No Majority Element exist");
    }

    public static int findpotential(int[] arr) {
        int count = 1;
        int val = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == val)
                count++;
            else {
                if (count > 0)
                    count--;
                else {
                    val = arr[i];
                    count = 1;
                }
            }
        }
        return val;

    }

    public static ArrayList<Integer> majorityElement2(int[] arr) {
        // find valid canidate
        int val1 = arr[0];
        int count1 = 1;

        int val2 = arr[0];
        int count2 = 0;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == val1) {
                count1++;
            } else if (arr[i] == val2)
                count2++;
            else {
                if (count1 == 0) {
                    val1 = arr[i];
                    count1 = 1;
                } else if (count2 == 0) {
                    val2 = arr[i];
                    count2 = 1;
                } else {
                    count1--;
                    count2--;
                }
            }
        }

        // check freq>n/3
        ArrayList<Integer> list = new ArrayList<>();
        if (count1 > 0 && count1 > arr.length / 3)
            list.add(count1);
        if (count2 > 0 && count2 > arr.length / 3)
            list.add(count2);
        return list;

    }

    // next greater permutation or next greater element 3
    public static String nextGreaterElement(String str) {
        // write your code here
        char[] arr = str.toCharArray();
        int dipidx = dipIdx(arr);
        if (dipidx == -1)
            return "-1";

        int ceilidx = ceilIndx(arr, dipidx);
        swap(arr, dipidx, ceilidx);
        reverse(arr, dipidx + 1);
        return String.valueOf(arr);
    }

    static int dipIdx(char arr[]) {
        int idx = arr.length - 2;
        while (idx >= 0 && arr[idx] >= arr[idx + 1])
            idx--;

        return idx;

    }

    static int ceilIndx(char[] arr, int idx) {
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] > arr[idx])
                return i;
        }
        return -1;
    }

    static void reverse(char arr[], int idx) {
        int l = idx;
        int r = arr.length - 1;
        while (l < r) {
            swap(arr, l, r);
            l++;
            r--;
        }

    }

    static void swap(char arr[], int idx1, int idx2) {
        char temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;

    }

    // max product of three numbers
    public int maximumProduct(int[] arr) {

        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for (int val : arr) {
            if (val >= max1) {
                max3 = max2;
                max2 = max1;
                max1 = val;
            } else if (val >= max2) {
                max3 = max2;
                max2 = val;
            } else if (val >= max3) {
                max3 = val;
            }

            if (val <= min1) {
                min2 = min1;
                min1 = val;
            } else if (val <= min2) {
                min2 = val;
            }
        }

        return Math.max(max1 * max2 * max3, min1 * min2 * max1);

    }

    // leetcode 769. Max Chunks To Make Sorted->guaranteed all are distinct elements
    public int maxChunksToSorted1(int[] arr) {
        int chunks = 0;
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            if (max == i)
                chunks++;
        }

        return chunks;

    }

    // leetcode768. Max Chunks To Make Sorted II
    public int maxChunksToSorted2(int[] arr) {
        int lmax = Integer.MIN_VALUE;
        int rmin[] = new int[arr.length + 1];
        rmin[arr.length] = Integer.MAX_VALUE;
        int chunks = 0;
        for (int i = arr.length - 1; i >= 0; i--)
            rmin[i] = Math.min(rmin[i + 1], arr[i]);

        for (int i = 0; i < arr.length; i++) {
            lmax = Math.max(lmax, arr[i]);
            if (lmax <= rmin[i + 1])
                chunks++;
        }
        return chunks;

    }

    // Reverse vowels of string-leetcode 345
    public String reverseVowels(String s) {
        char[] arr = s.toCharArray();
        int l = 0;
        int r = arr.length - 1;
        while (l < r) {
            while (l < r && !isvowel(arr[l])) {
                l++;
            }
            while (l < r && !isvowel(arr[r])) {
                r--;
            }
            char temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            l++;
            r--;

        }
        return String.valueOf(arr);

    }

    // Leetcode->238. Product of Array Except Self
    public int[] productExceptSelf(int[] nums) {
        int lmul[] = new int[nums.length];
        int rmul[] = new int[nums.length];
        lmul[0] = 1;

        rmul[nums.length - 1] = 1;
        for (int i = 1; i < nums.length; i++) {
            lmul[i] = lmul[i - 1] * nums[i - 1];
        }

        for (int i = nums.length - 2; i >= 0; i--) {
            rmul[i] = rmul[i + 1] * nums[i + 1];
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = lmul[i] * rmul[i];
        }
        return nums;
    }

    static boolean isvowel(char ch) {
        if (ch == 'a' || ch == 'A' || ch == 'e' || ch == 'E' || ch == 'i' || ch == 'I' || ch == 'o' || ch == 'O'
                || ch == 'u' || ch == 'U')
            return true;
        else
            return false;
    }

    // Wiggel sort-1
    public static void wiggleSort(int[] arr) {
        // write your code here
        for (int i = 0; i < arr.length - 1; i++) {
            if (i % 2 == 0) {
                // even idx;
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);

                }
            } else {
                if (arr[i + 1] > arr[i])
                    swap(arr, i, i + 1);
            }
        }
    }

    public static void wiggleSort2(int[] nums) {
        // write your code here
        int n = nums.length;
        int arr[] = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = nums[i];
        Arrays.sort(arr);
        int j = n - 1;
        int i = 1;
        // Fill odd indexes
        while (i < n) {
            nums[i] = arr[j];
            j--;
            i += 2;
        }
        i = 0;
        while (i < n) {
            nums[i] = arr[j];
            j--;
            i = i + 2;
        }

    }

    // leetcode 75->overall sub array
    public static int numSubarrayBoundedMax(int[] arr, int left, int right) {
        // write your code here
        int prev_count = 0;
        int overall_count = 0;
        int i = 0;
        int j = 0;
        while (j < arr.length) {
            if (left <= arr[j] && arr[j] <= right) {
                prev_count = j - i + 1;
                overall_count += prev_count;
            } else if (arr[j] < left)
                overall_count += prev_count;
            else {
                prev_count = 0;
                i = j + 1;
            }
            j++;
        }
        return overall_count;
    }

    static void swap(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public int maxDistToClosest(int[] seats) {
        int dist = 0;
        int zeros = 0;

        int idx = 0;
        // left sub part
        while (seats[idx] != 1) {
            idx++;
            zeros++;
        }
        dist = zeros;
        idx++;

        // segment calculation
        while (idx < seats.length) {
            zeros = 0;
            while (idx < seats.length && seats[idx] != 1) {
                zeros++;
                idx++;
            }

            if (idx == seats.length)
                break;

            idx++;
            dist = Math.max(dist, (zeros + 1) / 2);
        }

        // right sub part
        return Math.max(zeros, dist);

    }

    // First Missing positive
    public int firstMissingPositive(int[] nums) {
        // Find occurence of one and mark out of range
        boolean one = false;
        int n = nums.length;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1)
                one = true;

            if (nums[i] < 1 || nums[i] > nums.length)
                nums[i] = 1;
        }

        if (one == false)
            return 1;

        // mark element in array
        for (int i = 0; i < n; i++) {
            int val = Math.abs(nums[i]);
            int idx = val - 1;
            nums[idx] = -Math.abs(nums[idx]);
        }

        // check missing postive
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0)
                return i + 1;
        }
        return n + 1;

    }

    // Seggregate odd Even in arr
    // Leetcode905. Sort Array By Parity
    public int[] sortArrayByParity(int[] nums) {
        int i = 0;
        int j = 0;
        while (i < nums.length) {
            if (nums[i] % 2 == 0) {
                swap(i, j, nums);
                i++;
                j++;
            } else {
                i++;
            }
        }
        return nums;

    }

    static void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // Range addition
    public static int[] getModifiedArray(int length, int[][] queries) {
        // write your code here
        int res[] = new int[length];
        for (int arr[] : queries) {
            int st = arr[0];
            int end = arr[1];
            for (int i = st; i <= end; i++) {
                res[i] += arr[2];
            }
        }
        return res;
    }

    // Optimised
    public static int[] getModifiedArrayop(int length, int[][] queries) {
        // write your code here
        int res[] = new int[length];
        // make impact of query on res
        for (int arr[] : queries) {
            int st = arr[0];
            int end = arr[1];
            int incre = arr[2];
            res[st] += incre;
            if (end != length - 1) {
                res[end + 1] -= incre;
            }
        }

        // prefix sum
        for (int i = 1; i < res.length; i++) {
            res[i] += res[i - 1];
        }
        return res;
    }

    // Meeting Point Minimal Distance
    public static int minTotalDistance(int[][] grid) {
        // Write your code here
        // Fill x coordinates in sorted manner
        ArrayList<Integer> xcord = new ArrayList<>();
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1)
                    xcord.add(r);
            }
        }
        ArrayList<Integer> ycord = new ArrayList<>();
        // Fill y coordinate in sorted manner
        for (int c = 0; c < grid[0].length; c++) {
            for (int r = 0; r < grid.length; r++) {
                if (grid[r][c] == 1)
                    ycord.add(c);
            }
        }
        // find meeting point
        int x = xcord.get(xcord.size() / 2);
        int y = ycord.get(ycord.size() / 2);
        int dist = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) {
                    dist += Math.abs(x - r) + Math.abs(y - c);
                }
            }
        }
        return dist;

    }

    public static void main(String[] args) {

    }
}
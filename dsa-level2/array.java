
/**
 * array
 */
import java.util.*;

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

    public static void main(String[] args) {

    }
}
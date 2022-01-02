import java.util.*;
import java.io.*;

public class HashMapp {
    //Number Of Employees Under Every Manager
    static int getsize(HashMap<String, HashSet<String>> map, String ceo) {
        if (map.get(ceo) == null) {
            System.out.println(ceo + " " + 0);
            return 1;
        }
        int count = 0;
        for (String child : map.get(ceo)) {
            count += getsize(map, child);
        }
        System.out.println(ceo + " " + count);
        return count + 1;
    }

    // Find Iternnary from tickets
    static void prinpath(HashMap<String, String> tickets) {
        // find starting point
        HashMap<String, Boolean> map = new HashMap<>();
        for (String city1 : tickets.keySet()) {
            String city2 = tickets.get(city1);
            map.put(city2, false);
            if (map.containsKey(city1) == false)
                map.put(city1, true);
        }
        String src = "";
        for (String city : map.keySet()) {
            if (map.get(city)) {
                src = city;
                break;
            }
        }

        String path = "";
        while (tickets.containsKey(src) == true) {
            path += src + "->";
            src = tickets.get(src);
        }
        path += src + ".";
        System.out.println(path);

    }

    // Count Distinct Elements In Every Window Of Size K
    public static ArrayList<Integer> solution(int[] arr, int k) {
        // write your code here
        ArrayList<Integer> res = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        int j = 0;
        for (int i = 0; i < k - 1; i++)
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);

        for (int i = k - 1; i < arr.length; i++, j++) {
            // add impact of ith index
            int fq = map.getOrDefault(arr[i], 0);
            map.put(arr[i], fq + 1);
            // make result
            res.add(map.size());

            // remove impact of j
            if (map.get(arr[j]) == 1) {
                map.remove(arr[j]);
            } else
                map.put(arr[j], map.get(arr[j]) - 1);
        }
        return res;

    }

    // Check If An Array Can Be Divided Into Pairs Whose Sum Is Divisible By K
    public static void ispairingpossible(int[] arr, int k) {
        // write your code here
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int fq = map.getOrDefault(arr[i] % k, 0);
            map.put(arr[i] % k, fq + 1);
        }
        boolean res = true;
        for (int rem1 : map.keySet()) {
            if (rem1 == 0 || (k % 2 == 0 && rem1 == k / 2)) {
                if (map.get(rem1) % 2 != 0) {
                    res = false;
                }
            } else {
                if (map.containsKey(k - rem1) == false || map.get(rem1) != map.get(k - rem1)) {
                    res = false;
                    break;
                }
            }
        }
        System.out.println(res);

    }

    //https://practice.geeksforgeeks.org/problems/largest-subarray-with-0-sum/1
    //Largest Subarray with 0 sum
    int maxLen(int arr[], int n) {
        // Your code here
        int len = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int psum = 0;
        for (int i = 0; i < n; i++) {
            psum += arr[i];
            if (map.containsKey(psum)) {
                len = Math.max(len, i - map.get(psum));
            } else
                map.put(psum, i);
        }
        return len;
    }

    //count of subarrays with zero sum
    public static int solution(int[] arr) {
        // write your code here
        int res = 0;
        int psum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            psum += arr[i];
            if (map.containsKey(psum)) {
                res += map.get(psum);
                map.put(psum, map.get(psum) + 1);
            } else {
                map.put(psum, 1);
            }
        }

        return res;
    }

    //https://leetcode.com/problems/contiguous-array/
    //Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.
    public int findMaxLength(int[] nums) {
        int psum = 0;
        int len = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                psum += -1;
            } else {
                psum += 1;
            }
            if (map.containsKey(psum)) {
                len = Math.max(len, i - map.get(psum));
            } else {
                map.put(psum, i);
            }
        }
        return len;

    }

    //https://practice.geeksforgeeks.org/problems/count-subarrays-with-equal-number-of-1s-and-0s-1587115620/1
    //Subarrays with equal 1s and 0s
    static int countSubarrWithEqualZeroAndOne(int arr[], int n) {
        // add your code here
        HashMap<Integer, Integer> map = new HashMap<>();
        int psum = 0;
        int count = 0;
        map.put(0, 1);
        for (int i = 0; i < n; i++) {
            if (arr[i] == 0) {
                psum -= 1;
            } else
                psum += 1;

            if (map.containsKey(psum)) {
                count += map.get(psum);
                map.put(psum, map.get(psum) + 1);
            } else
                map.put(psum, 1);

        }
        return count;
    }

    //Maximum Size Subarray Sum Equals K->Portal question
    public static int lenOfLongSubarr(int A[], int N, int K) {
        //Complete the function
        int psum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int len = 0;
        for (int i = 0; i < A.length; i++) {
            psum += A[i];
            if (map.containsKey(psum - K)) {
                len = Math.max(len, i - map.get(psum - K));
            } else if (map.containsKey(psum) == false) {
                map.put(psum, i);
            }
        }
        return len;
    }

    //Count Of Subarrays Having Sum Equals To K
    //leetcode 560
    public static int solution1(int[] arr, int k) {
        // write your code here
        HashMap<Integer, Integer> map = new HashMap<>();
        int psum = 0;
        map.put(0, 1);
        int count = 0;
        for (int val : arr) {
            psum += val;
            if (map.containsKey(psum - k)) {
                count += map.get(psum - k);
            }
            map.put(psum, map.getOrDefault(psum, 0) + 1);

        }

        return count;
    }

    //Longest Subarray With Sum Divisible By K
    //https://practice.geeksforgeeks.org/problems/longest-subarray-with-sum-divisible-by-k1259/1
    int longSubarrWthSumDivByK(int a[], int n, int k) {
        // Complete the function
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int psum = 0;
        int len = 0;
        for (int i = 0; i < a.length; i++) {
            psum += a[i];
            int rem = psum % k;
            if (map.containsKey(rem)) {
                len = Math.max(len, i - map.get(rem));
            } else {
                map.put(rem, i);
            }
        }
        return len;
    }

    //https://leetcode.com/problems/subarray-sums-divisible-by-k/
    public int subarraysDivByK(int[] nums, int k) {
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int psum = 0;
        for (int val : nums) {
            psum += val;
            int rem = psum % k;
            rem = rem < 0 ? rem + k : rem;

            if (map.containsKey(rem)) {
                count += map.get(rem);
            }
            map.put(rem, map.getOrDefault(rem, 0) + 1);
        }
        return count;

    }

    //Longest Subarray With Equal Number Of 0s 1s And 2s
    public static int longsubarrayequal012(int[] arr) {
        // write your code here
        int count0 = 0;
        int count1 = 0;
        int count2 = 0;
        int deta10 = count1 - count0;
        int deta21 = count2 - count1;
        String key = deta21 + "#" + deta10;
        HashMap<String, Integer> map = new HashMap<>();
        map.put(key, -1);
        int len = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                count0++;
            } else if (arr[i] == 1) {
                count1++;
            } else if (arr[i] == 2) {
                count2++;
            }
            deta10 = count1 - count0;
            deta21 = count2 - count1;
            key = deta21 + "#" + deta10;

            if (map.containsKey(key)) {
                len = Math.max(len, i - map.get(key));
            } else {
                map.put(key, i);
            }

        }

        return len;
    }

    //Count Of Subarrays With Equal Number Of 0s 1s And 2s
    public static int countsubarrayequal012(int[] arr) {
        // write your code here
        int count = 0;
        int count0 = 0;
        int count1 = 0;
        int count2 = 0;
        int delta21 = count2 - count1;
        int delta10 = count1 - count0;
        String key = delta21 + "#" + delta10;
        HashMap<String, Integer> map = new HashMap<>();
        map.put(key, 1);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                count0++;
            } else if (arr[i] == 1) count1++;
            else if (arr[i] == 2) count2++;
            delta21 = count2 - count1;
            delta10 = count1 - count0;
            key = delta21 + "#" + delta10;
            if (map.containsKey(key)) {
                count += map.get(key);
            }
            map.put(key, map.getOrDefault(key, 0) + 1);

        }
        return count;
    }

    //complete task->Portal question
    public static void completeTask(int n, int m, int[] arr) {
        // write your code here
        HashSet<Integer> set = new HashSet<>();
        for (int val : arr)
            set.add(val);

        List<Integer> one = new ArrayList<>();
        List<Integer> two = new ArrayList<>();
        boolean flag = true;
        for (int i = 1; i <= n; i++) {
            if (set.contains(i) == false) {
                if (flag) {
                    one.add(i);
                } else {
                    two.add(i);
                }
                flag = !flag;
            }
        }

        for (int val : one)
            System.out.print(val + " ");

        System.out.println();

        for (int val : two)
            System.out.print(val + " ");

    }


    public static void main(String[] args) {

    }

}

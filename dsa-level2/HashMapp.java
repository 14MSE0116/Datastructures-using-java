import java.lang.management.MemoryType;
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
            if (map.containsKey(city1) == false) map.put(city1, true);
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
            } else map.put(arr[j], map.get(arr[j]) - 1);
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
            } else map.put(psum, i);
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
            } else psum += 1;

            if (map.containsKey(psum)) {
                count += map.get(psum);
                map.put(psum, map.get(psum) + 1);
            } else map.put(psum, 1);

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

    //ACQUIRE AND HOLD STRATEGY
    //the smallest substring of string containing all characters of another string
    public static String solution(String s1, String s2) {
        // write your code here

        //freq map for string 2
        HashMap<Character, Integer> fmap = new HashMap<>();
        for (int i = 0; i < s2.length(); i++)
            fmap.put(s2.charAt(i), fmap.getOrDefault(s2.charAt(i), 0) + 1);

        int acq = -1; //acquire
        int rel = -1; //release

        int count = 0;
        int requirement = s2.length();
        String ans = "";
        HashMap<Character, Integer> map = new HashMap<>();
        while (true) {
            boolean acflag = false;
            boolean relflag = false;
            //acquire
            while (acq < s1.length() - 1 && count < requirement) {
                acq++;
                char ch = s1.charAt(acq);
                map.put(ch, map.getOrDefault(ch, 0) + 1);

                //conditional increment in count
                if (map.get(ch) <= fmap.getOrDefault(ch, 0)) count++;
                acflag = true;

            }


            //release
            while (rel < acq && count == requirement) {
                //hold answer,if smallest then update the result
                String tempans = s1.substring(rel + 1, acq + 1);
                ans = ans.length() == 0 || tempans.length() < ans.length() ? tempans : ans;

                //get the character and remove from map
                rel++;
                char ch = s1.charAt(rel);
                map.put(ch, map.get(ch) - 1);

                if (map.get(ch) == 0) map.remove(ch);

                //decrement in count if release is invalid
                if (map.getOrDefault(ch, 0) < fmap.getOrDefault(ch, 0)) count--;

                relflag = true;
            }

            if (acflag == false && relflag == false) break;
        }


        return null;
    }

    public static int smallestuniquesubstritself(String str) {
        // write your code here
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < str.length(); i++)
            set.add(str.charAt(i));

        int acq = -1;
        int rel = -1;
        HashMap<Character, Integer> map = new HashMap<>();
        int ans = Integer.MAX_VALUE;
        while (true) {
            boolean acflag = false;
            boolean reflag = false;
            //acquire
            while (acq + 1 < str.length() && map.size() < set.size()) {
                acq++;
                char ch = str.charAt(acq);
                map.put(ch, map.getOrDefault(ch, 0) + 1);
                acflag = true;
            }

            //release
            while (map.size() == set.size()) {
                int templen = acq - rel;
                ans = Math.min(ans, templen);

                rel++;
                char ch = str.charAt(rel);
                map.put(ch, map.get(ch) - 1);
                if (map.get(ch) == 0) map.remove(ch);
                reflag = true;
            }

            if (acflag == false && reflag == false) break;
        }
        return ans;
    }

    //Longest Substring with non repeating characters
    public static int longsubstrwithnonrepeatchar(String str) {
        // write your code here
        HashMap<Character, Integer> map = new HashMap<>();
        int rel = -1;
        int acq = -1;
        int anslen = 0;
        while (true) {
            boolean acflag = false;
            boolean reflag = false;

            //acquire
            while (acq + 1 < str.length()) {
                acq++;
                acflag = true;
                char ch = str.charAt(acq);
                map.put(ch, map.getOrDefault(ch, 0) + 1);

                if (map.get(ch) == 2) break;
                else {
                    int templen = acq - rel;
                    if (templen > anslen) anslen = templen;
                }

            }

            //release
            while (rel < acq) {
                rel++;
                char ch = str.charAt(rel);
                map.put(ch, map.get(ch) - 1);
                if (map.get(ch) == 1) {
                    //after reducing if freq is 1 that means,its repeating
                    break;
                }
                reflag = true;
            }
            if (acflag == false && reflag == false) break;

        }
        return anslen;
    }

    //Count Of Substrings Having All Unique Characters
    public static int solution(String str) {
        // write your code here

        return 0;
    }

    //K Anagram-Checker->Nados portal question
    public static boolean areKAnagrams(String str1, String str2, int k) {
        // write your code here
        if (str1.length() != str2.length()) return false;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str1.length(); i++) {
            char ch = str1.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        //reduce mapping from s2
        for (int i = 0; i < str2.length(); i++) {
            char ch = str2.charAt(i);
            if (map.getOrDefault(ch, 0) > 0) map.put(ch, map.get(ch) - 1);
        }

        int count = 0;
        for (char ch : map.keySet())
            count += map.get(ch);

        return count <= k;
    }

    //group anagrams together
    //https://practice.geeksforgeeks.org/problems/print-anagrams-together/1
    public List<List<String>> Anagrams(String[] string_list) {
        // Code here
        HashMap<HashMap<Character, Integer>, List<String>> map = new HashMap<>();
        for (String val : string_list) {
            HashMap<Character, Integer> fmap = new HashMap<>();
            for (int i = 0; i < val.length(); i++) {
                char ch = val.charAt(i);
                fmap.put(ch, fmap.getOrDefault(ch, 0) + 1);
            }
            if (map.containsKey(fmap)) {
                map.get(fmap).add(val);
            } else {
                List<String> list = new ArrayList<>();
                list.add(val);
                map.put(fmap, list);
            }
        }

        List<List<String>> res = new ArrayList<>();
        for (List<String> subres : map.values()) {
            res.add(subres);
        }
        return res;

    }

    //Check arithmetic seqeunce portal question(Nados)
    public static boolean solutionn(int[] arr) {
        //find min and max,add element in hashset
        HashSet<Integer> set = new HashSet<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int val : arr) {
            min = Math.min(val, min);
            max = Math.max(val, max);
            set.add(val);
        }

        int n = arr.length;
        int d = (max - min) / (n - 1);
        int sum = min;
        while (sum < max) {
            sum += d;
            if (set.contains(sum) == false)
                return false;
        }

        return true;
    }

    //166. Fraction to Recurring Decimal
    //https://leetcode.com/problems/fraction-to-recurring-decimal/
    public String fractionToDecimal(int numerator, int denominator) {
        StringBuilder ans = new StringBuilder();
        //for managing negative cases
        if ((numerator > 0 && denominator < 0) || (numerator < 0 && denominator > 0))
            ans.append("-");

        int q = numerator / denominator;
        int rem = numerator % denominator;
        ans.append(q);
        if (rem == 0)
            return ans.toString();
        ans.append(".");
        HashMap<Integer, Integer> map = new HashMap<>(); //for storing repeting
        //for charecters after decimal
        while (rem != 0) {
            map.put(rem, ans.length());
            rem *= 10;
            q = rem / denominator;
            rem = rem % denominator;
            ans.append(q);
            if (map.containsKey(rem)) {
                //insert brackets
                int si = map.get(rem);
                ans.insert(si, "(");
                ans.append(")");
                break;

            }
        }
        return ans.toString();
    }

    //portal question count equivalent subarrays
    int countequivsubarrays(int arr[]) {
        int n = arr.length;
        HashSet<Integer> set = new HashSet<>();
        for (int val : arr)
            set.add(val);
        int k = set.size();

        //solve for number of subarrays having k distinct elements
        int acq = -1;
        int rel = -1;
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        while (true) {
            boolean relflag = false;
            boolean acflag = false;
            while (acq < n - 1) {
                acflag = true;
                acq++;
                int val = arr[acq];
                map.put(val, map.getOrDefault(val, 0) + 1);
                if (map.size() == k) {
                    count += n - acq;
                    break;
                }
            }

            while (rel < acq) {
                relflag = true;
                rel++;
                int val = arr[rel];
                map.put(val, map.get(val) - 1);
                if (map.get(val) == 0) {
                    map.remove(val);
                }
                if (map.size() == k)
                    count += n - acq;
                else
                    break;

            }
            if (acflag == false && relflag == false)
                break;
        }
        return count;

    }

    //Pair with equal sum
    public static boolean pairwithequlsum(int[] arr) {
        // write your code here
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int sum = arr[i] + arr[j];
                if (set.contains(sum))
                    return true;
                set.add(sum);
            }
        }

        return false;
    }

    //Pair with given sum
    public static int solve(int[][] num1, int[][] num2, int k) {
        // write your code here
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;

        for (int i = 0; i < num1.length; i++) {
            for (int j = 0; j < num1[0].length; j++) {
                map.put(num1[i][j], map.getOrDefault(num1[i][j], 0) + 1);
            }
        }

        for (int i = 0; i < num2.length; i++) {
            for (int j = 0; j < num2[0].length; j++) {
                count += map.getOrDefault(k - num2[i][j], 0);
            }
        }


        return count;
    }


    public static void main(String[] args) {
        Integer s=Integer.valueOf(28);
        System.out.println(Integer.valueOf(280));
    }

}

import java.util.*;
import java.io.*;

public class Sns {
    public static void main(String[] args) {
        System.out.println("Searching and Sorting class");
    }

    //https://practice.geeksforgeeks.org/problems/marks-of-pcm2529/1
    public void customSort(int phy[], int chem[], int math[], int N) {
        // your code here
        Student sarr[] = new Student[phy.length];
        for (int i = 0; i < phy.length; i++) {
            sarr[i] = new Student(phy[i], chem[i], math[i]);
        }
        Arrays.sort(sarr);
        for (int i = 0; i < phy.length; i++) {
            phy[i] = sarr[i].phy;
            chem[i] = sarr[i].chem;
            math[i] = sarr[i].maths;
        }
    }

    static class Student implements Comparable<Student> {
        int phy;
        int chem;
        int maths;

        Student(int phy, int chem, int maths) {
            this.phy = phy;
            this.chem = chem;
            this.maths = maths;
        }

        public int compareTo(Student o) {
            if (this.phy == o.phy) {
                if (this.chem == o.chem) {
                    return this.maths - o.maths;
                } else
                    return o.chem - this.chem;
            } else
                return this.phy - o.phy;
        }

        //https://practice.geeksforgeeks.org/problems/union-of-two-sorted-arrays/1
        public static ArrayList<Integer> findUnion(int arr1[], int arr2[], int n, int m) {
            // add your code here
            ArrayList<Integer> res = new ArrayList<>();
            int i = 0;
            int j = 0;
            while (i < arr1.length && j < arr2.length) {
                if (arr1[i] == arr2[j]) {
                    if (res.size() == 0 || res.get(res.size() - 1) != arr1[i])
                        res.add(arr1[i]);
                    i++;
                    j++;

                } else if (arr1[i] < arr2[j]) {
                    if (res.size() == 0 || res.get(res.size() - 1) != arr1[i])
                        res.add(arr1[i]);
                    i++;


                } else {
                    if (res.size() == 0 || res.get(res.size() - 1) != arr2[j])
                        res.add(arr2[j]);
                    j++;

                }
            }

            //either i will remain or j will remain
            while (i < n) {
                if (res.size() == 0 || res.get(res.size() - 1) != arr1[i])
                    res.add(arr1[i]);
                i++;
            }

            while (j < m) {
                if (res.size() == 0 || res.get(res.size() - 1) != arr2[j])
                    res.add(arr2[j]);
                j++;
            }

            return res;


        }
    }

    //https://leetcode.com/problems/find-pivot-index/
    public static int pivot_index(int[] arr) {
        //write your code here
        int psum = 0;
        for (int i = 0; i < arr.length; i++)
            psum += arr[i];
        int lsum = 0;
        for (int i = 0; i < arr.length; i++) {
            psum -= arr[i];
            if (lsum == psum)
                return i;
            lsum += arr[i];

        }
        return -1;
    }

    public static void findPair(int[] arr, int target) {
        //write your code here
    }

    //https://practice.geeksforgeeks.org/problems/max-sum-in-the-configuration/1
    int max_sum(int A[], int n) {
        // Your code here
        int mod = (int) 1e9 + 7;
        Arrays.sort(A);
        long sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum = sum + (A[i] * i);
        }
        return (int) sum % mod;
    }

    //Find Transition point
    public static int findTransition(int[] arr) {
        //write your code here
        int lo = 0;
        int hi = arr.length - 1;
        int ans = -1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (arr[mid] == 1) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return ans;
    }

    //find inversion count
    static int count;

    private static int countInversion(int[] arr) {
        count = 0;
        int[] res = mergesort(arr, 0, arr.length - 1);
        return count;
    }

    static int[] mergesort(int arr[], int lo, int hi) {

        if (lo == hi) {
            int base[] = new int[1];
            base[0] = arr[lo];
            return base;
        }

        int mid = lo + (hi - lo) / 2;
        int l[] = mergesort(arr, lo, mid);
        int r[] = mergesort(arr, mid + 1, hi);
        int res[] = merge(l, r);
        return res;

    }

    static int[] merge(int l[], int r[]) {
        int n1 = l.length;
        int n2 = r.length;
        int res[] = new int[n1 + n2];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < l.length && j < r.length) {
            if (l[i] <= r[j]) {
                res[k++] = l[i++];
            } else {
                count += n1 - i;
                res[k++] = r[j++];
            }
        }

        while (i < l.length)
            res[k++] = l[i++];

        while (j < r.length)
            res[k++] = r[j++];

        return res;
    }

    //median of two sorted arrays
    //https://leetcode.com/problems/median-of-two-sorted-arrays/

    public static double find(int[] a1, int[] a2) {
        //write your code here
        if (a2.length < a1.length)
            return find(a2, a1);
        int n1 = a1.length;
        int n2 = a2.length;
        int lo = 0;
        int hi = n1;

        while (lo <= hi) {
            int mid1 = lo + (hi - lo) / 2;
            int mid2 = (n1 + n2 + 1) / 2 - mid1;

            int left1 = mid1 == 0 ? Integer.MIN_VALUE : a1[mid1 - 1];
            int right1 = mid1 == n1 ? Integer.MAX_VALUE : a1[mid1];

            int left2 = mid2 == 0 ? Integer.MIN_VALUE : a2[mid2 - 1];
            int right2 = mid2 == n2 ? Integer.MAX_VALUE : a2[mid2];

            if (left1 <= right1 && left2 <= right1) {
                if ((n1 + n2) % 2 == 0) {
                    return (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0;
                } else {
                    return Math.max(left1, left2) / 1.0;
                }
            } else if (left1 > right2) {
                hi = mid1 - 1;
            } else {
                lo = mid1 + 1;
            }

        }

        return 0.0;

    }

    //https://leetcode.com/problems/koko-eating-bananas/
    public int minEatingSpeed(int[] piles, int h) {

        int lo = 1;
        int hi = -1;
        for (int val : piles)
            hi = Math.max(hi, val);
        if (h == piles.length)
            return hi;
        int ans = hi;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (ispossibletoeat(piles, mid, h)) {
                ans = mid;
                hi = mid - 1;
            } else
                lo = mid + 1;
        }
        return ans;


    }

    static boolean ispossibletoeat(int piles[], int speed, int h) {
        int time = 0;
        for (int i = 0; i < piles.length; i++) {
            time += (int) Math.ceil(piles[i] * 1.0 / speed);

        }

        return time <= h;


    }

    //https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/submissions/
    public static int findSmallestDivisor(int[] nums, int th) {
        //write your code here
        int lo = 0;
        int hi = -1;
        for (int val : nums)
            hi = Math.max(hi, val);

        int ans = hi;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (ispossible(nums, mid, th)) {
                ans = mid;
                hi = mid - 1;
            } else
                lo = mid + 1;
        }
        return ans;

    }

    static boolean ispossible(int nums[], int div, int th) {
        int sum = 0;
        for (int val : nums) {
            sum += (int) Math.ceil(val * 1.0 / div);
        }
        return sum <= th;
    }

    //https://practice.geeksforgeeks.org/problems/allocate-minimum-number-of-pages0937/1
    public static int minPages(int[] arr, int m) {
        //write your code here
        int lo;
        int hi;
        lo = -(int) 1e9;
        hi = 0;
        if (m > arr.length) return -1;
        for (int i = 0; i < arr.length; i++) {
            lo = Math.max(lo, arr[i]);
            hi += arr[i];

        }
        int ans = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (isallocpossbile(arr, mid, m)) {

                ans = mid;
                hi = mid - 1;
            } else
                lo = mid + 1;
        }
        return ans;

    }

    static boolean isallocpossbile(int arr[], int mid, int pages) {
        int cnt = 1;
        int sum = 0;
        for (int val : arr) {
            sum += val;
            if (sum > mid) {
                cnt++;
                sum = val;
            }
        }
        return cnt <= pages;

    }

    //https://leetcode.com/problems/split-array-largest-sum/
    public int splitArray(int[] nums, int m) {
        int lo = -(int) 1e9;
        int hi = 0;
        for (int val : nums) {
            lo = Math.max(lo, val);
            hi += val;
        }
        int ans = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (issplitpossible(nums, mid, m)) {
                ans = mid;
                hi = mid - 1;
            } else
                lo = mid + 1;
        }
        return ans;

    }

    static boolean issplitpossible(int nums[], int mid, int m) {
        int sum = 0;
        int cnt = 1;
        for (int val : nums) {
            sum += val;
            if (sum > mid) {
                cnt++;
                sum = val;
            }
        }
        return cnt <= m;
    }

    //Count zeros in sorted matrix
    public static int countZeros(int[][] mat) {
        //write your code here
        int count = 0;
        int i = 0;
        int j = mat[0].length - 1;
        while (i < mat.length && j >= 0) {
            if (mat[i][j] == 0) {
                count += j + 1;
                i++;
            } else if (mat[i][j] == 1) {
                j--;
            }
        }

        return count;
    }

    //Counting Elements In Two Arrays
    public static int[] findcount(int[] arr1, int[] arr2) {
        //write your code here
        int res[] = new int[arr1.length];
        Arrays.sort(arr2);
        int i = 0;
        while (i < arr1.length) {
            int val = arr1[i];
            int idx = ceilidx(arr2, val);
            res[i] = idx == -1 ? arr2.length : idx;
            i++;
        }
        return res;
    }

    static int ceilidx(int arr[], int val) {
        int lo = 0;
        int hi = arr.length - 1;
        int res = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] <= val) {
                lo = mid + 1;
            } else if (arr[mid] > val) {
                res = mid;
                hi = mid - 1;
            }

        }
        return res;
    }

    //count pairs,portal
    public static int countPairs(int[] arr) {
        //write your code here
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;

        for (int val : arr) {
            map.put(val, map.getOrDefault(val, 0) + 1);
        }

        for (int key : map.keySet()) {
            int k = map.get(key);
            count += k * (k - 1) / 2;
        }
        return count;
    }

    //Facing the sun portal
    public static int countBuildings(int[] ht) {
        //write your code here
        int max = -(int) 1e9;
        int count = 0;
        for (int val : ht) {
            if (val > max) {
                count++;
                max = val;
            }
        }
        return count;
    }

    //https://practice.geeksforgeeks.org/problems/distinct-absolute-array-elements4529/1
    int distinctCount(int[] arr, int n) {
        // code here
        int prev = -(int) 1e9;
        int next = (int) 1e9;
        int i = 0;
        int j = arr.length - 1;
        int count = 0;
        while (i <= j) {
            if (Math.abs(arr[i]) == Math.abs(arr[j])) {
                if (arr[i] != prev && arr[j] != next)
                    count++;
                prev = arr[i];
                next = arr[j];
                i++;
                j--;
            } else if (Math.abs(arr[i]) < Math.abs(arr[j])) {
                if (arr[j] != next)
                    count++;
                next = arr[j];
                j--;
            } else {
                if (arr[i] != prev)
                    count++;
                prev = arr[i];
                i++;
            }
        }
        return count;
    }

    //portal question
    public static int findSingleElement(int[] arr) {
        //write your code here
        int idx = -1;
        int lo = 0;
        int hi = arr.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (mid == 0 || mid == arr.length - 1) {
                idx = mid;
                break;
            } else if (arr[mid] != arr[mid - 1] && arr[mid] != arr[mid + 1]) {
                idx = mid;
                break;
            } else if (arr[mid] == arr[mid + 1]) {
                if ((hi - mid + 1) % 2 == 0) {
                    hi = mid - 1;
                } else {
                    lo = mid;
                }
            } else {
                if ((mid - lo + 1) % 2 == 0) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }

            }
        }
        return arr[idx];
    }

    //https://leetcode.com/problems/largest-perimeter-triangle/
    public int largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        int a = 0;
        int b = 0;
        int c = 0;
        int idx = nums.length - 3;
        while (idx >= 0) {
            a = nums[idx];
            b = nums[idx + 1];
            c = nums[idx + 2];
            if (a + b > c)
                return a + b + c;
            idx--;
        }
        return 0;
    }

    //portal quesion
    public static String largestNumber(int[] nums) {
        //write your code here
        String sarr[] = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            sarr[i] = nums[i] + "";
        }
        Arrays.sort(sarr, (a, b) -> {
            long d1 = Long.parseLong(a + b);
            long d2 = Long.parseLong(b + a);
            if (d1 > d2) {
                return -1;
            } else if (d1 < d2)
                return 1;
            else
                return 0;
        });
        StringBuilder sb = new StringBuilder();
        for (int i = sarr.length - 1; i >= 0; i--)
            sb.append(sarr[i]);
        return sb.toString();
    }

    //ishan and sticks-portal question
    public static ArrayList<Integer> solve(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int val : arr)
            map.put(val, map.getOrDefault(val, 0) + 1);
        int resarea = 0;
        int count = 0;
        for (int key : map.keySet()) {
            int side = key;
            if (map.get(key) >= 4) {
                int area = side * side;
                if (area > resarea) {
                    resarea = area;
                    count = map.get(key) / 4;
                }
            }
        }
        ArrayList<Integer> res = new ArrayList<>();
        res.add(resarea);
        res.add(count);
        return res;

    }

    //https://practice.geeksforgeeks.org/problems/toppers-of-class3826/1
    void kTop(node arr[], int n) {
        // Your code goes here
        Arrays.sort(arr, (a, b) -> {
            if (a.marks != b.marks)
                return b.marks - a.marks;
            else
                return a.index - b.index;
        });

        int min = (int) 1e9;
        int count = 0;
        for (int i = 0; i < arr.length && count <= n; i++) {
            if (min > arr[i].marks)
                count++;
            System.out.println(arr[i].marks + " " + arr[i].index);
        }

    }

    class node {
        int marks;
        int index;

        public void setMarks(int a) {
            this.marks = a;
        }

        public void setIndex(int b) {
            this.index = b;
        }
    }

    //https://practice.geeksforgeeks.org/problems/leaders-in-an-array-1587115620/1
    static ArrayList<Integer> leaders(int arr[], int n) {
        // Your code here
        ArrayList<Integer> res = new ArrayList<>();
        int rmax = -(int) 1e9;
        for (int i = n - 1; i >= 0; i--) {
            if (arr[i] >= rmax) {
                res.add(arr[i]);
                rmax = arr[i];
            }
        }
        Collections.reverse(res);
        return res;
    }


}

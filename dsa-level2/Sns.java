import java.util.*;
import java.io.*;

public class Sns {
    public static void main(String[] args) {
        System.out.println("ehfh");
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

}

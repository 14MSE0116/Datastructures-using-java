import java.util.*;
import java.io.*;

public class usage {

    public static void printKLargest(int arr[], int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // step1 add k elements;
        for (int i = 0; i < k; i++)
            pq.add(arr[i]);

        // step2 Process remaining
        for (int i = k; i < arr.length; i++) {
            if (arr[i] > pq.peek()) {
                pq.remove();
                pq.add(arr[i]);
            }
        }
        while (pq.size() > 0) {
            System.out.println(pq.remove());
        }

        // step3 print k elements from priority queue

    }

    public static void ksorted(int arr[], int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        // add k elements
        for (int i = 0; i < k; i++)
            pq.add(arr[i]);

        // manage remaing
        for (int i = k; i < arr.length; i++) {
            // add
            pq.add(arr[i]);
            // remove
            System.out.println(pq.remove());
        }
        while (pq.size() > 0) {
            System.out.println(pq.remove());
        }

    }

    public static class MedianPriorityQueue {
        PriorityQueue<Integer> left;
        PriorityQueue<Integer> right;

        public MedianPriorityQueue() {
            left = new PriorityQueue<>(Collections.reverseOrder());
            right = new PriorityQueue<>();
        }

        public void add(int val) {
            // write your code here
            if (right.size() > 0 && right.peek() < val)
                right.add(val);

            else
                left.add(val);

            if ((left.size() - right.size()) == 2)
                right.add(left.remove());

            else if ((right.size() - left.size()) == 2)
                left.add(right.remove());
        }

        public int remove() {
            // write your code here
            if (this.size() == 0) {
                System.out.println("Underflow");
                return -1;
            } else if (right.size() > left.size())
                return right.remove();

            else
                return left.remove();
        }

        public int peek() {
            // write your code here
            if (this.size() == 0) {
                System.out.println("Underflow");
                return -1;
            }
            if (right.size() > left.size())
                return right.peek();

            else
                return left.peek();
        }

        public int size() {
            // write your code here
            return left.size() + right.size();
        }
    }

    public static class Pair implements Comparable<Pair> {
        int li;
        int di;
        int val;

        Pair(int li, int di, int val) {
            this.li = li;
            this.di = di;
            this.val = val;
        }

        public int compareTo(Pair p) {
            return this.val - p.val;
        }
    }

    public static ArrayList<Integer> mergeKSortedLists(ArrayList<ArrayList<Integer>> lists) {
        ArrayList<Integer> rv = new ArrayList<>();

        // write your code here

        // step1 add 0th element of each list into pair prioority queue;

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for (int i = 0; i < lists.size(); i++) {
            Pair p = new Pair(i, 0, lists.get(i).get(0));
            pq.add(p);
        }

        while (pq.size() > 0) {
            Pair p = pq.remove();
            rv.add(p.val);
            p.di++;
            if (p.di < lists.get(p.li).size()) {
                p.val = lists.get(p.li).get(p.di);
                pq.add(p);
            }
        }

        return rv;
    }

    public static void Question() {

    }

    public static void demo() {
        // default priority->min
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(10);
        pq.add(20);
        pq.add(9);
        pq.add(7);
        pq.add(11);
        while (pq.size() > 0) {
            int rem = pq.remove();
            System.out.println(rem);
        }

        // max priority
        System.out.println("max heap");
        PriorityQueue<Integer> pq1 = new PriorityQueue<>(Collections.reverseOrder());
        pq1.add(10);
        pq1.add(9);
        pq1.add(7);
        pq1.add(11);
        pq1.add(20);

        while (pq1.size() > 0) {
            int rem = pq1.remove();
            System.out.println(rem);
        }
    }

    public static void main(String[] args) {
        // demo();
        Question();
    }
}

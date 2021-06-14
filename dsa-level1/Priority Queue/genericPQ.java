import java.util.ArrayList;

import javax.print.attribute.standard.MediaSize.Other;

import java.util.*;

//min priority queue
class priority_queue<K extends Comparable> {
    private ArrayList<K> data;
    private boolean flag;
    // flag=true->it is max priority
    // flat=false it is min priority

    public priority_queue() {
        data = new ArrayList<>();
        flag = false;
    }

    private void processArray(K arr[]) {
        // complexity more than o(nlogn)
        // for(int i=0;i<arr.length;i++)
        // add(arr[i]);


        // fill data list from array 
        //complexity is nearly o(n)
        for (int i = 0; i < arr.length; i++)
            data.add(arr[i]);

        // call downheapify
        for (int i = arr.length - 1; i >= 0; i--)
            downheapify(i);
    }

    public priority_queue(boolean flag) {
        data = new ArrayList<>();
        this.flag = flag;
    }

    public priority_queue(K arr[]) {
        data = new ArrayList<>();
        this.flag = false;
        processArray(arr);

    }

    public priority_queue(K arr[], boolean flag) {
        data = new ArrayList<>();
        this.flag = flag;
        processArray(arr);

    }

    private int checkPriority(int child, int parent) {
        if (flag == true) {
            if (data.get(parent).compareTo(data.get(child))<0) // max heap
                return 1;
        } else {
            if (data.get(child).compareTo(data.get(parent)) < 0 ) // min heap
                return 1;
        }
        return 0;
    }

    private void swap(int i, int j) {
        K temp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, temp);
    }

    private void upheapify(int idx) {
        if (idx == 0)
            return;

        int pi = (idx - 1) / 2;

        if (checkPriority(idx, pi) > 0) { // priority of children is max,then swap
            swap(idx, pi);
            upheapify(pi);
        }
    }

    public void add(K val) {
        data.add(val);

        // maintian heap order property and call upheapfity
        upheapify(data.size() - 1);
    }

    public void downheapify(int idx) {
        int min = idx;
        int lcn = 2 * idx + 1;
        int rcn = 2 * idx + 2;

        // if (lcn < data.size() && data.get(lcn) < data.get(min))
        if (lcn < data.size() && checkPriority(lcn, min) > 0)
            min = lcn;

        if (rcn < data.size() && checkPriority(rcn, min) > 0)
            min = rcn;

        if (min != idx) {
            swap(idx, min);
            downheapify(min);

        }

    }

    public K remove() {
        if (data.size() == 0) {
            System.out.println("underflow");
            return null;
        }

        swap(0, data.size() - 1);
        K val = data.remove(data.size() - 1);
        downheapify(0);
        return val;
    }

    public K peek() {
        if (data.size() == 0) {
            System.out.println("underflow");
            return null;
        }

        return data.get(0);
    }

    public int size() {
        return data.size();
    }

    public void display() {
        System.out.println(data);
    }

}

public class genericPQ {

    public static class Pair implements Comparable<Pair>
    {
        int val;
        public Pair(int val)
        {
            this.val=val;
        }

        public int compareTo(Pair other)
        {
            return this.val-other.val;
        }
    }

    public static void fun() {
        // priorityqueue1 pq = new priorityqueue1(true);
        // pq.add(10);
        // pq.add(5);
        // pq.add(20);
        // pq.add(30);
        // pq.display();
        // System.out.println(pq.remove());
        // System.out.println(pq.peek());
        // pq.display();

        int arr[] = { 15, 25, 15, 10, 15, 5, 100 };
        priority_queue<Pair>pq=new priority_queue<>(false);
        pq.add(new Pair(15));
        pq.add(new Pair(20));
        pq.add(new Pair(1));
        pq.add(new Pair(5));
        pq.add(new Pair(-1));
        pq.add(new Pair(100));
        System.out.println(pq.peek());
       
    }

    public static void main(String[] args) {
        fun();
    }

}

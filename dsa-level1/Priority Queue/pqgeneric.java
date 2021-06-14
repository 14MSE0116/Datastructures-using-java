import java.util.ArrayList;

import java.util.*;

//min priority queue
class priorityqueue1 {
    private ArrayList<Integer> data;
    private boolean flag;
    // flag=true->it is max priority
    // flat=false it is min priority

    public priorityqueue1() {
        data = new ArrayList<>();
        flag = false;
    }

    private void processArray(int arr[]) {
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

    public priorityqueue1(boolean flag) {
        data = new ArrayList<>();
        this.flag = flag;
    }

    public priorityqueue1(int arr[]) {
        data = new ArrayList<>();
        this.flag = false;
        processArray(arr);

    }

    public priorityqueue1(int arr[], boolean flag) {
        data = new ArrayList<>();
        this.flag = flag;
        processArray(arr);

    }

    private int checkPriority(int child, int parent) {
        if (flag == true) {
            if (data.get(child) > data.get(parent)) // max heap
                return 1;
        } else {
            if (data.get(child) < data.get(parent)) // min heap
                return 1;
        }
        return 0;
    }

    private void swap(int i, int j) {
        int temp = data.get(i);
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

    public void add(int val) {
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

    public int remove() {
        if (data.size() == 0) {
            System.out.println("underflow");
            return -1;
        }

        swap(0, data.size() - 1);
        int val = data.remove(data.size() - 1);
        downheapify(0);
        return val;
    }

    public int peek() {
        if (data.size() == 0) {
            System.out.println("underflow");
            return -1;
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

public class pqgeneric {

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
        priorityqueue1 pq = new priorityqueue1(arr);
        pq.display();
        priorityqueue1 pq1 = new priorityqueue1(arr, true);
        pq1.display();

    }

    public static void main(String[] args) {
        fun();
    }

}

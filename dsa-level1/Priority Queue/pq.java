import java.util.ArrayList;

import java.util.*;

//min priority queue
class priorityqueue {
    private ArrayList<Integer> data;
    private boolean flag;
    //flag=true->it is max priority
    //flat=false it is min priority

    public priorityqueue() {
        data = new ArrayList<>();
        flag=false;
    }

    public priorityqueue(boolean flag)
    {
        data = new ArrayList<>();
        this.flag=flag;
    }

    private int checkPriority(int child,int parent)
    {
        if(flag==true)
        {
            if (data.get(child) > data.get(parent))     //max heap
             return 1;
        }
        else
        {
            if (data.get(child) < data.get(parent))    //min heap
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

        if (checkPriority(idx, pi)>0) { //priority of children is max,then swap
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
        if(lcn<data.size() && checkPriority(lcn, min)>0)
            min = lcn;

        if (rcn < data.size() && checkPriority(rcn, min)>0)
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

public class pq {

    public static void fun() {
        priorityqueue pq = new priorityqueue(true);
        pq.add(10);
        pq.add(5);
        pq.add(20);
        pq.add(30);
        pq.display();
        System.out.println(pq.remove());
        System.out.println(pq.peek());
        pq.display();
    }

    public static void main(String[] args) {
        fun();
    }

}

import java.util.*;

public class Quicksort {
    public static void main(String[] args) {

    }

    public static class ListNode {
        int val = 0;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode quickSort(ListNode head) {
        if (head == null || head.next==null)
            return head;
        ListNode tail = head;
        while (tail.next != null)
            tail = tail.next;

         QPair res=quickSort(head,tail);   

    }

    public static class PPair {
        ListNode pnm1 = null;  //partition node-1;
        ListNode pn;  //partition node;
    }

    public static class QPair {
        //Qpair->quicksort pair
        ListNode nhead; //new head
        ListNode ntail;  //new tail
    }

}

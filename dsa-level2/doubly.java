import java.util.*;

class DoublyLinkedList {
    private class Node {
        int data;
        Node next;
        Node prev;

        public Node() {
            this.data = 0;
            this.next = this.prev = null;
        }

        public Node(int data) {
            this.data = data;
        }

        public Node(int data, Node prev, Node next) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node head = null;
    private Node tail = null;
    private int size = 0;

    // functions of add in DLL
    public void addwhensize0(int val) {
        Node nn = new Node(val);
        this.head = nn;
        this.tail = nn;
        this.size++;
    }

    public void addFirst(int val) {
        if (this.size == 0) {
            addwhensize0(val);
        } else {
            Node nn = new Node(val);
            nn.next = this.head;
            this.head.prev = nn;
            this.head = nn;
            this.size++;
        }

    }

    public void addAt(int pos, int val) {
        if (pos < 0 || pos > this.size) {
            return;
        }

        if (pos == 0) {
            addFirst(val);
        } else if (pos == this.size) {
            addLast(val);
        } else {
            Node nn = new Node(val);
            Node nm1 = head;
            for (int i = 0; i < pos - 1; i++)
                nm1 = nm1.next;

            nn.prev = nm1;
            nn.next = nm1.next;
            nm1.next = nn;
            this.size++;

        }
    }

    public void addLast(int val) {
        if (this.size == 0)
            addwhensize0(val);
        else {
            Node nn = new Node(val);
            this.tail.next = nn;
            nn.prev = this.tail;
            this.tail = nn;
            this.tail.next = null;
            this.size++;
        }
    }

    private void addAfter(int data, Node node) {
        if (node == this.tail) {
            addFirst(data);
            return;
        }
        Node nn = new Node(data);
        nn.next = node.next;
        node.next.prev = nn;
        node.next = nn;
        nn.prev = node;
        this.size++;
    }

    private void addBefore(int data, Node node) {
        if (node == head) {
            addFirst(data);
            return;
        }
        Node nn = new Node(data);
        nn.prev = node.prev;
        node.prev.next = nn;
        nn.next = node;
        node.prev = nn;
        this.size++;
    }

    // functions of remove in DLL
    private int handleRemoveWhenSize1() {
        int val = this.head.data;
        this.head = this.tail = null;
        this.size = 0;
        return val;
    }

    public int removeFirst() {
        if(this.size == 0) {
            return -1;
        } else if(this.size == 1) {
            return handleRemoveWhenSize1();
        } else {
            int val = this.head.data;
            this.head = this.head.next;
            this.head.prev = null;

            this.size--;
            return val;
        }
    }

    public int removeLast() {
        if(this.size == 0) {
            return -1;
        } else if(this.size == 1) {
            return handleRemoveWhenSize1();
        } else {
            int val = this.tail.data;
            this.tail = this.tail.prev;
            this.tail.next = null;
            this.size--;
            return val;
        }
    }

    private Node getNodeAt(int pos) {
        Node temp = this.head;
        for(int i = 0; i < pos; i++) {
            temp = temp.next;
        }
        return temp;
    }
    public int removeAt(int pos) {
        if(pos < 0 || pos >= this.size) {
            return -1;
        } else if(pos == 0) {
            return removeFirst();
        } else if(pos == this.size - 1) {
            return removeLast();
        } else {
            Node node = getNodeAt(pos);
            return removeNode(node);
        }
    }

    private int removeAfter(Node node) {
        if (node.next == null)
            return -1;
        return removeNode(node.next);
    }

    private int removeBefore(Node node) {
        if (node.prev == null)
            return -1;
        return removeNode(node.prev);
    }

    private int removeNode(Node node) {
        if (node == this.head)
            return removeFirst();
        else if (node == this.tail)
            return removeLast();

        int val = node.data;
        Node nm1 = node.prev;
        Node np1 = node.next;

        nm1.next = np1;
        np1.prev = nm1;
        this.size--;
        return val;

    }

    // get in DLL
    public Node getNode(int idx) {

        Node temp = this.head;
        for (int i = 0; i < idx; i++)
            temp = temp.next;

        return temp;
    }

    public int getFirst() {
        return this.head.data;
    }

    public int getLast() {
        return this.tail.data;
    }

    public int getAt(int idx) {
        if (this.size == 0)
            return -1;

        if (idx < 0 || idx >= this.size)
            return -1;

        else if (idx == 0) {
            return getFirst();

        } else if (idx == this.size - 1) {
            return getLast();
        } else {
            Node temp = this.head;
            for (int i = 0; i < idx; i++)
                temp = temp.next;

            return temp.data;

        }
    }

    // functions of display in DLL
    public void forwarddisplay() {
        Node temp = this.head;
        System.out.print("[");
        while (temp.next != null) {
            System.out.print(temp.data + ",");
            temp = temp.next;
        }

        System.out.print(temp.data);
        System.out.print("]");
    }

    public void backwarddisplay() {
        Node temp = this.tail;
        System.out.print("[");
        while (temp.prev != null) {
            System.out.print(temp.data + ",");
            temp = temp.prev;
        }
        System.out.print(temp.data);
        System.out.print("]");
    }

    // size
    // size function
    public int size() {
        return this.size;
    }
}

public class doubly {
    public static void main(String[] args) {

        DoublyLinkedList ll = new DoublyLinkedList();
        ll.addFirst(10);
        ll.addFirst(20);
        ll.addLast(30);
        ll.addLast(40);
        ll.addAt(1, 99);
        ll.forwarddisplay();
        ll.backwarddisplay();
        System.out.println(ll.getAt(1));
        System.out.println(ll.removeFirst());
        System.out.println(ll.removeLast());
        System.out.println(ll.removeAt(1));
        ll.forwarddisplay();

    }
}

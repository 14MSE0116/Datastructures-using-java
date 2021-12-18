import javax.naming.LinkLoopException;
import java.util.*;

public class Stacks {
    public static int largestRectangleArea(int[] heights) {
        int rsi[] = rsi(heights);
        int lsi[] = lsi(heights);

        int area = -(int) 1e9;
        for (int i = 0; i < heights.length; i++) {
            area = Math.max(area, (rsi[i] - lsi[i] - 1) * heights[i]);
        }
        return area;
    }

    static int[] rsi(int[] arr) {
        int res[] = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        st.push(0);
        for (int i = 1; i < arr.length; i++) {
            while (st.size() > 0 && arr[i] < arr[st.peek()]) {
                res[st.pop()] = i;
            }
            st.push(i);

        }

        while (st.size() > 0)
            res[st.pop()] = arr.length;

        return res;
    }

    static int[] lsi(int arr[]) {
        int res[] = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        st.push(arr.length - 1);
        for (int i = arr.length - 2; i >= 0; i--) {
            while (st.size() > 0 && arr[i] < arr[st.peek()]) {
                res[st.pop()] = i;
            }
            st.push(i);
        }

        while (st.size() > 0)
            res[st.pop()] = -1;
        return res;
    }

    public static int maximalRectangle(int[][] ar) {
        return 0;
    }

    //leetcode 946->https://leetcode.com/problems/validate-stack-sequences/
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> st = new Stack<>();
        int idx = 0;
        for (int val : pushed) {

            st.push(val);
            while (st.size() > 0 && st.peek() == popped[idx]) {
                st.pop();
                idx++;
            }
        }
        return idx == popped.length;

    }

    //leetcode 921->https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/
    public int minAddToMakeValid(String s) {
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (st.size() == 0 || ch == '(')
                st.push(ch);
            else if (st.peek() == '(') {
                st.pop();
            } else {
                st.push(ch);
            }


        }
        return st.size();

    }

    //leetcode 1021->https://leetcode.com/problems/remove-outermost-parentheses/
    public String removeOuterParentheses(String s) {
        StringBuilder sb = new StringBuilder();
        int op = 0;
        int cl = 0;
        int si = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(')
                op++;
            else if (ch == ')')
                cl++;

            if (op == cl) {
                for (int j = si + 1; j < i; j++)
                    sb.append(s.charAt(j));
                op = 0;
                cl = 0;
                si = i + 1;
            }
        }
        return sb.toString();
    }

    //leetcode 856->https://leetcode.com/problems/score-of-parentheses/
    public int scoreOfParentheses(String s) {
        Stack<Integer> st = new Stack<>();
        //-1 ->means opening bracket
        //number->score of parenthesis
        //()->1 (a)->2*a ab->a+b
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                st.push(-1);
            } else {
                if (st.peek() == -1) {
                    st.pop();
                    st.push(1);
                } else {
                    int sum = 0;
                    while (st.peek() != -1) {
                        sum += st.pop();
                    }
                    st.pop();
                    st.push(2 * sum);
                }
            }
        }

        int sum = 0;
        while (st.size() > 0) {
            sum += st.pop();
        }
        return sum;
    }

    //https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/
    public String reverseParentheses(String s) {
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch != ')') {
                st.push(ch);
            } else {
                StringBuilder str = new StringBuilder();
                while (st.peek() != '(') {
                    str.append(st.pop());
                }
                st.pop();
                for (int j = 0; j < str.length(); j++)
                    st.push(str.charAt(j));
            }

        }
        StringBuilder str = new StringBuilder();
        while (st.size() > 0) {
            str.append(st.pop());
        }
        str.reverse();
        return str.toString();

    }

    //leetcode->1249
    // https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(')
                st.push(i);
            else if (ch == ')') {
                if (st.size() == 0 || s.charAt(st.peek()) == ')')
                    st.push(i);
                else {
                    //st.peek()=='('
                    st.pop();
                }
            }

        }
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (st.peek() == i)
                st.pop();
            else
                sb.append(s.charAt(i));
        }
        return sb.reverse().toString();

    }

    //https://leetcode.com/problems/minimum-insertions-to-balance-a-parentheses-string/
    public int minInsertions(String s) {
        return -1;
    }

    //leetcode->901 https://leetcode.com/problems/online-stock-span/
    //Online stack span
    class StockSpanner {
        class Pair {
            int val;
            int idx;

            Pair(int val, int idx) {
                this.val = val;
                this.idx = idx;
            }
        }

        Stack<Pair> st;
        int idx;

        public StockSpanner() {
            st = new Stack<>();
            idx = -1;
            st.push(new Pair(Integer.MAX_VALUE, idx));
            idx++;
        }

        public int next(int price) {
            while (st.size() > 0 && st.peek().val <= price) {
                st.pop();
            }
            int span = idx - st.peek().idx;
            st.push(new Pair(price, idx));
            idx++;
            return span;
        }
    }

    //https://leetcode.com/problems/backspace-string-compare/
    public boolean backspaceCompare(String s, String t) {
        Stack<Character> st = new Stack<>();
        Stack<Character> tt = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch != '#') {
                st.push(ch);
            } else if (st.size() > 0)
                st.pop();
        }

        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            if (ch != '#') {
                tt.push(ch);
            } else if (tt.size() > 0)
                tt.pop();
        }

        if (st.size() != tt.size())
            return false;
        while (st.size() > 0) {
            if (st.peek() != tt.peek())
                return false;
            st.pop();
            tt.pop();
        }

        return true;

    }

    // leetcode 636, https://leetcode.com/problems/exclusive-time-of-functions/
    public int[] exclusiveTime(int n, List<String> logs) {
        //n->number of id's
        int res[] = new int[n];
        Stack<ETHelper> st = new Stack<>();

        for (String str : logs) {
            String[] info = str.split(":");
            int id = Integer.parseInt(info[0]);
            String status = info[1];
            int timestamp = Integer.parseInt(info[2]);

            if (status.equals("start")) {
                st.push(new ETHelper(id, timestamp, 0));
            } else {
                int fn_diff = timestamp - st.peek().stime + 1;
                int exec_time = fn_diff - st.peek().cet;
                st.pop();
                if (st.size() > 0) {
                    st.peek().cet += fn_diff;
                }
                res[id] += exec_time;
            }

        }
        return res;

    }

    private class ETHelper {
        int id;
        int stime;
        int cet;//child execution time

        ETHelper(int id, int stime, int cet) {
            this.id = id;
            this.stime = stime;
            this.cet = cet;
        }
    }

    // leetcode 456, https://leetcode.com/problems/132-pattern/
    public boolean find132pattern(int[] nums) {
        int lmin[] = new int[nums.length];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            min = Math.min(min, nums[i]);
            lmin[i] = min;
        }

        Stack<Integer> st = new Stack<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            //pop
            while (st.size() > 0 && st.peek() <= lmin[i])
                st.pop();
            //check for result
            if (st.size() > 0 && st.peek() < nums[i] && lmin[i] < nums[i])
                return true;
            st.push(nums[i]);
        }
        return false;

    }

    // leetcode 735, https://leetcode.com/problems/asteroid-collision/
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> st = new Stack<>();
        for (int val : asteroids) {
            if (val > 0) {
                st.push(val);
            } else {
                //val is -ve
                //peek is positive but smaller than abs(val)
                while (st.size() > 0 && st.peek() < -val && st.peek() > 0) {
                    st.pop();
                }
                if (st.size() > 0 && st.peek() == -val)
                    st.pop(); //equal in size but opposite in direction
                else if (st.size() == 0 || st.peek() < 0)
                    st.push(val);
            }
        }
        int res[] = new int[st.size()];
        for (int i = res.length - 1; i >= 0; i--)
            res[i] = st.pop();
        return res;

    }

    // leetcode 402, https://leetcode.com/problems/remove-k-digits/
    public String removeKdigits(String num, int k) {
        //use linked list as stack
        LinkedList<Character> st = new LinkedList<>();

        for (int i = 0; i < num.length(); i++) {
            char ch = num.charAt(i);
            while (k > 0 && st.size() > 0 && ch < st.getLast()) {
                st.removeLast();
                k--;
            }
            st.addLast(ch);
        }
        //manage remaining k's
        while (k > 0) {
            st.removeLast();
            k--;
        }
        //manage leading zero's
        while (st.size() > 0 && st.getFirst() == '0') {
            st.removeFirst();
        }
        StringBuilder sb = new StringBuilder();
        for (char ch : st)
            sb.append(ch);
        return sb.length() == 0 ? "0" : sb.toString();

    }

    // leetcode 316, https://leetcode.com/problems/remove-duplicate-letters/
    //keep freqcount[] and isexists[] array
    //for 0 to n-1
    //freq[ch-'a']--
    //if isexist[ch]==true continue->since duplicate shouldnt be repeated
    /*
      while st.peek>ch && freq[st.peek]>0
        pop
        isexists[peek]=false

      push(ch)
      isexists[ch]=true;



     */
    public String removeDuplicateLetters(String s) {
        int freq[] = new int[26];
        boolean exists[] = new boolean[26];
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            freq[ch - 'a']++;
        }
        LinkedList<Character> st = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            freq[ch - 'a']--;

            if (exists[ch - 'a']) continue;

            while (st.size() > 0 && st.getLast() > ch && freq[st.getLast() - 'a'] > 0) {
                char rem = st.removeLast();
                exists[rem - 'a'] = false;
            }

            st.addLast(ch);
            exists[ch - 'a'] = true;
        }
        StringBuilder sb = new StringBuilder();
        for (char ch : st)
            sb.append(ch);
        return sb.toString();


    }

    //Trapping rain water without extra space
    static long trappingWater(int arr[], int n) {
        // Your code here
        long ans = 0;
        int l = 0;
        int r = arr.length - 1;
        int lmax = 0;
        int rmax = 0;
        while (l < r) {
            lmax = Math.max(lmax, arr[l]);
            rmax = Math.max(rmax, arr[r]);
            if (lmax < rmax) {
                ans += lmax - arr[l];
                l++;
            } else {
                ans += rmax - arr[r];
                r--;
            }
        }
        return ans;
    }

    //trapping rain water 2
    //https://leetcode.com/problems/trapping-rain-water-ii/
    private class TRPHelper implements Comparable<TRPHelper> {
        int x;
        int y;
        int ht;

        TRPHelper(int x, int y, int ht) {
            this.x = x;
            this.y = y;
            this.ht = ht;
        }

        public int compareTo(TRPHelper o) {
            return this.ht - o.ht;
        }
    }

    void addBoundaryTRW(PriorityQueue<TRPHelper> pq, int[][] hts, boolean vis[][]) {
        //top
        for (int c = 0; c < hts[0].length; c++) {
            if (vis[0][c] == false) {
                pq.add(new TRPHelper(0, c, hts[0][c]));
                vis[0][c] = true;
            }
        }
        //left
        for (int r = 0; r < hts.length; r++) {
            if (vis[r][0] == false) {
                pq.add(new TRPHelper(r, 0, hts[r][0]));
                vis[r][0] = true;
            }
        }


        //right
        for (int r = 0; r < hts.length; r++) {
            if (vis[r][hts[0].length - 1] == false) {
                pq.add(new TRPHelper(r, hts[0].length - 1, hts[r][hts[0].length - 1]));
                vis[r][hts[0].length - 1] = true;
            }
        }

        //bottom depth wall
        for (int c = 0; c < hts[0].length; c++) {
            if (vis[hts.length - 1][c] == false) {
                pq.add(new TRPHelper(hts.length - 1, c, hts[hts.length - 1][c]));
                vis[hts.length - 1][c] = true;
            }
        }
    }

    static int xdir[] = {-1, 0, 1, 0};
    static int ydir[] = {0, -1, 0, 1};

    public int trapRainWater(int[][] hts) {
        boolean vis[][] = new boolean[hts.length][hts[0].length];
        PriorityQueue<TRPHelper> pq = new PriorityQueue<>();
        //add boundary in pq
        addBoundaryTRW(pq, hts, vis);
        int water = 0;
        while (pq.size() > 0) {
            TRPHelper rem = pq.remove();
            for (int d = 0; d < xdir.length; d++) {
                int r = rem.x + xdir[d];
                int c = rem.y + ydir[d];
                if (r >= 0 && r < hts.length && c >= 0 && c < hts[0].length
                        && vis[r][c] == false) {
                    if (hts[r][c] < rem.ht) {
                        water += rem.ht - hts[r][c];
                        pq.add(new TRPHelper(r, c, rem.ht));
                    } else {
                        pq.add(new TRPHelper(r, c, hts[r][c]));
                    }
                    vis[r][c] = true;
                }
            }
        }

        return water;
    }

    //Basic Calculator->leetcode 224
    //https://leetcode.com/problems/basic-calculator/
    public int calculate(String s) {
        Stack<Integer> st = new Stack<>();
        int sign = 1;
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == ' ')
                continue;
            else if (ch >= '0' && ch <= '9') {
                //number may have more than one digit
                long n = 0;
                while (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                    n *= 10;
                    n += (int) (s.charAt(i) - '0');
                    i++;
                }
                i--;
                n *= sign;
                sum += (int) n;
                sign = 1;
            } else if (ch == '(') {
                st.push(sum);
                st.push(sign);
                sign = 1;
                sum = 0;

            } else if (ch == ')') {
                sum *= st.pop();//multiply sign from sum
                sum += st.pop();//add old sum in new one
            } else if (ch == '-') {
                sign *= -1;
            } else {
                //if ch is +ve
                //nothing to do
            }
        }
        return sum;
    }

    //Basic Calculator-2->leetcode 227
    //https://leetcode.com/problems/basic-calculator-ii/submissions/
    public int calculate2(String s) {
        int ptr = 0;
        Stack<Integer> st = new Stack<>();
        Stack<Character> op = new Stack<>();
        while (ptr < s.length()) {
            char curr = s.charAt(ptr);
            if (curr == ' ')
                ptr++;
            else if (curr >= '0' && curr <= '9') {
                int val = 0;
                while (ptr < s.length() && s.charAt(ptr) >= '0' && s.charAt(ptr) <= '9') {
                    val = val * 10 + (s.charAt(ptr) - '0');
                    ptr++;
                }
                st.push(val);

            } else {
                while (op.size() > 0 && priority(op.peek()) >= priority(curr)) {
                    int val2 = st.pop();
                    int val1 = st.pop();
                    char oper = op.pop();
                    int res = solve(val1, val2, oper);
                    st.push(res);
                }
                op.push(curr);
                ptr++;
            }
        }

        while (op.size() > 0) {
            char oper = op.pop();
            int val2 = st.pop();
            int val1 = st.pop();
            int res = solve(val1, val2, oper);
            st.push(res);
        }
        return st.peek();
    }

    int priority(char ch) {
        if (ch == '*' || ch == '/')
            return 2;
        else if (ch == '+' || ch == '-')
            return 1;
        else
            return 0;
    }

    int solve(int val1, int val2, char ch) {
        if (ch == '*')
            return val1 * val2;
        else if (ch == '/')
            return val1 / val2;
        else if (ch == '-')
            return val1 - val2;
        else if (ch == '+')
            return val1 + val2;
        else
            return 0;
    }


    //number of valid subarrays
    public static int validSubarrays(int[] nums) {
        int count = 0;
        int nsr[] = new int[nums.length];
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            while (st.size() > 0 && nums[st.peek()] > nums[i]) {
                nsr[st.pop()] = i;
            }
            st.push(i);
        }
        while (st.size() > 0)
            nsr[st.pop()] = nums.length;
        //take contribution
        for (int i = 0; i < nums.length; i++) {
            count += nsr[i] - i;
        }
        return count;
    }

    //Lexicographically Smallest Subsequence
    public static int[] smallest(int[] nums, int k) {
        // write your code here
        Stack<Integer> st = new Stack<>();
        k = nums.length - k;
        for (int i = 0; i < nums.length; i++) {
            while (st.size() > 0 && nums[i] < st.peek() && k > 0) {
                k--;
                st.pop();
            }
            st.push(nums[i]);
        }
        while (k > 0) {
            st.pop();
            k--;
        }
        int res[] = new int[st.size()];
        int idx = 0;
        while (st.size() > 0)
            res[idx++] = st.pop();
        for (int i = 0; i < res.length / 2; i++) {
            int temp = res[i];
            res[i] = res[res.length - i - 1];
            res[res.length - i - 1] = temp;
        }
        return res;

    }

    //1381. Design a Stack With Increment Operation
    //https://leetcode.com/problems/design-a-stack-with-increment-operation/
    class CustomStack {
        int val[];
        int inc[];
        int top;
        public CustomStack(int maxSize) {
            this.val=new int[maxSize];
            this.inc=new int[maxSize];
            this.top=-1;

        }

        public void push(int x) {
            if(top+1==val.length)
                return;
            val[top+1]=x;
            top++;
        }

        public int pop() {
            if(top==-1)
                return -1;
            int value=val[top]+inc[top];
            if(top!=0){
                inc[top-1]+=inc[top];
            }
            inc[top]=0;
            top--;
            return  value;

        }

        public void increment(int k, int val) {
            if(k>top+1){
                inc[top]+=val;
            }
            else{
                inc[k-1]+=val;
            }
        }
    }

    //641. Design Circular Deque
    //https://leetcode.com/problems/design-circular-deque/
    class MyCircularDeque {
        LinkedList<Integer>list;
        int len;
        public MyCircularDeque(int k) {
            list=new LinkedList<>();
            this.len=k;
        }

        public boolean insertFront(int value) {
            if(list.size()==this.len)
                return false;
            list.addFirst(value);
            return  true;
        }

        public boolean insertLast(int value) {
            if(list.size()==this.len)
                return  false;
            list.addLast(value);
            return true;
        }

        public boolean deleteFront() {
            if(list.size()==0)
                return  false;
            list.removeFirst();
            return true;
        }

        public boolean deleteLast() {
            if(list.size()==0)
                return false;
            list.removeLast();
            return true;
        }

        public int getFront() {
            if(list.size()==0)
                return -1;
            return list.getFirst();
        }

        public int getRear() {
            if(list.size()==0)
                return -1;
            return list.getLast();
        }

        public boolean isEmpty() {
            if(list.size()==0)
                return true;
            return false;
        }

        public boolean isFull() {
            if(list.size()==this.len)
                return true;
            return false;
        }
    }


    public static void main(String[] args) {
        System.out.println("stacks");
    }
}

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

    public static void main(String[] args) {
        System.out.println("stacks");
    }
}

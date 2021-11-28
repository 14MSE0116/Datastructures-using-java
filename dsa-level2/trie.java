import java.util.*;

class trie {
    //https://leetcode.com/problems/implement-trie-prefix-tree/
    //implement trie basic
    static class Trie {
        class Node {
            Node[] chidren;
            boolean isEnd;

            Node() {
                this.chidren = new Node[26];
                this.isEnd = false;
            }
        }

        private Node root = null;

        Trie() {
            root = new Node();
        }

        /**
         * Inserts a word into the trie.
         */

        public void insert(String word) {
            Node ptr = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (ptr.chidren[ch - 'a'] == null) {
                    Node nn = new Node();
                    ptr.chidren[ch - 'a'] = nn;
                }
                ptr = ptr.chidren[ch - 'a'];
            }
            ptr.isEnd = true;

        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            Node ptr = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (ptr.chidren[ch - 'a'] == null)
                    return false;
                ptr = ptr.chidren[ch - 'a'];
            }
            return ptr.isEnd;

        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String word) {
            Node ptr = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (ptr.chidren[ch - 'a'] == null)
                    return false;
                ptr = ptr.chidren[ch - 'a'];
            }
            return true;
        }

    }

    //Design and Search words Data Structure
    //https://leetcode.com/problems/design-add-and-search-words-data-structure/
    class WordDictionary {

        class Node {
            Node children[];
            boolean isEnd;

            Node() {
                this.children = new Node[26];
                this.isEnd = false;
            }
        }

        private Node root = null;

        public WordDictionary() {

            root = new Node();
        }

        public void addWord(String word) {
            Node temp = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (temp.children[ch - 'a'] == null) {
                    Node nn = new Node();
                    temp.children[ch - 'a'] = nn;
                }
                temp = temp.children[ch - 'a'];
            }
            temp.isEnd = true;
        }

        public boolean search(String word) {

            return find(root, word, 0);

        }

        boolean find(Node node, String word, int idx) {
            if (idx == word.length()) {
                return node.isEnd;
            }
            char ch = word.charAt(idx);
            if (ch == '.') {
                for (int i = 0; i < 26; i++) {
                    Node child = node.children[i];
                    if (child != null && find(child, word, idx + 1)) {
                        return true;
                    }
                }

            } else if (node.children[ch - 'a'] != null) {
                return find(node.children[ch - 'a'], word, idx + 1);

            }
            return false;
        }
    }

    //https://leetcode.com/problems/word-search-ii/
    private class Node {
        Node[] children;
        boolean isEnd;
        int freq;

        Node() {
            this.children = new Node[26];
            this.isEnd = false;
            this.freq = 0;
        }
    }

    private void insert(String word, Node root) {
        //make a trie and add words in it
        Node root1 = new Node();

    }

    public List<String> findWords(char[][] board, String[] words) {

    }

    //leetcode677 https://leetcode.com/problems/map-sum-pairs/
    class MapSum {
        private class Node {
            Node children[];
            int impact;

            Node() {
                this.children = new Node[26];
                this.impact = 0;
            }
        }

        private HashMap<String, Integer> map;
        private Node root;

        public MapSum() {
            root = new Node();
            this.map = new HashMap<>();
        }

        public void insert(String key, int val) {
            int oldval = map.getOrDefault(key, 0);
            int newval = val;
            int impact = newval - oldval;
            map.put(key, val);

            Node ptr = root;
            for (int i = 0; i < key.length(); i++) {
                char ch = key.charAt(i);
                if (ptr.children[ch - 'a'] == null) {
                    ptr.children[ch - 'a'] = new Node();
                }
                ptr = ptr.children[ch - 'a'];
                ptr.impact += impact;
            }


        }

        public int sum(String prefix) {
            Node ptr = root;
            for (int i = 0; i < prefix.length(); i++) {
                char ch = prefix.charAt(i);
                if (ptr.children[ch - 'a'] == null) {
                    return 0;
                }
                ptr = ptr.children[ch - 'a'];
            }
            return ptr.impact;

        }
    }

    //https://leetcode.com/problems/longest-word-in-dictionary/
    private class Nodee {
        Nodee children[];
        String s;

        Nodee() {
            this.children = new Nodee[26];
            this.s = null;
        }
    }

    private void insert(Nodee root, String word) {
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (root.children[ch - 'a'] == null) {
                root.children[ch - 'a'] = new Nodee();
            }
            root = root.children[ch - 'a'];
        }
        root.s = word;
    }

    static String str;

    public String longestWord(String[] words) {
        Nodee root = new Nodee();
        for (String word : words) {
            insert(root, word);
        }
        str = "";
        for (int i = 0; i < 26; i++) {
            if (root.children[i] != null)
                dfsTrie(root.children[i]);
        }
        return str;

    }

    void dfsTrie(Nodee node) {
        if (node.s == null)
            return;
        if (node.s.length() > str.length())
            str = node.s;
        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null)
                dfsTrie(node.children[i]);
        }

    }

    class StreamChecker {

        private class Node {
            Node[] children;
            boolean isEnd;

            Node() {
                this.children = new Node[26];
                this.isEnd = false;
            }
        }

        private Node root;
        private StringBuilder stream;

        private void insert(Node node, String word) {
            for (int i = word.length() - 1; i >= 0; i--) {
                char ch = word.charAt(i);
                if (node.children[ch - 'a'] == null) {
                    node.children[ch - 'a'] = new Node();
                }
                node = node.children[ch - 'a'];
            }
            node.isEnd = true;
        }

        public StreamChecker(String[] words) {
            root = new Node();
            stream = new StringBuilder();
            for (String word : words)
                insert(root, word);

        }

        public boolean query(char letter) {
            stream.append(letter);
            return find();
        }

        private boolean find() {
            Node temp = root;
            for (int i = stream.length() - 1; i >= 0; i--) {
                char ch = stream.charAt(i);
                if (temp.children[ch - 'a'] == null)
                    return false;
                temp = temp.children[ch - 'a'];

                if (temp.isEnd == true)
                    return true;

            }
            return false;
        }
    }

    public static void main(String[] args) {

    }
}
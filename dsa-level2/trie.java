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

    public static void main(String[] args) {

    }
}
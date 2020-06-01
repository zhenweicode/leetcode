import java.util.*;

class WordDictionary {
    class TrieNode {
        TrieNode[] children;
        boolean flag;

        public TrieNode() {
            children = new TrieNode[26];
            flag = false;
            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
        }

    }

    TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        char[] array = word.toCharArray();
        TrieNode cur = root;
        for (int i = 0; i < array.length; i++) {
            // 当前孩子是否存在
            if (cur.children[array[i] - 'a'] == null) {
                cur.children[array[i] - 'a'] = new TrieNode();
            }
            cur = cur.children[array[i] - 'a'];
        }
        // 当前节点代表结束
        cur.flag = true;
    }

    // 查询是否存在，支持.匹配
    public boolean search(String word) {
        return searchHelp(word, root);
    }

    private boolean searchHelp(String word, TrieNode root) {
        char[] array = word.toCharArray();
        TrieNode cur = root;
        for (int i = 0; i < array.length; i++) {
            // 对于 . , 递归的判断所有不为空的孩子
            if (array[i] == '.') {
                for (int j = 0; j < 26; j++) {
                    if (cur.children[j] != null) {
                        if (searchHelp(word.substring(i + 1), cur.children[j])) {
                            return true;
                        }
                    }
                }
                return false;
            }
            // 不含有当前节点
            if (cur.children[array[i] - 'a'] == null) {
                return false;
            }
            cur = cur.children[array[i] - 'a'];
        }
        // 当前节点是否为是某个单词的结束
        return cur.flag;
    }
}
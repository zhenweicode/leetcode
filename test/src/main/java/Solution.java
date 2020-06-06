import java.util.*;

class Solution {
    public int shortestWordDistance(String[] words, String word1, String word2) {//相等时值更新一个。。。
        int idx1 = -1, idx2 = -1, res = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                idx1 = i;
                if (idx2 >= 0) res = Math.min(res, Math.abs(idx2 - idx1));
            }
            if (words[i].equals(word2)) {
                idx2 = i;
                if (idx1 >= 0 && idx1 != idx2) {
                    res = Math.min(res, Math.abs(idx2 - idx1));
                }
            }
        }

        return res;
    }
}




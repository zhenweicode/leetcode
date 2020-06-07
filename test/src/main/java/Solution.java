import java.util.*;

class Solution {
    private int max = 0;

    public int maxLength(List<String> arr) {
        backtrack(arr, 0, new int[26]);
        return max;
    }

    private void backtrack(List<String> arr, int begin, int[] chars) {
        max = Math.max(getLength(chars), max);

        if (begin == arr.size()) {
            return;
        }

        for (int i = begin; i < arr.size(); i++) {
            String word = arr.get(i);
            if (!isLegal(word, chars)) {
                continue;
            }

            add(word, chars);
            backtrack(arr, i + 1, chars);
            remove(word, chars);
        }
    }

    private int getLength(int[] chars) {
        int count = 0;
        for (int c : chars) {
            if (c != 0) {
                count++;
            }
        }

        return count;
    }

    private boolean isLegal(String str, int[] chars) {
        int[] temp = new int[26];
        for (char c : str.toCharArray()) {
            // 与已有不重复
            if (chars[c - 'a'] != 0) {
                return false;
            }

            // 自身不重复
            if (temp[c - 'a'] != 0) {
                return false;
            }

            temp[c - 'a'] = 1;
        }

        return true;
    }

    private void add(String str, int[] chars) {
        for (char c : str.toCharArray()) {
            chars[c - 'a'] = 1;
        }
    }

    private void remove(String str, int[] chars) {
        for (char c : str.toCharArray()) {
            chars[c - 'a'] = 0;
        }
    }
}
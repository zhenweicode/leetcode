import java.util.*;


class Solution {
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }

        List<String> result = new ArrayList<>();
        for (String s1 : s.split("\\s+")) {
            result.add(reverseWord(s1));
        }
        return String.join(" ", result);
    }

    private String reverseWord(String s) {
        StringBuilder b = new StringBuilder();
        char[] c = s.toCharArray();
        for (int i = c.length - 1; i >= 0; i--) {
            b.append(c[i]);
        }

        return b.toString();
    }
}
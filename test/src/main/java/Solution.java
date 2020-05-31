import java.util.*;

public class Solution {
    private HashMap<Integer, List<String>> map = new HashMap<>();

    public List<String> wordBreak(String s, List<String> wordDict) {
        return word_Break(s, new HashSet<>(wordDict), 0);
    }

    private List<String> word_Break(String s, Set<String> wordDict, int start) {
        if (map.containsKey(start)) {
            return map.get(start);
        }
        LinkedList<String> res = new LinkedList<>();
        if (start == s.length()) {
            res.add("");  // 标志结尾，如果不加的话，下面一步不会执行，就不会把当前结果加进去
        }

        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end))) {
                List<String> list = word_Break(s, wordDict, end);
                // 只有最后满足start == s.length()的list才不为空
                for (String l : list) {
                    res.add(s.substring(start, end) + (l.equals("") ? "" : " ") + l);
                }
            }
        }
        map.put(start, res);
        return res;
    }

    public static void main(String[] args) {
        List<String> result = new ArrayList<>();
        result.add("cat");
        result.add("cats");
        result.add("and");
        result.add("sand");
        result.add("dog");
        System.out.println(new Solution().wordBreak("catsanddog", result));
    }
}
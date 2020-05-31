import java.util.*;

public class Solution {

    public List<String> restoreIpAddresses(String s) {
        int len = s.length();
        List<String> res = new ArrayList<>();
        if (len > 12 || len < 4) {
            return res;
        }

        List<String> path = new ArrayList<>(4);
        backtrack(s, 0, 4, path, res);
        return res;
    }

    /**
     * @param begin  下一个遍历下标
     * @param remain 剩余多少段还没被分割
     */
    private void backtrack(String s, int begin, int remain, List<String> path, List<String> res) {
        if (begin == s.length()) {
            if (remain == 0) {
                res.add(String.join(".", path));
            }
            return;
        }

        for (int i = begin; i < begin + 3; i++) {
            if (i >= s.length()) { // 越界
                break;
            }

            if (remain * 3 < s.length() - i) {  // 剪枝
                continue;
            }

            if (judgeIpSegment(s, begin, i)) {
                String currentIpSegment = s.substring(begin, i + 1);
                path.add(currentIpSegment);

                backtrack(s, i + 1, remain - 1, path, res);
                path.remove(path.size() - 1);
            }
        }
    }

    // 判断left到right是否符合0-255
    private boolean judgeIpSegment(String s, int left, int right) {
        int len = right - left + 1;
        if (len > 1 && s.charAt(left) == '0') {
            return false;
        }

        int res = 0;
        while (left <= right) {
            res = res * 10 + s.charAt(left) - '0';
            left++;
        }

        return res >= 0 && res <= 255;
    }
}
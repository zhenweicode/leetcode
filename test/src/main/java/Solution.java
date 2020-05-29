import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = map.get(s.charAt(j)) + 1; // 由于map没有remove操作，防止i向左滑动
            }

            ans = Math.max(ans, j - i + 1);  // j - i + 1代表长度，上面解法先执行了j++
            map.put(s.charAt(j), j);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abba"));
    }
}
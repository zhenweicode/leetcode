import java.util.*;

class Solution {
    public static List<Integer> findAnagrams(String s, String p) {
        if (s == null || s.length() == 0 || p == null || p.length() == 0) {
            return new ArrayList<>();
        }

        Map<Character, Integer> pMap = new HashMap<>();
        for (Character c : p.toCharArray()) {
            pMap.put(c, pMap.getOrDefault(c, 0) + 1);
        }

        Map<Character, Integer> realMap = new HashMap<>();
        int required = pMap.size();
        int count = 0;
        int left = 0, right = 0;
        List<Integer> result = new ArrayList<>();
        while (right < s.length()) {
            char c = s.charAt(right);
            realMap.put(c, realMap.getOrDefault(c, 0) + 1);
            if (realMap.get(c).intValue() == pMap.getOrDefault(c, 0).intValue()) {
                count++;
            }

            while (count == required && left <= right) {
                if ((right - left + 1) == p.length()) {
                    result.add(left);
                }
                char t = s.charAt(left);
                realMap.put(t, realMap.getOrDefault(t, 0) - 1);
                if (realMap.get(t) < pMap.getOrDefault(t, 0)) {
                    count--;
                }

                left++;
            }

            right++;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(findAnagrams("abab", "ab"));
    }
}
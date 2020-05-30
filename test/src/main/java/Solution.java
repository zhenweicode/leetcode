import java.util.*;

class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> res = new LinkedList<>();
        if (digits == null || digits.length() == 0) {
            return res;
        }

        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        getCombinations(0, digits, res, map, new ArrayList<>(digits.length()));
        return res;
    }

    private void getCombinations(int start, String digits, List<String> res, Map<Character, String> map, List<Character> temp) {
        if (start == digits.length()) {
            res.add(getString(temp));
            return;
        }

        for (char c : map.get(digits.charAt(start)).toCharArray()) {
            temp.add(c);
            getCombinations(start + 1, digits, res, map, temp);
            temp.remove(temp.size() - 1);
        }
    }

    private String getString(List<Character> temp) {
        String result = "";
        for (char c : temp) {
            result = result + c;
        }

        return result;
    }
}
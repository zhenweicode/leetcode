import java.util.*;

class Solution {
    public boolean isValid(String s) {
        if (s == null || s.length() == 0 || (s.length() % 2 != 0)) {
            return false;
        }

        Stack<Character> stack = new Stack<>();
        for (Character c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (stack.size() > 0 && (
                    (c == ')' && stack.pop() == '(') || (c == '}' && stack.pop() == '{') || (c == ']' && stack.pop() == '['))) {
                continue;
            } else {
                return false;
            }
        }

        return stack.size() == 0;
    }
}
import java.util.*;


class Solution {
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }

        String last = "1";
        int i = 1;
        while (i < n) {
            i++;
            StringBuilder next = new StringBuilder();
            int index = 0;
            while (index < last.length()) {
                char cur = last.charAt(index);
                int count = 0;
                while (index < last.length() && last.charAt(index) == cur) {
                    index++; // 循环结束，index指向下一个字符，count统计数量正好
                    count++;
                }

                next.append(count).append(cur);
            }

            last = next.toString();
        }

        return last;
    }
}
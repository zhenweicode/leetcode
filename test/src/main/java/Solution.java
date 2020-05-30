import java.util.*;

class Solution {
    public int trap(int[] height) {
        int sum = 0;
        int max_left = 0;
        int max_right = 0;
        int left = 0;
        int right = height.length - 1; // 加右指针进去
        while (left <= right) {
            if (max_left < max_right) {
                sum += Math.max(0, max_left - height[left]);
                max_left = Math.max(max_left, height[left]);
                left++;
            } else {
                sum += Math.max(0, max_right - height[right]);
                max_right = Math.max(max_right, height[right]);
                right--;
            }
        }
        return sum;
    }
}
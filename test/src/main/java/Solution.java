import java.util.*;

class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];  // 第i个元素表示i左侧第一个比他矮的下标
        int[] right = new int[n];  // 第i个元素表示i右侧第一个比他矮的下标

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            // 丢掉的都是比heights[i]高的，肯定不是heights[i+1]左侧的最小值
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }

            // 栈不为空，栈顶是第一个比他小的，栈为空，则设置为-1，表示没有比他矮的
            left[i] = (stack.isEmpty() ? -1 : stack.peek());
            stack.push(i);
        }

        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }

            // 栈不为空，栈顶是第一个比他小的，栈为空，则设置为n，表示没有比他矮的
            // 注意这里设为n，计算面积的时候就可以(n减去某个值)，表示右侧所有的柱形都有效
            right[i] = (stack.isEmpty() ? n : stack.peek());
            stack.push(i);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            // 计算面积是在开区间(left[i], right[i])，因此right[i] - left[i] - 1
            // 这里也可以看出来上面设置-1和n的用意
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }
}
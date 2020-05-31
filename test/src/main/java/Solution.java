import java.util.*;

class Solution {
    public boolean isValidBST(TreeNode head) {
        if (head == null) {
            return true;
        }
        TreeNode p = head;
        Stack<TreeNode> stack = new Stack<>();
        int temp = Integer.MIN_VALUE;
        while (!stack.isEmpty() || p != null) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                p = stack.pop();
                if (p.val <= temp) {
                    return false;
                }
                temp = p.val;
                p = p.right;
            }
        }
        return true;
    }
}
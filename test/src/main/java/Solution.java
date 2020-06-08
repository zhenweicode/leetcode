import java.util.*;

class Solution {
    private int max = 0;

    public int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root);
        return max;
    }

    private int dfs(TreeNode root) {
        if (root.left == null && root.right == null) {
            return 0;
        }

        int leftSize = root.left != null ? dfs(root.left) + 1 : 0;
        int rightSize = root.right != null ? dfs(root.right) + 1 : 0;
        if (leftSize > 0 && root.left.val != root.val) {
            // 唯一的区别在这里，按照上题思路求出了左边边长后， 如果当前节点和左孩子节点不同值，就把边长重新赋值为0。
            leftSize = 0;
        }
        if (rightSize > 0 && root.right.val != root.val) {
            // 同上。
            rightSize = 0;
        }
        max = Math.max(max, leftSize + rightSize);
        return Math.max(leftSize, rightSize);
    }
}
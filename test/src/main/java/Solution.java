import java.util.*;

class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null) {
            return null;
        }

        return generate(nums, 0, nums.length - 1);
    }

    private static TreeNode generate(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        TreeNode head = new TreeNode(nums[mid]);
        head.left = generate(nums, start, mid - 1);
        head.right = generate(nums, mid + 1, end);
        return head;
    }
}
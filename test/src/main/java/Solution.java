import java.util.*;

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (n <= 0 || k <= 0 || n < k) {
            return result;
        }

        int[] nums = new int[n];
        for (int i = 1; i <= n; i++) {
            nums[i - 1] = i;
        }

        backtrack(nums, 0, new ArrayList<>(), result, k);
        return result;

    }

    private void backtrack(int[] nums, int begin, List<Integer> temp, List<List<Integer>> result, int k) {
        if (temp.size() == k) {
            result.add(new ArrayList<>(temp));
            return;
        }

        for (int i = begin; i < nums.length; i++) {
            temp.add(nums[i]);
            backtrack(nums, i + 1, temp, result, k);
            temp.remove(temp.size() - 1);
        }
    }
}
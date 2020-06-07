import java.util.*;


class Solution {
    public int lengthOfLIS(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int[] dp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}

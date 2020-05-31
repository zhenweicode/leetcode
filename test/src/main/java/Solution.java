import java.util.*;


class Solution {
    public int maxProfit(int[] prices) {
        return maxProfit(2, prices);
    }

    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (k > n / 2)
            return greedy(prices);

        int[][][] dp = new int[n][k + 1][2];

        for (int i = 0; i < n; i++) {
            // base case
            dp[i][0][0] = 0;//至今为止没有交易，收益为0
            dp[i][0][1] = Integer.MIN_VALUE;//交易了0次，但持有股票，不符合规则

            for (int j = k; j >= 1; j--) {
                if (i == 0) {
                    dp[i][j][0] = 0;//第一天买入t次，当天卖出t次,收入为0
                    dp[i][j][1] = -prices[i];// 甭管第一天买多少次，反正最后少卖一次，持有了股票
                    continue;
                }

                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }


        return dp[n - 1][k][0];
    }

    private int greedy(int[] prices) {
        int max = 0;
        for (int i = 1; i < prices.length; ++i) {
            if (prices[i] > prices[i - 1])
                max += prices[i] - prices[i - 1];
        }
        return max;
    }
}
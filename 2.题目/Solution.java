class Solution {
    /**
     * 鸡蛋掉落，鹰蛋（Leetcode 887）：（经典dp）
     * 思路：
     * 本题应该逆向思维，若你有 K 个鸡蛋，你最多操作 F 次，求 N 最大值。
     * <p>
     * dp[k][f] = dp[k][f-1] + dp[k-1][f-1] + 1;
     * 解释：
     * 0.dp[k][f]：如果你还剩 k 个蛋，且只能操作 f 次了，所能确定的楼层。
     * 1.dp[k][f-1]：蛋没碎，因此该部分决定了所操作楼层的上面所能容纳的楼层最大值
     * 2.dp[k-1][f-1]：蛋碎了，因此该部分决定了所操作楼层的下面所能容纳的楼层最大值
     * 又因为第 f 次操作结果只和第 f-1 次操作结果相关，因此可以只用一维数组。
     * <p>
     * 时复：O(K*根号(N))
     */
    public int superEggDrop(int K, int N) {
        int[] dp = new int[K + 1];
        int ans = 0;    // 操作的次数
        while (dp[K] < N) {
            for (int i = K; i > 0; i--) // 从后往前计算
                dp[i] = dp[i] + dp[i - 1] + 1;
            ans++;
        }
        return ans;
    }
}

public class SubsetSumProblem {
    public static void main(String[] srgs) {
        int[] sets = {7, 34, 4, 12, 5, 3};
        int sum = 87;
        boolean isExistSubSet = subsetSumProblem(sets, sum);
        System.out.println("集合" + Arrays.toString(sets) + "是否存在子集的和等于" + sum + ":" + isExistSubSet);
    }

    private static boolean subsetSumProblem(int[] arr, int sum) {
        int row = arr.length + 1;
        int col = sum + 1;
        int[][] dp = new int[row][col];
        dp[0][0] = 1;

        for (int j = 1; j < col; j++) {
            dp[0][j] = 0;
        }

        // 按行填充
        for (int i = 1; i < row; i++) {
            dp[i][0] = 1;
            for (int j = 1; j < col; j++) {
                dp[i][j] = dp[i - 1][j];

                if (dp[i][j] != 1) {
                    if (j - arr[i - 1] >= 0 && dp[i][j - arr[i - 1]] == 1) {
                        dp[i][j] = dp[i][j - arr[i - 1]];
                    } else {
                        dp[i][j] = 0;
                    }
                }
            }
        }
        return dp[row - 1][col - 1] == 1 ? true : false;
    }
}
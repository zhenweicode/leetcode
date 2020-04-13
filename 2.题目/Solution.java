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

class Solution {
    public int numDecodings(String s) {
        return getAns(s, 0);
    }

    private int getAns(String s, int start) {
        //划分到了最后返回 1
        if (start == s.length()) {
            return 1;
        }
        //开头是 0,0 不对应任何字母，直接返回 0
        if (s.charAt(start) == '0') {
            return 0;
        }
        //得到第一种的划分的解码方式
        int ans1 = getAns(s, start + 1);
        int ans2 = 0;
        //判断前两个数字是不是小于等于 26 的
        if (start < s.length() - 1) {
            int ten = (s.charAt(start) - '0') * 10;
            int one = s.charAt(start + 1) - '0';
            if (ten + one <= 26) {
                ans2 = getAns(s, start + 2);
            }
        }
        return ans1 + ans2;
    }
}
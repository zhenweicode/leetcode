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
    public static int[] cal_next(String str) {                // 计算关于 模式串 的 next 数组
        int len = str.length(), next[] = new int[len];
        next[0] = -1;
        int i = 0, j = 1;                                    // i j 分别是前后缀指针
        while (j < len) {
            if (i == -1 || str.charAt(i) == str.charAt(j)) {
                i++;
                j++;
                if (j < len) next[j] = i;
            } else i = next[i];
        }

        return next;
    }

    public static int strStr(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        if (haystack.length() == 0) return -1;
        int[] next = cal_next(needle);                        // 计算 needle 的前后缀
        int i = 0, j = 0, len = haystack.length(), subLen = needle.length();
        while (i < len) {
            if (j == -1 || haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
                if (j == subLen) return i - j;                // needle 指针走到末尾，说明匹配成功
            } else j = next[j];
        }
        return -1;
    }
}
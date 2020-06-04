import java.util.*;

class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) return 0;

        int width = matrix[0].length;
        int maxSide = 0;

        int[] dp = new int[width + 1];
        int northwest = 0; // 西北角、左上角

        for (char[] chars : matrix) {
            northwest = 0; // 遍历每行时，还原回辅助的原值0
            for (int col = 0; col < width; col++) {
                int nextNorthwest = dp[col + 1];
                if (chars[col] == '1') {
                    dp[col + 1] = Math.min(Math.min(dp[col], dp[col + 1]), northwest) + 1;
                    maxSide = Math.max(maxSide, dp[col + 1]);
                } else {
                    dp[col + 1] = 0;
                }
                northwest = nextNorthwest;
            }
        }
        return maxSide * maxSide;
    }
}




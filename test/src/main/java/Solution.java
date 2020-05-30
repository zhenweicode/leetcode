import java.util.*;

class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int tR = 0;
        int tC = 0;
        int dR = n - 1;
        int dC = n - 1;

        int[] sum = new int[]{1};
        while (tR <= dR && tC <= dC) {
            getEdge(matrix, tR++, tC++, dR--, dC--, sum);
        }

        return matrix;
    }

    public static void getEdge(int[][] m, int tR, int tC, int dR, int dC, int[] sum) {
        if (tR == dR) {
            m[tR][tC] = sum[0]++;
        } else {
            int curC = tC;
            int curR = tR;
            while (curC != dC) {
                m[tR][curC] =sum[0]++;
                curC++;
            }
            while (curR != dR) {
                m[curR][dC] = sum[0]++;
                curR++;
            }
            while (curC != tC) {
                m[dR][curC] = sum[0]++;
                curC--;
            }
            while (curR != tR) {
                m[curR][tC] = sum[0]++;
                curR--;
            }
        }
    }

    public static void main(String[] args) {
        new Solution().generateMatrix(3);
    }
}
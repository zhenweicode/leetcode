import java.util.*;

public class Solution extends Relation {
    public int findCelebrity(int n) {
        for (int i = 0; i < n; i++) {
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                // 过滤自己认识自己的情况
                if (i == j) {
                    continue;
                }
                // 假设i为名人， 如果i认识除自己以外的其他人，或者有其他人（j）不认识i
                // 那么就可以直接断定当前的i并不是名人
                if (knows(i, j) || !knows(j, i)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i;
            }
        }
        return -1;
    }
}
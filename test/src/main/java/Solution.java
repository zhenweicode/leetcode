import java.util.*;

class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        boolean x_overlap = !(rec1[2] <= rec2[0] || rec2[2] <= rec1[0]);
        boolean y_overlap = !(rec1[3] <= rec2[1] || rec2[3] <= rec1[1]);
        return x_overlap && y_overlap;
    }
}
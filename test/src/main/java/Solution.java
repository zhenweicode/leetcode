import java.util.*;

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        int p = m + n - 1;
        while ((p1 >= 0) && (p2 >= 0)) {
            nums1[p--] = (nums1[p1] < nums2[p2]) ? nums2[p2--] : nums1[p1--];
        }

        // 如果是nums1没遍历完，那么已经在正确的位置
        // 如果是nums2没遍历完，需要拷贝
        if (p2 >= 0) {
            while (p2 >= 0) {
                nums1[p--] = nums2[p2--];
            }
        }
    }
}
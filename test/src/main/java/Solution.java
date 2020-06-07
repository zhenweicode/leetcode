import java.util.*;


public class Solution {
       public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        int left = 0;
        int right = len - 1;

        // 转换一下，第 k 大元素的索引是 len - k
        int target = len - k;

        while (true) {
            int index = partition(nums, left, right);
            if (index == target) {
                return nums[index];
            } else if (index < target) {
                left = index + 1;
            } else {
                right = index - 1;
            }
        }
    }

    private int partition(int[] nums, int low, int high) {
        int temp = nums[low];                   // 数组的第一个作为基准
        while (low < high) {                //从数组的两端交替地向中间扫描
            while (low < high && nums[high] >= temp) { // 不考虑=的话会陷入死循环
                high--;
            }
            nums[low] = nums[high];            // 比基准小的记录移到低端
            while (low < high && nums[low] <= temp) {
                low++;
            }
            nums[high] = nums[low];           // 比基准大的记录移到高端
        }
        nums[low] = temp;                     // 将基准的记录放到此时low=high的位置
        return low;                          // 返回此时基准的位置
    }
}
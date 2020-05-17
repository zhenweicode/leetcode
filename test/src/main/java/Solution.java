import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Solution{
    public static int biSearch(int[] nums, int target){
        if(nums == null || nums.length == 0){
            return -1;
        }

        if(target < nums[0] || target > nums[nums.length - 1]){
            return -1;
        }

        int low = 0;
        int high = nums.length - 1;
        while(low <= high){
            int mid = low + (high - low) / 2;
            if(target == nums[mid]){
                return mid;
            } else if(target > nums[mid]){
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }
}
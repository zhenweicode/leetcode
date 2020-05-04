class Solution {
    public int[] searchRange(int[] nums, int target) {
        if(nums == null || nums.length == 0 || target < nums[0] || target > nums[nums.length - 1]){
            return new int[]{-1, -1};
        }

        int high = nums.length - 1;
        int low = 0;
        int mid = 0;
        while(low <= high){
            mid = low + (high - low) / 2;
            if(target == nums[mid]){
                return findTarget(nums, target, mid);
            } else if(target > nums[mid]){
                low = mid + 1;
            } else{
                high = mid - 1;
            }
        }

        return new int[]{-1, -1};
    }

    private int[] findTarget(int[] nums, int target, int mid){
        int low = mid;
        int high = mid;
        int[] res = new int[]{mid, mid};
        while(low >= 0 && nums[low] == target){
            res[0] = low;
            low--;
        }

        while(high < nums.length && nums[high] == target){
            res[1] = high;
            high++;
        }

        return res;
    }

    public static void main(String[] args) {
        new Solution().searchRange(new int[]{5,7,7,8,8,10}, 8);
    }
}
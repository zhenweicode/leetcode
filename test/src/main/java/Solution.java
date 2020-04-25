class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;
        int mid;
        while (start <= end) {
            mid = start + (end - start) / 2;  // 这么写是为了防止两个数相加超过Integer.MAX_VALUE,专业点的一般都这么写
            if (nums[mid] == target) {
                return mid;
            }
            //前半部分有序,注意此处用小于等于,如果nums只有两个数，start和mid就相同了，不加等号结果会出错
            if (nums[start] <= nums[mid]) {
                //target在前半部分
                if (target >= nums[start] && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (target <= nums[end] && target > nums[mid]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }

        }
        return -1;
    }
}
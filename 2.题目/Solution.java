class Solution {
    public static void splitArray(int[] arr, int k) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            while (left < right && arr[left] < k) {
                left++;
            }
            while (left < right && arr[right] >= k) {
                right--;
            }
            swap(arr, left++, right--);
        }
        // 注意：在上面运行时，left可能停在不比k小的子数组的第一个元素（最后left==right的位置符合arr[left] < k），
        // 也可能停在第二个元素(最后left==right的位置符合arr[left] >=k)，因为交换完成后又执行了left++;right--

        left = left - 1;// 保证能包括不比k小的子数组中的所有元素即可
        right = arr.length - 1;
        while (left < right) {
            while (left < right && arr[left] <= k) {// 取等号防止left = left-1后包括了arr[left] == k的元素
                left++;
            }
            while (left < right && arr[right] > k) {
                right--;
            }
            swap(arr, left++, right--);
        }
    }

    public static void swap(int[] nums, int n1, int n2) {
        int tmp = nums[n1];
        nums[n1] = nums[n2];
        nums[n2] = tmp;
    }
}
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }

        // 按照会议结束时间有序，优先队列大小为分配的会议室数
        PriorityQueue<Integer> allocator = new PriorityQueue<Integer>(intervals.length, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return a - b;
            }
        });

        // 按照会议开始时间排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(final int[] a, final int[] b) {
                return a[0] - b[0];
            }
        });

        allocator.add(intervals[0][1]);  // 添加第一场会议
        for (int i = 1; i < intervals.length; i++) {

            // 找到第一个结束的会议室分配
            if (intervals[i][0] >= allocator.peek()) {
                allocator.poll();
            }

            // 新会议加入
            allocator.add(intervals[i][1]);
        }

        return allocator.size();
    }
}
import java.util.*;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // 使用字典，统计每个元素出现的次数，元素为键，元素出现的次数为值
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }


        for(Map.Entry<Integer, Integer> entry : map.entrySet()){

        }

        // 遍历map，用最小堆保存频率最大的k个元素，注意这里：塞进去的是key，排序的是map的value
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return map.get(a) - map.get(b);
            }
        });

        for (Integer key : map.keySet()) {
            if (pq.size() < k) {
                pq.add(key);
            } else if (map.get(key) > map.get(pq.peek())) {
                pq.remove();
                pq.add(key);
            }
        }
        // 取出最小堆中的元素
        int[] res = new int[pq.size()];
        int i = pq.size() - 1;
        while (!pq.isEmpty()) {
            res[i--] = pq.remove();
        }

        return res;
    }

    private static class Count {
        int num;
        int count;
    }
}
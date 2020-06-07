import java.util.*;

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) return new int[0];
        int[] inDegrees = new int[numCourses];
        // 建立入度表
        for (int[] p : prerequisites) { // 获取每门课的入度表，其中p[1]是p[0]的前置
            inDegrees[p[0]]++;
        }

        // 入度为0的节点队列
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegrees.length; i++) {
            if (inDegrees[i] == 0) queue.offer(i);
        }

        int count = 0;  // 记录可以学完的课程数量
        int[] res = new int[numCourses];  // 可以学完的课程
        // 根据提供的先修课列表，删除入度为 0 的节点
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            res[count++] = curr;   // 将可以学完的课程加入结果当中
            for (int[] p : prerequisites) {  // 这里没用邻接表
                if (p[1] == curr) {
                    inDegrees[p[0]]--;
                    if (inDegrees[p[0]] == 0) queue.offer(p[0]);
                }
            }
        }
        if (count == numCourses) return res;
        return new int[0];
    }
}

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0) {
            return new int[0];
        }

        int[] indegrees = new int[numCourses]; // 课程入度表
        List<List<Integer>> adjacency = new ArrayList<>();  // 课程安排图的邻接表
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++)
            adjacency.add(new ArrayList<>());

        for (int[] p : prerequisites) { // 获取每门课的入度和邻接表，其中cp[1]是cp[0]的前置
            indegrees[p[0]]++;
            adjacency.get(p[1]).add(p[0]);
        }

        for (int i = 0; i < numCourses; i++)  // 获取所有入度为零的课程
            if (indegrees[i] == 0) queue.add(i);

        int count = 0;  // 记录可以学完的课程数量
        int[] res = new int[numCourses];  // 可以学完的课程
        while (!queue.isEmpty()) {  // 广度优先遍历，BFS
            int pre = queue.poll();
            res[count++] = pre;   // 将可以学完的课程加入结果当中
            for (int cur : adjacency.get(pre)) {
                if (--indegrees[cur] == 0) queue.add(cur);
            }
        }

        if (count == numCourses) return res;
        return new int[0];
    }
}

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0) {
            return new int[0];
        }

        HashSet<Integer>[] adj = new HashSet[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adj[i] = new HashSet<>();
        }

        // [1,0] 0 -> 1
        int[] inDegree = new int[numCourses];
        for (int[] p : prerequisites) {
            adj[p[1]].add(p[0]);
            inDegree[p[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        int[] res = new int[numCourses];
        // 当前结果集列表里的元素个数，正好可以作为下标
        int count = 0;

        while (!queue.isEmpty()) {
            // 当前入度为 0 的结点
            Integer head = queue.poll();
            res[count] = head;
            count++;

            Set<Integer> successors = adj[head];
            for (Integer nextCourse : successors) {
                inDegree[nextCourse]--;
                // 马上检测该结点的入度是否为 0，如果为 0，马上加入队列
                if (inDegree[nextCourse] == 0) {
                    queue.offer(nextCourse);
                }
            }
        }

        // 如果结果集中的数量不等于结点的数量，就不能完成课程任务，这一点是拓扑排序的结论
        if (count == numCourses) {
            return res;
        }
        return new int[0];
    }
}


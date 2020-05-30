import java.util.*;

class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        // base cases
        if (head == null || k <= 0 || head.next == null) {
            return head;
        }

        // 连接成环
        ListNode old_tail = head;
        int n;  // 链表长度
        for (n = 1; old_tail.next != null; n++)
            old_tail = old_tail.next;
        old_tail.next = head;

        // 新的头节点：(n - k % n)th node
        // 新的尾结点：(n - k % n - 1)th node
        ListNode new_tail = head;
        for (int i = 0; i < n - k % n - 1; i++)
            new_tail = new_tail.next;
        ListNode new_head = new_tail.next;

        // break the ring
        new_tail.next = null;

        return new_head;
    }
}

作者：LeetCode
        链接：https://leetcode-cn.com/problems/rotate-list/solution/xuan-zhuan-lian-biao-by-leetcode/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
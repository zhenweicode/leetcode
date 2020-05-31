import java.util.*;

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy, fast = head;
        boolean repeat = false;  // 是否重复
        while (slow.next != null) {
            while (fast.next != null && fast.next.val == slow.next.val) {
                fast = fast.next;
                repeat = true;//如果有重复节点
            }
            if (repeat) {
                slow.next = fast.next;//删掉重复节点
                fast = fast.next;//再指针后移！！这时候slow不用动！
                repeat = false;
            } else {
                slow = fast;//指针后移!slow,fast都向后移动一下
                if (fast.next != null) fast = fast.next;
            }
        }
        return dummy.next;
    }
}
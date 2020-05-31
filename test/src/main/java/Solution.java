import java.util.*;

public class Solution {
    public ListNode FindLoopPort(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next; // slow指针走一步
            fast = fast.next.next; // fast指针走两步
            if (slow == fast) { // 一旦两个指针相遇，说明链表是有环的
                break;
            }
        }
        slow = head;
        while (slow != fast) {
            slow = slow.next; // slow指针走一步
            fast = fast.next; // fast指针走两步
        }
        return slow;
    }
}
import java.util.*;

public class Solution {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }

        // 找中点
        ListNode mid = getMid(head);
        ListNode newHead = mid.next;
        mid.next = null;

        //第二个链表倒置
        newHead = reverseList(newHead);

        ListNode headTemp = head; // 不改变原有的头节点
        //链表节点依次连接
        while (newHead != null) {
            ListNode temp = newHead.next;
            newHead.next = headTemp.next;
            headTemp.next = newHead;
            headTemp = newHead.next;
            newHead = temp;
        }
    }

    private ListNode getMid(ListNode head) {
        // 找中点，链表分成两个
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    private ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }
}
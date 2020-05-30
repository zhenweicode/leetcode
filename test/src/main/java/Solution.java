import java.util.*;

class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode temp = dummy;
        while (temp.next != null && temp.next.next != null) {
            ListNode first = temp.next;
            ListNode second = first.next;
            ListNode third = second.next;
            second.next = first;
            first.next = third;
            temp.next = second;
            temp = first; // 翻转之后，first后面是third
        }

        return dummy.next;
    }
}
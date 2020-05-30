import java.util.*;

class Solution {
    public Node findLastNode2(Node head, int k) {
        if (k == 0 || head == null) {
            return null;
        }
        Node first = head;
        Node second = head;
        for (int i = 0; i < k - 1; i++) {
            second = second.next;
            if (second == null) { // 说明k的值已经大于链表的长度了
                // throw new NullPointerException("链表的长度小于" + k);
                return null;
            }
        }

        while (second.next != null) {
            first = first.next;
            second = second.next;
        }
        return first;
    }
}
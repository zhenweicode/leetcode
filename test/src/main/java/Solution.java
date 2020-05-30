import java.util.*;

class Solution {
    public void delDup2(Node head) {
        Node p = head;
        Node q = null;
        while (p != null) {
            q = p;
            while (q.next != null) {
                if (p.val == q.next.val) {
                    q.next = q.next.next;
                } else {
                    q = q.next;
                }
            }
            p = p.next;
        }
    }
}
import java.util.*;

class Solution {
    public Node findMidNode(Node head) {
        if (head == null) {
            return null;
        }
        Node first = head;
        Node second = head;
        while (second.next != null && second.next.next != null) { //如果判断second != null && second.next != null，会1,2,3,4,5,6的中点为4
            first = first.next;
            second = second.next.next;
        }
        return first;
    }
}
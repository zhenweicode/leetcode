class Solution {
    public Node connect(Node root) {
        if (root == null) return root;

        Node head = root;
        while(head != null) {
            Node dummy = new Node();
            Node cur = dummy;
            while(head != null) {
                if (head.left != null) {
                    cur.next = head.left;
                    cur = cur.next;
                }
                if (head.right != null) {
                    cur.next = head.right;
                    cur = cur.next;
                }
                head = head.next;
            }
            head = dummy.next;  // 下一层最左节点
        }

        return root;
    }
}
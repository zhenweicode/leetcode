import java.util.*;

public class Solution {
    public static void preOrderUnRecur(Node head) {
        if (head != null) {
            Stack<Node> stack = new Stack<Node>();
            stack.push(head);
            while (!stack.isEmpty()) {
                head = stack.pop();
                System.out.print(head.value + " ");
                if (head.right != null) {
                    stack.push(head.right);
                }
                if (head.left != null) {
                    stack.push(head.left);
                }
            }
        }
    }

    public static void inOrderUnRecur(Node head) {
        if (head != null) {
            Stack<Node> stack = new Stack<Node>();
            while (!stack.isEmpty() || head != null) {//打印根节点时栈为空
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    System.out.print(head.value + " ");
                    head = head.right;
                }
            }
        }
    }

    //h表示最近一次弹出并打印的节点
    //c表示stack的栈顶元素
    public static void posOrderUnRecur(Node h) {
        if (h != null) {
            Stack<Node> stack = new Stack<Node>();
            stack.push(h);
            Node c = null;
            while (!stack.isEmpty()) {
                c = stack.peek();
                if (c.left != null && h != c.left && h != c.right) {//c左子树不为空，且左右子树都未打印
                    stack.push(c.left);
                } else if (c.right != null && h != c.right) {//c右子树不为空，且右子树都未打印
                    stack.push(c.right);
                } else {//c左右子树都已打印
                    System.out.print(stack.pop().value + " ");
                    h = c;
                }
            }
        }
    }
}
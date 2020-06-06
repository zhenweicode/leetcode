import java.util.*;

class MyStack {
    private Queue<Integer> queue1 = null;
    private Queue<Integer> queue2 = null;
    private int flag = 0;

    public MyStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    public void push(int x) {
        if (flag == 0) {
            queue1.offer(x);
        } else {
            queue2.offer(x);
        }
    }

    public int pop() {
        int num = 0;
        if (flag == 0 && !queue1.isEmpty()) {
            num = pop(queue1, queue2);
            flag = 1;
        } else if (flag == 1 && !queue2.isEmpty()) {
            num = pop(queue2, queue1);
            flag = 0;
        } else {
            throw new NullPointerException("stack is empty");
        }

        return num;
    }

    private int pop(Queue<Integer> q1, Queue<Integer> q2) {
        if (q1.size() == 1) {
            return q1.poll();
        }

        while (q1.size() > 1) {
            q2.offer(q1.poll());
        }

        return q1.poll();
    }

    public int top() {
        int num = 0;
        if (flag == 0 && !queue1.isEmpty()) {
            num = top(queue1, queue2);
        } else if (flag == 1 && !queue2.isEmpty()) {
            num = top(queue2, queue1);
        } else {
            throw new NullPointerException("stack is empty");
        }

        return num;
    }

    private int top(Queue<Integer> q1, Queue<Integer> q2) {
        if (q1.size() == 1) {
            return q1.peek();
        }

        while (q1.size() > 1) {
            q2.offer(q1.poll());
        }

        return q1.peek();
    }

    public boolean empty() {
        return queue1.isEmpty() && queue2.isEmpty();
    }
}




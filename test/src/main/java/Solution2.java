import java.util.LinkedList;

/**
 * @author xiezhenwei
 * @date 2020/4/25
 */
class Solution2 {
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        LinkedList<Boolean> stack = new LinkedList<>();
        int result = 0, opr = 0; //result: 当前的结果值; opr: 当前的被加/被减数
        Character op = null; //当前要执行的运算符，每次遇到下一个操作符，我们才会执行上一个，保证数字被统计完毕

        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                opr = opr * 10 + c - '0';
            } else if (c == '+' || c == '-') {  // 遇到操作符，说明前面连续的数字统计完成，比如123，需要123 >> 120 + 3 >> 100 + 20 + 3，计算即可
                if (op == null) {
                    result = opr;  //遇到第一个运算符时，将result置为opr（即第一个运算符左边的数字）
                } else {
                    result = cal(op, result, opr);   //result = result +/- opr;
                }

                op = swap(stack.peek() == null ? false : stack.peek(), c); //根据栈顶元素决定是否反转运算符
                opr = 0;
            } else if (c == '(') {
                stack.push(op != null && op == '-');
            } else if (c == ')') {
                stack.pop();
            }
        }

        if (op == null) {
            //算式中没有运算符时，opr就是最终结果
            return opr;
        } else {
            //否则将result与opr（即算式中最右边的数字）执行一次运算
            return cal(op, result, opr);
        }
    }

    private char swap(boolean swap, char c) {
        if (swap) {
            return c == '+' ? '-' : '+';
        } else {
            return c;
        }
    }

    private int cal(char op, int opr1, int opr2) {
        switch (op) {
            case '+':
                return opr1 + opr2;
            case '-':
                return opr1 - opr2;
            default:
                return 0;
        }
    }
}

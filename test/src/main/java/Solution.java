import java.util.*;

class Solution {
    public int calculate(String s) {
        String[] polish = getPolish(s); //转后缀表达式
        return evalRPN(polish);
    }

    //中缀表达式转后缀表达式
    private String[] getPolish(String s) {
        List<String> res = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        char[] array = s.toCharArray();
        int n = array.length;
        int temp = -1; //累加数字，-1 表示当前没有数字
        for (int i = 0; i < n; i++) {
            if (array[i] == ' ') {
                continue;
            }
            //遇到数字
            if (isNumber(array[i])) {
                //进行数字的累加
                if (temp == -1) {
                    temp = array[i] - '0';
                } else {
                    temp = temp * 10 + array[i] - '0';
                }
            } else {
                //遇到其它操作符，将数字加入到结果中
                if (temp != -1) {
                    res.add(temp + "");
                    temp = -1;
                }
                if (isOperation(array[i] + "")) {
                    //遇到操作符将栈中的操作符加入到结果中
                    while (!stack.isEmpty()) {
                        //遇到左括号结束
                        if (stack.peek().equals("(")) {
                            break;
                        }
                        res.add(stack.pop());
                    }
                    //当前操作符入栈
                    stack.push(array[i] + "");
                } else {
                    //遇到左括号，直接入栈
                    if (array[i] == '(') {
                        stack.push(array[i] + "");
                    }
                    //遇到右括号，将出栈元素加入到结果中，直到遇到左括号
                    if (array[i] == ')') {
                        while (!stack.peek().equals("(")) {
                            res.add(stack.pop());
                        }
                        //左括号出栈
                        stack.pop();
                    }

                }
            }
        }
        //如果有数字，将数字加入到结果
        if (temp != -1) {
            res.add(temp + "");
        }
        //栈中的其他元素加入到结果
        while (!stack.isEmpty()) {
            res.add(stack.pop());
        }
        String[] sArray = new String[res.size()];
        //List 转为 数组
        for (int i = 0; i < res.size(); i++) {
            sArray[i] = res.get(i);
        }
        return sArray;
    }

    // 下边是 150 题的代码，求后缀表达式的值
    public static int evalRPN(String[] tokens) {
        int[] numStack = new int[tokens.length / 2 + 1];
        int index = 0;
        for (String s : tokens) {
            switch (s) {
                case "+":
                    numStack[index - 2] += numStack[--index];
                    break;
                case "-":
                    numStack[index - 2] -= numStack[--index];
                    break;
                case "*":
                    numStack[index - 2] *= numStack[--index];
                    break;
                case "/":
                    numStack[index - 2] /= numStack[--index];
                    break;
                default:
                    // numStack[index++] = Integer.valueOf(s); valueOf改为parseInt，减少自动拆箱装箱操作
                    numStack[index++] = Integer.parseInt(s);
                    break;
            }
        }
        return numStack[0];
    }
}
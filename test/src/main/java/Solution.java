import java.util.*;

class Solution {
    /**
     * 10进制转k进制
     *
     * @param num 输入10进制数
     * @param k   输出k进制数
     */
    public static String toKConvert(int num, int k) {
        StringBuffer numk = new StringBuffer();
        int temp = 0;
        while (num != 0) {
            temp = num % k;
            if (temp > 9) {
                numk.append((char) ('a' + temp - 10));// 千万注意，这里括号不能删除(char)('a' + b - 10)
            } else {
                numk.append(temp);
            }
            num = num / k;
        }
        return numk.reverse().toString();
    }

    /**
     * 将36进制内的任意进制数转换为10进制
     *
     * @param numk 输入36进制内字符串
     * @param k    输入进制数
     */
    public static int to10Convert(String numk, int k) {
        int num = 0; // 转换后的10进制
        int temp = 0; // numk的任一位的ASCII码
        int temp2 = 0; // temp对应的10进制数
        for (int i = 0; i < numk.length(); i++) {
            temp = (int) numk.charAt(i);
            if (temp >= (int) 'a') {
                temp2 = temp - 'a' + 10;
            } else {
                temp2 = temp - '0';
            }
            num = num * k + temp2;
        }
        return num;
    }

    /**
     * 将任意进制转任意进制
     * <p>
     * 比如16进制4cf，转为25进制数,那就先转换为10进制，再转换为25进制
     *
     * @param numk 输入36进制内字符串
     * @param ki   输入进制数
     * @param ko   输出进制数
     */
    public static String kConvert3(String numk, int ki, int ko) {
        int num = to10Convert(numk, ki);
        return toKConvert(num, ko);
    }
}
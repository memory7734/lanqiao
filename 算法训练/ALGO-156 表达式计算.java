/**
问题描述
　　输入一个只包含加减乖除和括号的合法表达式，求表达式的值。其中除表示整除。
输入格式
　　输入一行，包含一个表达式。
输出格式
　　输出这个表达式的值。
样例输入
1-2+3*(4-5)
样例输出
-4
数据规模和约定
　　表达式长度不超过100，表达式运算合法且运算过程都在int内进行。
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    private Stack<String> operator = new Stack<String>();
    private Stack<String> result = new Stack<String>();
    private List<String> infix = new ArrayList<String>();
    private List<String> postfix = new ArrayList<String>();

    public Main(String string) {
        StringTokenizer tokenizer = new StringTokenizer(string, "+-*/()", true);
        while (tokenizer.hasMoreElements()) {
            infix.add(tokenizer.nextToken());
        }
    }

    private List<String> convertPostifx() {
        operator.clear();
        for (String string : infix) {
            if (isNumber(string)) {
                postfix.add(string);
            } else if (isOperate(string)) {
                getPostifx(string);
            }
        }
        while (!operator.empty()) {
            postfix.add(operator.pop());
        }
        return postfix;
    }

    private void getPostifx(String string) {
        if (operator.empty() || string.equals("(")) {
            operator.push(string);
        } else if (string.equals(")")) {
            String tmp;
            while (!(tmp = operator.pop()).equals("(")) {
                postfix.add(tmp);
            }
        } else if (operator.peek().equals("(") || comparePriority(string, operator.peek())) {
            operator.push(string);
        } else {
            postfix.add(operator.pop());
            getPostifx(string);
        }
    }
    private String calculatePostifx() {
        result.clear();
        for (String string : postfix) {
            if (isNumber(string)) {
                result.push(string);
            } else if (isOperate(string)) {
                int val2 = Integer.parseInt(result.pop());
                int val1 = Integer.parseInt(result.pop());
                if (string.equals("*")) {
                    result.push(String.valueOf(val1 * val2));
                }else if (string.equals("/")) {
                    result.push(String.valueOf(val1 / val2));
                }else if (string.equals("+")) {
                    result.push(String.valueOf(val1 + val2));
                }else if (string.equals("-")) {
                    result.push(String.valueOf(val1 - val2));
                }
            }
        }
        return result.pop();
    }


    private boolean isOperate(String op) {
        return op.matches("[\\+\\*\\/\\(\\)-]");
    }

    private boolean isNumber(String num) {
        return num.matches("\\d+");
    }

    private boolean comparePriority(String op1, String op2) {
        return getPriority(op1) > getPriority(op2);
    }
    private int getPriority(String string) {
        if (string.equals("+") || string.equals("-")) {
            return 1;
        }
        if (string.equals("*") || string.equals("/")) {
            return 2;
        }
        if (string.equals("(") || string.equals(")")) {
            return 3;
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        Main main = new Main(s);
        main.convertPostifx();
        System.out.println(main.calculatePostifx());
    }


}
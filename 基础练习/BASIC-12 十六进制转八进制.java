/**
问题描述
　　给定n个十六进制正整数，输出它们对应的八进制数。

输入格式
　　输入的第一行为一个正整数n （1<=n<=10）。
　　接下来n行，每行一个由0~9、大写字母A~F组成的字符串，表示要转换的十六进制正整数，每个十六进制数长度不超过100000。

输出格式
　　输出n行，每行为输入对应的八进制正整数。

　　【注意】
　　输入的十六进制数不会有前导0，比如012A。
　　输出的八进制数也不能有前导0。

样例输入
　　2
　　39
　　123ABC

样例输出
　　71
　　4435274

　　【提示】
　　先将十六进制数转换成某进制数，再由某进制数转换成八进制。
 */
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int row = sc.nextInt();
        while (row > 0) {
            String input = sc.next();
            System.out.println(convert(input));
            row--;
        }
    }

    public static String convert(String s) {
        StringBuilder builder = new StringBuilder();
        if (s.length() % 3 == 1) {
            builder.append("00");
        } else if (s.length() % 3 == 2) {
            builder.append("0");
        }
        for (int i = 0; i < s.length(); i++) {
            builder.append(getNum(s.charAt(i)));
        }
        String binaryString = builder.toString();
        int pos = 0;
        for (int i = 0; i < binaryString.length(); i++) {
            if (binaryString.substring(3 * i, 3 * (i + 1)).equals("000")) {
                pos = i + 1;
            } else {
                break;
            }
        }
        binaryString = binaryString.substring(pos * 3);
        builder = new StringBuilder();
        for (int i = 0; i < binaryString.length() / 3; i++) {
            builder.append(Integer.valueOf(binaryString.substring(3 * i, 3 * (i + 1)), 2));
        }
        return builder.toString();
    }

    public static String getNum(char c) {
    	int num=Integer.parseInt(""+c, 16);
    	String s="0000"+Integer.toBinaryString(num);   	
    	return s.substring(s.length()-4);
    }
}

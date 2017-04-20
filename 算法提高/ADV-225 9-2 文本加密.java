/**
问题描述
　　先编写函数EncryptChar,按照下述规则将给定的字符c转化（加密）为新的字符："A"转化"B"，"B"转化为"C"，... ..."Z"转化为"a"，"a"转化为"b",... ..., "z"转化为"A"，其它字符不加密。编写程序，加密给定字符串。
样例输出
与上面的样例输入对应的输出。
例：

数据规模和约定
　　输入数据中每一个数的范围。
　　例：50个字符以内无空格字符串。
*/
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 'a' && c < 'z') {
                builder.append((char) (c + 1));
            } else if (c == 'z') {
                builder.append('A');
            } else if (c >= 'A' && c < 'Z') {
                builder.append((char) (c + 1));
            } else if (c == 'Z') {
                builder.append('a');
            } else {
                builder.append(c);
            }
        }
        System.out.println(builder.toString());
    }
}
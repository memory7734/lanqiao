/**
问题描述
　　Given two integers A and B, your task is to output their sum, A+B.
输入格式
　　The input contains of only one line, consisting of two integers A and B. (0 ≤ A,B ≤ 1 000)
输出格式
　　The output should contain only one number that is A+B.
样例输入
1 1
样例输出
2
 */
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(scanner.nextInt() + scanner.nextInt());
    }
}

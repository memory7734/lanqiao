/**
问题描述
　　输入A,B。
　　输出A+B。
输入格式
　　输入包含两个整数A,B，用一个空格分隔。
输出格式
　　输出一个整数，表示A+B的值。
样例输入
5 8
样例输出
13
数据规模和约定
　　-1,000,000,000<=A,B<=1,000,000,000。 
*/
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(scanner.nextInt() + scanner.nextInt());
    }
}

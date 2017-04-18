/**
问题描述
　　编写一个程序，输入3个整数，然后程序将对这三个整数按照从大到小进行排列。
　　输入格式：输入只有一行，即三个整数，中间用空格隔开。
　　输出格式：输出只有一行，即排序后的结果。
　　输入输出样例
样例输入
9 2 30
样例输出
30 9 2
 */
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        int max = Math.max(Math.max(a, b), c);
        int min = Math.min(Math.min(a, b), c);
        int sum = a + b + c;
        System.out.println(max + " " + (sum - max - min) + " " + min);
    }
}

/**
问题描述
已知一个正整数N，问从1~N中任选出三个数，他们的最小公倍数最大可以为多少。
输入格式
输入一个正整数N。
输出格式
输出一个整数，表示你找到的最小公倍数。
样例输入
9
样例输出
504
数据规模与约定
1 <= N <= 106。
 */
import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        BigInteger integer = BigInteger.valueOf(n);
        if (n <= 2) {
            System.out.println(n);
        } else if (n % 2 == 1) {
            System.out.println(integer.multiply(BigInteger.valueOf(n - 1)).multiply(BigInteger.valueOf(n - 2)));
        } else if (n % 3 == 0) {
            System.out.println(BigInteger.valueOf(n - 1).multiply(BigInteger.valueOf(n - 3)).multiply(BigInteger.valueOf(n - 2)));
        } else {
            System.out.println(integer.multiply(BigInteger.valueOf(n - 1)).multiply(BigInteger.valueOf(n - 3)));
        }
    }
}
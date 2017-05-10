/**
问题描述
　　给两组数，各n个。
　　请调整每组数的排列顺序，使得两组数据相同下标元素对应相乘，然后相加的和最小。要求程序输出这个最小值。
　　例如两组数分别为:1 3　　-5和-2 4 1

　　那么对应乘积取和的最小值应为：
　　(-5) * 4 + 3 * (-2) + 1 * 1 = -25
输入格式
　　第一个行一个数T表示数据组数。后面每组数据，先读入一个n，接下来两行每行n个数，每个数的绝对值小于等于1000。
　　n<=8,T<=1000
输出格式
　　一个数表示答案。
样例输入
2
3
1 3 -5
-2 4 1
5
1 2 3 4 5
1 0 1 0 1

样例输出


-25
6
 */
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            int num = scanner.nextInt();
            int[] a = new int[num];
            int[] b = new int[num];
            for (int j = 0; j < num; j++) {
                a[j] = scanner.nextInt();
            }
            for (int j = 0; j < num; j++) {
                b[j] = scanner.nextInt();
            }
            Arrays.sort(a);
            Arrays.sort(b);
            int sum = 0;
            for (int j = 0; j < num; j++) {
                sum += a[j] * b[num - j - 1];
            }
            System.out.println(sum);
        }
    }
}

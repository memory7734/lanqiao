/**
问题描述
　　有n个矩阵，大小分别为a0*a1, a1*a2, a2*a3, ..., a[n-1]*a[n]，现要将它们依次相乘，只能使用结合率，求最少需要多少次运算。
　　两个大小分别为p*q和q*r的矩阵相乘时的运算次数计为p*q*r。
输入格式
　　输入的第一行包含一个整数n，表示矩阵的个数。
　　第二行包含n+1个数，表示给定的矩阵。
输出格式
　　输出一个整数，表示最少的运算次数。
样例输入
3
1 10 5 20
样例输出
150
数据规模和约定
　　1<=n<=1000, 1<=ai<=10000。
 */
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n+1];
        for (int i = 0; i <= n; i++) {
            nums[i] = scanner.nextInt();
        }
        long[][] m = new long[n + 1][n + 1];
        for (int l = 2; l <= n; l++) {
            for (int i = 1; i <= n - l + 1; i++) {
                int j = i + l - 1;
                m[i][j] = Long.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    m[i][j] = Math.min(m[i][j], m[i][k] + m[k + 1][j] + nums[i - 1] * nums[k] * nums[j]);
                }
            }
        }
        System.out.println(m[1][n]);
    }
}
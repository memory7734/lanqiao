/**
问题描述
　　X国的一段古城墙的顶端可以看成 2*N个格子组成的矩形（如下图所示），现需要把这些格子刷上保护漆。


　　你可以从任意一个格子刷起，刷完一格，可以移动到和它相邻的格子（对角相邻也算数），但不能移动到较远的格子（因为油漆未干不能踩！）
　　比如：a d b c e f 就是合格的刷漆顺序。
　　c e f d a b 是另一种合适的方案。
　　当已知 N 时，求总的方案数。当N较大时，结果会迅速增大，请把结果对 1000000007 (十亿零七) 取模。
输入格式
　　输入数据为一个正整数（不大于1000）
输出格式
　　输出数据为一个正整数。
样例输入
2
样例输出
24
样例输入
3
样例输出
96
样例输入
22
样例输出
359635897
 */
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long[] q1 = new long[1001];
        long[] q2 = new long[1001];
        int mod = 1000000007;
        q1[1] = 1;
        q2[1] = 1;
        q1[2] = 2;
        q2[2] = 6;
        for (int i = 3; i <= n; i++) {
            q1[i] = q1[i - 1] * 2 % mod;
            q2[i] = (q2[i - 1] * 2 % mod + q1[i - 1] * 2 % mod + q2[i - 2] * 4 % mod) % mod;
        }
        long sum = (q2[n] * 4 % mod);
        for (int i = 2; i < n; i++) {
            sum += (4 * (q1[i] * q2[n - i] % mod + q1[n - i + 1] * q2[i - 1] % mod)) % mod;
        }
        System.out.println(sum % mod);
    }
}

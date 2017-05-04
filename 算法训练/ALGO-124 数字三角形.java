/**
问题描述
　　（图３.１－１）示出了一个数字三角形。 请编一个程序计算从顶至底的某处的一条路
　　径，使该路径所经过的数字的总和最大。
　　●每一步可沿左斜线向下或右斜线向下走；
　　●1＜三角形行数≤100；
　　●三角形中的数字为整数0，1，…99；


　　.
　　（图３.１－１）
输入格式
　　文件中首先读到的是三角形的行数。

　　接下来描述整个三角形
输出格式
　　最大总和（整数）
样例输入
5
7
3 8
8 1 0
2 7 4 4
4 5 2 6 5
样例输出
30 */
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] map = new int[n][n];
        int[][] dp = new int[n + 1][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                map[i][j] = scanner.nextInt();
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= i && j < n; j++) {
                dp[i][j] = Math.max(dp[i - 1][j] + map[i - 1][j], j > 0 ? dp[i - 1][j - 1] + map[i - 1][j] : 0);
            }
        }
        int max = dp[n][0];
        for (int i = 1; i < n; i++) {
            max = Math.max(dp[n][i], max);
        }
        System.out.println(max);
    }
}
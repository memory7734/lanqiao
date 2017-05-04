/**
问题描述
　　在一条直线上有n堆石子，每堆有一定的数量，每次可以将两堆相邻的石子合并，合并后放在两堆的中间位置，合并的费用为两堆石子的总数。求把所有石子合并成一堆的最小花费。
输入格式
　　输入第一行包含一个整数n，表示石子的堆数。
　　接下来一行，包含n个整数，按顺序给出每堆石子的大小 。
输出格式
　　输出一个整数，表示合并的最小花费。
样例输入
5
1 2 3 4 5
样例输出
33
数据规模和约定
　　1<=n<=1000, 每堆石子至少1颗，最多10000颗。
 */
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] stone = new int[n];
        int[][] dp = new int[n][n];
        stone[0] = scanner.nextInt();
        for (int i = 1; i < n; i++) {
            stone[i] = stone[i - 1] + scanner.nextInt();
        }
        for (int l = 1; l < n; l++) {
            for (int i = 0; i < n; i++) {
                int j = i + l;
                if (j >= n) break;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j]);
                }
                dp[i][j] += stone[j] - ((i > 0) ? stone[i - 1] : 0);
            }
        }
        System.out.println(dp[0][n - 1]);
    }
}
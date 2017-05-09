/**
问题描述
　　已知递推公式：

　　F(n, 1)=F(n-1, 2) + 2F(n-3, 1) + 5,

　　F(n, 2)=F(n-1, 1) + 3F(n-3, 1) + 2F(n-3, 2) + 3.

　　初始值为：F(1, 1)=2, F(1, 2)=3, F(2, 1)=1, F(2, 2)=4, F(3, 1)=6, F(3, 2)=5。
　　输入n，输出F(n, 1)和F(n, 2)，由于答案可能很大，你只需要输出答案除以99999999的余数。
输入格式
　　输入第一行包含一个整数n。
输出格式
　　输出两行，第一行为F(n, 1)除以99999999的余数，第二行为F(n, 2)除以99999999的余数。
样例输入
4
样例输出
14

21
数据规模和约定
　　1<=n<=10^18。
 */
import java.util.Scanner;

public class Main{
    public static int con = 999101;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long[][] dp = new long[4][2];
        long[][] dpold = new long[4][2];
        int point = 2;
        dp[0][0] = 2;
        dp[0][1] = 3;
        dp[1][0] = 1;
        dp[1][1] = 4;
        dp[2][0] = 6;
        dp[2][1] = 5;
        dpold[0][0] = 2;
        dpold[0][1] = 3;
        dpold[1][0] = 1;
        dpold[1][1] = 4;
        dpold[2][0] = 6;
        dpold[2][1] = 5;
        if (n <= 3) {
            System.out.println(dp[(int) (n - 1)][0]);
            System.out.println(dp[(int) (n - 1)][1]);
            return;
        }
        for (int i = 3; i < n; i++) {
            point++;
            point %= 4;
            int p1 = (point + 3) % 4;
            int p3 = (point + 1) % 4;
            dp[point][0] = (dp[p1][1] + 2 * dp[p3][0] + 5) % 99999999;
            dp[point][1] = (dp[p1][0] + 3 * dp[p3][0] + 2 * dp[p3][1] + 3) % 99999999;
            if (is(dp, dpold, point)) {
                System.out.println(i + 1);
            }
        }
        System.out.println(dp[point][0]);
        System.out.println(dp[point][1]);
    }

    public static boolean is(long[][] map, long[][] old, int point) {
        point = (point + 1) % 4;
        for (int i = 0; i < 4; i++) {
            if (map[point+i][0]!=old[i][0]) return false;
            if (map[point+i][1]!=old[i][1]) return false;
        }
        return true;
    }
}
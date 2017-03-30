/**
问题描述
有一棵 n 个节点的树，树上每个节点都有一个正整数权值。如果一个点被选择了，那么在树上和它相邻的点都不能被选择。求选出的点的权值和最大是多少？
输入格式
第一行包含一个整数 n 。
接下来的一行包含 n 个正整数，第 i 个正整数代表点 i 的权值。
接下来一共 n-1 行，每行描述树上的一条边。
输出格式
输出一个整数，代表选出的点的权值和的最大值。 
样例输入
5
1 2 3 4 5
1 2
1 3
2 4
2 5 
样例输出
12 
样例说明
选择3、4、5号点，权值和为 3+4+5 = 12 。 
数据规模与约定
对于20%的数据， n <= 20。
对于50%的数据， n <= 1000。
对于100%的数据， n <= 100000。
权值均为不超过1000的正整数。

这个题可能是因为Java性能的问题，参照的C++代码可以提交通过，但是Java代码总是会出现运行错误
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            dp[i][1] = scanner.nextInt();
        }
        List<Integer>[] map = new List[n];
        for (int i = 0; i < n; i++) {
            map[i] = new ArrayList<Integer>();
        }
        for (int i = 1; i < n; i++) {
            int p1 = scanner.nextInt() - 1;
            int p2 = scanner.nextInt() - 1;
            map[p1].add(p2);
            map[p2].add(p1);
        }
        treeDP(0, -1, map, dp);
        System.out.println(Math.max(dp[0][1], dp[0][0]));
    }

    public static void treeDP(int son, int father, List<Integer>[] map, int[][] dp) {
        for (int i = 0; i < map[son].size(); i++) {
            int t = map[son].get(i);
            if (t != father) {
                treeDP(t, son, map, dp);
                dp[son][1] += dp[t][0];
                dp[son][0] += Math.max(dp[t][0], dp[t][1]);
            }
        }
    }
}
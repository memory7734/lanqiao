/**
问题描述
抗日战争时期，冀中平原的地道战曾发挥重要作用。

地道的多个站点间有通道连接，形成了庞大的网络。但也有隐患，当敌人发现了某个站点后，其它站点间可能因此会失去联系。

我们来定义一个危险系数DF(x,y)：

对于两个站点x和y (x != y), 如果能找到一个站点z，当z被敌人破坏后，x和y不连通，那么我们称z为关于x,y的关键点。相应的，对于任意一对站点x和y，危险系数DF(x,y)就表示为这两点之间的关键点个数。

本题的任务是：已知网络结构，求两站点之间的危险系数。

输入格式
输入数据第一行包含2个整数n(2 <= n <= 1000), m(0 <= m <= 2000),分别代表站点数，通道数；

接下来m行，每行两个整数 u,v (1 <= u, v <= n; u != v)代表一条通道；

最后1行，两个数u,v，代表询问两点之间的危险系数DF(u, v)。

输出格式
一个整数，如果询问的两点不连通则输出-1.
样例输入
7 6
1 3
2 3
3 4
3 5
4 5
5 6
1 6
样例输出
2
 */
import java.util.Scanner;

public class Main {
    private static int[] vist;
    private static int[] way;
    private static int[] cnt;
    private static int ans = 0;
    private static int[][] map;
    private static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int m = sc.nextInt();
        vist = new int[n];
        way = new int[n];
        cnt = new int[n];
        map = new int[n][n];
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt() - 1;
            int v = sc.nextInt() - 1;
            map[u][v] = 1;
            map[v][u] = 1;
        }
        int s = sc.nextInt()-1;
        int t = sc.nextInt()-1;
        dfs(s, t, 0);
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (cnt[i] == ans) {
                sum++;
            }
        }
        System.out.println(sum - 2);
    }

    public static void dfs(int s, int t, int step) {
        vist[s] = 1;
        way[step] = s;
        if (s == t) {
            ans++;
            for (int i = 0; i <= step; i++) {
                cnt[way[i]]++;
            }
        }
        for (int i = 0; i < n; i++) {
            if (map[s][i] != 0 && vist[i] == 0) {
                vist[i] = 1;
                dfs(i, t, step + 1);
                vist[i] = 0;
            }
        }
    }
}

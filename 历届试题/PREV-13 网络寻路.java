/**
问题描述
X 国的一个网络使用若干条线路连接若干个节点。节点间的通信是双向的。某重要数据包，为了安全起见，必须恰好被转发两次到达目的地。该包可能在任意一个节点产生，我们需要知道该网络中一共有多少种不同的转发路径。

源地址和目标地址可以相同，但中间节点必须不同。

如下图所示的网络。



1 -> 2 -> 3 -> 1 是允许的

1 -> 2 -> 1 -> 2 或者 1 -> 2 -> 3 -> 2 都是非法的。

输入格式
输入数据的第一行为两个整数N M，分别表示节点个数和连接线路的条数(1<=N<=10000; 0<=M<=100000)。

接下去有M行，每行为两个整数 u 和 v，表示节点u 和 v 联通(1<=u,v<=N , u!=v)。

输入数据保证任意两点最多只有一条边连接，并且没有自己连自己的边，即不存在重边和自环。

输出格式
输出一个整数，表示满足要求的路径条数。
样例输入1
3 3
1 2
2 3
1 3
样例输出1
6
样例输入2
4 4
1 2
2 3
3 1
1 4
样例输出2
10

感觉是Java的性能问题，尝试过邻接表与直接暴力都是只能AC 60%，但是c++代码可以AC，暴力的内存使用率比邻接表小些，所以直接把暴力的代码放上来了
 */
import java.util.Scanner;
public class Main {
    private static boolean[] vist;
    private static boolean[][] map;
    private static int ans = 0;
    private static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int m = sc.nextInt();
        vist = new boolean[n];
        map = new boolean[n][n];
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt() - 1;
            int v = sc.nextInt() - 1;
            map[u][v] = true;
            map[v][u] = true;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j]) {
                    for (int k = 0; k < n; k++) {
                        if (k == i) {
                            continue;
                        } else if (map[j][k]) {
                            for (int l = 0; l < n; l++) {
                                if (l == j) {
                                    continue;
                                }
                                if (map[k][l]) {
                                    ans++;
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(ans);
    }
}

/**
问题描述
如下图所示，3 x 3 的格子中填写了一些整数。

+--*--+--+
|10* 1|52|
+--****--+
|20|30* 1|
*******--+
| 1| 2| 3|
+--+--+--+
我们沿着图中的星号线剪开，得到两个部分，每个部分的数字和都是60。

本题的要求就是请你编程判定：对给定的m x n 的格子中的整数，是否可以分割为两个部分，使得这两个区域的数字和相等。

如果存在多种解答，请输出包含左上角格子的那个区域包含的格子的最小数目。

如果无法分割，则输出 0。

输入格式
程序先读入两个整数 m n 用空格分割 (m,n<10)。

表示表格的宽度和高度。

接下来是n行，每行m个正整数，用空格分开。每个整数不大于10000。

输出格式
输出一个整数，表示在所有解中，包含左上角的分割区可能包含的最小的格子数目。
样例输入1
3 3
10 1 52
20 30 1
1 2 3
样例输出1
3
样例输入2
4 3
1 1 1 1
1 30 80 2
1 1 1 100
样例输出2
10
 */
import java.util.Scanner;
public class Main {
    static int sum;
    static int m, n;
    static int max = 0;
    static int[][] map;
    static boolean[][] visited;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        m = scanner.nextInt();
        n = scanner.nextInt();
        map = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = scanner.nextInt();
                sum += map[i][j];
            }
        }
        visited[0][0] = true;
        sum /= 2;
        search(0, 0, 0, 0);
        System.out.println(max);
    }

    public static void search(int i, int j, int value, int count) {
        value += map[i][j];
        if (value == sum) {
            if (++count > max) {
                max = count;
            }
            return;
        } else if (value > sum) {
            return;
        }
        if (j + 1 < m && !visited[i][j + 1]) {
            visited[i][j + 1] = true;
            search(i, j + 1, value, count + 1);
            visited[i][j + 1] = false;
        }
        if (j - 1 >= 0 && !visited[i][j - 1]) {
            visited[i][j - 1] = true;
            search(i, j - 1, value, count + 1);
            visited[i][j - 1] = false;
        }
        if (i + 1 < n && !visited[i + 1][j]) {
            visited[i + 1][j] = true;
            search(i + 1, j, value, count + 1);
            visited[i + 1][j] = false;
        }
        if (i - 1 >= 0 && !visited[i - 1][j]) {
            visited[i - 1][j] = true;
            search(i - 1, j, value, count + 1);
            visited[i - 1][j] = false;
        }
    }
}
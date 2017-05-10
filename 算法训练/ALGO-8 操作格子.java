/**
问题描述
有n个格子，从左到右放成一排，编号为1-n。

共有m次操作，有3种操作类型：

1.修改一个格子的权值，

2.求连续一段格子权值和，

3.求连续一段格子的最大值。

对于每个2、3操作输出你所求出的结果。

输入格式
第一行2个整数n，m。

接下来一行n个整数表示n个格子的初始权值。

接下来m行，每行3个整数p,x,y，p表示操作类型，p=1时表示修改格子x的权值为y，p=2时表示求区间[x,y]内格子权值和，p=3时表示求区间[x,y]内格子最大的权值。

输出格式
有若干行，行数等于p=2或3的操作总数。

每行1个整数，对应了每个p=2或3操作的结果。

样例输入
4 3
1 2 3 4
2 1 3
1 4 3
3 1 4
样例输出
6
3
数据规模与约定
对于20%的数据n <= 100，m <= 200。

对于50%的数据n <= 5000，m <= 5000。

对于100%的数据1 <= n <= 100000，m <= 100000，0 <= 格子权值 <= 10000。
 */
import java.util.Scanner;

public class Main {
    static class Node {
        int l;
        int r;
        int max;
        int sum;
    }

    static Node[] a;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        a = new Node[10 * n];
        int m = scanner.nextInt();
        init(1, n, 1);
        for (int i = 1; i <= n; i++) {
            insert(1, i, scanner.nextInt());
        }
        for (int i = 0; i < m; i++) {
            int p = scanner.nextInt();
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            if (p == 1) {
                insert(1, x, y);
            } else if (p == 2) {
                System.out.println(findSum(1, x, y));
            } else if (p == 3) {
                System.out.println(findMax(1, x, y));
            }
        }
    }

    public static void insert(int i, int j, int value) {
        if (a[i].l == a[i].r) {
            a[i].max = value;
            a[i].sum = value;
            return;
        }
        int mid = (a[i].l + a[i].r) / 2;
        if (j <= mid) {
            insert(2 * i, j, value);
        } else {
            insert(2 * i + 1, j, value);
        }
        a[i].max = Math.max(a[2 * i].max, a[2 * i + 1].max);
        a[i].sum = a[2 * i].sum + a[2 * i + 1].sum;
    }

    public static int findSum(int i, int x, int y) {
        if (x == a[i].l && y == a[i].r) {
            return a[i].sum;
        }
        int mid = (a[i].l + a[i].r) / 2;
        if (y <= mid) return findSum(2 * i, x, y);
        else if (x > mid) return findSum(2 * i + 1, x, y);
        else return findSum(2 * i, x, mid) + findSum(2 * i + 1, mid + 1, y);
    }

    public static int findMax(int i, int x, int y) {
        if (x == a[i].l && y == a[i].r) {
            return a[i].max;
        }
        int mid = (a[i].l + a[i].r) / 2;
        if (y <= mid) return findMax(2 * i, x, y);
        else if (x > mid) return findMax(2 * i + 1, x, y);
        else return Math.max(findMax(2 * i, x, mid), findMax(2 * i + 1, mid + 1, y));
    }

    public static void init(int left, int right, int i) {
        a[i] = new Node();
        a[i].l = left;
        a[i].r = right;
        a[i].max = 0;
        a[i].sum = 0;
        if (left != right) {
            int mid = (left + right) / 2;
            init(left, mid, 2 * i);
            init(mid + 1, right, 2 * i + 1);
        }
    }

}

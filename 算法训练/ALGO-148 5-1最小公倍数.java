/**
问题描述
　　编写一函数lcm，求两个正整数的最小公倍数。
样例输入
一个满足题目要求的输入范例。
例：

3 5
样例输出
与上面的样例输入对应的输出。
例：
15
数据规模和约定
　　输入数据中每一个数的范围。
　　例：两个数都小于65536。
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int ma = Math.max(n, m);
        int mi = Math.min(n, m);
        for (int i = 1; i <= mi; i++) {
            if (((long) ma * (long) i) % mi == 0) {
                System.out.println((long) ma * (long) i);
                break;
            }
        }

    }

}


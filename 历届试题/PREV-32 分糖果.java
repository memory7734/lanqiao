/**
问题描述
　　有n个小朋友围坐成一圈。老师给每个小朋友随机发偶数个糖果，然后进行下面的游戏：

　　每个小朋友都把自己的糖果分一半给左手边的孩子。

　　一轮分糖后，拥有奇数颗糖的孩子由老师补给1个糖果，从而变成偶数。

　　反复进行这个游戏，直到所有小朋友的糖果数都相同为止。

　　你的任务是预测在已知的初始糖果情形下，老师一共需要补发多少个糖果。
输入格式
　　程序首先读入一个整数N(2<N<100)，表示小朋友的人数。
　　接着是一行用空格分开的N个偶数（每个偶数不大于1000，不小于2）
输出格式
　　要求程序输出一个整数，表示老师需要补发的糖果数。
样例输入
3
2 2 4
样例输出
4
 */
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] pal = new int[n];
        for (int i = 0; i < n; i++) {
            pal[i] = sc.nextInt();
        }
        int count = 0;
        while (!equals(pal)) {
            int left = pal[0] / 2;
            pal[0] -= left;
            for (int i = 1; i < n; i++) {
                pal[i - 1] += pal[i] / 2;
                pal[i] /= 2;
                if (pal[i - 1] % 2 == 1) {
                    pal[i - 1]++;
                    count++;
                }
            }
            pal[n - 1] += left;
            if (pal[n - 1] % 2 == 1) {
                pal[n - 1]++;
                count++;
            }
        }
        System.out.println(count);
    }

    public static boolean equals(int[] pal) {
        for (int i = 0; i < pal.length; i++) {
            if (pal[i] != pal[0]) {
                return false;
            }
        }
        return true;
    }
}

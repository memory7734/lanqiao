/**
问题描述
已知Ckn=1 k=0或k=n Ckn= Ckn-1 +Ck-1 n-1 0<k<n,使用递归方法求Ckn
样例输入
一个满足题目要求的输入范例。
3 10
样例输出
120
与上面的样例输入对应的输出。

数据规模和约定
　　输入数据中每一个数的范围。
　　例：结果在int表示时不会溢出
 */
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();


        System.out.println(digui(m, n));
    }
    public static int digui(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        }
        return digui(n - 1, k) + digui(n - 1, k - 1);
    }
}


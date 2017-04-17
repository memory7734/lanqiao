/**
问题描述
　　给出两个整数集合A、B，求出他们的交集、并集以及B在A中的余集。
输入格式
　　第一行为一个整数n，表示集合A中的元素个数。
　　第二行有n个互不相同的用空格隔开的整数，表示集合A中的元素。
　　第三行为一个整数m，表示集合B中的元素个数。
　　第四行有m个互不相同的用空格隔开的整数，表示集合B中的元素。
　　集合中的所有元素均为int范围内的整数，n、m<=1000。
输出格式
　　第一行按从小到大的顺序输出A、B交集中的所有元素。
　　第二行按从小到大的顺序输出A、B并集中的所有元素。
　　第三行按从小到大的顺序输出B在A中的余集中的所有元素。
样例输入
5
1 2 3 4 5
5
2 4 6 8 10
样例输出
2 4
1 2 3 4 5 6 8 10
1 3 5
样例输入
4
1 2 3 4
3
5 6 7
样例输出
1 2 3 4 5 6 7
1 2 3 4
 */
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Set<Integer> A = new TreeSet<Integer>();
        Set<Integer> B = new TreeSet<Integer>();
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            A.add(scanner.nextInt());
        }
        n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            B.add(scanner.nextInt());
        }
        Set<Integer> u = new TreeSet<Integer>();
        u.addAll(A);
        u.addAll(B);
        for (Integer i : A) {
            if (B.contains(i)) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
        for (Integer i : u) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (Integer i : A) {
            if (!B.contains(i)) {
                System.out.print(i + " ");
            }
        }
    }
}

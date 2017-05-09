/**
问题描述
　　从万能词典来的聪明的海狸已经使我们惊讶了一次。他开发了一种新的计算器，他将此命名为"Beaver's Calculator 1.0"。它非常特别，并且被计划使用在各种各样的科学问题中。
　　为了测试它，聪明的海狸邀请了n位科学家，编号从1到n。第i位科学家给这个计算器带来了 ki个计算题。第i个科学家带来的问题编号1到n，并且它们必须按照编号一个一个计算，因为对于每个问题的计算都必须依赖前一个问题的计算结果。
　　每个教授的每个问题都用一个数 ai, j  来描述，i（1≤i≤n）是科学家的编号，j（1≤j≤ ki ）是问题的编号， ai, j  表示解决这个问题所需资源单位的数量。
　　这个计算器非常不凡。它一个接一个的解决问题。在一个问题解决后，并且在下一个问题被计算前，计算器分配或解放资源。
　　计算器中最昂贵的操作是解放资源，解放远远慢于分配。所以对计算器而言，每一个接下来的问题所需的资源不少于前一个，是非常重要的。
　　给你关于这些科学家所给问题的相关信息。你需要给这些问题安排一个顺序，使得“坏对”尽可能少。
　　所谓“坏对”，就是相邻两个问题中，后一个问题需求的资源比前一个问题少。别忘了，对于同一个科学家给出的问题，计算它们的相对顺序必须是固定的。
输入格式
　　第一行包含一个整数n，表示科学家的人数。接下来n行每行有5个整数，ki, ai, 1, xi, yi, mi (0 ≤ ai, 1 < mi ≤ 109, 1 ≤ xi, yi ≤ 109) ，分别表示第i个科学家的问题个数，第1个问题所需资源单位数，以及3个用来计算 ai, j 的参量。ai, j = (ai, j - 1 * xi + yi)mod mi。
输出格式
　　第一行输出一个整数，表示最优顺序下最少的“坏对”个数。
　　如果问题的总个数不超过200000,接下来输出  行，表示解决问题的最优顺序。每一行两个用空格隔开的整数，表示这个问题所需的资源单位数和提供这个问题的科学家的编号。
样例输入
2
2 1 1 1 10
2 3 1 1 10
样例输出
0
1 1
2 1
3 2
4 2
数据规模和约定
　　20%的数据 n = 2, 1 ≤ ki ≤ 2000；
　　另外30%的数据 n = 2, 1 ≤ ki ≤ 200000；
　　剩下50%的数据 1 ≤ n ≤ 5000, 1 ≤ ki ≤ 5000。
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        class Problem {
            long science;
            long pos;
            long resource;
        }
        List<Problem> list = new ArrayList<Problem>();
        int n = scanner.nextInt();
        int ans = -1;
        for (int i = 0; i < n; i++) {
            long k = scanner.nextInt();
            long a = scanner.nextInt();
            long x = scanner.nextInt();
            long y = scanner.nextInt();
            long m = scanner.nextInt();
            long b;
            int t = 0;
            for (int j = 0; j < k; j++) {
                Problem problem = new Problem();
                problem.science = i;
                problem.pos = t;
                problem.resource = a;
                b = (a * x + y) % m;
                if (b < a && j != k - 1) {
                    t++;
                }
                a = b;
                list.add(problem);
            }
            ans = Math.max(ans, t);
        }
        System.out.println(ans);
        if (list.size() <= 200000) {
            Collections.sort(list, new Comparator<Problem>() {
                @Override
                public int compare(Problem o1, Problem o2) {
                    if (o1.pos == o2.pos) {
                        return (int) (o1.pos - o2.pos);
                    }
                    if (o1.resource != o2.resource) {
                        return (int) (o1.resource - o2.resource);
                    }
                    return (int) (o1.science - o2.science);
                }
            });
            for (int i = 0; i < list.size(); i++) {
                Problem problem = list.get(i);
                System.out.println(problem.resource + " " + (int) (problem.science + 1));
            }
        }
    }
}
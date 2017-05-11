/**
问题描述
Farmer John变得非常懒，他不想再继续维护供奶牛之间供通行的道路。道路被用来连接N个牧场，牧场被连续地编号为1到N。每一个牧场都是一个奶牛的家。FJ计划除去P条道路中尽可能多的道路，但是还要保持牧场之间 的连通性。你首先要决定那些道路是需要保留的N-1条道路。第j条双向道路连接了牧场Sj和Ej(1 <= Sj <= N; 1 <= Ej <= N; Sj != Ej)，而且走完它需要Lj的时间。没有两个牧场是被一条以上的道路所连接。奶牛们非常伤心，因为她们的交通系统被削减了。你需要到每一个奶牛的住处去安慰她们。每次你到达第i个牧场的时候(即使你已经到过)，你必须花去Ci的时间和奶牛交谈。你每个晚上都会在同一个牧场(这是供你选择的)过夜，直到奶牛们都从悲伤中缓过神来。在早上 起来和晚上回去睡觉的时候，你都需要和在你睡觉的牧场的奶牛交谈一次。这样你才能完成你的 交谈任务。假设Farmer John采纳了你的建议，请计算出使所有奶牛都被安慰的最少时间。

输入格式
第1行包含两个整数N和P。

接下来N行，每行包含一个整数Ci。

接下来P行，每行包含三个整数Sj, Ej和Lj。

输出格式
输出一个整数, 所需要的总时间(包含和在你所在的牧场的奶牛的两次谈话时间)。
样例输入
5 7
10
10
20
6
30
1 2 5
2 3 5
2 4 12
3 4 17
2 5 15
3 5 6
样例输出
176
数据规模与约定
5 <= N <= 10000，N-1 <= P <= 100000，0 <= Lj <= 1000，1 <= Ci <= 1,000。
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static class Edge implements Comparable<Edge> {
        int src, dest, weight;
        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
    static class Subset {
        int parent, rank;

        public Subset(int parent) {
            this.parent = parent;
            rank = 0;
        }
    }
    public static int find(Subset sub[], int i) {
        if (sub[i].parent != i)
            sub[i].parent = find(sub, sub[i].parent);
        return sub[i].parent;
    }

    public static void union(Subset sub[], int x, int y) {
        int xroot = sub[x].parent;
        int yroot = sub[y].parent;
        if (sub[xroot].rank < sub[yroot].rank) {
            sub[xroot].parent = yroot;
        } else if (sub[xroot].rank > sub[yroot].rank) {
            sub[yroot].parent = xroot;
        } else {
            sub[yroot].parent = xroot;
            sub[xroot].rank++;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        int v = Integer.parseInt(tmp[0]);
        int e = Integer.parseInt(tmp[1]);
        int[] c = new int[v];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < v; i++) {
            c[i] = Integer.parseInt(br.readLine());
            min = Math.min(min, c[i]);
        }
        Edge[] edges = new Edge[e];
        for (int i = 0; i < e; i++) {
            tmp = br.readLine().split(" ");
            edges[i] = new Edge();
            edges[i].src = Integer.parseInt(tmp[0]) - 1;
            edges[i].dest = Integer.parseInt(tmp[1]) - 1;
            edges[i].weight = Integer.parseInt(tmp[2]) * 2 + c[edges[i].src] + c[edges[i].dest];
        }
        br.close();
        Arrays.sort(edges);
        Subset[] subset = new Subset[v];
        for (int i = 0; i < v; i++) {
            subset[i] = new Subset(i);
        }
        int res = 0;
        for (int i = 0; i < edges.length && v > 1; i++) {
            int x = find(subset, edges[i].src);
            int y = find(subset, edges[i].dest);
            if (x != y) {
                res += edges[i].weight;
                union(subset, x, y);
                v--;
            }
        }
        System.out.println(res + min);
    }

}

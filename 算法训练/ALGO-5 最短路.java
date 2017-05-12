/**
问题描述
给定一个n个顶点，m条边的有向图（其中某些边权可能为负，但保证没有负环）。请你计算从1号点到其他点的最短路（顶点从1到n编号）。

输入格式
第一行两个整数n, m。

接下来的m行，每行有三个整数u, v, l，表示u到v有一条长度为l的边。

输出格式
共n-1行，第i行表示1号点到i+1号点的最短路。
样例输入
3 3
1 2 -1
2 3 -1
3 1 2
样例输出
-1
-2
数据规模与约定
对于10%的数据，n = 2，m = 2。

对于30%的数据，n <= 5，m <= 10。

对于100%的数据，1 <= n <= 20000，1 <= m <= 200000，-10000 <= l <= 10000，保证从任意顶点都能到达其他所有顶点。
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static class Edge implements Comparable<Edge> {
        int src, dest, weight;
        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strings = br.readLine().split(" ");
        int n = Integer.parseInt(strings[0]);
        int m = Integer.parseInt(strings[1]);
        Edge[] edges = new Edge[m];
        int[] dist = new int[n];
        int[] pre = new int[n];
        for (int i = 1; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < m; i++) {
            edges[i] = new Edge();
            strings = br.readLine().split(" ");
            edges[i].src = Integer.parseInt(strings[0]) - 1;
            edges[i].dest = Integer.parseInt(strings[1]) - 1;
            edges[i].weight = Integer.parseInt(strings[2]);
        }
        Edge edge;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < edges.length; j++) {
                edge = edges[j];
                if (dist[edge.src] != Integer.MAX_VALUE && dist[edge.src] + edge.weight < dist[edge.dest]) {
                    dist[edge.dest] = dist[edge.src] + edge.weight;
                    pre[edge.dest] = edge.src;
                }
            }
        }
        for (int i = 1; i < n; i++) {
            System.out.println(dist[i]);
        }
    }
}

/**
问题描述
　　﻿队列操作题。根据输入的操作命令，操作队列（1）入队、（2）出队并输出、（3）计算队中元素个数并输出。
输入格式
　　第一行一个数字N。
　　下面N行，每行第一个数字为操作命令（1）入队、（2）出队并输出、（3）计算队中元素个数并输出。
输出格式
　　若干行每行显示一个2或3命令的输出结果。注意：2.出队命令可能会出现空队出队（下溢），请输出“no”，并退出。
样例输入
7
1 19
1 56
2
3
2
3
2
样例输出
19
1
56
0
no
数据规模和约定
　　1<=N<=50
*/
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Queue<Integer> queue = new LinkedList<Integer>();
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            int now = scanner.nextInt();
            if (now == 1) {
                queue.offer(scanner.nextInt());
            } else if (now == 2) {
                if (queue.size() > 0) {
                    System.out.println(queue.poll());
                } else {
                    System.out.println("no");
                    return;
                }
            } else if (now == 3) {
                System.out.println(queue.size());
            }
        }
    }
}
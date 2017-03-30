/**
问题描述
如果一个自然数N的K进制表示中任意的相邻的两位都不是相邻的数字，那么我们就说这个数是K好数。求L位K进制数中K好数的数目。例如K = 4，L = 2的时候，所有K好数为11、13、20、22、30、31、33 共7个。由于这个数目很大，请你输出它对1000000007取模后的值。
输入格式
输入包含两个正整数，K和L。
输出格式
输出一个整数，表示答案对1000000007取模后的值。 
样例输入
4 2 
样例输出
7 
数据规模与约定
对于30%的数据，KL <= 106；
对于50%的数据，K <= 16， L <= 10；
对于100%的数据，1 <= K,L <= 100。
 */
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        int l = scanner.nextInt();
        if (l <= 1) {
            System.out.println(0);
        } else {
            //用于计算在i+1个长度的时候末尾数字为k的个数
            int[][] map = new int[l][k];
            int m = 1000000007;
            for (int i = 1; i < map[0].length; i++) {
                map[0][i] = 1;
            }
            for (int i = 1; i < map.length; i++) {
                for (int j = 0; j < k; j++) {
                    int currect = map[i - 1][j];
                    for (int a = 0; a < k; a++) {
                        if (Math.abs(a - j) != 1) {
                            map[i][j] = (map[i][j] + map[i - 1][a]) % m;
                        }
                    }
                }
            }
            long sum = 0;
            for (int i = 0; i < map[0].length; i++) {
                sum += (map[map.length - 1][i]) % m;
            }
            System.out.println(sum % m);
        }

    }
}
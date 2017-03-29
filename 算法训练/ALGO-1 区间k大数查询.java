/**
问题描述
给定一个序列，每次询问序列中第l个数到第r个数中第K大的数是哪个。
输入格式
第一行包含一个数n，表示序列长度。
第二行包含n个正整数，表示给定的序列。
第三个包含一个正整数m，表示询问个数。
接下来m行，每行三个数l,r,K，表示询问序列从左往右第l个数到第r个数中，从大往小第K大的数是哪个。序列元素从1开始标号。
输出格式
总共输出m行，每行一个数，表示询问的答案。 
样例输入
5
1 2 3 4 5
2
1 5 2
2 3 2 
样例输出
4
2 
数据规模与约定
对于30%的数据，n,m<=100；
对于100%的数据，n,m<=1000；
保证k<=(r-l+1)，序列中的数<=106。
 */
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        int size = scanner.nextInt();
        int[] result = new int[size];
        while (size-- > 0) {
            int l = scanner.nextInt();
            int r = scanner.nextInt();
            int k = scanner.nextInt();
            int[] numsCopy = new int[r + 1 - l];
            for (int i = 0; i < numsCopy.length; i++) {
                numsCopy[i] = nums[l - 1 + i];
            }
            Arrays.sort(numsCopy);
            result[result.length - size - 1] = numsCopy[numsCopy.length - k];
        }
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }
}
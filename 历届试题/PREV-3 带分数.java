/**
问题描述
100 可以表示为带分数的形式：100 = 3 + 69258 / 714。
还可以表示为：100 = 82 + 3546 / 197。
注意特征：带分数中，数字1~9分别出现且只出现一次（不包含0）。
类似这样的带分数，100 有 11 种表示法。
输入格式
从标准输入读入一个正整数N (N<1000*1000)
输出格式
程序输出该数字用数码1~9不重复不遗漏地组成带分数表示的全部种数。
注意：不要求输出每个表示，只统计有多少表示法！
样例输入1
100 
样例输出1
11 
样例输入2
105 
样例输出2
6 
 */
import java.util.Scanner;
public class Main {
    static int num, result;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        num = sc.nextInt();
        result = 0;
        sc.close();
        int[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        allsort(data, 0);
        System.out.println(result);
    }

    public static void allsort(int[] data, int pos) {
        if (pos == data.length) {
            for (int i = 1; i < data.length; i++) {
                for (int j = i + 1; j < data.length; j++) {
                    int num1 = split(data, 0, i);
                    int num2 = split(data, i, j);
                    int num3 = split(data, j, 9);
                    if (num2 % num3 != 0) {
                        continue;
                    }
                    if (num1 + num2 / num3 == num) {
                        result++;
                    }
                }
            }
            return;
        }
        for (int i = pos; i < data.length; i++) {
            int temp = data[i];
            data[i] = data[pos];
            data[pos] = temp;
            allsort(data, pos + 1);
            temp = data[i];
            data[i] = data[pos];
            data[pos] = temp;
        }
    }

    public static int split(int[] data, int i, int j) {
        int num = 0;
        for (int k = i; k < j; k++) {
            num = data[k] + num * 10;
        }
        return num;
    }


}


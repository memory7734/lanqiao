/**
问题描述
　　输入两个矩阵，分别是m*s，s*n大小。输出两个矩阵相乘的结果。
输入格式
　　第一行，空格隔开的三个正整数m,s,n（均不超过200）。
　　接下来m行，每行s个空格隔开的整数，表示矩阵A（i，j）。
　　接下来s行，每行n个空格隔开的整数，表示矩阵B（i，j）。
输出格式
　　m行，每行n个空格隔开的整数，输出相乘後的矩阵C（i，j）的值。
样例输入
2 3 2
1 0 -1
1 1 -3
0 3
1 2
3 1
样例输出
-3 2
-8 2

提示
矩阵C应该是m行n列，其中C(i,j)等于矩阵A第i行行向量与矩阵B第j列列向量的内积。
例如样例中C(1,1)=(1,0,-1)*(0,1,3) = 1 * 0 +0*1+(-1)*3=-3
 */
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int s = scanner.nextInt();
        int n = scanner.nextInt();
        int[][] A = new int[m][s];
        int[][] B = new int[s][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < s; j++) {
                A[i][j] = scanner.nextInt();
            }
        }
        for (int i = 0; i < s; i++) {
            for (int j = 0; j < n; j++) {
                B[i][j] = scanner.nextInt();
            }
        }
        int[][] C = matrixMultiple(A, B);
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < C[0].length; j++) {
                System.out.print(C[i][j] + " ");
            }
            System.out.println();
        }

    }

    public static int[][] matrixMultiple(int[][] A, int[][] B) {
        int[][] C = new int[A.length][B[0].length];
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < C[0].length; j++) {
                for (int k = 0; k < A[0].length; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }
}

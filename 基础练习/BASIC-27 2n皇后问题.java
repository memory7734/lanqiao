/**
问题描述
　　给定一个n*n的棋盘，棋盘中有一些位置不能放皇后。现在要向棋盘中放入n个黑皇后和n个白皇后，使任意的两个黑皇后都不在同一行、同一列或同一条对角线上，任意的两个白皇后都不在同一行、同一列或同一条对角线上。问总共有多少种放法？n小于等于8。
输入格式
　　输入的第一行为一个整数n，表示棋盘的大小。
　　接下来n行，每行n个0或1的整数，如果一个整数为1，表示对应的位置可以放皇后，如果一个整数为0，表示对应的位置不可以放皇后。
输出格式
　　输出一个整数，表示总共有多少种放法。
样例输入
4
1 1 1 1
1 1 1 1
1 1 1 1
1 1 1 1
样例输出
2
样例输入
4
1 0 1 1
1 1 1 1
1 1 1 1
1 1 1 1
样例输出
0
 */
import java.util.Scanner;
public class Main {
    public static int count=0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] board = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = sc.nextInt();
            }
        }
        WhiteQueen(board, new int[n], 0);
        System.out.println(count);
    }

    public static void BlackQueen(int[][] board, int[] queen, int row) {
        if (row == board.length) {
            count++;
            return;
        }
        for (int i = 0; i < queen.length; i++) {
            if (board[row][i] == 1 && conflict(queen, row, i)) {
                queen[row] = i;
                BlackQueen(board, queen, row + 1);
            }
        }
    }

    public static void WhiteQueen(int[][] board, int[] queen, int row) {
        if (row == board.length) {
            int[][] boardCopy = new int[board.length][board.length];
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    boardCopy[i][j] = board[i][j];
                }
            }
            for (int i = 0; i < board.length; i++) {
                boardCopy[i][queen[i]] = 0;
            }
            BlackQueen(boardCopy, new int[queen.length], 0);
            return;
        }
        for (int i = 0; i < queen.length; i++) {
            if (board[row][i] == 1 && conflict(queen, row, i)) {
                queen[row] = i;
                WhiteQueen(board, queen, row + 1);
            }
        }
    }

    //判断第row行第j列对于queen数组是否可以摆放皇后
    public static boolean conflict(int[] queen, int row, int j) {
        for (int i = 0; i < row; i++) {
            if (queen[i] == j || Math.abs(row - i) == Math.abs(queen[i] - j)) {
                return false;
            }
        }
        return true;
    }
}
/**
问题描述
　　给定一个N阶矩阵A，输出A的M次幂（M是非负整数）
　　例如：
　　A =
　　1 2
　　3 4
　　A的2次幂
　　7 10
　　15 22
输入格式
　　第一行是一个正整数N、M（1<=N<=30, 0<=M<=5），表示矩阵A的阶数和要求的幂数
　　接下来N行，每行N个绝对值不超过10的非负整数，描述矩阵A的值
输出格式
　　输出共N行，每行N个整数，表示A的M次幂所对应的矩阵。相邻的数之间用一个空格隔开
样例输入
2 2
1 2
3 4
样例输出
7 10
15 22
 */
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        int M=sc.nextInt();
        int m=M;
        int[][] nums=new int[N][N];
        int[][] result=new int[N][N];
        for(int i=0;i<N;i++){
        	for(int j=0;j<N;j++){
        		nums[i][j]=sc.nextInt();
        		result[i][j]=nums[i][j];
        	}
        }
        while(M-->1){
            int[][] temp=new int[N][N];
        	for(int i=0;i<N;i++){
        		for(int j=0;j<N;j++){
        			int sum=0;
        			for(int k=0;k<N;k++){
        				sum+=result[i][k]*nums[k][j];
        			}
        			temp[i][j]=sum;
        		}
        	}
        	result=temp;
        } 
        if(m==0){
        	for(int i=0;i<N;i++){
            	for(int j=0;j<N;j++){
            		result[i][j]=i==j?1:0;
            	}
            }
        }
        for(int i=0;i<N;i++){
        	for(int j=0;j<N;j++){
        		System.out.print(result[i][j]+" ");
        	}
        	System.out.println("");
        }
        
    }
}
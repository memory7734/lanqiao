/**
问题描述
　　求出区间[a,b]中所有整数的质因数分解。
输入格式
　　输入两个整数a，b。
输出格式
　　每行输出一个数的分解，形如k=a1*a2*a3...(a1<=a2<=a3...，k也是从小到大的)(具体可看样例)
样例输入
3 10
样例输出
3=3
4=2*2
5=5
6=2*3
7=7
8=2*2*2
9=3*3
10=2*5
提示
　　先筛出所有素数，然后再分解。
数据规模和约定
　　2<=a<=b<=10000
 */
 import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int start=sc.nextInt();
        int end=sc.nextInt();
        for(int i=start;i<=end;i++){
        	if(isPrime(i)==0){
        		System.out.println(i+"="+i);
        		continue;
        	}
        	int tmp=i;
        	System.out.print(i+"=");
        	while(isPrime(tmp)!=0){
        		for(int j=2;j<tmp;j++){
        			if(tmp%j==0){
        				System.out.print(j+"*");
        				tmp/=j;
        				break;
        			}
        		}
        	}
        	System.out.println(tmp);
        }
    }
    
    public static int isPrime(int n){
    	if(n<=1) return 0;
    	for(int i=2;i<n;i++){
    		if(n%i==0)return 1;
    	}
		return 0;
    }
}
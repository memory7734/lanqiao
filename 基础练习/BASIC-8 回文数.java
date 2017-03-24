/**
问题描述
　　1221是一个非常特殊的数，它从左边读和从右边读是一样的，编程求所有这样的四位十进制数。
输出格式
　　按从小到大的顺序输出满足条件的四位十进制数。
 */
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
		for(int i=1000;i<10000;i++){
			int j=0;
			int tmp=i;
			while(tmp>0){
				j*=10;
				j+=tmp%10;
				tmp/=10;
			}
			
			if(i==j){
				System.out.println(i);
			}
		}
		
    }
}
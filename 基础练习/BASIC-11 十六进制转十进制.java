/**
问题描述
　　从键盘输入一个不超过8位的正的十六进制数字符串，将它转换为正的十进制数后输出。
　　注：十六进制数中的10~15分别用大写的英文字母A、B、C、D、E、F表示。
样例输入
FFFF
样例输出
65535
 */
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		String string=scanner.next();
		scanner.close();
		long result=0;
		for (int i = 0; i < string.length(); i++) {
			result*=16;
			char c=string.charAt(i);
			if (c>='0'&&c<='9') {
				result+=(c-'0');
			}else {
				result+=(c-'A'+10);
			}
		}
		System.out.println(result);		
	}
}

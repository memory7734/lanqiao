/**
问题描述
　　栋栋正在和同学们玩一个数字游戏。

　　游戏的规则是这样的：栋栋和同学们一共n个人围坐在一圈。栋栋首先说出数字1。接下来，坐在栋栋左手边的同学要说下一个数字2。再下面的一个同学要从上一个同学说的数字往下数两个数说出来，也就是说4。下一个同学要往下数三个数，说7。依次类推。

　　为了使数字不至于太大，栋栋和同学们约定，当在心中数到 k-1 时，下一个数字从0开始数。例如，当k=13时，栋栋和同学们报出的前几个数依次为：
　　1, 2, 4, 7, 11, 3, 9, 3, 11, 7。

　　游戏进行了一会儿，栋栋想知道，到目前为止，他所有说出的数字的总和是多少。
输入格式
　　输入的第一行包含三个整数 n,k,T，其中 n 和 k 的意义如上面所述，T 表示到目前为止栋栋一共说出的数字个数。
输出格式
　　输出一行，包含一个整数，表示栋栋说出所有数的和。
样例输入
3 13 3
样例输出
17
样例说明
　　栋栋说出的数依次为1, 7, 9，和为17。
数据规模和约定
　　1 < n,k,T < 1,000,000；
*/
/**
AC代码如下，是不断通过前面的值，推算下一个值递推进行的
 */
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        long n=in.nextLong();
        long k=in.nextLong();
        long t=in.nextLong();
        long num=1;            
        long sum=1;
        long d=(n+1)*n/2;      
        for(int i=1;i<t;i++){
            num += d;
            d += n * n;
            if (num > k - 1) {
                num %= k;
            }
            sum += num;
        }
        System.out.println(sum);
    }
}
/**
下面这份代码通过了前面五个测试，最后一个没有通过，我是直接算出第i个值，然后进行求和的，并不能想出来哪里错了，而且只有最后一个测试用例是不通过的……
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int t = sc.nextInt();
        long sum = 0;
        long j = 1;
        for (int i = 0; i < t; i++) {
            sum += ((j * (j - 1) / 2 + 1) % k);
            j += n;
        }
        System.out.println(sum);
        
    }
} */
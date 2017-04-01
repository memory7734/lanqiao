/**
问题描述
　　已知一个有理数类Zrf_Ratio，实现如下的操作符重载形式：
　　friend std::ostream& operator<<(std::ostream&, const zrf_Ratio&);//输出最简分数
　　friend std::istream& operator>>(std::istream&, zrf_Ratio&);
　　friend bool operator==(const zrf_Ratio&, const zrf_Ratio&);
　　friend bool operator<(const zrf_Ratio&, const zrf_Ratio&);
测试
　　测试时主程序会输入四个整数a, b, c, d，表示两个分数a/b和c/d。要求输出最简分数以及两个分数相等和大小的比较结果。
样例输入
1 7 26 25
样例输出
zrf is:1/7; ssh is:26/25
(zrf==ssh) is:0; (zrf<ssh) is:1
一开始用Java写了，发现只需要写c++的几个重载符函数，所以就必须用c++
 */
ostream& operator<<(ostream&os, const zrf_Ratio& zrf_Ratio) { 
   os << zrf_Ratio.num << "/" <<zrf_Ratio.den; 
   return os; 
} 
 
istream& operator>>(istream&in, zrf_Ratio& zrf_Ratio) { 
   in >> zrf_Ratio.num >> zrf_Ratio.den; 
   return in; 
} 
 
bool operator==(const zrf_Ratio&zrf_Ratio1, const zrf_Ratio& zrf_Ratio2) { 
   if (zrf_Ratio1.num == zrf_Ratio2.num && 
       zrf_Ratio1.den == zrf_Ratio2.den) { 
       return 1; 
   } 
   return 0; 
} 
 
bool operator<(const zrf_Ratio&zrf_Ratio1, const zrf_Ratio& zrf_Ratio2) { 
   if (zrf_Ratio1.num * 1.0 / zrf_Ratio1.den < 
       zrf_Ratio2.num * 1.0 / zrf_Ratio2.den) { 
       return 1; 
   } 
   return 0; 
}  
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        int d = scanner.nextInt();
        int ra = zhanzhuan(a, b);
        int rb = zhanzhuan(c, d);
        a = a / ra;
        b = b / ra;
        c = c / rb;
        d = d / rb;
        System.out.printf("zrf is:%d/%d; ssh is:%d/%d%n", a, b, c, d);
        System.out.printf("(zrf==ssh) is:%d; (zrf<ssh) is:%d", a * d == b * c ? 1 : 0, a * d < b * c ? 1 : 0);
    }

    public static int zhanzhuan(int a, int b) {
        if (a < b) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        int c = a % b;
        while (c > 0) {
            a = b;
            b = c;
            c = a % b;
        }
        return b;
    }

}
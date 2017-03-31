/**
编程实现两个复数的运算。设有两个复数 和 ，则他们的运算公式为：

　　要求：（1）定义一个结构体类型来描述复数。
　　（2）复数之间的加法、减法、乘法和除法分别用不用的函数来实现。
　　（3）必须使用结构体指针的方法把函数的计算结果返回。
　　说明：用户输入：运算符号(+,-,*,/) a b c d.
　　输出：a+bi，输出时不管a,b是小于0或等于0都按该格式输出，输出时a,b都保留两位。

输入：
　　- 2.5 3.6 1.5 4.9
输出：
　　1.00+-1.30i
 */
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.next();
        Complex a = new Complex(scanner.nextDouble(), scanner.nextDouble());
        Complex b = new Complex(scanner.nextDouble(), scanner.nextDouble());
        Complex res = new Complex(0, 0);
        if (string.equals("+")) {
            res = a.add(b);
        } else if (string.equals("-")) {
            res = a.minus(b);
        } else if (string.equals("*")) {
            res = a.multi(b);
        } else if (string.equals("/")) {
            res = a.devide(b);
        }
        System.out.printf("%.2f+%.2fi", res.shishu, res.xushu);
    }

    static class Complex{
        double shishu;
        double xushu;

        public Complex(double shishu, double xushu) {
            this.shishu = shishu;
            this.xushu = xushu;
        }

        public Complex add(Complex complex) {
            return new Complex(this.shishu + complex.shishu, this.xushu + complex.xushu);
        }

        public Complex minus(Complex complex) {
            return new Complex(this.shishu - complex.shishu, this.xushu - complex.xushu);
        }

        public Complex multi(Complex complex) {
            return new Complex(this.shishu * complex.shishu - this.xushu * complex.xushu, this.xushu * complex.shishu + this.shishu * complex.xushu);
        }

        public Complex devide(Complex complex) {
            double a = this.shishu;
            double b = this.xushu;
            double c = complex.shishu;
            double d = complex.xushu;
            return new Complex((a * c + b * d) / (c * c + d * d), (b * c - a * d) / (c * c + d * d));
        }
    }

}
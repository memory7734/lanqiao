/**
问题描述
　　X星球十分特殊，它的自转速度与公转速度相同，所以阳光总是以固定的角度照射。
　　最近，X星球为发展星际旅游业，把空间位置出租给Y国游客来晒太阳。每个租位是漂浮在空中的圆盘形彩云（圆盘与地面平行）。当然，这会遮挡住部分阳光，被遮挡的土地植物无法生长。
　　本题的任务是计算某个农场宜于作物生长的土地面积有多大。
输入格式
　　输入数据的第一行包含两个整数a, b，表示某农场的长和宽分别是a和b，此时，该农场的范围是由坐标(0, 0, 0), (a, 0, 0), (a, b, 0), (0, b, 0)围成的矩形区域。
　　第二行包含一个实数g，表示阳光照射的角度。简单起见，我们假设阳光光线是垂直于农场的宽的，此时正好和农场的长的夹角是g度，此时，空间中的一点(x, y, z)在地面的投影点应该是(x + z * ctg(g度), y, 0)，其中ctg(g度)表示g度对应的余切值。
　　第三行包含一个非负整数n，表示空中租位个数。
　　接下来 n 行，描述每个租位。其中第i行包含4个整数xi, yi, zi, ri，表示第i个租位彩云的圆心在(xi, yi, zi)位置，圆半径为ri。
输出格式
　　要求输出一个实数，四舍五入保留两位有效数字，表示农场里能长庄稼的土地的面积。
样例输入
10 10
90.0
1
5 5 10 5
样例输出
21.46
样例输入
8 8
90.0
1
4 4 10 5
样例输出
1.81
样例输入
20 10
45.0
2
5 0 5 5
8 6 14 6
样例输出
130.15

这题实在是想不出来应该用什么方法求解，网上也没有大神做出来这道题目，所以只能在设置好了可能的范围之后，判断矩形区域内的随机点是否处于圆形内，理论上来说，当无限多的点之后，就可以得到结果，但是因为Java的性能只能尝试一百万次，所以不能做到全部AC。
这是B组的最后一题，果然蓝桥杯决赛就不能全部用暴力了，等大神发issue
*/
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        double d = scanner.nextDouble() / 180 * Math.PI;//获取角度的数值表示
        double g = 1.0 / Math.tan(d);                   //获取余切值
        int n = scanner.nextInt();
        List<double[]> list = new ArrayList<double[]>();
        for (int i = 0; i < n; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int z = scanner.nextInt();
            int r = scanner.nextInt();
            double realx = x + 1.0 * z * g;
            //判断圆是否与矩形相互重叠
            if (((realx - a) * (realx - a) + (y - b) * (y - b) <= r * r) || (realx <= a && y <= b)) {
                list.add(new double[]{realx, y, r * r});
            }
        }
        //如果全部都没有重叠返回矩形面积
        if (list.size() == 0) {
            System.out.printf("%.2f", 1.0 * a * b);
            return;
        }
        int count = 0;
        int cou = 1000000;//尝试点的次数
        for (int i = 0; i < cou; i++) {
            double x = Math.random() * a;
            double y = Math.random() * b;
            for (double[] num : list) {
                if ((x - num[0]) * (x - num[0]) + (y - num[1]) * (y - num[1]) >= num[2]) {
                    count++;
                }
            }
        }
        double p = 1.0 * count / cou;
        System.out.printf("%.2f", p * a * b);
    }
}

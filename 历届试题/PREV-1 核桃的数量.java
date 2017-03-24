/**
问题描述
小张是软件项目经理，他带领3个开发组。工期紧，今天都在加班呢。为鼓舞士气，小张打算给每个组发一袋核桃（据传言能补脑）。他的要求是：
1. 各组的核桃数量必须相同
2. 各组内必须能平分核桃（当然是不能打碎的）
3. 尽量提供满足1,2条件的最小数量（节约闹革命嘛）
输入格式
输入包含三个正整数a, b, c，表示每个组正在加班的人数，用空格分开（a,b,c<30） 
输出格式
输出一个正整数，表示每袋核桃的数量。 
样例输入1
2 4 5 
样例输出1
20 
样例输入2
3 1 1 
样例输出2
3 
 */
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num1 = sc.nextInt();
        int num2 = sc.nextInt();
        int num3 = sc.nextInt();

        int max = Math.max(Math.max(num1, num2), num3);
        int multi = num1 * num2 * num3;
        for (int i = 1; i <= multi / max; i++) {
            if (max * i % num1 == 0 && max * i % num2 == 0 && max * i % num3 == 0) {
                System.out.println(max * i);
                break;
            }
        }

    }
}

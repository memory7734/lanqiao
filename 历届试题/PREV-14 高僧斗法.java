/**
问题描述
　　古时丧葬活动中经常请高僧做法事。仪式结束后，有时会有“高僧斗法”的趣味节目，以舒缓压抑的气氛。
　　节目大略步骤为：先用粮食（一般是稻米）在地上“画”出若干级台阶（表示N级浮屠）。又有若干小和尚随机地“站”在某个台阶上。最高一级台阶必须站人，其它任意。(如图1所示)
　　两位参加游戏的法师分别指挥某个小和尚向上走任意多级的台阶，但会被站在高级台阶上的小和尚阻挡，不能越过。两个小和尚也不能站在同一台阶，也不能向低级台阶移动。
　　两法师轮流发出指令，最后所有小和尚必然会都挤在高段台阶，再也不能向上移动。轮到哪个法师指挥时无法继续移动，则游戏结束，该法师认输。
　　对于已知的台阶数和小和尚的分布位置，请你计算先发指令的法师该如何决策才能保证胜出。
输入格式
　　输入数据为一行用空格分开的N个整数，表示小和尚的位置。台阶序号从1算起，所以最后一个小和尚的位置即是台阶的总数。（N<100, 台阶总数<1000）
输出格式
　　输出为一行用空格分开的两个整数: A B, 表示把A位置的小和尚移动到B位置。若有多个解，输出A值较小的解，若无解则输出-1。
样例输入
1 5 9
样例输出
1 4
样例输入
1 5 8 10
样例输出
1 3
 */
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        int[] nums = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            if (s[i].length() > 0) {
                nums[i] = Integer.valueOf(s[i]);
            }
        }
        int[] nim = new int[nums.length];
        int p = 0;
        for (int i = 1; i < nums.length; i++) {
            nim[p++] = nums[i] - nums[i - 1] - 1;
        }
        int ans = 0;
        for (int i = 0; i < p; i += 2) {
            ans ^= nim[i];
        }
        if (ans == 0) {
            System.out.println(-1);
            return;
        }
        for (int i = 0; i < p; i++) {
            for (int j = 1; j <= nim[i]; j++) {
                nim[i] -= j;
                if (i != 0) {
                    nim[i - 1] += j;
                }
                int ans1 = 0;
                for(int k=0; k<p; k+=2) {
                    ans1 ^= nim[k];
                }
                if (ans1 != 0) {
                    nim[i] += j;
                    if (i != 0) {
                        nim[i - 1] -= j;
                    }
                } else {
                    System.out.println(nums[i] + " " + Integer.valueOf(nums[i] + j));
                    break;
                }
            }
        }
    }
}

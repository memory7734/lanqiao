/**
问题描述
　　给定一条标有整点(1, 2, 3, ...)的射线. 定义两个点之间的距离为其下标之差的绝对值.
　　Laharl, Etna, Flonne一开始在这条射线上不同的三个点, 他们希望其中某个人能够到达下标最大的点.
　　每个角色只能进行下面的3种操作, 且每种操作不能每人不能进行超过一次.
　　1.移动一定的距离
　　2.把另一个角色高举过头
　　3.将举在头上的角色扔出一段距离
　　每个角色有一个movement range参数, 他们只能移动到没有人的位置, 并且起点和终点的距离不超过movement range.
　　如果角色A和另一个角色B距离为1, 并且角色B没有被别的角色举起, 那么A就能举起B. 同时, B会移动到A的位置,B原来所占的位置变为没有人的位置. 被举起的角色不能进行任何操作, 举起别人的角色不能移动.同时, 每个角色还有一个throwing range参数, 即他能把举起的角色扔出的最远的距离. 注意, 一个角色只能被扔到没有别的角色占据的位置. 我们认为一个角色举起另一个同样举起一个角色的角色是允许的. 这种情况下会出现3个人在同一个位置的情况. 根据前面的描述, 这种情况下上面的两个角色不能进行任何操作, 而最下面的角色可以同时扔出上面的两个角色. 你的任务是计算这些角色能够到达的位置的最大下标, 即最大的数字x, 使得存在一个角色能够到达x.
输入格式
　　输入共三行, 分别为Laharl, Etna, Floone的信息.
　　每一行有且仅有3个整数, 描述对应角色的初始位置, movement range, throwing range.
　　数据保证3个角色的初始位置两两不相同且所有的数字都在1到10之间.</div>
输出格式
　　仅有1个整数, 即Laharl, Etna, Flonne之一能到达的最大距离.
样例输入
9 3 3
4 3 1
2 3 3
样例输出
15
样例说明
　　一开始Laharl在位置9, Etna在位置4, Flonne在位置2.
　　首先, Laharl移动到6.
　　然后Flonne移动到位置5并且举起Etna.
　　Laharl举起Flonne将其扔到位置9.
　　Flonne把Etna扔到位置12.
　　Etna移动到位置15.
 */
import java.util.Scanner;
public class Main {
    //定义数组大小为4，从一开始，空出下标0，方便计算
    static int[] x = new int[4];    //三个人的位置
    static int[] l = new int[4];    //三个人的可移动距离
    static int[] t = new int[4];    //三个人的可抛出的距离
    static int ans = 0;             //最远距离
    static int[] w = new int[4];    //初始化为0，0表示可进行操作，非零表示不可以
    static int[] p = new int[4];    //初始化为0，表示a[i]所举起的人
    static int[] a = {3, 3, 3, 3};    //初始化为3，表人的状态，这里a对应的二进制为0011，后三位分别是三个动作：抛出，举起，移动。0（无意义）0（不可抛出）1（未进行举起）1（未进行移动）。这道题中，a只有六个可能值：0(0000)、1(0001)、2(0010)、3(0011)、4(0100)、5(0101)，表示人的六种状态

    public static boolean near(int s) {
        for (int i = 1; i < 4; i++) {
            if (Math.abs(s - x[i]) == 1) {
                return true;
            }
        }
        return false;
    }

    public static void dfs(int d) {
        int e = 0;
        for (int i = 1; i < 4; i++) {
            ans = Math.max(ans, x[i]);
        }
        for (int i = 1; i < 4; i++) {
            if (w[i] != 0) {
                continue;
            }
            //a[i] == 1 || a[i] == 3（未进行移动且不可抛出）
            if ((a[i] & 1) != 0 && (a[i] & 4) == 0) {
                for (int j = 1; j <= l[i]; j++) {   //移动
                    x[i] += j;                                      //a[i]向前移动j
                    a[i] ^= 1;                                      //已移动
                    if (near(x[i]) || j == l[i]) {                   //如果a[i]移动后的位置旁边有人或者移动距离达到上限
                        dfs(d + 1);
                    }
                    x[i] -= j;                                      //归位
                    x[i] -= j;                                      //a[i]向后移动j
                    if (near(x[i]) || j == l[i]){                    //如果a[i]移动后的位置旁边有人或者移动距离达到上限
                        dfs(d + 1);
                    }
                    x[i] += j;                                      //归位
                    a[i] ^= 1;                                      //还原为未移动
                }
            }
            //a[i] == 2 || a[i] == 3 || a[i] == 5（未进行举起）
            if ((a[i] & 2) != 0) {
                for (int j = 1; j <= 3; j++){                            //举起
                    if (i != j && (w[j] == 0) && t[i] > 0) {               //是否可以进行操作
                        if (x[i] == x[j] + 1 || x[j] == x[i] + 1){   //a[i]附近是否有人
                            w[j] = 1;                               //即将举起（抛出）j，抛出前将j是否可操作标记变更为否
                            a[i] ^= 2;                              //已举起
                            a[i] ^= 4;                              //可抛出
                            p[i] = j;                               //记录a[i]举起（抛出）了j
                            e = x[j];                               //记录a[j]的举起前位置
                            x[j] = -j;                              //a[j]（被举起）的位置定为负数，只作用于下一层递归时的取最远位置的循环
                            dfs(d + 1);
                            x[j] = e;                               //归位
                            w[j] = 0;                               //还原为可以进行操作
                            a[i] ^= 2;                              //还原为未举起
                            a[i] ^= 4;                              //还原为不可抛出
                        }
                    }
                }
            }
            //a[i] == 4 || a[i] == 5（可抛出）
            if ((a[i] & 4) != 0) {
                for (int j = 1; j <= t[i]; j++){                        //抛出
                    w[p[i]] = 0;                                    //变更a[j]为可操作（以下a[j]指a[i]所举起的人）
                    a[i] ^= 4;                                      //不可抛出
                    e = x[p[i]];                                    //记录a[j]被举起前位置
                    x[p[i]] = x[i] + j;                             //抛出a[j]，并更新a[j]位置
                    if (near(x[p[i]]) || j == t[i]){                 //如果a[j]被抛出后的位置旁边有人或者抛出距离达到上限
                        dfs(d + 1);
                    }
                    x[p[i]] -= j;                                   //归位
                    x[p[i]] -= j;                                   //a[j]向后抛出j
                    if (near(x[p[i]]) || j == t[i]){                 //如果a[j]被抛出后的位置旁边有人或者抛出距离达到上限
                        dfs(d + 1);
                    }
                    x[p[i]] = e;                                    //还原a[j]为未举起前的位置
                    a[i] ^= 4;                                      //还原a[j]为可抛出
                    w[p[i]] = 1;                                    //还原a[j]为不可操作
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 1; i < 4; i++) {
            x[i] = scanner.nextInt();
            l[i] = scanner.nextInt();
            t[i] = scanner.nextInt();
        }
        dfs(1);
        System.out.println(ans);
    }
}
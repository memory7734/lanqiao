/**
问题描述
　　现有如下一个算法：
　　repeat ni times
　　yi := y
　　y := yi+1
　　end repeat
　　令n[1]为你需要算加法的第一个数字，n[2]为第二个，...n[N]为第N个数字（N为需要算加法的数字个数），
　　并令y初始值为0，先令i=1运行这个算法（如上所示，重复n[i]次），然后令i=2运行这个算法。。直到i=N。注意y值一直不要清零。最后y的值就是你需要的加法答案。
　　你想知道，有没有某种运算顺序能使答案等于W。
　　一个循环中的全部语句，是不能改变在总的语句排列中的相对顺序的。
　　（这里的第i个循环是指这n[i]*2条语句。就是你把属于第i个循环的语句抽出来看，它们需要按照原顺序排列。在你没有运行完这个循环的最靠前一条未完成的 语句的时候，你是不能跳过它先去完成这个循环后面的语句的。你能做的仅是把若干个循环按照你所规定的顺序“归并”起来。）
　　举个例子，n[1]= 2 ,n[2]=1, W=1.一种可行的运算顺序是“2 1 1 1 1 2”，数字为几表示运行第几个算法的下一条语句（你可以看到”1”出现了4次，是因为n[1]=2即循环两次，而每次循环里面有两条语句，所以2*2=4次）


y值
y[1] 值
y[2] 值
执行0条语句过后
0
0
0
执行1条过后(y[2]=y)
0
0
0
执行2条过后(y[1]=y)
0
0
0
执行3条过后(y=y[1]+1)
1
0
0
执行4条过后(y[1]=y)
1
1
0
执行5条过后(y=y[1]+1)
2
1
0
执行6条过后(y=y[2]+1)
1
1
0


　　可以看到，最后y值变成了1，也就完成了我们的任务。
输入格式
　　第一行你会得到用空格分开的两个整数N(1<=N<=100)和W(-10^9<=W<=10^9)，（N为需要算加法的数字个数，W是你希望算出的数）。
　　第二行你会得到n个整数n[i] (1<=n[i]<=1000).
输出格式
　　第一行您应该输出Yes(若能以某种顺序使得这个算法得出W的值) 或No。
　　如果第一行是No，接下来就不用继续输出了。
　　如果是Yes, 请在第2行输出2*sigma(n[i])个用空格隔开的数，表示任意一种满足条件的运算顺序。
样例输入
1 10
11
样例输出
No
样例输入
2 3
4 4
样例输出
Yes
1 1 2 1 2 2 2 2 2 1 2 1 1 1 1 2
样例输入
3 6
1 2 3
样例输出
Yes
1 1 2 2 2 2 3 3 3 3 3 3
数据规模和约定
　　对于30%的数据，n<=4, n[i]的和小于10.
　　对于100%的数据，n<=100 , -10^9<=W<=10^9, 1<=n[i]<=1000
 */
import java.util.Arrays;
import java.util.Scanner;
public class Main {

    static class Node implements Comparable{
        public int id;
        public int value;

        @Override
        public int compareTo(Object o) {
            if (o != null && o instanceof Node) {
                Node node = (Node) o;
                return this.value - node.value;
            }
            return 0;
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int w = scanner.nextInt();
        int sum = 0;
        Node[] nums = new Node[n];
        for (int i = 0; i < n; i++) {
            nums[i] = new Node();
            nums[i].value = scanner.nextInt();
            nums[i].id = i + 1;
            sum += nums[i].value;
        }
        if (sum < w || w <= 0) {
            System.out.println("No");
        } else {
            Arrays.sort(nums);
            if (nums[0].value <= w) {
                System.out.println("Yes");
                int rest = sum - w;
                System.out.print(nums[0].id);
                int i = n - 1;
                while (i >= 0 && rest >= nums[i].value) {
                    for (int j = 0; j < nums[i].value; j++) {
                        System.out.print(" " + nums[i].id + " " + nums[i].id);
                    }
                    rest -= nums[i].value;
                    i--;
                }
                for (int j = 0; j < rest; j++) {
                    System.out.print(" " + nums[i].id + " " + nums[i].id);
                }
                System.out.print(" " + nums[0].id);
                nums[0].value -= 1;
                nums[i].value -= rest;
                for (int j = 0; j <= i; j++) {
                    for (int k = 0; k < nums[j].value; k++) {
                        System.out.print(" " + nums[j].id + " " + nums[j].id);
                    }
                }
            } else if (n == 1 || w == 1) {
                System.out.println("No");
            } else {
                System.out.println("Yes");
                System.out.print(nums[1].id);
                for (int i = 0; i < nums[0].value - 1; i++) {
                    System.out.print(" " + nums[0].id + " " + nums[0].id);
                }
                System.out.print(" "+nums[1].id);
                for (int i = 0; i < w - 2; i++) {
                    System.out.print(" " + nums[1].id + " " + nums[1].id);
                }
                System.out.print(" " + nums[0].id);
                nums[1].value = nums[1].value - w + 1;
                for (int i = 1; i < n; i++) {
                    for (int j = 0; j < nums[i].value; j++) {
                        System.out.print(" " + nums[i].id + " " + nums[i].id);
                    }
                }
                System.out.print(" " + nums[0].id);
            }
        }
    }
}
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
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int w = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        printResult(nums, w);
    }

    public static void printResult(int[] nums, int w) {
        int maxY = 0;
        for (int i = 0; i < nums.length; i++) {
            maxY += nums[i];
            nums[i] *= 2;
        }
        if (w <= 0) {
            System.out.println("No");
        } else if (w == 1) {
            int min = getMin(nums);
            if (nums[min] == 2) {
                System.out.println("Yes");
                runThread1(nums, min);
                if (min == 0) {
                    runThread2(nums, 1, nums.length - 1);
                } else {
                    runThread2(nums, 0, min - 1);
                    runThread2(nums, min + 1, nums.length - 1);
                }
                runThread1(nums, min);
            } else {
                System.out.println("No");
            }
        } else if (nums.length == 1 && w > 1 && w < maxY) {
            System.out.println("No");
        } else if (nums.length == 1 && w == maxY) {
            System.out.println("Yes");
            while (nums[0] > 0) {
                runThread1(nums, 0);
            }
        } else if (nums.length != 1 && w > 1 && w <= maxY) {
            System.out.println("Yes");
            int count = (w - 2) * 2;
            if (count == 0) {
                int tempI = 0;
                for (int i = 0; i < nums.length - 1; i++) {
                    if (nums[i] >= 4) {
                        tempI = i;
                        break;
                    }
                }
                runThread1(nums, tempI);
                if (tempI == 0)
                    runThread2(nums, 1, nums.length - 2);
                else {
                    runThread2(nums, 0, tempI - 1);
                    runThread2(nums, tempI + 1, nums.length - 2);
                }
                while (nums[nums.length - 1] > 2) {
                    runThread1(nums, nums.length - 1);
                }
                runThread1(nums, tempI);       //此时，y = tempY[tempI] + 1 = 1
                runThread1(nums, nums.length - 1);  //此时，tempY[N.length-1] = y = 1
                while (nums[tempI] > 0) {       //执行完N[tempI]中剩余的线程
                    runThread1(nums, tempI);
                }
                runThread1(nums, nums.length - 1);   //此时执行最后一个线程，y = tempY[N.length-1] + 1 = 2
            } else {
                int i = -1;
                //从第0个进程开始，执行到第i个进程，当tempY[i] = w-3, y = w - 2时结束
                while(count > 0) {
                    i++;
                    while(nums[i] > 0) {
                        runThread1(nums, i);    //此处执行两次runThread1表示执行一次整个循环，y值便会自增1
                        runThread1(nums, i);
                        count = count - 2;
                        if(count == 0)
                            break;
                    }
                }
                runThread1(nums, i);          //执行完此句，此时tempY[i] = w - 2
                runThread2(nums, i+1, nums.length-2);    //执行进程i+1到 N.length-2之间的所有线程
                while(nums[nums.length-1] > 2) {    //执行最后一个进程中的线程
                    runThread1(nums, nums.length-1);
                }
                runThread1(nums, i);         //执行完此句，此时y = tempY[i] + 1 = w - 1
                runThread1(nums, nums.length-1);  //执行完此句，此时tempY[N.length-1] = y =  w - 1
                while(nums[i] > 0) {     //执行完进程i中剩余的线程
                    runThread1(nums, i);
                }
                runThread1(nums, nums.length-1);   //这是最后一个线程，执行完后，y = tempY[N.length-1] + 1 = w
            }
        } else if (w > maxY) {
            System.out.println("No");
        }


    }

    //执行第i个进程中的一个线程，执行完后，总线程数减1
    public static void runThread1(int[] N, int i) {
//        if (N[i] > 0) {
            System.out.print((i+1)+" ");
            N[i]--;
//        }
    }
    //从第i个进程开始，到第n个进程结束，顺序执行其中的每一个线程
    public static void runThread2(int[] N, int i, int n) {
        for( ;i <= n;i++) {
            while(N[i] > 0) {  //执行第i个进程中的每一个线程
                System.out.print((i+1)+" ");
                N[i]--;
            }
        }
    }

    public static int getMin(int[] nums) {
        int min = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < nums[min]) {
                min = i;
            }
        }
        return min;
    }

    public static int getMax(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > nums[max]) {
                max = i;
            }
        }
        return max;
    }

}
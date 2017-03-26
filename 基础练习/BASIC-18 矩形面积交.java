/**
问题描述
　　平面上有两个矩形，它们的边平行于直角坐标系的X轴或Y轴。对于每个矩形，我们给出它的一对相对顶点的坐标，请你编程算出两个矩形的交的面积。
输入格式
　　输入仅包含两行，每行描述一个矩形。
　　在每行中，给出矩形的一对相对顶点的坐标，每个点的坐标都用两个绝对值不超过10^7的实数表示。
输出格式
　　输出仅包含一个实数，为交的面积，保留到小数后两位。
样例输入
1 1 3 3
2 2 4 4
样例输出
1.00
 */
import java.util.Arrays;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        double[] a=new double[4];
        double[] b=new double[4];
        for(int i=0;i<4;i++){
        	a[i]=sc.nextDouble();
        }
        for(int i=0;i<4;i++){
        	b[i]=sc.nextDouble();
        }
        swap(a);
        swap(b);
        double x[]={a[0],a[2],b[0],b[2]};
        double y[]={a[1],a[3],b[1],b[3]};
        if((x[1]<x[2]&&x[0]<x[2])||(y[1]<y[2]&&y[0]<y[2])||(x[3]<x[0]&&x[2]>x[1])||(y[3]<y[0]&&y[2]>y[1])){
        	System.out.println("0.00");
        }else{
        	Arrays.sort(x);
        	Arrays.sort(y);
        	double area = (x[2] - x[1]) * (y[2] - y[1]);
        	System.out.printf("%.2f", area);
        }
        
    }
    public static void swap(double[] nums){
    	if(nums[0]>nums[2]){
    		double tmp=nums[0];
    		nums[0]=nums[2];
    		nums[2]=tmp;
    	}
    	if(nums[1]>nums[3]){
    		double tmp=nums[1];
    		nums[1]=nums[3];
    		nums[3]=tmp;
    	}
    }
}
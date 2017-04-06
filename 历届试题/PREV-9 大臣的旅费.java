/**
问题描述
很久以前，T王国空前繁荣。为了更好地管理国家，王国修建了大量的快速路，用于连接首都和王国内的各大城市。

为节省经费，T国的大臣们经过思考，制定了一套优秀的修建方案，使得任何一个大城市都能从首都直接或者通过其他大城市间接到达。同时，如果不重复经过大城市，从首都到达每个大城市的方案都是唯一的。

J是T国重要大臣，他巡查于各大城市之间，体察民情。所以，从一个城市马不停蹄地到另一个城市成了J最常做的事情。他有一个钱袋，用于存放往来城市间的路费。

聪明的J发现，如果不在某个城市停下来修整，在连续行进过程中，他所花的路费与他已走过的距离有关，在走第x千米到第x+1千米这一千米中（x是整数），他花费的路费是x+10这么多。也就是说走1千米花费11，走2千米要花费23。

J大臣想知道：他从某一个城市出发，中间不休息，到达另一个城市，所有可能花费的路费中最多是多少呢？

输入格式
输入的第一行包含一个整数n，表示包括首都在内的T王国的城市数

城市从1开始依次编号，1号城市为首都。

接下来n-1行，描述T国的高速路（T国的高速路一定是n-1条）

每行三个整数Pi, Qi, Di，表示城市Pi和城市Qi之间有一条高速路，长度为Di千米。

输出格式
输出一个整数，表示大臣J最多花费的路费是多少。

样例输入1
5
1 2 2
1 3 1
2 4 5
2 5 4
样例输出1
135
输出格式
大臣J从城市4到城市5要花费135的路费。
 */
import java.util.*;  
public class Main {  
    public static void main(String args[]){  
        Scanner scan=new Scanner (System.in);  
        int n=scan.nextInt();    
        QiDian[] QiDians=new QiDian[n];  
        for(int i=0;i<n;i++){  
            QiDians[i]=new QiDian(i,new ArrayList<ZhongDian>());  
        }  
        int tem1=0;  
        int tem2=0;  
        int quanzhong=0;  
        for(int i=0;i<n-1;i++){  
            tem1=scan.nextInt();  
            tem2=scan.nextInt();  
            quanzhong=scan.nextInt();  
            QiDians[tem1-1].list.add(new ZhongDian(quanzhong,QiDians[tem2-1]));  
            QiDians[tem2-1].list.add(new ZhongDian(quanzhong,QiDians[tem1-1]));  
        }  
        int[] data=search(QiDians[0],null);  
        int[] data2=search(QiDians[data[1]],null);  
        int sum=0;  
        for(int i=1;i<=data2[0];i++){  
            sum+=i+10;  
        }  
        System.out.println(sum);  
      }  
     public static int[] search(QiDian q,QiDian p){  
        int[] data=new int[]{0,q.index};  
        for(int i=0;i<q.list.size();i++){  
            if(q.list.get(i).qidian.equals(p)==false){  
                int [] data2=search(q.list.get(i).qidian,q);  
                int tem=q.list.get(i).quanzhong+data2[0];  
                if(tem>data[0]){  
                    data[0]=tem;  
                    data[1]=data2[1];  
                }  
            }  
        }    
        return data;  
     }  
    }  
class QiDian{  
    int index;  
    ArrayList<ZhongDian> list=new ArrayList<ZhongDian>();  
    public QiDian(int index, ArrayList<ZhongDian> list) {  
        super();  
        this.index = index;  
        this.list = list;  
    }  
}  
class ZhongDian{  
    int quanzhong;  
    QiDian qidian;  
    public ZhongDian(int quanzhong, QiDian qidian) {  
        super();  
        this.quanzhong = quanzhong;  
        this.qidian = qidian;  
    }    
}  
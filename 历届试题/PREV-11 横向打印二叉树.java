/**
问题描述
二叉树可以用于排序。其原理很简单：对于一个排序二叉树添加新节点时，先与根节点比较，若小则交给左子树继续处理，否则交给右子树。

当遇到空子树时，则把该节点放入那个位置。

比如，10 8 5 7 12 4 的输入顺序，应该建成二叉树如下图所示，其中.表示空白。

...|-12
10-|
...|-8-|
.......|...|-7
.......|-5-|
...........|-4
本题目要求：根据已知的数字，建立排序二叉树，并在标准输出中横向打印该二叉树。

输入格式
输入数据为一行空格分开的N个整数。 N<100，每个数字不超过10000。

输入数据中没有重复的数字。

输出格式
输出该排序二叉树的横向表示。为了便于评卷程序比对空格的数目，请把空格用句点代替：

样例输入1
10 5 20
样例输出1
...|-20
10-|
...|-5
样例输入2
5 10 20 8 4 7
样例输出2
.......|-20
..|-10-|
..|....|-8-|
..|........|-7
5-|
..|-4
 */
import java.util.Scanner;
public class Main {
    /**
     * 节点
     */
    static class Node{
        //值
        int data;
        Node left;
        Node right;
        //输出的值
        String s;
        public Node(int e){
            this.data=e;
        }
    }

    public static void main(String[] args) {
        //int[] n = { 34, 31, 36, 47, 23, 35, 41, 14, 12, 15, 7, 10, 8, 5, 12, 7, 3, 6 };
        int[] n=getInput();
        Node root=new Node(n[0]);
        root.s=root.data+"-|";

        for(int i=1;i<n.length;i++){
            Node node=new Node(n[i]);
            if(node.data>root.data){
                addRight(node, root,0);
            }else{
                addLeft(node,root,0);
            }
        }

        print(root);
    }
    /**
     * 接收输入
     * @return
     */
    public static int[] getInput(){
        Scanner scan=new Scanner(System.in);
        String s=scan.nextLine();
        String[] ss=s.split(" ");
        int[] nn=new int[ss.length];
        for(int i=0;i<ss.length;i++){
            nn[i]=Integer.parseInt(ss[i]);
        }
        return nn;
    }
    /**
     * 打印
     * @param node 根节点
     */
    public static void print(Node node){
        //始终先打印右节点，然后打印本身，最后打印左节点
        if(node.right!=null){
            print(node.right);
        }
        //如果没有子节点，就不打印后面的"-|“
        if(node.left==null&&node.right==null){
            System.out.println(node.s.substring(0, node.s.length()-2));
        }else{
            System.out.println(node.s);
        }
        if(node.left!=null){
            print(node.left);
        }
    }
    /**
     * 添加右节点
     * @param node 子节点
     * @param root 父节点
     * @param flag 括号层数（0 只有一层括号，1 有多层括号）
     */
    public static void addRight(Node node,Node root,int flag){
        if(root.right==null){
            node.s=root.s.replaceAll("[0-9]|-", ".").substring(0, root.s.length()-1);
            if(flag==0){
                int index=node.s.lastIndexOf("|");
                if(index!=-1){
                    node.s=node.s.substring(0,index)+"."+node.s.substring(index+1);
                }
            }
            node.s+="|-"+node.data+"-|";

            root.right=node;
        }else if(node.data>root.right.data){
            addRight(node, root.right,0);
        }else{
            addLeft(node,root.right,1);
        }
    }
    /**
     * 添加左节点
     * @param node 子节点
     * @param root 父节点
     * @param flag 括号层数（0 只有一层括号，1 有多层括号）
     */
    public static void addLeft(Node node,Node root,int flag){
        if(root.left==null){
            node.s=root.s.replaceAll("[0-9]|-", ".").substring(0, root.s.length()-1);
            if(flag==0){
                int index=node.s.lastIndexOf("|");
                if(index!=-1){
                    node.s=node.s.substring(0,index)+"."+node.s.substring(index+1);
                }
            }
            node.s+="|-"+node.data+"-|";
            root.left=node;
        }else if(node.data>root.left.data){
            addRight(node, root.left,1);
        }else{
            addLeft(node,root.left,0);
        }
    }
}
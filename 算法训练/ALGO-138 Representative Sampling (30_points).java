/**
【题目描述】
　　来自ABBYY的小明有一个与“细胞与遗传学研究所”的合作。最近，研究所用一个新的题目考验小明。题目如下。
　　有由n个细胞组成的一个集合（不一定不同）每个细胞是一个由小写拉丁字母组成的字符串。科学家给小明提出的问题是从给定集合中选出一个大小为k的子集，使得所选子集的代表值最大。
　　小明做了些研究并得出了一个结论，即一个蛋白质集合的代表制可以用一个方便计算的整数来表示。我们假设当前的集合为{a1, ..., ak}，包含了k个用以表示蛋白质的字符串。那么蛋白质集合的代表值可以用如下的式子来表示：

　　其中f(x, y)表示字符串x和y的最长公共前缀的长度，例如：
　　f("abc", "abd") = 2 ， f("ab", "bcd") = 0.
　　因此，蛋白质集合{"abc", "abd", "abe"}的代表值等于6，集合{"aaa", "ba", "ba"}的代表值等于2。
　　在发现了这个之后，小明要求赛事参与者写一个程序选出，给定蛋白质的集合中的大小为k的子集中，能获得最大可能代表性值得一个子集。帮助他解决这个问题吧！
【输入格式】
　　输入数据第一行包含2个正整数n和k（1≤k≤n），由一个空格隔开。接下来的n行每一行都包含对蛋白质的描述。每个蛋白质都是一个仅有不超过500个小写拉丁字母组成的非空字符串。有些字符串可能是相等的。
输出格式
　　输出一个整数，表示给定蛋白质集合的大小为k的子集的代表值最大可能是多少。

【数据规模】
　　20%的数据保证：1 ≤ n ≤ 20
　　50%的数据保证：1 ≤ n ≤ 100
　　100%的数据保证：1 ≤ n ≤ 2000

【样例输入1】
　　3 2
　　aba
　　bzd
　　abq
【样例输出1】
　　2

【样例输入2】
　　4 3
　　eee
　　rrr
　　ttt
　　qqq
【样例输出2】
　　0
【样例输入3】
　　4 3
　　aaa
　　abba
　　abbc
　　abbd
【样例输出3】
　　9
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class TrieNode implements Comparable{
    public char val;
    public int count;
    public int deep;
    public int weight;
    public List<TrieNode> children;

    public TrieNode(char val, int deep) {
        this.val = val;     //char值
        this.count = 1;     //在当前节点位置相同前缀的字符串数量
        this.deep = deep;   //string的位置，也是trie tree的深度
        this.weight = 0;    //从当前结点开始的字串相同前缀长度和
        this.children = new ArrayList<TrieNode>();  //子节点
    }
    @Override
    public int compareTo(Object o) {
        return ((TrieNode) o).weight - this.weight;
    }
}
public class Main {
    //创建trie tree
    public static void insert(TrieNode root, String string, int deep) {
        //string的长度为0则返回
        if (string.length() == 0)
            return;
        //获取初始值
        char start = string.charAt(0);
        for (int i = 0; i < root.children.size(); i++) {
            if (root.children.get(i).val == start) {
                insert(root.children.get(i), string.substring(1), deep + 1);
                root.children.get(i).count++;
                return;
            }
        }
        TrieNode node = new TrieNode(start, deep + 1);
        root.children.add(node);
        insert(node, string.substring(1), deep + 1);
    }
    //根据树结构初始化权重值
    public static void weight(TrieNode root) {
        if (root.count <= 1) {
            return;
        }
        for (int i = 0; i < root.children.size(); i++) {
            weight(root.children.get(i));
        }
        //当前节点权重等于所有后缀的长度和+当前节点的数量排列组合
        root.weight = root.count * (root.count - 1) / 2;
        for (int i = 0; i < root.children.size(); i++) {
            TrieNode node = root.children.get(i);
            root.weight += node.weight;
        }
    }

    public static int search(TrieNode root, int k,int pos) {
        if (root == null) {
            return 0;
        }
        Collections.sort(root.children);
        TrieNode node;
        int res = 0;
        int max = 0;
        boolean flag = true;    //标记为真的时候，求排序后的子节点的前i个节点权重和，使得节点数量<k
        for (int i = 0; i < root.children.size(); i++) {
            node = root.children.get(i);
            if (flag && k >= node.count) {
                res += node.weight + node.count * (node.count - 1) / 2 * (node.deep - 1 - pos);
                k -= node.count;
            } else if (k < node.count && node.count != node.children.size()) {
                max = Math.max(search(node, k, pos + 1) + k * (k - 1) / 2 * (node.deep - pos), max);
                flag = false;
            }
        }
        return res + max;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strings = br.readLine().split(" ");
        int n = Integer.parseInt(strings[0]);
        int k = Integer.parseInt(strings[1]);
        TrieNode root = new TrieNode((char) (0), 0);
        root.count = n;
        for (int i = 0; i < n; i++) {
            insert(root, br.readLine(), 0);
        }
        weight(root);
        System.out.println(search(root, k, 0));
    }
}
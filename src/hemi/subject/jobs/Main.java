package hemi.subject.jobs;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.*;


/**
 * 牛客网等上的编程题
 */
public class Main {

    public static void main(String[] arg){
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){

        }
        sc.close();
    }


    /**
     * 小易喜欢的序列
     *
     * 复杂度过大
     * @param args
     */
    public void arr(String[] args){
        Scanner sc = new Scanner(System.in);
        if(sc.hasNext()){
            int n = sc.nextInt();
            int k = sc.nextInt();
            long count = 0,finish =0;
            int[] data = new int[n];
            for(int i=0;i<n;i++){
                data[i] = 1;
            }
            for(int i=1;finish == 0;i++){
                int index = 0;
                int flag = 0 ;
                data[index]=i;

                //进位

                while(data[index]>k){
                    i=0;
                    flag = 1;
                    data[index]=1;
                    if(index+1>=data.length){
                        finish = 1;
                        break;
                    }
                    data[++index]++;
                }
                int j = 0;
                if(flag==0){
                    //判定
                    for(;j<data.length-1;j++){
                        int a = data[j];
                        int b = data[j+1];
                        if(a>b && a%b == 0){
                            break;
                        }
                    }
                    if(j==data.length-1){
                        count++;
                    }
                }
            }
            System.out.println(count%1000000007);
        }
    }

    /**
     * 堆砖块
     *
     * 如果能堆砌出两座高度相同的塔，输出最高能拼凑的高度，如果不能则输出-1.
     *
     * dp[i][j]表示前i个砖块中分两堆，较矮一堆的高度
     *
     */
    public void bricks(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] bricks = new int[n];
        int sum =0;
        for(int i=0;i<n;i++){
            bricks[i]=sc.nextInt();
            sum += bricks[i];
        }
        int[] dp = new int[sum+1];
        for(int i=0;i<=sum;i++){
            dp[i]=Integer.MIN_VALUE;
        }
        dp[0] =0 ;
        for(int i=1;i<=n;i++){
            int b = bricks[i-1];
            int[] temp = new int[sum+1];
            for(int j=0;j<=sum;j++){
                //不放
                temp[j] = dp[j];
                //放矮塔，放后矮塔依然矮
                if(j+b<=sum){
                    temp[j] = Math.max(temp[j],dp[j+b]+b);
                }
                //放矮塔，放后矮塔高
                if(b>=j){
                    temp[j] = Math.max(temp[j],dp[b-j]+b-j);
                }else{
                    //放高塔，高塔更高
                    temp[j] = Math.max(temp[j],dp[j-b]);
                }
            }
            dp = temp;
        }
        System.out.println(dp[0]>0?dp[0]:-1);
    }
    /**
     * 分饼干
     *
     * 饼干数k=9XXXX98X
     * 分给n个小朋友
     *
     * dp[i][j]表示前i个字符串中模n余j的个数
     * 则 dp[i][(10*j+c)%n] += dp[i-1][j]
     *
     * 以上发现第i行的值只会访问到第i-1行的值，所以实际只需要两个数组即可，可以优化空间成类似堆砖块
     */
    public void cookie(String[] args){
        Scanner sc = new Scanner(System.in);
        String k = sc.nextLine();
        int n = sc.nextInt();

        long[][] dp = new long[k.length()+1][n];
        dp[0][0] = 1;
        for(int i=1;i<=k.length();i++){
            char c = k.charAt(i-1);
            for(int j=0;j<n;j++){
                for(int z=0;z<10;z++){
                    if(c == 'X' || c ==('0'+z)){
                        dp[i][(10*j+z)%n] += dp[i-1][j];
                    }
                }
            }
        }
        System.out.println(dp[k.length()][0]);
    }



    /**
     * 数串
     *
     *  设有n个正整数，将他们连接成一排，组成一个最大的多位整数。
        如:n=3时，3个整数13,312,343,连成的最大整数为34331213。
        如:n=4时,4个整数7,13,4,246连接成的最大整数为7424613。
     * @param
     */
    public void shuchuang(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<String> input = new ArrayList<>();
        for(int i=0;i<n;i++){
            input.add(sc.next());
        }
        StringBuffer result = new StringBuffer();

        Collections.sort(input, new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        int len1 = o1.length();
                        int len2 = o2.length();

                        for (int i = 0; i < len1 && i < len2; i++) {
                            if (o1.charAt(i) < (o2.charAt(i))) {
                                return -1;
                            } else if (o1.charAt(i) > o2.charAt(i)) {
                                return 1;
                            }
                        }

                        if (len1 > len2) {
                            return compare(o1.substring(len2), o2);
                        } else if (len1 < len2) {
                            return compare(o1, o2.substring(len1));
                        }
                        return 0;
                    }
                }
        );
        for(int i=input.size()-1;i>=0;i--){
            result.append(input.get(i));
        }


        System.out.println(result.toString());
    }


    public static void main3(String[] args){
        Scanner in = new Scanner(System.in);
        int pieces = Integer.parseInt(in.nextLine().trim());
        Integer[] parts = new Integer[pieces];
        for (int i = 0; i < pieces; i++) {
            parts[i] = Integer.parseInt(in.nextLine().trim());
        }
        System.out.println(cut(parts));
    }

    static int cut(Integer[] parts){
        if(parts.length<1) return 0;

        Arrays.sort(parts);
        int arr1 = 0;
        int arr2 = 0;
        ArrayList<Integer> deque1 = new ArrayList<>();
        ArrayList<Integer> deque2 = new ArrayList<>();
        int result = 0;
        for(int i=parts.length-1;i>=0;i--){
            if(parts[i]<1) return 0;
            if(arr1<arr2){
                arr1 += parts[i];
                deque1.add(parts[i]);
            }else {
                arr2 += parts[i];
                deque2.add(parts[i]);
            }
        }
        if(deque1.size()>1){
            result += cut(toArray(deque1));
        }
        if(deque2.size()>1){
            result += cut(toArray(deque2));
        }
        result += arr1 +arr2;
        return result;
    }

    static Integer[] toArray(List<Integer> list){
        int size = list.size();
        Integer[] result = new Integer[size];
        for(int i=0;i<size;i++){
            result[i] = list.get(i);
        }
        return result;
    }

    //
    private static double get(List<Integer[]> rlist,double max){
        int rsize = rlist.size();
        double temp = 0;
        for(int i=0;i<rsize-2;i++){
            for(int j=i+1;j<rsize-1;j++){
                for(int k=j+1;k<rsize;k++){
                    temp = area(length(rlist.get(i),rlist.get(j)),length(rlist.get(i),rlist.get(k)),length(rlist.get(j),rlist.get(k)));
                    if(temp>max) max = temp;
                }
            }
        }
        return max;
    }

    //求边长
    private static double length(Integer[] a,Integer[] b){
        int sum = 0;
        for(int i=0;i<a.length;i++){
            sum += Math.pow(a[i]-b[i],2);
        }
        return Math.sqrt(sum);
    }
    //求面积
    private static double area(double a,double b,double c){
        double p = (a+b+c)/2;
        return Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }
}

class Solution {


    /**
     * 平安果数量
     *
     * 待完成
     */
    public int appleNum(int row,int col,int[][] input){
        --row;
        --col;
        int sum =input[row][col];
        while (row>0 && col >0){
            if(input[row][col-1]>input[row-1][col]){
                sum += input[row][col-1];
                col--;
            }else{
                sum += input[row-1][col];
                row--;
            }
        }
        return 0;
    }

    /**
     * 二叉树的遍历
     */
    /*
    递归
     */
    // 先序遍历
    public void preOrder(BinaryNode node){
        if(node == null) return;
        System.out.println(node.element);
        preOrder(node.left);
        preOrder(node.right);
    }
    // 中序遍历
    public void inOrder(BinaryNode node){
        if(node == null) return;
        inOrder(node.left);
        System.out.println(node.element);
        inOrder(node.right);
    }
    // 后序遍历
    public void postOrder(BinaryNode node){
        if(node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.element);
    }

    /*
    非递归
     */
    // 先序遍历
    public void preOrder2(BinaryNode node){
        Stack<BinaryNode> stack = new Stack<>();
        while (node!=null || !stack.isEmpty()){
            while (node!=null){
                System.out.println(node.element);
                stack.push(node);
                node = node.left;
            }
            if(!stack.isEmpty()){
                node = stack.pop();
                node = node.right;
            }
        }
    }
    // 中序遍历
    public void inOrder2(BinaryNode node){
        Stack<BinaryNode> stack = new Stack<>();
        while (node!=null || !stack.isEmpty()){
            while (node!=null){
                stack.push(node);
                node = node.left;
            }
            if(!stack.isEmpty()){
                node = stack.pop();
                System.out.println(node.element);
                node = node.right;
            }
        }
    }
    // 后序遍历
    public void postOrder2(BinaryNode p){
        Stack<BinaryNode> stack = new Stack<BinaryNode>();//TODO
        BinaryNode node = p, prev = p;
        while (node != null || stack.size() > 0) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            if (stack.size() > 0) {
                BinaryNode temp = stack.peek().right;
                if (temp == null || temp == prev) {
                    node = stack.pop();
                    System.out.println(node.element);
                    prev = node;
                    node = null;
                } else {
                    node = temp;
                }
            }

        }
    }



    /**
     * 爬楼梯
     *
     */
    public int louti(int k){
        if(k<0) return 0;
        int[] temp = new int[k+1];
        temp[1]=1;
        temp[2]=2;
        for(int i=3;i<k+1;i++){
            temp[i]=temp[i-1]+temp[i-2];
        }
        return temp[k];
    }


    /**
     * 最大子序列和
     * @param sequence
     * @return
     */
    public int maxSubSum(int[] sequence){
        int maxSum = 0,curSum = 0;
        for(int i=0;i<sequence.length;i++){
            curSum += sequence[i];
            if(curSum>maxSum){
                maxSum=curSum;
            }else if (curSum<0){
                curSum=0;
            }
        }
        return maxSum;
    }

    /**
     * 求环的入口节点
     * @param node
     * @return
     */
    public ListNode getCycleBegin(ListNode node){
        if(hasCycle(node)==false) return null;
        int cycleLength = getCycleLength(node);
        ListNode first = node, second = node;
        for(int i=0;i<cycleLength;i++){
            first = first.next;
        }
        while(first!=second){
            first=first.next;
            second=second.next;
        }
        return first;
    }

    /**
     * 计算有环链表环的长度
     * @param node
     * @return
     */
    public int getCycleLength(ListNode node){
        if(hasCycle(node)==false) return 0;
        ListNode fast = node,slow = node;
        int len = 0 ,flag = 0 , loop = 0;
        while (slow != null && slow.next!=null){
            fast = fast.next;
            slow = slow.next.next;
            len += flag;
            if(fast == slow){
                flag = 1;
                ++loop;
            }
            if(loop==2) break;
        }
        return len;
    }
    /**
     * 判段链表是否有环
     * @param node
     * @return
     */
    public boolean hasCycle(ListNode node){
        ListNode fast = node,slow = node;
        while (slow != null && slow.next!=null){
            fast = fast.next;
            slow = slow.next.next;
            if(fast == slow){
                return true;
            }
        }
        return false;
    }

    /**
     * 字符串匹配-？单字符，*字符串
     * @param str 被字符串
     * @param strStrat
     * @param pattern 匹配模式
     * @param patStart
     * @return
     */
    public static int match(String str,int strStrat,String pattern,int patStart){
        while(patStart < pattern.length()){
            char p = pattern.charAt(patStart);
            if(p=='?' || p == str.charAt(strStrat)){
                ++strStrat;
                ++patStart;
            }else if(p=='*'){
                ++patStart;

                while(pattern.charAt(patStart)!=str.charAt(strStrat)){
                    ++strStrat;
                    if(strStrat>=str.length()) return 0;
                }
            }else{
                ++strStrat;
                if(strStrat>=str.length()) return 0;
            }
        }
        return 1;
    }

    public int MoreThanHalfNum_Solution(Integer [] array) {
        Sort.quickSort(array);
        int cf = array[(array.length-1)/2];
        int count=0;
        for(int i=0;i<array.length;i++){
            if(array[i]==cf)
                count ++;
        }
        return count>array.length/2?cf:0;
    }

    /**
     * 阶乘函数
     * @param n
     * @return
     */
    public long jiec(int n) {
        long ans = 1;
        for (int i = n; i > 0; i--) {
            ans *= i;
        }
        return ans;
    }

    /**
     * 对于一个n*n的方格，从左上角到右下角的路径数
     * 一次只能向右或向下移动一步
     *
     * @param x
     * @param y
     * @return
     */
    public int getRoutes_Recursivity(int n, int x, int y) {
        int count = 0;
        if (x < n)
            count += getRoutes_Recursivity(n, x + 1, y);
        if (y < n)
            count += getRoutes_Recursivity(n, x, y + 1);
        if (x == n && y == n) {
            return 1;
        }
        return count;
    }

    public int getRoutes_Dp(int n) {
        int[][] net = new int[n+1][n+1];
        for (int i = 0; i < n+1; i++) {
            net[n][i] = 1;
            net[i][n] = 1;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                net[i][j] = net[i + 1][j] + net[i][j + 1];
            }
        }
        return net[0][0];
    }

    /**
     * 输入两棵二叉树A，B，判断B是不是A的子结构。
     *
     * @param root1
     * @param root2
     * @return
     */
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) return false;
        boolean result = false;
        if (root1.val == root2.val) {
            result = hasSubtree(root1, root2);
        }
        if (!result) {
            result = hasSubtree(root1.left, root2);
        }
        if (!result) {
            result = hasSubtree(root1.right, root2);
        }
        return result;
    }

    private boolean hasSubtree(TreeNode parent, TreeNode child) {
        if (parent == null && child != null) return false;
        if (child == null) return true;
        if (parent.val != child.val) return false;
        return hasSubtree(parent.left, child.left) && hasSubtree(parent.right, child.right);
    }

    /**
     * 打卡记录
     */
    public void cardRecord() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            int m = sc.nextInt();
            String[] records = new String[m + 1];
            for (int j = 0; j < m + 1; j++) {
                records[j] = sc.nextLine();
            }

            String[] record;
            Date early, late;
            DateFormat df = new SimpleDateFormat("HH:mm:ss");
            String first = null, last = null;
            try {
                early = df.parse("23:59:59");
                late = df.parse("00:00:00");
                for (int j = 1; j < m + 1; j++) {
                    record = records[j].split(" ");
                    if (early.compareTo(df.parse(record[1])) > 0) {
                        early = df.parse(record[1]);
                        first = record[0];
                    }
                    if (late.compareTo(df.parse(record[2])) < 0) {
                        late = df.parse(record[2]);
                        last = record[0];
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println(first + " " + last);
        }
    }

    /**
     * 分饼干
     */
    public void cucie() {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int n = sc.nextInt();
        long[][] dp = new long[str.length()+1][n];
//        for(int i=0;i<=str.length();i++){
//            dp[i]=new long[n];
//        }
        dp[0][0]=1;
        for(int i=1;i<=str.length();i++){
            char c = str.charAt(i-1);
            for(int j=0;j<n;j++){
                if(c=='X'){
                    for(int k=0;k<10;k++){
                        dp[i][(j*10+k)%n] += dp[i-1][j];
                    }
                }else{
                    dp[i][(j*10+Integer.valueOf(c))%n] += dp[i-1][j];
                }
            }
        }
        System.out.println(dp[str.length()][0]);
    }

    /**
     * 调整队形
     */
    public void adjectQueue() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            int countG = 0, countB = 0, sumGI = 0, sumBI = 0, baseSumOfG = 0, baseSumOfB = 0;
            for (int i = 0; i < str.length(); i++) {
                if ("G".equals(String.valueOf(str.charAt(i)))) {
                    ++countG;
                    sumGI += i;
                } else {
                    ++countB;
                    sumBI += i;
                }
            }
            baseSumOfG = countG * (countG - 1) / 2;
            baseSumOfB = countB * (countB - 1) / 2;
            System.out.println(sumBI - baseSumOfB > sumGI - baseSumOfG ? sumGI - baseSumOfG : sumBI - baseSumOfB);
        }
        sc.close();
    }

    /**
     * 打印螺旋矩阵
     */
    public void rotateMat() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int row = sc.nextInt();
            int col = sc.nextInt();
            if (row < 1 || col < 1 || row > 200 || col > 200)
                return;
            int[][] ans = new int[row][col];
            int rowl = 0, rowr = col, colu = 0, cold = row;
            int flag = 1;
            int count = 0;

            while (count < row * col) {
                if (flag == 1) {
                    for (int i = rowl; i < rowr; i++) {
                        ans[colu][i] = ++count;
                    }
                    if (count == row * col) break;
                    ++colu;
                    flag = 2;
                }
                if (flag == 2) {
                    for (int i = colu; i < cold; i++) {
                        ans[i][rowr - 1] = ++count;
                    }
                    if (count == row * col) break;
                    --rowr;
                    flag = 3;
                }
                if (flag == 3) {
                    for (int i = rowr - 1; i >= rowl; i--) {
                        ans[cold - 1][i] = ++count;
                    }
                    if (count == row * col) break;
                    --cold;
                    flag = 4;
                }
                if (flag == 4) {
                    for (int i = cold - 1; i >= colu; i--) {
                        ans[i][rowl] = ++count;
                    }
                    if (count == row * col) break;
                    ++rowl;
                    flag = 1;
                }

            }

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    System.out.print(ans[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
        sc.close();
    }

    /**
     * 击鼓传花
     * m 次数
     * n 人数
     */
    public void flower() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int dp[][] = new int[m + 1][n + 1];
            dp[0][1] = 1;
            dp[1][n] = 1;
            dp[1][2] = 1;
            for (int i = 1; i <= m; i++) {//对每一次数
                for (int j = 1; j <= n; j++) {//对每一个人
                    if (j == 1)
                        dp[i][j] = dp[i - 1][n] + dp[i - 1][2];
                    else if (j == n)
                        dp[i][j] = dp[i - 1][1] + dp[i - 1][n - 1];
                    else
                        dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j + 1];
                }
            }
            System.out.println(dp[m][1]);
        }
        sc.close();
    }

    /**
     * 循环报数
     * <p>
     * 从头开始一至二报数，凡报到二的出列剩下的依次向前靠拢，再从头开始进行一至三报数，
     * 凡报到三的出列，剩下的依次向前靠拢，继续从头开始进行一至二报数。。。
     * 以后每次从头开始轮流进行一至二报数、一至三报数直到剩下的人数不超过三人为止。
     */
    public void circleDelet() {
        Scanner cin = new Scanner(System.in);
        while (cin.hasNext()) {
            int a = cin.nextInt();
            while (a > 0) {
                int N = cin.nextInt();
                ArrayList<Integer> list = new ArrayList<Integer>();
                for (int i = 1; i <= N; i++) {
                    list.add(i);
                }
                baoshu(list, 2);
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(list.get(i) + " ");
                }
                System.out.println();
                a--;
            }
        }
    }

    private void baoshu(ArrayList<Integer> list, int num) {
        if (list.size() > 3) {
            int count = 0;
            for (int i = 0; i < list.size(); i++) {
                count++;
                if (count == num) {
                    list.remove(i);
                    i--;
                    count = 0;
                }
            }
            if (num == 2) {
                baoshu(list, 3);
            } else {
                baoshu(list, 2);
            }
        }
    }

    /**
     * 约德尔测试
     */
    public void test(String s1, String s2) {
        String s1s = new String();
        s1.replace("_", "!");
        for (int i = 0; i < s1.length(); i++) {
            if (String.valueOf(s1.charAt(i)).matches("^\\w?$")) {
                s1s += 1;
            } else {
                s1s += 0;
            }
        }
        double count = 0.0;
        for (int i = 0; i < s1s.length(); i++) {
            if (String.valueOf(s1s.charAt(i)).equals(String.valueOf(s2.charAt(i)))) {
                count++;
            }
        }
        double ans = 100 * count / s1s.length();
        System.out.println(new DecimalFormat("0.00").format(ans) + "%");
    }


    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        int temp;
        if (k <= input.length && k > 0) {
            for (int i = 0; i < k; i++) {
                for (int j = 1; j < input.length - i; j++) {
                    if (input[j] > input[j - 1]) {
                        temp = input[j];
                        input[j] = input[j - 1];
                        input[j - 1] = temp;
                    }
                }
                list.add(input[input.length - i - 1]);
            }
        }
        return list;
    }

    /**
     * 重建二叉树
     *
     * @param pre
     * @param in
     * @return
     */
    /*
    输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
    假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
    例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
     */
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        return reConstructBinaryTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    private TreeNode reConstructBinaryTree(int[] pre, int startPre, int endPre, int[] in, int startIn, int endIn) {
        if (startPre > endPre || startIn > endIn)
            return null;
        TreeNode root = new TreeNode(pre[startPre]);
        for (int i = startIn; i <= endIn; i++) {
            if (in[i] == pre[startPre]) {
                root.left = reConstructBinaryTree(pre, startPre + 1, startPre + i - startIn, in, startIn, i - 1);
                root.right = reConstructBinaryTree(pre, i - startIn + startPre + 1, endPre, in, i + 1, endIn);
            }
        }
        return root;
    }

    /**
     * 二叉树的镜像
     *
     * @param root
     */
    public void Mirror(TreeNode root) {
        if (root == null)
            return;

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        if (root.left != null) {
            Mirror(root.left);
        }
        if (root.right != null) {
            Mirror(root.right);
        }
    }

    /**
     * 二维数组中的查找————各行各列分别递增排列
     *
     * @param target
     * @param array
     * @return
     */
    /*
    从左下角元素开始，当前元素若比target大，行-1；
    当前元素若比target小，列+1
     */
    public boolean Find(int target, int[][] array) {
        int row = array.length - 1;
        int col = array[0].length - 1;
        int i = row, j = 0;
        while (i >= 0 && j <= col) {
            if (target > array[i][j]) j++;
            else if (target < array[i][j]) i--;
            else return true;
        }
        return false;
    }

    /**
     * 替换空格
     * 将一个字符串中的空格替换成“%20”
     *
     * @param str
     * @return
     */
    public String replaceSpace(StringBuffer str) {
        String newString = new String();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                newString += "%20";
            } else {
                newString += str.charAt(i);
            }
        }
        return newString;
    }

    /**
     * 合并两个排序的链表
     *
     * @param list1
     * @param list2
     * @return
     */
    public static ListNode Merge(ListNode list1, ListNode list2) {
        ListNode headnode = null;
        ListNode curnode = null;
        if (list1 == null)
            return list2;
        if (list2 == null)
            return list1;
        while (list1 != null && list2 != null) {
            if (list1.val > list2.val) {
                if (headnode == null) {
                    headnode = curnode = list2;
                } else {
                    curnode.next = list2;
                    curnode = curnode.next;
                }
                list2 = list2.next;
            } else {
                if (headnode == null) {
                    headnode = curnode = list1;
                } else {
                    curnode.next = list1;
                    curnode = curnode.next;
                }
                list1 = list1.next;
            }
        }
        if (list1 != null)
            curnode.next = list1;
        else
            curnode.next = list2;
        return headnode;
    }

    //递归
    public static ListNode mergeByResc(ListNode list1, ListNode list2) {
        if (list1 == null)
            return list2;
        if (list2 == null)
            return list1;
        if (list1.val < list2.val) {
            list1.next = mergeByResc(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeByResc(list1, list2.next);
            return list2;
        }
    }

    /**
     * 判断是否为二叉搜索树的后序遍历序列
     *
     * @param sequence
     * @return
     */
    /*
BST的后序序列的合法序列是，对于一个序列S，最后一个元素是x（也就是根），
如果去掉最后一个元素的序列为T，那么T满足：T可以分成两段，前一段（左子树）小于x，
后一段（右子树）大于x，且这两段（子树）都是合法的后序序列。完美的递归定义。
     */
    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence.length < 1)
            return false;
        //非递归法
        /*        int len = sequence.length;
        if(len < 1)
            return false;
        int i=0;
        while(--len != 0 ){
            while(i+1<=len && sequence[i++]<sequence[len]);
            while(i+1<=len && sequence[i++]>sequence[len]);
            if(i<len)
                return false;
            i=0;
        }
        return true;*/
        return verify(sequence, 0, sequence.length - 1);
    }

    private boolean verify(int[] a, int left, int right) {
        if (left >= right)
            return true;
        int cur = right;
        while (left < cur && a[cur - 1] > a[right])
            --cur;

        for (int i = cur - 1; i >= left; i--) {
            if (a[i] > a[right])
                return false;
        }
        return verify(a, left, cur - 1) && verify(a, cur, right - 1);
    }



}

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}

class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }

}
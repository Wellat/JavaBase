package hemi.xmu.jobs;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        solution.multiplyByBigNum("99999999","99999999");
        int t[] = {2,3,10,5,10};
        System.out.println(Integer.MAX_VALUE);
//        solution.t();
//        int[] a ={0,1};
//        int[] a2 ={1,2};
//        int[][] t = new int[2][2];
//        t[0] = a;
//        t[1] = a2;
//        int[] res = solution.canFinish(3,t);

    }
}


class TestData{
    public static TreeNode getTreeNode(){
        TreeNode t1 = new TreeNode(3);
        TreeNode t2 = new TreeNode(12);
        TreeNode t3 = new TreeNode(4);
        TreeNode t4 = new TreeNode(7);
        TreeNode t5 = new TreeNode(0);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        return t1;
    }
}

/**
 * 考试题解
 */
class Solution {

    /**
     * 大数乘法
     */
    public void multiplyByBigNum(String a,String b){
        int[] ans = new int[100];
        int index = ans.length-1;
        for (int i = b.length()-1; i >= 0; i--) {
            int tb = b.charAt(i)-'0';
            int k = index--;
            for (int j = a.length()-1; j >= 0; j--) {
                int ta = a.charAt(j)-'0';
                int tempSum = ta * tb + ans[k];
                ans[k] = tempSum % 10;
                ans[k-1] += tempSum / 10;
                k--;
            }
        }
        int i=0;
        while (ans[i]==0){
            i++;
        }
        for (; i < ans.length; i++) {
            System.out.print(ans[i]);
        }
        System.out.println();
    }
    /**
     * LRU算法
     * 删除最近最少使用的
     */
    public void testlru(){
        LRU<Integer,String> lru = new LRU<>(3);
        lru.set(0,"zero");
        lru.set(1,"one");
        lru.set(2,"tow");
        lru.set(3,"three");
        lru.get(1);
        lru.set(5,"five");
        System.out.println();
    }

    class LRU<K, V> extends LinkedHashMap<K, V> {
        private final int MAX_CACHE_SIZE;

        public LRU(int cacheSize) {
            super((int) Math.ceil(cacheSize / 0.75) + 1, 0.75f, true);
            MAX_CACHE_SIZE = cacheSize;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry eldest) {
            return size() > MAX_CACHE_SIZE;
        }

        public V set(K key, V value) {
            return put(key, value);
        }
    }
    //自己实现数据结构
    class LRU2<K,V>{
        private final int MAX_CACHE_SIZE;
        private HashMap<K,Entry<K,V>> map;
        private Entry head;
        private Entry tail;

        public LRU2(int cacheSize){
            map = new HashMap<K,Entry<K,V>>();
            MAX_CACHE_SIZE = cacheSize;
        }

        public void set(K key,V value){
            Entry entry = map.get(key);
            if(null == entry){
                if(map.size()>MAX_CACHE_SIZE){
                    map.remove(tail.key);
                    tail=tail.prev;
                    tail.next=null;
                }
                entry = new Entry();
                entry.key= key;
            }
            entry.value = value;
            moveToFirst(entry);
            map.put(key,entry);
        }

        public V get(K key){
            Entry<K,V> entry = getEntry(key);
            if(entry == null) return null;
            moveToFirst(entry);
            return entry.value;
        }

        private void moveToFirst(Entry entry){
            //TODO
        }

        private Entry<K,V> getEntry(K key){
            return map.get(key);
        }

        class Entry<K,V>{
            K key;
            V value;
            Entry prev;
            Entry next;
        }
    }
    /**
     * 数组组合，和为k的个数
     * 回溯法，不存储结果
     */
    int count = 0;
    public void pdd02(int[] data,int k){
        bt(data,0,k);
        System.out.println(count);
    }
    public void bt(int[] data,int start,int sum){
        if(sum==0){
            ++count;
            return;
        }
        if(sum < 0) return;
        for (int i = start; i < data.length; i++) {
            bt(data,i+1,sum-data[i]);
        }
    }

    /**
     * 由rand6()生成rand7()
     */
    public int rand7() {
        int i = rand6() + 3*(rand6()-1);
        return i % 7 + 1;
    }
    private int rand6(){
        return 0;//TODO
    }

    /**
     * 拓扑排序
     * leetCode 207 & 210
     */
    /*
    1 建图
    2 将入度为0的顶点入队
    3 出队,访问出队的顶点能访问到的顶点
    4 将出队顶点能访问到的顶点的入度减1
    5 重复2-4,直到队列为空
    6 当队列为空时,如果count的值小于定点数,证明还存在入度大于0的顶点,也就是图中存在环
     */
    public int[] canFinish(int numCourses, int[][] prerequisites) {
        int[] indeed = new int[numCourses];//记录各个顶点的入度
        int[][] graph = new int[numCourses][numCourses]; //利用邻接矩阵建图
        int[] result = new int[numCourses];//保存节点路径
        int count = 0;//记录访问的顶点个数
        for (int i = 0; i < prerequisites.length; i++) { //建立有向图
            int[] num = prerequisites[i];
            if (graph[num[0]][num[1]] == 1) return new int[0]; //如果存在直接矛盾两门课程则返回false
            graph[num[1]][num[0]] = 1;
            indeed[num[0]]++;  //入度+1
        }
        Queue<Integer> queue = new LinkedList<>(); //建立辅助队列
        for (int i = 0; i < indeed.length; i++) {
            if (indeed[i] == 0) {  //入度为0的入队
                queue.offer(i);
                result[count++] = i;
            }
        }
        while (!queue.isEmpty()) {
            int num = queue.poll();//头结点
            for (int i = 0; i < graph.length; i++) {
                if (graph[num][i] != 0) {
                    indeed[i]--;
                    if (indeed[i] == 0) { //当入度变为0时，入队，count++
                        queue.offer(i);
                        result[count++] = i;
                    }
                }
            }
        }
        if(count == numCourses){//count数=顶点数返回true，否则返回false
            return result;
        } else {
            return new int[0];
        }
    }


    /**
     * 前k个数
     * @param a
     * @param k
     * @return
     */
    List<Integer> findTopKth(int a[],int k){
        List<Integer> list = new ArrayList<>();
        partition(list,a,k-1,0,a.length-1);
        return list;
    }
    public void partition(List<Integer> list,int[] a,int k,int left,int right){
        int c = quicksort(a,left,right);
        if(c>k){
            partition(list,a,k,left,c-1);
        }else if (c<k){
            partition(list,a,k,c+1,right);
        }else{
            System.out.println("封装结果");
        }
    }
    int quicksort(int a[],int low,int high){
        int i=low,j=high;
        int t = a[i];
        while (i<j){
            while (i<j && a[j]>t){
                j--;
            }
            a[i++]=a[j];
            while (i<j && a[i]<t){
                i++;
            }
            a[j--]=a[i];
        }
        a[i]=t;
        return i;
    }

    /**
     * 二分查找
     * @param a 已排序数组
     * @param key
     * @return
     */
    boolean binarySearch(int[] a,int key){
        int low = 0;
        int high = a.length-1;
        int mid = 0;
        while (low<high){
            mid = (low+high)/2;
            if(a[mid]>key){
                high = mid -1;
            }else if(a[mid]<key){
                low = mid+1;
            }else{
                return true;
            }
        }
        return false;
    }

    /**
     * 回溯法集合
     * https://leetcode.com/problems/subsets/discuss/
     */
    static class BackTrack{
        //所有子集
        public static List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> list = new ArrayList<>();
            Arrays.sort(nums);
            backtrack(list, new ArrayList<>(), nums, 0);
            return list;
        }
        private static void backtrack(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
            list.add(new ArrayList<>(tempList));
            for(int i = start; i < nums.length; i++){
                if(i > start && nums[i] == nums[i-1]) continue; // skip duplicates
                tempList.add(nums[i]);
                backtrack(list, tempList, nums, i + 1);
                tempList.remove(tempList.size() - 1);
            }
        }

        /**
         * 全排列
         * @param nums
         * @return
         */
        //所有排列----1 无重复值
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> list = new ArrayList<>();
            backtrack(list, new ArrayList<>(), nums);
            return list;
        }
        public void backtrack(List<List<Integer>> list,List<Integer> tempList,int[] nums){
            if(tempList.size() == nums.length){
                list.add(new ArrayList<>(tempList));
            }else{
                for(int i = 0; i < nums.length; i++){
                    if(tempList.contains(nums[i])) continue;
                    tempList.add(nums[i]);
                    backtrack(list, tempList, nums);
                    tempList.remove(tempList.size() - 1);
                }
            }
        }
        //所有排列----2 有重复值
        public List<List<Integer>> permute2(int[] nums) {
            List<List<Integer>> list = new ArrayList<>();
            Arrays.sort(nums);
            backtrack(list, new ArrayList<>(), nums, new boolean[nums.length]);
            return list;
        }
        private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, boolean [] used){
            if(tempList.size() == nums.length){
                list.add(new ArrayList<>(tempList));
            } else{
                for(int i = 0; i < nums.length; i++){
                    if(used[i] || i > 0 && nums[i] == nums[i-1] && !used[i - 1]) continue;
                    used[i] = true;
                    tempList.add(nums[i]);
                    backtrack(list, tempList, nums, used);
                    used[i] = false;
                    tempList.remove(tempList.size() - 1);
                }
            }
        }

        /**
         * Combination Sum
         * @param nums
         * @param target
         * @return
         */
        // 数组内元素组合，和为target的所有结果，元素可多次使用
        public List<List<Integer>> combinationSum(int[] nums, int target) {
            List<List<Integer>> list = new ArrayList<>();
            Arrays.sort(nums);
            backtrack(list, new ArrayList<>(), nums, target, 0);
            return list;
        }
        private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int remain, int start){
            if(remain < 0) return;
            else if(remain == 0) list.add(new ArrayList<>(tempList));
            else{
                for(int i = start; i < nums.length; i++){
                    tempList.add(nums[i]);
                    backtrack(list, tempList, nums, remain - nums[i], i); // not i + 1 because we can reuse same elements
                    tempList.remove(tempList.size() - 1);
                }
            }
        }
        // (can't reuse same element)
        public List<List<Integer>> combinationSum2(int[] nums, int target) {
            List<List<Integer>> list = new ArrayList<>();
            Arrays.sort(nums);
            backtrack2(list, new ArrayList<>(), nums, target, 0);
            return list;
        }
        private void backtrack2(List<List<Integer>> list, List<Integer> tempList, int [] nums, int remain, int start){
            if(remain < 0) return;
            else if(remain == 0) list.add(new ArrayList<>(tempList));
            else{
                for(int i = start; i < nums.length; i++){
                    if(i > start && nums[i] == nums[i-1]) continue; // skip duplicates
                    tempList.add(nums[i]);
                    backtrack2(list, tempList, nums, remain - nums[i], i + 1);
                    tempList.remove(tempList.size() - 1);
                }
            }
        }
        /**
         * 回文子集合
         */
        public List<List<String>> partition(String s) {
            List<List<String>> list = new ArrayList<>();
            backtrack(list, new ArrayList<>(), s, 0);
            return list;
        }
        public void backtrack(List<List<String>> list, List<String> tempList, String s, int start){
            if(start == s.length())
                list.add(new ArrayList<>(tempList));
            else{
                for(int i = start; i < s.length(); i++){
                    if(isPalindrome(s, start, i)){
                        tempList.add(s.substring(start, i + 1));
                        backtrack(list, tempList, s, i + 1);
                        tempList.remove(tempList.size() - 1);
                    }
                }
            }
        }
        public boolean isPalindrome(String s, int low, int high){
            while(low < high)
                if(s.charAt(low++) != s.charAt(high--)) return false;
            return true;
        }
    }


    /**
     * 查找未出现的最小正整数
     * @param a
     * @return
     */
    static int findMinMis(int[] a) {
        int left = 0;
        int right = a.length - 1;
        while (left <= right) {
            int t = a[left] - 1;
            if (t == left) {
                left++;
            } else if (t < 0 || t > right || a[left] == a[t]) {
                a[left] = a[right--];
            } else {
                a[left] = a[t];
                a[t] = t + 1;
            }
        }
        return left + 1;
    }
    static int findMinMis2(int[] a) {
        Arrays.sort(a);
        int min = 1;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > 0) {
                if (min == a[i]) {
                    min++;
                } else {
                    break;
                }
            }
        }
        return min;
    }

    /**
     * Given a string s, partition s such that every substring of the partition is a palindrome.
     * Return all possible palindrome partitioning of s.
     * @param s
     * @return
     */
    ArrayList<ArrayList<String>> resultList;
    ArrayList<String> current;
    public ArrayList<ArrayList<String>> partition(String s) {
        resultList = new ArrayList<ArrayList<String>>();
        current = new ArrayList<String>();
        findPalindrome(s, 0);
        return resultList;
    }
    private void findPalindrome(String str, int left) {
        if (current.size() > 0 && left >= str.length()) {
            ArrayList<String> temp = (ArrayList<String>) current.clone();
            resultList.add(temp);
        }
        for (int right = left; right < str.length(); right++) {
            if (isPalindrome(str, left, right)) {
                if (left == right) {
                    current.add(String.valueOf(str.charAt(left)));
                } else {
                    current.add(str.substring(left, right + 1));
                }
                findPalindrome(str, right + 1);
                current.remove(current.size() - 1);
            }
        }
    }

    /**
     * 判断是否为回文
     * @param s
     * @return
     */
    public boolean isPalindrome(String s,int left,int right) {
        while(left<right){
            if( s.charAt(left++) != s.charAt(right--) ) return false;
        }
        return true;
    }


    /**
     * Word Break II
     * @param s catsanddog
     * @param dict cat,cats,and,sand,dog
     * @return cat sand dog,cats and dog
     */
    public ArrayList<String> wordBreak(String s, Set<String> dict) {
        return dfs(s, dict, new HashMap<String, ArrayList<String>>());
    }
    public ArrayList<String> dfs(String s, Set<String> dict, HashMap<String, ArrayList<String>> map) {
        if (map.containsKey(s)) {
            return map.get(s);
        }
        ArrayList<String> list = new ArrayList<String>();

        if (s.length() == 0) {
            list.add("");
            return list;
        }

        for (String d : dict) {
            if (s.startsWith(d)) {
                for (String str : dfs(s.substring(d.length()), dict, map)) {
                    list.add(d + (str == "" ? "" : " ") + str);
                }
            }
        }
        map.put(s, list);
        return list;
    }

    /**
     * word-ladder
     * @param start
     * @param end
     * @param dict
     * @return
     */
    //主要思想：广度优先搜索。先构造一个字符串队列，并将start加入队列。1.对队列头字符串做单个字符替换
    //每次替换后，2.判断是否和end匹配，如果匹配，返回答案；3.没有匹配，则在字典里面查询是否有“邻居字符串”,
    //如果有，则将该字符串加入队列，同时将该字符串从字典里删除。重复1的过程，知道和end匹配。如果最后队列
    //为空还未匹配到，则返回0.
    public int ladderLength(String start, String end, HashSet<String> dict) {
        ArrayDeque<String> queue = new ArrayDeque<String>();
        ArrayDeque<Integer> sum = new ArrayDeque<Integer>();
        HashSet<String> visited = new HashSet<String>();

        queue.add(start);
        sum.add(1);
        visited.add(start);

        while(!queue.isEmpty()){
            String str = queue.remove();
            int n = sum.remove();
            if(str.equals(end)){
                return n;
            }
            for(int i=0;i<str.length();i++){
                char[] cstr = str.toCharArray();
                for(char c='a';c<='z';c++){
                    cstr[i] = c;
                    String temp = new String(cstr);
                    if(dict.contains(temp) && !visited.contains(temp)){
                        queue.add(temp);
                        sum.add(n+1);
                        visited.add(temp);
                    }
                }
            }
        }
        return 0;
    }
    /**
     * 密码破译
     */
    public static void crackPsw(){
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){

        }
        sc.close();

        String a = "adss";
        int L,i,j,k;
        L= a.length();
        for (i=1;i<=L;i++) {
            for (j=0;j<=L-i;j++) {
                for (k=j;k<j+i;k++) {
                    System.out.print(a.charAt(k));
                }
                System.out.println();
            }
        }
    }
    public static void crackPsw(String psw,int window){
        //window =1,2
        String out = "";
        char temp = '0';
        for (int i = 0; i < psw.length(); i++) {
            for (int j = i; j < i + window; j++) {
                temp =(char)(psw.charAt(j)-'1'+'a');
                out += temp;
            }
        }
        System.out.println(out);
    }

    /**
     * 失落的IP
     * 如输入2119913227
     * 输出21.199.13.227,211.99.13.227等所有可能
     */
    public static void perseIp(){
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            List<String> result = new ArrayList<String>();
            perseIp(sc.nextLine(), result,"", 0);
            for (String s :result) {
                System.out.println(s);
            }
        }
    }
    private static void perseIp(String s, List<String> list, String out,int n) {
        if (n == 4) {
            if (s.isEmpty()){
                list.add(out);
            }
            return;
        }
        for (int k = 1; k < 4; ++k) {
            if (s.length() < k) break;
            int val = Integer.valueOf(s.substring(0, k));
            if (val > 255 || k != String.valueOf(val).length() || val == 0) continue;
            perseIp(s.substring(k), list, out + val + (n == 3 ? "" : "."), n + 1);
        }
    }



    public void regex(){
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            String read = sc.nextLine();
            String[] stds = read.split("\\.");
            StringBuffer out = new StringBuffer();
            for(String str:stds){
                for (int i = 0; i < str.length()-1; i++) {
                    if(isUpperCase(str.charAt(i)) && !isUpperCase(str.charAt(i+1))){
                        out.append("_");
                        out.append(str.charAt(i));
                    }else if(!isUpperCase(str.charAt(i)) && isUpperCase(str.charAt(i+1))){
                        out.append(str.charAt(i));
                        out.append("_");
                    }else{
                        out.append(str.charAt(i));
                    }
                }
                out.append(str.charAt(str.length()-1));
                out.append("_");
            }
            System.out.println(out.toString().toUpperCase());
        }
    }
    private boolean isUpperCase(char c){
        if(c>='A' && c<='Z'){
            return true;
        }else {
            return false;
        }
    }


    /**
     * unix
     */
    public void unix(){
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            String str = sc.next();
            String[] os = str.split("/");
            Stack<String> stack = new Stack<String>();
            for(String s:os){
                if(s == null || s.equals("") || s.equals(".")){
                    continue;
                }
                if(!stack.isEmpty() && s.equals("..") && !stack.peek().equals("..")){
                    stack.pop();
                }else{
                    stack.push(s);
                }
            }

            String result = "";
            while (!stack.isEmpty()){
                result = "/"+ stack.pop() + result;
            }
            System.out.println(result.equals("")?"/":result);
        }
    }

    /**
     * 状态转移方程：
     * f(i) 表示s[0,i]是否可以分词
     * f(i) = f(j) && f(j+1,i); 0 <= j < i;
     *
     */
    public boolean wordBreak2(String s, Set<String> dict) {
        int len = s.length();
        boolean[] arrays = new boolean[len + 1];
        arrays[0] = true;
        for (int i = 1; i <= len; ++i) {
            for (int j = 0; j < i; ++j) {
                if (arrays[j] && dict.contains(s.substring(j, i))) {
                    arrays[i] = true;
                    break;
                }
            }
        }
        return arrays[len];
    }

    static int num = 0;

    /**
     * 面值为2^k的硬币各两枚，k任意
     * 问有几种方式可以凑成面值为n
     */
    public void tengcent() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            num = 0;
            int sum = sc.nextInt();
            int index = (int) Math.floor(Math.log(sum) / Math.log(2));
            int p = index * 2 + 1;
            getNum(sum, p);
            System.out.println(num);
        }
        sc.close();
    }

    public static void getNum(int sum, int p) {
        if (p == -1 || sum < 0) {
            return;
        }
        if (sum > 0) {
            //都不加
            getNum(sum, p - 2);
            //加一个
            int index = p / 2;
            sum = sum - (int) Math.pow(2, index);
            if (sum == 0) {
                num++;
                return;
            }
            if (sum > 0) {
                getNum(sum, p - 2);
            } else {
                return;
            }
            //加两个
            sum = sum - (int) Math.pow(2, index);
            if (sum == 0) {
                num++;
                return;
            }
            if (sum > 0) {
                getNum(sum, p - 2);
            }
        }
    }


    public static long set(int a, int b) {
        String tempa = String.valueOf(a);
        String tempb = String.valueOf(b);
        return Long.valueOf(tempa + tempb);
    }

    public void meituan1() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int[] data = new int[n];
            for (int i = 0; i < n; i++) {
                data[i] = sc.nextInt();
            }
            int count = 0;
            for (int i = 0; i < n; i++) {
                int a = data[i];
                for (int j = i + 1; j < n; j++) {
                    int b = data[j];

                    if (set(a, b) % 7 == 0) {
                        count++;
                    }
                    if (set(b, a) % 7 == 0) {
                        count++;
                    }
                }
            }
            System.out.println(count);
        }
    }

    /**
     * 公交车
     */
    public void hikvision1() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String[] input = sc.nextLine().split(",");
            int n = Integer.valueOf(input[0]);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date begin = null;
            Date now = null;
            try {
                begin = sdf.parse(input[1]);
                now = sdf.parse(input[2]);
            } catch (ParseException e) {
                System.out.println("incorrect data");
                continue;
            }
            long detTime = now.getTime() - begin.getTime();
            long det = detTime / (1000 * 60);

            long cycleTime = 15 * n;
            long cycle = det / cycleTime + 1;//cycle圈
            long db = (det % cycleTime) / 15 + 1;
            long d = (det % cycleTime) % 15;
            long de = 0;


            if (d == 0) {
                de = db;
            } else if (d < 10) {
                de = db + 1;
            } else {
                db++;
                if (db > n) db = 1;
                de = db;
            }
            if (de > n) {
                de = 1;
            }

            System.out.println(cycle + ";" + db + "-" + de);
        }
    }

    /**
     * 链表快排
     *
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        return quickSort(head, null);
    }

    private ListNode quickSort(ListNode head, ListNode end) {
        if (head != end) {
            ListNode cur = partion(head, end);
            quickSort(head, cur);
            quickSort(cur.next, end);
        }
        return head;
    }

    private ListNode partion(ListNode head, ListNode end) {
        int key = head.val;
        ListNode p = head;
        ListNode q = head.next;
        while (q != end) {
            if (q.val < key) {
                p = p.next;
                swap(p, q);
            }
            q = q.next;
        }
        swap(head, p);
        return p;
    }

    private void swap(ListNode p, ListNode q) {
        int temp = p.val;
        p.val = q.val;
        q.val = temp;
    }

    /**
     * 判断一个数是否为质数
     *
     * @param num
     * @return
     */
    public static boolean isPrime(int num) {
        if (num == 1) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }


    public void colorok() {
        Map<Integer, List<Integer>> colors = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int c = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            int num = sc.nextInt();
            for (int j = 1; j <= num; j++) {
                int color = sc.nextInt();
                if (colors.containsKey(color)) {
                    List<Integer> list = colors.get(color);
                    list.add(i);
                    colors.put(color, list);
                    if (i == n) {
                        list.add(0);
                        colors.put(color, list);
                    }
                } else {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    colors.put(color, list);
                    if (i == n) {
                        list.add(0);
                        colors.put(color, list);
                    }
                }
            }
        }
        sc.close();
        int wrong = 0;
        for (Integer i : colors.keySet()) {
            List<Integer> list = colors.get(i);
            Collections.sort(list);
            for (int j = 1; j < list.size(); j++) {
                if (list.get(j) - list.get(j - 1) < m) {
                    wrong++;
                    break;
                }
            }

        }
        System.out.println(wrong);
    }

    /**
     * 用户喜好
     */
    public void favot() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int[] user = new int[n + 1];
            for (int i = 1; i < n + 1; i++) {
                user[i] = sc.nextInt();
            }
            int q = sc.nextInt();
            int[] result = new int[q];
            for (int i = 0; i < q; i++) {
                int l = sc.nextInt();
                int r = sc.nextInt();
                int k = sc.nextInt();
                int count = 0;
                for (int j = l; j <= r; j++) {
                    if (user[j] == k) {
                        ++count;
                    }
                }
                result[i] = count;
            }
            for (int i = 0; i < q; i++) {
                System.out.println(result[i]);
            }
        }
    }


    /**
     * 字符串碎片
     */


    /**
     * 相反数
     */
    public void oppositeNumber() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            int oppo = 0;
            for (int i = input.length() - 1; i >= 0; i--) {
                oppo = oppo * 10 + input.charAt(i) - '0';
            }
            System.out.println(oppo + Integer.valueOf(input));
        }
    }

    /**
     * 魔法币
     */
    public void magicMoney() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Stack<Integer> stack = new Stack<>();
        while (n > 0) {
            if (n % 2 == 0) {
                stack.push(2);
                n = (n - 2) / 2;
            } else {
                stack.push(1);
                n = (n - 1) / 2;
            }
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop());
        }
        System.out.println();
    }

    /**
     * 重排数列
     */
    public void reorderArr() {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (--t >= 0) {
            int n = sc.nextInt();
            int[] a = new int[n];
            int cout4 = 0;
            int countJ = 0;
            for (int j = 0; j < n; j++) {
                a[j] = sc.nextInt();
            }
            for (int j = 0; j < n; j++) {
                if (a[j] % 4 == 0) {
                    cout4++;
                }
                if (a[j] % 2 != 0) {
                    countJ++;
                }
            }
            if (cout4 >= countJ) {
                System.out.println("Yes");
            } else
                System.out.println("No");
        }
    }

    /**
     * 小易喜欢的序列
     * <p>
     * 复杂度过大
     *
     * @param args
     */
    public void arr(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (sc.hasNext()) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            long count = 0, finish = 0;
            int[] data = new int[n];
            for (int i = 0; i < n; i++) {
                data[i] = 1;
            }
            for (int i = 1; finish == 0; i++) {
                int index = 0;
                int flag = 0;
                data[index] = i;

                //进位

                while (data[index] > k) {
                    i = 0;
                    flag = 1;
                    data[index] = 1;
                    if (index + 1 >= data.length) {
                        finish = 1;
                        break;
                    }
                    data[++index]++;
                }
                int j = 0;
                if (flag == 0) {
                    //判定
                    for (; j < data.length - 1; j++) {
                        int a = data[j];
                        int b = data[j + 1];
                        if (a > b && a % b == 0) {
                            break;
                        }
                    }
                    if (j == data.length - 1) {
                        count++;
                    }
                }
            }
            System.out.println(count % 1000000007);
        }
    }

    /**
     * 堆砖块
     * <p>
     * 如果能堆砌出两座高度相同的塔，输出最高能拼凑的高度，如果不能则输出-1.
     * <p>
     * dp[i][j]表示前i个砖块中分两堆，较矮一堆的高度
     */
    public void bricks(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] bricks = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            bricks[i] = sc.nextInt();
            sum += bricks[i];
        }
        int[] dp = new int[sum + 1];
        for (int i = 0; i <= sum; i++) {
            dp[i] = Integer.MIN_VALUE;
        }
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            int b = bricks[i - 1];
            int[] temp = new int[sum + 1];
            for (int j = 0; j <= sum; j++) {
                //不放
                temp[j] = dp[j];
                //放矮塔，放后矮塔依然矮
                if (j + b <= sum) {
                    temp[j] = Math.max(temp[j], dp[j + b] + b);
                }
                //放矮塔，放后矮塔高
                if (b >= j) {
                    temp[j] = Math.max(temp[j], dp[b - j] + b - j);
                } else {
                    //放高塔，高塔更高
                    temp[j] = Math.max(temp[j], dp[j - b]);
                }
            }
            dp = temp;
        }
        System.out.println(dp[0] > 0 ? dp[0] : -1);
    }

    /**
     * 分饼干
     * <p>
     * 饼干数k=9XXXX98X
     * 分给n个小朋友
     * <p>
     * dp[i][j]表示前i个字符串中模n余j的个数
     * 则 dp[i][(10*j+c)%n] += dp[i-1][j]
     * <p>
     * 以上发现第i行的值只会访问到第i-1行的值，所以实际只需要两个数组即可，可以优化空间成类似堆砖块
     */
    public void cookie(String[] args) {
        Scanner sc = new Scanner(System.in);
        String k = sc.nextLine();
        int n = sc.nextInt();

        long[][] dp = new long[k.length() + 1][n];
        dp[0][0] = 1;
        for (int i = 1; i <= k.length(); i++) {
            char c = k.charAt(i - 1);
            for (int j = 0; j < n; j++) {
                for (int z = 0; z < 10; z++) {
                    if (c == 'X' || c == ('0' + z)) {
                        dp[i][(10 * j + z) % n] += dp[i - 1][j];
                    }
                }
            }
        }
        System.out.println(dp[k.length()][0]);
    }


    /**
     * 数串
     * <p>
     * 设有n个正整数，将他们连接成一排，组成一个最大的多位整数。
     * 如:n=3时，3个整数13,312,343,连成的最大整数为34331213。
     * 如:n=4时,4个整数7,13,4,246连接成的最大整数为7424613。
     *
     * @param
     */
    public void shuchuang() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<String> input = new ArrayList<>();
        for (int i = 0; i < n; i++) {
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
        for (int i = input.size() - 1; i >= 0; i--) {
            result.append(input.get(i));
        }


        System.out.println(result.toString());
    }


    public static void main3(String[] args) {
        Scanner in = new Scanner(System.in);
        int pieces = Integer.parseInt(in.nextLine().trim());
        Integer[] parts = new Integer[pieces];
        for (int i = 0; i < pieces; i++) {
            parts[i] = Integer.parseInt(in.nextLine().trim());
        }
        System.out.println(cut(parts));
    }

    static int cut(Integer[] parts) {
        if (parts.length < 1) return 0;

        Arrays.sort(parts);
        int arr1 = 0;
        int arr2 = 0;
        ArrayList<Integer> deque1 = new ArrayList<>();
        ArrayList<Integer> deque2 = new ArrayList<>();
        int result = 0;
        for (int i = parts.length - 1; i >= 0; i--) {
            if (parts[i] < 1) return 0;
            if (arr1 < arr2) {
                arr1 += parts[i];
                deque1.add(parts[i]);
            } else {
                arr2 += parts[i];
                deque2.add(parts[i]);
            }
        }
        if (deque1.size() > 1) {
            result += cut(toArray(deque1));
        }
        if (deque2.size() > 1) {
            result += cut(toArray(deque2));
        }
        result += arr1 + arr2;
        return result;
    }

    static Integer[] toArray(List<Integer> list) {
        int size = list.size();
        Integer[] result = new Integer[size];
        for (int i = 0; i < size; i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    //
    private static double get(List<Integer[]> rlist, double max) {
        int rsize = rlist.size();
        double temp = 0;
        for (int i = 0; i < rsize - 2; i++) {
            for (int j = i + 1; j < rsize - 1; j++) {
                for (int k = j + 1; k < rsize; k++) {
                    temp = area(length(rlist.get(i), rlist.get(j)), length(rlist.get(i), rlist.get(k)), length(rlist.get(j), rlist.get(k)));
                    if (temp > max) max = temp;
                }
            }
        }
        return max;
    }

    //求边长
    private static double length(Integer[] a, Integer[] b) {
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += Math.pow(a[i] - b[i], 2);
        }
        return Math.sqrt(sum);
    }

    //求面积
    private static double area(double a, double b, double c) {
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }


    /**
     * 平安果数量
     * <p>
     * 待完成
     */
    public int appleNum(int row, int col, int[][] input) {
        --row;
        --col;
        int sum = input[row][col];
        while (row > 0 && col > 0) {
            if (input[row][col - 1] > input[row - 1][col]) {
                sum += input[row][col - 1];
                col--;
            } else {
                sum += input[row - 1][col];
                row--;
            }
        }
        return 0;
    }


    /**
     * 爬楼梯
     */
    public int louti(int k) {
        if (k < 0) return 0;
        int[] temp = new int[k + 1];
        temp[1] = 1;
        temp[2] = 2;
        for (int i = 3; i < k + 1; i++) {
            temp[i] = temp[i - 1] + temp[i - 2];
        }
        return temp[k];
    }


    /**
     * 最大子序列和
     *
     * @param sequence
     * @return
     */
    public int maxSubSum(int[] sequence) {
        int maxSum = 0, curSum = 0;
        for (int i = 0; i < sequence.length; i++) {
            curSum += sequence[i];
            if (curSum > maxSum) {
                maxSum = curSum;
            } else if (curSum < 0) {
                curSum = 0;
            }
        }
        return maxSum;
    }

    /**
     * 求环的入口节点
     *
     * @param node
     * @return
     */
    public ListNode getCycleBegin(ListNode node) {
        if (hasCycle(node) == false) return null;
        int cycleLength = getCycleLength(node);
        ListNode first = node, second = node;
        for (int i = 0; i < cycleLength; i++) {
            first = first.next;
        }
        while (first != second) {
            first = first.next;
            second = second.next;
        }
        return first;
    }

    /**
     * 计算有环链表环的长度
     *
     * @param node
     * @return
     */
    public int getCycleLength(ListNode node) {
        if (hasCycle(node) == false) return 0;
        ListNode fast = node, slow = node;
        int len = 0, flag = 0, loop = 0;
        while (slow != null && slow.next != null) {
            fast = fast.next;
            slow = slow.next.next;
            len += flag;
            if (fast == slow) {
                flag = 1;
                ++loop;
            }
            if (loop == 2) break;
        }
        return len;
    }

    /**
     * 判段链表是否有环
     *
     * @param node
     * @return
     */
    public boolean hasCycle(ListNode node) {
        ListNode fast = node, slow = node;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    /**
     * 字符串匹配-？单字符，*字符串
     *
     * @param str      被字符串
     * @param strStrat
     * @param pattern  匹配模式
     * @param patStart
     * @return
     */
    public static int match(String str, int strStrat, String pattern, int patStart) {
        while (patStart < pattern.length()) {
            char p = pattern.charAt(patStart);
            if (p == '?' || p == str.charAt(strStrat)) {
                ++strStrat;
                ++patStart;
            } else if (p == '*') {
                ++patStart;

                while (pattern.charAt(patStart) != str.charAt(strStrat)) {
                    ++strStrat;
                    if (strStrat >= str.length()) return 0;
                }
            } else {
                ++strStrat;
                if (strStrat >= str.length()) return 0;
            }
        }
        return 1;
    }

    public int MoreThanHalfNum_Solution(Integer[] array) {
        Sort.quickSort(array);
        int cf = array[(array.length - 1) / 2];
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == cf)
                count++;
        }
        return count > array.length / 2 ? cf : 0;
    }

    /**
     * 阶乘函数
     *
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
        int[][] net = new int[n + 1][n + 1];
        for (int i = 0; i < n + 1; i++) {
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
        long[][] dp = new long[str.length() + 1][n];
//        for(int i=0;i<=str.length();i++){
//            dp[i]=new long[n];
//        }
        dp[0][0] = 1;
        for (int i = 1; i <= str.length(); i++) {
            char c = str.charAt(i - 1);
            for (int j = 0; j < n; j++) {
                if (c == 'X') {
                    for (int k = 0; k < 10; k++) {
                        dp[i][(j * 10 + k) % n] += dp[i - 1][j];
                    }
                } else {
                    dp[i][(j * 10 + Integer.valueOf(c)) % n] += dp[i - 1][j];
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
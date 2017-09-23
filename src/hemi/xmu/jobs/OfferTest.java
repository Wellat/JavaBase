package hemi.xmu.jobs;


import java.util.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OfferTest {
    public static void main(String[] args) throws Exception {

        Offer of = new Offer();
        int[] inputs = {1, 2, 4, 7, 11, 15};
        String str = "I am a student.";
        of.permutation("abc");
        System.out.println();
        int[] gb = {7,5,6,4,1};
        of.inversePairs(gb);
        for(int g:gb){
            System.out.print(g+" ");
        }
        System.out.println();
        System.out.println(of.times);
    }


}

class ComplexListNode {
    int value;
    ComplexListNode next;
    ComplexListNode sibling;
}

class CicleLink {
    class Node {
        public Integer value;
        public Node next = null;
    }

    private int size;
    private Node first;
    private Node last;

    public Integer get(int index) {
        if (outIndex(index)) {
            System.out.println("index out of size.");
            return null;
        }
        Node node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.value;
    }

    private boolean outIndex(int index) {
        return (index >= size) ? false : true;
    }

    public int size() {
        return size;
    }

    public void remove(int index) {
        if (outIndex(index)) {
            System.out.println("index out of size.");
            return;
        }
        Node node = first;
        for (int i = 0; i < index - 1; i++) {
            node = node.next;
        }
//        node.next
    }

    public void insert(int in) {
        Node node = new Node();
        node.value = in;
        if (first == null) {
            first = node;
            last = node;
        } else {
            last.next = node;
            last = node;
            last.next = first;
        }
        size++;
    }
}

class Offer {


    /**
     * 49 把字符串转换成整数
     */
    public Integer string2Num(String str) {
        if (str == null || str.equals("")) {
            System.out.println("null");
            return null;
        }
        int begin_flag = 0;//0，1
        int zf_flag = 0;//0为正数，1为负数

        // 判断正负
        char first = str.charAt(0);
        if (first >= '0' && first <= '9') {

        } else if (first == '+') {
            begin_flag = 1;
        } else if (first == '-') {
            begin_flag = 1;
            zf_flag = 1;
        } else {
            System.out.println("bad begin.");
            return null;
        }
        // 正则判断非法字符输入
        Pattern pattern = Pattern.compile("(\\D+)");
        Matcher matcher = pattern.matcher(str.substring(begin_flag));
        if (matcher.find()) {
            System.out.println("bad input.");
            return null;
        }

        //转换
        int out = 0;
        for (int i = begin_flag; i < str.length(); i++) {
            out = out * 10 + (str.charAt(i) - '0');
        }
        return zf_flag == 1 ? (-1) * out : out;
    }


    /**
     * 47 不用加减乘除做加法
     */
    public int add(int a, int b) {
        int sum, carry;
        do {
            sum = a ^ b;//异或
            carry = (a & b) << 1;//获取进位
            a = sum;
            b = carry;
        } while (b != 0);
        return a;
    }


    /**
     * 45 圆圈中最后剩下的数字
     * ❤❤❤
     */
    /*
    0到n-1这n个数字排成一个圈，从数字0开始每次删除第m个数字，
    求这个圆圈里剩下的最后一个数字。
     */
    public int lastNum(int n, int m) {//TODO
        if (n < 1 || m < 1) return -1;
        int last = 0;
        for (int i = 2; i <= n; i++) {
            last = (last + m) % i;
        }
        return last;
    }
    //环形链表也是一种解法


    /**
     * 44 扑克牌的顺子
     */

    /**
     * 43 n个骰子的点数
     * 点数之和为s，输出s的所有可能值出现的概率
     *
     */


    /**
     * 42.1 翻转单词顺序
     *
     * @param input
     */
    public void reversalWords(String input) {
        if (input == null) return;
        String[] strs = input.split(" ");
        StringBuffer sb = new StringBuffer();
        for (int i = strs.length - 1; i >= 0; i--) {
            sb.append(strs[i] + " ");
        }
        System.out.println(sb.toString());
    }

    /**
     * 42.2 左旋转字符串
     * 如输入'abcdefg'和数字2，输出'cdefgab'
     */
    public void leftRotateString(String input, int k) {
        if (input.length() < k) return;
        for (int i = 0; i < k; i++) {
            input += input.charAt(0);
            input = input.substring(1);
        }
        System.out.println(input);
    }


    /**
     * 41 ①和为s的两个数字、②和为s的连续整数序列
     *
     * @return
     */
    /*
    输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得他们的和
    正好为s，输出一对即可。
     */
    public void findSumOfS(int input[], int s) {
        int len = input.length;
        if (len < 1 || s < 0) return;
        int head = 0, tail = len - 1;
        while (head < tail) {
            int sum = input[head] + input[tail];
            if (sum > s) {
                tail--;
            } else if (sum < s) {
                head++;
            } else {
                break;
            }
        }
        System.out.println(input[head] + " + " + input[tail] + " = " + s);
    }

    /*
    输入一个正数s,打印输出所有和为s的连续正数序列（至少含有两个数）
     */
    public void findSequenceOfSumS(int s) {
        int head = 1, tail = s, cur = 0;
        int sum = 0;
        while (cur <= (tail + 1) / 2) {
            if (sum > s) {
                sum -= head;
                head++;
            } else if (sum == s) {
                printSequence(head, cur);
                ++cur;
                sum += cur;
            } else {
                ++cur;
                sum += cur;
            }
        }
    }

    private void printSequence(int a, int b) {
        for (int i = a; i <= b; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }


    /**
     * 40 数组中只出现一次的数字
     * 如{2,2,3,3,5,6,6}，其他数字均只出现两次
     *
     * @param input
     * @return
     */
    public int findNumsAppearOnce(int[] input) {
        int ans = 0;
        for (int i = 0; i < input.length; i++) {
            //两个相同数字相异或，结果为0
            ans ^= input[i];
        }
        return ans;
    }

    /**
     * 39 二叉树的深度
     * ❤❤
     * @param tree
     * @return
     */
    //最大深度
    public int treeDepth(TreeNode tree) {
        if (tree == null) return 0;
        int dLeft = treeDepth(tree.left);
        int dright = treeDepth(tree.right);
        return (dLeft > dright) ? (dLeft + 1) : (dright + 1);
    }
    //最小深度
    public int minDepth(TreeNode tree){
        if (tree == null) return 0;

        if(tree.left==null) return minDepth(tree.right)+1;
        if(tree.right==null) return minDepth(tree.left)+1;

        int dLeft = minDepth(tree.left);
        int dright = minDepth(tree.right);
        return (dLeft < dright) ? (dLeft + 1) : (dright + 1);
    }

    /*
    判断一课二叉树是不是平衡二叉树（左右子树深度差超过1）
     */
    public boolean isBalanced(BinaryNode root) {
        return isBalanced(root, 0);
    }

    private boolean isBalanced(BinaryNode root, int depth) {
        if (root == null) {
            depth = 0;
            return true;
        }
        int left = 0, right = 0;
        if (isBalanced(root.left, left) && isBalanced(root.right, right)) {
            int diff = left - right;
            if (diff > -1 && diff < 1) {
                depth = 1 + (left > right ? left : right);
                return true;
            }
        }
        return false;
    }


    /**
     * 38 数字在排序数组中出现的次数
     * 二分法分别获取重复数字的开始和结束位置
     *
     * @param input
     * @param k
     * @return
     */
    public int numberOfK(int[] input, int k) {
        int index, low = 0, high = input.length - 1;
        while (low <= high) {
            index = (high + low) / 2;
            if (input[index] > k) {
                high = index - 1;
            } else if (input[index] < k) {
                low = index + 1;
            } else {
                return getLastK(input, index, high, k) - getFirstK(input, low, index, k) + 1;
            }
        }
        return 0;
    }

    private int getFirstK(int[] input, int low, int high, int k) {
        int index;
        while (low < high) {
            index = (high + low) / 2;
            if (input[index] > k) {
                high = index - 1;
            } else if (input[index] < k) {
                low = index + 1;
            } else {
                return getFirstK(input, low, index, k);
            }
        }
        return high;
    }

    private int getLastK(int[] input, int low, int high, int k) {
        int index;
        while (low < high) {
            index = (high + low + 1) / 2;
            if (input[index] > k) {
                high = index - 1;
            } else if (input[index] < k) {
                low = index + 1;
            } else {
                return getLastK(input, index, high, k);
            }
        }
        return low;
    }

    /**
     * 37 两个单向链表的第一个公共节点
     * ❤❤
     */
    /*
    首先遍历两个链表得到他们的长度，就能知道哪个链表比较长，以及长的链表比短的
    链表多几个节点。在第二次遍历的时候，让较长的链表先走若干步，接着再同时在两个
    链表上遍历，找到的第一个相同节点就是它们的第一个公共节点。
     */


    /**
     * 36 数组中的逆序对数
     * ❤❤❤❤
     */
    //普通方法
    public int getInversePairs(int[] input){
        if(input.length<1) return 0;
        int count =0;
        for(int i=0;i<input.length;i++){
            for(int j=i;j<input.length;j++){
                if(input[i]>input[j]){
                    count++;
                }
            }
        }
        return count;
    }

    public static int times = 0;
    //利用归并排序法
    public void inversePairs(int[] input){
        int[] temp = new int[input.length];
        mergeSort(input,temp,0,input.length-1);
    }
    private void mergeSort(int[] input,int[] temp,int begin,int end){
        if(begin<end){
            int mid = (begin + end)/2;
            mergeSort(input,temp,begin,mid);
            mergeSort(input,temp,mid+1,end);
            merge2(input,temp,begin,mid+1,end);
        }
    }
    private void merge2(int[] input, int[] temp, int begin, int mid, int end) {
        int leftEnd = mid -1;
        int tempIndex = end;
        int numElements = end - begin + 1;
        while (leftEnd>=begin && end>=mid){
            if(input[leftEnd]>input[end]){
                temp[tempIndex--]=input[leftEnd--];
                times += end - mid + 1;
            }else {
                temp[tempIndex--]=input[end--];
            }
        }
        while (leftEnd>=begin)
            temp[tempIndex--]=input[leftEnd--];
        while (end>=mid)
            temp[tempIndex--]=input[end--];

        for(int i=0;i<numElements;i++,begin++){
            input[begin]=temp[begin];
        }
    }

    /**
     * 35 第一个只出现一次的字符
     *
     * @param str
     * @return
     */
    public Object firstNotRepeatChar(String str) {
        char[] chars = str.toCharArray();
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        for (char c : chars) {
            map.put(c + "", map.get(c + "") == null ? 1 : map.get(c + "") + 1);
        }
        for (Map.Entry entry : map.entrySet()) {
            if (entry.getValue().equals(1)) return entry.getKey();
        }
        return null;
    }

    /**
     * 34 求按从小到大的顺序的第index个丑数
     * ❤❤❤
     *
     * @param index
     * @return
     */
    public int getUglyNumber(int index) {
        if (index < 1) return 0;
        int[] result = new int[index];
        result[0] = 1;
        int ind2 = 0, ind3 = 0, ind5 = 0;
        for (int i = 1; i < index; i++) {
            result[i] = getMin(result[ind2] * 2, result[ind3] * 3, result[ind5] * 5);
            while (result[ind2] * 2 <= result[i]) ind2++;
            while (result[ind3] * 3 <= result[i]) ind3++;
            while (result[ind5] * 5 <= result[i]) ind5++;
        }
        return result[index - 1];
    }

    //判断一个数是否为丑数
    private boolean isUgly(int num) {
        while (num % 2 == 0) num /= 2;
        while (num % 3 == 0) num /= 3;
        while (num % 5 == 0) num /= 5;
        return (num == 1) ? true : false;
    }

    private int getMin(int a, int b, int c) {
        int temp = a < b ? a : b;
        return temp < c ? temp : c;
    }

    /**
     * 33 把数组排成最小的数
     * 如{3,32,321}-->321323
     *
     * @param input
     */
    //未考虑大数问题
    public void printMinNumber(int[] input) {
        int len = input.length;
        if (len < 1) return;
        int temp = input[0];
        for (int i = 1; i < len; i++) {
            temp = sortAB(temp, input[i]);
        }
        System.out.println(temp);
    }

    private int sortAB(int a, int b) {
        return Integer.valueOf(a + "" + b) > Integer.valueOf(b + "" + a) ? Integer.valueOf(b + "" + a) : Integer.valueOf(a + "" + b);
    }

    /**
     * 32 从1到n整数中1出现的次数
     *
     * @param n
     * @return
     */
    public int numberOf1Between1AndN(int n) {
        //
        return 0;
    }

    /**
     * 31 连续子数组的最大和
     * ❤
     */
    public int maxSequenceSum(int[] input) {
        int thisSum = 0, maxSum = 0;
        if (input.length < 1) return 0;

        for (int i = 0; i < input.length; i++) {
            thisSum += input[i];
            if (thisSum > maxSum) maxSum = thisSum;
            if (thisSum < 0) thisSum = 0;
        }
        return maxSum;
    }


    /**
     * 30 最小的K个数
     *
     */
    //法1利用快速排序核，复杂度为O(n)
    public int[] getLeastNumbers(int[] input, int k) {
        if (input.length < 1 || input.length < k) {
            System.out.println("error input...");
            return null;
        }
        int[] output = new int[k];
        int index;
        int low = 0, high = input.length - 1;
        while (low < high) {
            index = partition(input, low, high);
            if (index > k - 1) {
                low = index + 1;
            } else if (index < k - 1) {
                high = index - 1;
            } else {
                break;
            }
        }
        for (int i = 0; i < k; i++) {
            output[i] = input[i];
        }
        return output;
    }
    //法2利用大顶堆


    /**
     * 29 数组中出现次数超过一半的数字
     *
     */
    public int moreThanHalfNum(int[] arr) {
        if (arr.length == 0) return 0;
        return usequicksort(arr, 0, arr.length - 1);
    }

    // 快速排序函数
    private int partition(int[] arr, int low, int high) {
        int i = low, j = high;
        int choosen = arr[j];
        while (i < j) {
            while (i < j && arr[i] < choosen)
                i++;
            if (i < j)
                arr[j--] = arr[i];
            while (i < j && arr[j] > choosen)
                j--;
            if (i < j)
                arr[i++] = arr[j];
        }
        arr[j] = choosen;
        return j;
    }

    // 利用快速排序法
    private int usequicksort(int[] arr, int begin, int end) {
        int j = partition(arr, begin, end);
        if (j > arr.length / 2) {
            return usequicksort(arr, begin, j - 1);
        } else if (j < arr.length / 2) {
            return usequicksort(arr, j + 1, end);
        } else {
            return arr[j];
        }
    }

    // 更好的方法
    public int moreThanHalfNum2(int[] arr) {
        if (arr.length == 0)
            return -1;
        int result = arr[0];
        int times = 1;
        for (int i = 1; i < arr.length; i++) {
            if (times == 0) {
                result = arr[i];
                times = 1;
            } else if (arr[i] == result)
                times++;
            else
                times--;
        }
        return result;
    }

    /**
     * 28 打印字符串的排列
     * 打印输入字符串的所有字符排列
     * ❤❤❤❤❤
     *
     * @param str
     */
    public void permutation(String str) {
        if (str == null) return;
        char[] chars = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            chars[i] = str.charAt(i);
        }
        permutation(chars, 0, str.length()-1);

        Stack<Character> stack = new Stack<>();
        for (int i = 1; i <= chars.length; i++) {
            combine(chars, 0, i, stack);
        }
    }


    /**
     * 给一个字符串，比如ABC， 把所有的排列，即：ABC, ACB, BAC, BCA, CAB, CBC 都找出来。
     */
    private void permutation(char str[], int start, int end) {
        if (start == end) {
            for (int i = 0; i <= end; i++) {
                System.out.print(str[i]);
            }
            System.out.println();
        } else {
            for (int i = start; i <= end; i++) {
                swap(str, i, start);
                permutation(str, start + 1, end);
                swap(str, i, start);
            }
        }
    }


    private static void swap(char arr[], int a, int b) {
        char temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    /**
     * 给一个字符串，比如ABC， 把所有的组合，即：A, B, C, AB, AC, BC, ABC, 都找出来。
     */
    public void combine(char[] chs, int begin, int number, Stack<Character> stack) {
        if (number == 0) {
            System.out.println(stack.toString());
            return;
        }
        if (begin == chs.length) return;

        stack.push(chs[begin]);
        combine(chs, begin + 1, number - 1, stack);
        stack.pop();
        combine(chs, begin + 1, number, stack);
    }


    /**
     * 27 二叉搜索树与双向链表
     * 将二叉搜索树转换成一个排序的双向链表，
     * 要求不能创建新节点，只能调整数中节点指针的指向
     */
    public LinkedList convert(BinaryNode node) {
        LinkedList linked = new LinkedList();
        return convert(linked, node);
    }

    private LinkedList convert(LinkedList list, BinaryNode node) {
        if (node == null) return null;
        convert(list, node.left);
        list.add(node.element);
        convert(list, node.right);
        return list;
    }

    /**
     * 26 复杂链表的复制
     * ❤❤❤
     *
     * 节点中，每个节点除了有一个next指向下一个节点，还有一个sibling指向链表中任意节点或者null
     */
    public ComplexListNode clone(ComplexListNode head) {
        ComplexListNode pHead = head;
        //创建原链节点对于的N'个节点，N'链接在N的后面
        while (pHead != null) {
            ComplexListNode curNode = new ComplexListNode();
            curNode.value = pHead.value;
            curNode.next = pHead.next;
            curNode.sibling = null;
            pHead.next = curNode;
            pHead = curNode.next;
        }
        //查找sibling节点，
        pHead = head;
        while (pHead != null) {
            if (pHead.sibling != null) {
                pHead.next.sibling = pHead.sibling.next;
//                pHead = pHead.next;
            }
            pHead = pHead.next;
        }
        //将长链表奇偶分离
        pHead = head;
        ComplexListNode cloneNode = null;
        ComplexListNode cloneHead = null;

        if (pHead != null) {
            cloneHead = head.next;
            cloneNode = cloneHead;
            pHead.next = cloneHead.next;
            pHead = pHead.next;
        }
        while (pHead != null) {
            cloneNode.next = pHead.next;
            cloneNode = cloneNode.next;
            pHead.next = cloneNode.next;
            pHead = pHead.next;
        }
        return cloneHead;
    }

    /**
     * 25 二叉树中和为某一值的路径
     * ❤❤❤
     *
     * @param node
     * @param expectedSum
     */
    public void findPath(BinaryNode node, int expectedSum) {
        if (node == null) return;
        Stack<Integer> stack = new Stack<>();
        findPath(node, expectedSum, stack, 0);
    }

    private void findPath(BinaryNode node, int expectedSum, Stack stack, int sum) {
        stack.push(node.element);
        sum += node.element;

        if (node.right == null && node.left == null) {
            if (sum == expectedSum) printPath(stack);
        }

        if (node.left != null) findPath(node.left, expectedSum, stack, sum);
        if (node.right != null) findPath(node.right, expectedSum, stack, sum);

        stack.pop();
    }

    private void printPath(Stack<Integer> stack) {
        Stack<Integer> s1 = new Stack<>();
        while (!stack.isEmpty()) {
            s1.push(stack.pop());
        }
        while (!s1.isEmpty()) {
            System.out.print(s1.pop() + "->");
        }
        System.out.println();
    }

    /**
     * 24 二叉搜索树的后序遍历
     * ❤❤❤
     * <p>
     * 输入一个整数数组，判断其是否为...后续遍历
     *
     * @param sequence
     * @param len
     * @return
     */
    public boolean verifySquenceOfBST(int[] sequence, int len) {
        if (len <= 0 || sequence == null) return false;
        return verify(sequence, 0, len - 1);
    }

    private boolean verify(int[] sequence, int begin, int end) {
        int root = sequence[end];
        int i = begin;
        for (; i < end; i++) {
            if (sequence[i] > root) break;
        }

        int j = i;
        for (; j < end; j++) {
            if (sequence[j] < root) return false;
        }

        boolean left = true;
        if (i > 0)
            left = verify(sequence, begin, i - 1);
        boolean right = true;
        if (i < end)
            right = verify(sequence, i, end - 1);
        return left && right;
    }

    /**
     * 23 行序打印二叉树
     *
     * @param node
     */
    public void printFromTopToBottom(BinaryNode node) {
        if (node == null) return;
        ArrayList<BinaryNode> queue = new ArrayList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            BinaryNode curnode = queue.remove(0);
            System.out.print(curnode.element + " ");
            if (curnode.left != null) {
                queue.add(curnode.left);
            }
            if (curnode.right != null) {
                queue.add(curnode.right);
            }
        }
    }

    /**
     * 22 栈的压入、弹出序列
     *
     * @param pushA
     * @param popA
     * @return
     */
    /*
    输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。
    假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，
    序列4，5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。
     */
    public boolean IsPopOrder(int[] pushA, int[] popA) {
        if (pushA.length == 0 || popA.length == 0)
            return false;
        Stack<Integer> stackin = new Stack<>();
        int popIndex = 0;
        for (int i = 0; i < pushA.length; i++) {
            stackin.push(pushA[i]);
            while (!stackin.isEmpty() && stackin.peek() == popA[popIndex]) {
                stackin.pop();
                popIndex++;
            }
        }
        return stackin.isEmpty();
    }

    /**
     * 21 定义包含min函数的栈
     */
    class MinStack {
        Linked link = new Linked();
        Linked link_ = new Linked();//辅助栈，依次存小值
        int min = Integer.MAX_VALUE;

        public void push(int in) {
            link.insert(in);
            if (in < min) {
                link_.insert(in);
                min = in;
            } else {
                link_.insert(min);
            }
        }

        public int pop() {
            int po = 0;
            try {
                po = (int) link.deleteFirst();
                link_.deleteFirst();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return po;
        }

        public int getMin() {
            hemi.xmu.jobs.Linked.ListNode ans;
            ans = link_.first;
            return (int) ans.obj;
        }
    }

    /**
     * 20 打印螺旋矩阵
     */


    /**
     * 19 二叉树的镜像
     *
     * @param root
     */
    public void mirrorRecursively(BinaryNode root) {
        if (root == null) return;

        BinaryNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        if (root.right != null) {
            mirrorRecursively(root.right);
        }
        if (root.left != null) {
            mirrorRecursively(root.left);
        }
    }

    /**
     * 18 输入两个二叉树A和B，判断B是不是A的子结构
     *
     * @param root1
     * @param root2
     * @return
     */
    public boolean hasSubtree(BinaryNode root1, BinaryNode root2) {
        boolean result = false;

        if (root1 != null && root2 != null) {
            if (root1.element == root2.element) {
                result = chasSubtree(root1, root2);
            }
            if (!result) {
                result = hasSubtree(root1.left, root2);
            }
            if (!result) {
                result = hasSubtree(root1.right, root2);
            }
        }
        return result;
    }

    private boolean chasSubtree(BinaryNode root1, BinaryNode root2) {
        if (root2 == null) return true;
        if (root1 == null) return false;
        if (root1.element != root2.element) return false;
        return chasSubtree(root1.right, root2.right) && chasSubtree(root1.left, root2.left);
    }

    /**
     * 17 合并两个排序的链表
     *
     * @param head1
     * @param head2
     * @return
     */
    public ListNode merge(ListNode head1, ListNode head2) {
        ListNode head = null;
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        if (head1.val < head2.val) {
            head.val = head1.val;
            head.next = merge(head1.next, head2);
        } else {
            head.val = head2.val;
            head.next = merge(head1, head2.next);
        }
        return head;
    }

    /**
     * 16 反转链表————区分05反向打印链表
     * ❤❤
     *
     * @param root
     * @return
     */
    public ListNode reverseList(ListNode root) {
        ListNode reverseHead = null;
        ListNode cur = root;
        ListNode prev = null;
        ListNode next;
        while (cur != null) {
            next = cur.next;
            if (next == null)
                reverseHead = cur;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return reverseHead;
    }

    /**
     * 15 链表中倒数第k个节点
     *
     * @param head
     * @param k
     * @return
     */
    /*
    两个指针，先让第一个指针和第二个指针都指向头结点，然后再让第一个指正走(k-1)步，
    到达第k个节点。然后两个指针同时往后移动，当第一个结点到达末尾的时候，
    第二个结点所在位置就是倒数第k个节点了。
     */
    public ListNode findKthToTail(ListNode head, int k) {
        if (head == null || k <= 0)
            return null;
        ListNode pre = head;
        ListNode cur = head;
        for (int i = 0; i < k - 1; i++) {
            if (pre.next != null) {
                pre = pre.next;
            } else {
                return null;
            }
        }
        while (pre.next != null) {
            pre = pre.next;
            cur = cur.next;
        }
        return cur;
    }

    /**
     * 14 调整数组顺序使奇数位于偶数前面
     *
     * @param a
     */
    public void reorderOddEven(int[] a) {
        printArray(a);
        int i, j = a.length - 1, temp;
        for (i = 0; i < j; i++) {
            if (a[i] % 2 == 0) {
                for (; j > i; j--) {
                    if (a[j] % 2 == 1) {
                        temp = a[i];
                        a[i] = a[j];
                        a[j] = temp;
                        break;
                    }
                }
            }
        }
        printArray(a);
    }

    private void printArray(int[] a) {
        for (int i : a)
            System.out.print(i + " ");
        System.out.println();
    }

    /**
     * 13 在O(1)时间删除链表节点
     *
     * @param root
     * @param target
     */
    public void deleteNode(ListNode root, ListNode target) {
        if (root == null || target == null) return;

        //删除节点为头节点
        if (root == target) {
            root = null;
            return;
        }
        //删除节点为尾节点
        if (target.next == null) {
            ListNode node = root;
            while (node.next != target) {
                node = node.next;
            }
            node.next = null;
        }

        //删除节点在中部
        target.val = target.next.val;
        target.next = target.next.next;
    }

    /**
     * 02 单例模式
     */
    // 饿汉模式---设为静态，类加载即初始化，不存在多线程问题
    class Singleton1 {
        private Singleton1() {
        }

        private Singleton1 singleton = new Singleton1();

        public Singleton1 getInstance() {
            return singleton;
        }
    }

    // 懒汉模式---DCL双检查锁机制
    class Singleton2 {
        private Singleton2() {
        }

        private Singleton2 singleton = null;

        public Singleton2 getInstance() {
            if (singleton == null) {
                synchronized (Singleton2.class) {
                    if (singleton == null) {
                        singleton = new Singleton2();
                    }
                }
            }
            return singleton;
        }
    }
    // 静态内置类实现单例
    /*
    class Singleton3{
        private Singleton3(){}
        private static class MyObjectHandler{//static
            private static Singleton3 my = new Singleton3();
        }
        public static Singleton3 getInstance(){
            return MyObjectHandler.my;
        }
    }
    */

    /**
     * 03 二维数组中的查找
     * 数组左下角最小，右上角最大
     */
    public boolean find(int[][] mat, int number) {
        if (mat.length < 1)
            return false;
        int rows = mat.length;
        int cols = mat[0].length;
        int row = 0, col = cols - 1;
        boolean found = false;
        while (row < rows && col >= 0) {
            if (mat[row][col] == number) {
                found = true;
                break;
            } else if (mat[row][col] > number) {
                col--;
            } else {
                row++;
            }
        }
        return found;
    }

    /**
     * 04 替换空格
     */
    public String replaceString(String str) {
        String ans = null;
        ans = str.replace(" ", "%20");
        return ans;
    }

    public String replaceString2(String str) {
        StringBuilder s = new StringBuilder();
        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++) {
            String v = String.valueOf(c[i]);
            if (" ".equals(v)) {
                v = "%20";
            }
            s.append(v);
        }
        return s.toString();
    }

    /**
     * 05 反向打印链表
     *
     * @param node
     */
    public void printListReversingly_Iteratively(ListNode node) {
        Stack<Integer> stack = new Stack<>();
        while (node != null) {
            stack.push(node.val);
            node = node.next;
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    public void printListReversingly_Recursively(ListNode node) {
        if (node != null) {
            if (node.next != null) {
                printListReversingly_Recursively(node.next);
            }
            System.out.print(node.val + " ");
        }
    }

    /**
     * 06 重建二叉树
     *
     * @param pre 前序序列{1,2,4,7,3,5,6,8}
     * @param mid 中序序列{4,7,2,1,5,3,8,6}
     */
    public TreeNode rebuildBinaryTree(int[] pre, int[] mid) {
        return rebuildBinaryTree(pre, 0, pre.length - 1, mid, 0, mid.length - 1);
    }

    private TreeNode rebuildBinaryTree(int[] pre, int preBegin, int preEnd, int[] mid, int midBegin, int midEnd) {
        if (preBegin > preEnd || midBegin > midEnd)
            return null;
        TreeNode root = new TreeNode(pre[preBegin]);
        for (int i = midBegin; i < midEnd; i++) {
            if (mid[i] == pre[preBegin]) {
                root.left = rebuildBinaryTree(pre, preBegin + 1, i + preBegin - midBegin, mid, midBegin, i - 1);
                root.right = rebuildBinaryTree(pre, i + preBegin - midBegin + 1, preEnd, mid, i + 1, midEnd);
            }
        }
        return root;
    }

    /**
     * 07 两个栈实现一个队列
     */


    /**
     * 08 旋转数组中的最小数字
     */
    public int binarySearch(int[] a, int key) {
        if (a.length < 1) {
            return -1;
        }
        int begin = 0;
        int end = a.length - 1;
        int middle;
        while (begin < end) {
            middle = (end - begin) / 2;
            if (a[middle] > key) {
                end = middle - 1;
            } else if (a[middle] < key) {
                begin = middle + 1;
            } else {
                return 1;
            }
        }
        return -1;
    }

    /**
     * 08+ 时间复杂度为O(n)的排序算法
     *
     * @param ages
     * @param length
     */
    void sortAge(int ages[], int length) {
        if (ages == null || length < 0)
            return;
        int maxAge = 99;
        int[] ageTimesArr = new int[maxAge + 1];
        for (int i = 0; i < ages.length; i++) {
            ++ageTimesArr[ages[i]];
        }
        int index = 0;
        for (int i = 0; i < ageTimesArr.length; i++) {
            for (int j = 0; j < ageTimesArr[i]; j++) {
                ages[index++] = ageTimesArr[i];
            }
        }
    }

    /**
     * 09 裴波那契数列
     *
     * @param n
     * @return
     */
    public long fibonacci1(int n) {
        if (n < 0) {
            System.out.println("Wrong Input!");
            return -1;
        } else if (n < 2) {
            return n;
        } else {
            return fibonacci1(n - 1) + fibonacci1(n - 2);
        }
    }

    public long fibonacci2(int n) {
        if (n < 2)
            return n;
        long f0 = 0;
        long f1 = 1;
        long fn = 0;
        for (int i = 2; i <= n; i++) {
            fn = f0 + f1;
            f0 = f1;
            f1 = fn;
        }
        return fn;
    }

    /**
     * 10 二进制中1的个数
     *
     * @param num
     * @return
     */
    public int numberOf1(int num) {
        int count = 0;
        int flag = 1;
        while (flag != 0) {
            if ((flag & num) != 0)
                count++;
            flag = flag << 1;
        }
        return count;
    }

    public int numberOf1_better(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = (n - 1) & n;
        }
        return count;
    }

    /**
     * 11 数值的整数次方
     */
    // 未对底和指数做特殊情况考虑
    public double Power(double base, int exponent) {
        double ans = 1;
        while ((exponent--) > 0) {
            ans = ans * base;
        }
        return ans;
    }

    // 递归法——若指数为负数，对结果取倒数
    public double Power_Recur(double base, int exponent) {
        if (exponent == 0)
            return 1;
        if (exponent == 1)
            return base;
        double result = Power_Recur(base, exponent >> 1);
        result *= result;
        if (exponent % 2 == 1)
            result *= base;
        return result;
    }

    /**
     * 12 打印1到最大的n位数
     */
    public void PrintToMaxOfNDigits(int n) {
        if (n < 1)
            return;
        int[] num = new int[n + 1];
        while (!increment(num, n))
            printNumber(num);
    }

    private boolean increment(int[] num, int n) {
        ++num[n];
        while (num[n] == 10 && n > 0) {
            num[n] = 0;
            ++num[--n];
        }
        if (num[0] == 1)
            return true;
        return false;
    }

    private void printNumber(int[] num) {
        int i;
        StringBuffer sb = new StringBuffer();
        for (i = 0; i < num.length; i++) {
            if (num[i] != 0)
                break;
        }
        for (; i < num.length; i++) {
            sb.append(num[i]);
        }
        System.out.println(sb.toString());
    }

}

/*
 * 05 链表结构
 */
class Linked {

    class ListNode {
        public Object obj;
        public ListNode next = null;

        public ListNode(Object obj) {
            this.obj = obj;
        }

        public ListNode() {
        }
    }

    public ListNode first = null;
    private int size = 0;

    public void insert(Object obj) {
        ListNode node = new ListNode(obj);
        node.next = first;
        first = node;
        size++;
    }

    public Object deleteFirst() throws Exception {
        if (first == null) {
            throw new Exception("Empty!");
        }
        ListNode node = first;
        first = first.next;
        return node.obj;
    }

    public int size() {
        return size;
    }

    public Object find(Object obj) throws Exception {
        if (first == null) {
            throw new Exception("ListNode is empty!");
        }
        ListNode node = first;
        while (node != null) {
            if (node.obj.equals(obj)) {
                return node.obj;
            }
            node = node.next;
        }
        return null;
    }

    public void remove(Object obj) throws Exception {
        if (first == null) {
            throw new Exception("ListNode is Empty!");
        }
        if (first.obj.equals(obj)) {
            first = first.next;
        } else {
            ListNode pre = first;
            ListNode cur = first.next;
            while (cur != null) {
                if (cur.obj.equals(obj)) {
                    pre.next = cur.next;
                }
                pre = cur;
                cur = cur.next;
            }
        }
    }

    public boolean isEmpty() {
        return (first == null);
    }

    public void display() {
        if (first == null) {
            System.out.println("ListNode is Empty!");
        }
        ListNode cur = first;
        while (cur != null) {
            System.out.print(cur.obj.toString() + " -> ");
            cur = cur.next;
        }
        System.out.println();
    }
}
package hemi.subject.jobs;

import java.util.Stack;

public class OfferTest {
	public static void main(String[] args) throws Exception {
		Offer offer = new Offer();


        System.out.println("adfasdf".toUpperCase());
/*
		 * int mat[][] = { { 1, 2, 8, 9 }, { 2, 4, 9, 12 }, { 4, 7, 10, 13 }, {
		 * 6, 8, 11, 15 } }; boolean ans = offer.find(mat, 5);
		 * System.out.println(ans);
		 *
		 * String s = offer.replaceString2("We are young!");
		 * System.out.println(s);
*/

		/*
		 * Linked link = new Linked(); link.insert("head"); link.insert(4);
		 * link.insert(3); link.insert("middle"); link.insert(1);
		 * link.insert(0); link.insert("tail"); link.display(); ListNode node;
		 * int len = link.size(); System.out.println("size:" + len); while
		 * ((len--) != 0) { node = link.first; for (int i = len; i > 0; i--) {
		 * node = node.next; } System.out.print(node.obj + "-->"); }
		 * System.out.println();
		 */

		// System.out.println(offer.fibonacci1(18));
        ListNode node0 = new ListNode(2);
        node0.next = new ListNode(3);

        ListNode node = new ListNode(1);
        node.next = node0;



        offer.printListReversingly_Recursively(node);
        System.out.println();
        offer.printListReversingly_Iteratively(node);
		/*System.out.println(offer.Power(2, 3));
		int[] arr = {1,2,3,4,5,7,8,10,11,13,15,1,2};
		offer.reorderOddEven(arr);*/
	}
}

class Offer {

    /**
     * 02 单例模式
     */
    // 饿汉模式---设为静态，类加载即初始化，不存在多线程问题
    class Singleton1{
        private Singleton1(){
        }

        private Singleton1 singleton = new Singleton1();
        public Singleton1 getInstance(){
            return singleton;
        }
    }
    // 懒汉模式---DCL双检查锁机制
    class Singleton2{
        private Singleton2(){}
        private Singleton2 singleton = null;
        public Singleton2 getInstance(){
            if(singleton==null){
                synchronized (Singleton2.class){
                    if(singleton==null){
                        singleton=new Singleton2();
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
	 *
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
	 * @param node
     */
	public void printListReversingly_Iteratively(ListNode node){
		Stack<Integer> stack = new Stack<>();
		while (node!=null){
			stack.push(node.val);
			node=node.next;
		}
		while (!stack.isEmpty()){
			System.out.print(stack.pop()+" ");
		}
	}
	public void printListReversingly_Recursively(ListNode node){
		if(node!=null){
			if(node.next!=null){
				printListReversingly_Recursively(node.next);
			}
			System.out.print(node.val+" ");
		}
	}

    /**
     * 06 重建二叉树
     * @param pre 前序序列{1,2,4,7,3,5,6,8}
     * @param mid 中序序列{4,7,2,1,5,3,8,6}
     */
    public TreeNode rebuildBinaryTree(int[] pre,int[] mid){
        return rebuildBinaryTree(pre,0,pre.length-1,mid,0,mid.length-1);
    }
    private TreeNode rebuildBinaryTree(int[] pre,int preBegin,int preEnd,int[] mid,int midBegin,int midEnd){
        if(preBegin>preEnd || midBegin>midEnd)
            return null;
        TreeNode root = new TreeNode(pre[preBegin]);
        for(int i=midBegin;i<midEnd;i++){
            if(mid[i]==pre[preBegin]){
                root.left = rebuildBinaryTree(pre,preBegin+1,i+preBegin-midBegin,mid,midBegin,i-1);
                root.right = rebuildBinaryTree(pre,i+preBegin-midBegin+1,preEnd,mid,i+1,midEnd);
            }
        }
        return root;
    }

    /**
     * 07 两个栈实现一个队列
     */

	/*
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
     * @param ages
     * @param length
     */
    void sortAge(int ages[],int length){
        if(ages==null || length<0)
            return;
        int maxAge = 99;
        int[] ageTimesArr = new int[maxAge+1];
        for(int i=0;i<ages.length;i++){
            ++ageTimesArr[ages[i]];
        }
        int index = 0;
        for(int i=0;i<ageTimesArr.length;i++){
            for(int j=0;j<ageTimesArr[i];j++){
                ages[index++]=ageTimesArr[i];
            }
        }
    }

    /**
     * 09 裴波那契数列
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
    public double Power_Recur(double base,int exponent){
        if(exponent == 0)
            return 1;
        if(exponent == 1)
            return base;
        double result = Power_Recur(base,exponent>>1);
        result *= result;
        if(exponent %2 == 1)
            result *= base;
        return result;
    }

	/**
	 * 12 打印1到最大的n位数
	 */
    public void PrintToMaxOfNDigits(int n){
        if(n<1)
            return;
        int[] num = new int[n+1];
        while (!increment(num,n))
            printNumber(num,n);
    }
    private boolean increment(int[] num,int n){
        ++num[n];
        while(num[n]==10 && n>=0){
            num[n]=0;
            ++num[--n];
        }
        if(num[0]==1)
            return true;
        return false;
    }

    private void printNumber(int[] num,int n){

    }
	/*
	 * 14
	 */
	public void reorderOddEven(int[] a){
		printArray(a);
		int i=0,j=a.length-1,temp;
		for(i=0;i<j;i++){
			if(a[i]%2==0){
				for(;j>i;j--){
					if(a[j]%2==1){
						temp=a[i];
						a[i]=a[j];
						a[j]=temp;
						break;
					}
				}				
			}
		}
		printArray(a);
	}
	private void printArray(int[] a){
		for(int i:a)
			System.out.print(i+" ");
		System.out.println();
	}
}


/*
 * 05 ??β??????????
 */
class Linked {
	class ListNode {
		public Object obj;
		public ListNode next = null;

		public ListNode(Object obj) {
			this.obj = obj;
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
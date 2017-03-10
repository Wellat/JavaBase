package hemi.subject.jobs;

import java.util.LinkedList;
import java.util.List;

import hemi.subject.jobs.Linked.ListNode;

public class OfferTest {
	public static void main(String[] args) throws Exception {
		Offer offer = new Offer();
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

		System.out.println(offer.Power(2, 3));
		int[] arr = {1,2,3,4,5,7,8,10,11,13,15,1,2};
		offer.reorderOddEven(arr);
	}
}

class Offer {
	/*
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

	/*
	 * 04 字符串的替换
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

	/*
	 * 08 二分法查找
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

	/*
	 * 09 球斐波那契数列
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

	/*
	 * 10 二进制中1的个数
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

	public int numberOF1(int n) {
		int count = 0;
		while (n != 0) {
			count++;
			n = (n - 1) & n;
		}
		return count;
	}

	/*
	 * 11 数值的整数次方
	 */
	// 以下解法未对输入做考虑，算法极差。
	public double Power(double base, int exponent) {
		double ans = 1;
		while ((exponent--) > 0) {
			ans = ans * base;
		}
		return ans;
	}

	/*
	 * 12 打印从1到n的最大（n-1）位数，考察用字符串表示大数
	 */

	/*
	 * 14 调整整数数组顺序使期数位于偶数前面
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
 * 05 从尾到头打印链表
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
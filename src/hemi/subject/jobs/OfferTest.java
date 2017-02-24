package hemi.subject.jobs;

import java.util.LinkedList;
import java.util.List;

import hemi.subject.jobs.Offer.ListNode;

public class OfferTest {
	public static void main(String[] args) {
		Offer offer = new Offer();
		int mat[][] = { { 1, 2, 8, 9 }, { 2, 4, 9, 12 }, { 4, 7, 10, 13 }, { 6, 8, 11, 15 } };
		boolean ans = offer.find(mat, 5);
		System.out.println(ans);

		String s = offer.replaceString2("We are young!");
		System.out.println(s);

		ListNode ln = null;
		
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
	 * 05 从尾到头打印链表
	 */
	class ListNode {
		public Object obj;
		public ListNode next = null;

		public ListNode(Object obj) {
			this.obj = obj;
		}
	}
	

}

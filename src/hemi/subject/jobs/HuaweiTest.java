package hemi.subject.jobs;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class HuaweiTest {
	public static void main(String[] args) {
		Topic topic = new Topic();
		int ans = topic.deletNum(15);
		System.out.println("题1、最后一个数为：" + ans);
		String str = "abcqaweracb";
		System.out.println("题2、原字符串为：" + str);
		topic.printSet(str);
		int[][] sudoku = { { 8, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 3, 6, 0, 0, 0, 0, 0 }, { 0, 7, 0, 0, 9, 0, 2, 0, 0 },
				{ 0, 5, 0, 0, 0, 7, 0, 0, 0 }, { 0, 0, 0, 0, 4, 5, 7, 0, 0 }, { 0, 0, 0, 1, 0, 6, 0, 3, 0 },
				{ 0, 0, 1, 0, 0, 0, 0, 6, 8 }, { 0, 0, 8, 5, 0, 0, 0, 1, 0 }, { 0, 9, 0, 0, 0, 0, 4, 0, 0 } };
		Topic topic2 = new Topic(sudoku);
		topic2.backTrack(0, 0);
	}
}

class Topic {
	public Topic() {
	}

	/*
	 * 2016--1、循环删除数组中的数，输出最后一个数
	 */
	public int deletNum(int n) {
		if (n < 1) {
			System.out.println("Error input.");
		} else if (n > 999) {
			n = 999;
		}
		if (n == 1) {
			return 0;
		} else if (n == 2 || n == 3) {
			return 1;
		} else {
			List<Integer> list = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				list.add(i);
			}
			return deletNum(list, 2, 0);
		}
	}

	private int deletNum(List<Integer> list, int begin, int det) {
		int i = 0;
		int len = list.size();
		int m = (len - det) % 3;
		for (i = begin; i < list.size(); i += 2) {
			list.remove(i);
		}
		det = len - i - 1;
		if (list.size() > 3)
			deletNum(list, (2 - m), det);
		return list.get(1);
	}

	/*
	 * 2016--2、字符集合，输入一个字符串，求出该字符串包含的字符集合
	 * 
	 */
	public void printSet(String str) {
		if (str.length() > 0 && str != null && str.length() < 100) {
			Set<String> set = new LinkedHashSet<>();
			char[] b = str.toCharArray();
			for (int i = 0; i < b.length; i++) {
				set.add(String.valueOf(b[i]));
			}
			System.out.print("-------结果：");
			for (String s : set) {
				System.out.print(s);
			}
		}
	}

	/*
	 * 2016--3、数独
	 */
	private int matrix[][];
	private long timeAfter = 0;
	private long timeBefore = 0;

	public Topic(int m[][]) {
		matrix = new int[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				matrix[i][j] = m[i][j];
			}
		}
		this.timeBefore = Calendar.getInstance().getTimeInMillis();
	}

	public void backTrack(int i, int j) {
		System.gc();
		if (i == 8 && j >= 9) {
			this.timeAfter = Calendar.getInstance().getTimeInMillis();
			this.showMatrix();
			return;
		}
		if (j == 9) {
			j = 0;
			i++;
		}
		if (matrix[i][j] == 0) {
			// 数字为零
			for (int k = 1; k <= 9; k++) {
				if (bound(i, j, k)) {
					matrix[i][j] = k;
					// 符合条件，查找下一个方格
					backTrack(i, j + 1);
					matrix[i][j] = 0;
				}
			}
		} else {
			backTrack(i, j + 1);
		}
	}

	private boolean bound(int i, int j, int k) {
		int m = i / 3;
		int n = j / 3;
		for (int p = 0; p < 9; p++) {
			if (k == matrix[i][p]) {
				return false;
			}
			if (k == matrix[p][j]) {
				return false;
			}
			if (k == matrix[3 * m + p / 3][3 * n + p % 3]) {
				return false;
			}
		}
		return true;
	}

	public long printTime() {
		return this.timeAfter - this.timeBefore;
	}

	public void showMatrix() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("解题时间： " + printTime() + "毫秒");
		System.out.println();
	}

}
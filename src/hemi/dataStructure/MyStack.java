package hemi.dataStructure;

import java.util.Arrays;

public class MyStack {
	public static void main(String[] args) {
		int[] arr = { 5, 1, 3, 4, 1, 0, 12, 7 };
		MyStack stack = new MyStack();
		int[] a = stack.sort(arr);
		for (int j = 1; j < a.length; j++)
			System.out.print(a[j] + "¡¢");

	}

	private static int left = 0;
	private static int right = 0;

	public MyStack() {
	}

	public int[] sort(int[] arr) {
		int len = arr.length;
		int[] a = new int[len + 1];
		a[0] = 0;
		for (int i = 0; i < len; i++) {
			a[i + 1] = arr[i];
		}
//		a = Arrays.copyOfRange(arr, 1, len-1);
		int[] ans = sort(a, len + 1);
		return ans;
	}

	private int[] sort(int[] arr, int len) {
		int index = 0;
		int[] ans = arr;
		for (int i = len; i > 0; i--) {
			index = (i - 1) / 2;
			ans = buildStack(ans, index, i);
			if ((i - 1) > 0 && ans[1] < ans[i - 1]) {
				swap(ans, 1, i - 1);
			}
		}
		return ans;
	}

	/**
	 * 
	 * @param arr
	 * @param parent
	 *            ³õÊ¼ÅÐ¶ÏÎ»ÖÃ
	 * @return
	 */
	private int[] buildStack(int[] arr, int parent, int len) {
		int origin = parent;
		int[] a = arr;

		while (parent > 0) {
			left = 2 * parent;
			right = left + 1;
			if (right < len && parent <= origin) {
				if (arr[parent] > arr[right]) {
					swap(arr, parent, right);
					parent = right + 1;
				}
			}
			if (left < len && parent <= origin) {
				if (arr[parent] > arr[left]) {
					swap(arr, parent, left);
					parent = left + 1;
				}
			}

			for (int i = 0; i < len; i++) {
				a[i] = arr[i];
			}
			parent--;
		}
		return arr;
	}

	private void swap(int[] arr, int a, int b) {
		arr[0] = arr[a];
		arr[a] = arr[b];
		arr[b] = arr[0];
	}
}

package hemi.hotAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class HotAlgorithmDemo {
	public static void main(String args[]) {
		MyFunc test = new MyFunc();
		System.out.println(test.funIsPrime(7));
		int data[] = { 5, 8, 9, 2, 1, 7, 6, 0, 4, 3 };
		int a[] = { 2, 4, 6, 8, 10, 0, 0, 0, 0, 0 };
		int b[] = { 1, 3, 5, 7, 9 };
		// test.MergeArray(a, 5, b, 5);
		test.MyBubsort(data);
		for (int i : data) {
			System.out.print(i + "、");
		}
		System.out.println();
		System.out.println("查找的位置在：" + test.binarySearch(data, 8));
	}
}
//单例模式例子
//饿汉式在类创建的同时就已经创建好一个静态的对象供系统使用，以后不再改变，所以天生是线程安全的。
class Test {
	private Test() {}
	private static final Test single = new Test();
	public static Test getInstance() {
		return single;
	}
}
class MyFunc {
	// 不使用临时变量交换两个数
	void funSwapTwo(int a, int b) {
		a = a ^ b;
		b = b ^ a;
		a = a ^ b;
		System.out.println(a + " " + b);
	}

	// 判断一个数是否为素数
	boolean funIsPrime(int m) {
		boolean flag = true;
		if (m == 1) {
			flag = false;
		} else {
			for (int i = 2; i <= Math.sqrt(m); i++) {
				if (m % i == 0) {
					flag = false;
					break;
				}
			}
		}
		return flag;
	}

	// 合并排序，将两个已经排序的数组合并成一个数组，其中一个数组能容下两个数组的所有元素
	void MergeArray(int a[], int alen, int b[], int blen) {
		int len = alen + blen - 1;
		alen--;
		blen--;
		while (alen >= 0 && blen >= 0) {
			if (a[alen] > b[blen]) {
				a[len--] = a[alen--];
			} else {
				a[len--] = b[blen--];
			}
		}

		while (alen >= 0) {
			a[len--] = a[alen--];
		}
		while (blen >= 0) {
			a[len--] = b[blen--];
		}
	}

	// 快速排序
	void sort(int array[], int low, int high) {
		int i, j, choosen;
		if (low > high)
			return;
		i = low;
		j = high;
		choosen = array[i];
		while (i < j) {
			while (i < j && array[j] <= choosen)
				j--;
			if (i < j)
				array[i++] = array[j];
			while (i < j && array[i] >= choosen)
				i++;
			if (i < j)
				array[j--] = array[i];
		}
		array[i] = choosen;
		sort(array, low, i - 1);
		sort(array, i + 1, high);
	}

	// 冒泡排序
	void MyBubsort(int[] a) {
		int i, j, temp;
		int len = a.length;
		for (i = 0; i < len - 1; i++) {
			for (j = 0; j < len - 1 - i; j++) {
				if (a[j] > a[j + 1]) {
					temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
	}

	// 插入排序
	void insertSort(int[] a) {
		for (int p = 1; p < a.length; p++) {
			Integer tmp = a[p];
			int j = p;
			for (; j > 0 && tmp.compareTo(a[j - 1]) < 0; j--)
				a[j] = a[j - 1];
			a[j] = tmp;
		}
	}

	// 二分法查找——输入为已排序数组
	int binarySearch(int[] a, int key) {
		if (a.length < 1)
			return -1;
		int begin = 0;
		int end = a.length - 1;
		while (begin <= end) {
			int middle = (begin + end) / 2;
			if (key < a[middle]) {
				end = middle - 1;
			} else if (key > a[middle]) {
				begin = middle + 1;
			} else {
				return middle;
			}
		}
		return -1;
	}
	

}
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
//		test.MyBubsort(data);
		for (int i : data) {
			System.out.print(i + "��");
		}
		System.out.println();
		System.out.println("���ҵ�λ���ڣ�" + test.binarySearch(data, 8));
	}
}
//����ģʽ����
//����ʽ���ഴ����ͬʱ���Ѿ�������һ����̬�Ķ���ϵͳʹ�ã��Ժ��ٸı䣬�����������̰߳�ȫ�ġ�
class Test {
	private Test() {}
	private static final Test single = new Test();
	public static Test getInstance() {
		return single;
	}
}
class MyFunc {
	// ��ʹ����ʱ��������������
	void funSwapTwo(int a, int b) {
		a = a ^ b;
		b = b ^ a;
		a = a ^ b;
		System.out.println(a + " " + b);
	}

	// �ж�һ�����Ƿ�Ϊ����
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

	// �ϲ����򣬽������Ѿ����������ϲ���һ�����飬����һ�������������������������Ԫ��
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




	// ��������
	void insertSort(int[] a) {
		for (int p = 1; p < a.length; p++) {
			Integer tmp = a[p];
			int j = p;
			for (; j > 0 && tmp.compareTo(a[j - 1]) < 0; j--)
				a[j] = a[j - 1];
			a[j] = tmp;
		}
	}

	// ���ַ����ҡ�������Ϊ����������
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
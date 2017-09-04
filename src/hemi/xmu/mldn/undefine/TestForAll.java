package hemi.xmu.mldn.undefine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestForAll {
	public static void main(String args[]) {
		int[] data = { 5, 4, 9, 8, 7, 6, 0, 1, 3, 2, -1, 15, 11 };
		List<Integer> dataList = new ArrayList<>();
		for (int i = 1; i < data.length; i++) {
			dataList.add(data[i]);
		}

		String str = "LXH:98|MLDN:90|LI:100";
		String[] resultl = str.split("\\|");
		for (String result : resultl) {
			System.out.println(result.replaceAll(":", "\t"));
		}

		TestForAll.funSwapTwo(6, 3);
		String s = "���Ǹ���!";
		System.out.println(s.length());

		TestForAll.MyBubsort(data);
		for (int da : data) {
			System.out.print(da + "��");
		}

		int a=5,b=63;
		System.out.println("3^3:"+(a^b));
		System.out.println("3&3:"+(a&b));
		System.out.println("3^3<<1:"+((a&b)<<1));
		System.out.println("3+3=:"+AddWithoutArithmetic(a,b));
		
	}
	
	static int AddWithoutArithmetic(int num1,int num2)
	{
		if(num2==0) return num1;//û�н�λ��ʱ���������
		int sum,carry;
		sum=num1^num2;//��ɵ�һ��û�н�λ�ļӷ�����
		carry=(num1&num2)<<1;//��ɵڶ�����λ������������
		return AddWithoutArithmetic(sum,carry);//���еݹ飬���
	}


	// ��ʹ����ʱ��������������
	static void funSwapTwo(int a, int b) {
		a = a ^ b;
		b = b ^ a;
		a = a ^ b;
		System.out.println(a + " " + b);
	}

	// �򵥿�������
	static void sort(List<Integer> items) {
		if (items.size() > 1) {
			List<Integer> smaller = new ArrayList<Integer>();
			List<Integer> same = new ArrayList<Integer>();
			List<Integer> larger = new ArrayList<Integer>();

			Integer chosenItem = items.get(items.size() / 2);
			for (Integer i : items) {
				if (i < chosenItem)
					smaller.add(i);
				else if (i > chosenItem)
					larger.add(i);
				else
					same.add(i);
			}
			sort(smaller);
			sort(larger);

			items.clear();
			items.addAll(smaller);
			items.addAll(same);
			items.addAll(larger);
		}
	}

	static void sort(int array[], int low, int high) {
		int i, j, choosen;
		if (low > high)
			return;
		i = low;
		j = high;
		choosen = array[i];
		while (i < j) {
			while (i < j && array[j] >= choosen)
				j--;
			if (i < j)
				array[i++] = array[j];
			while (i < j && array[i] <= choosen)
				i++;
			if (i < j)
				array[j--] = array[i];
		}
		array[i] = choosen;
		sort(array, low, i - 1);
		sort(array, i + 1, high);
	}

	static void Bubsort(int[] a) {
		int i, j, temp;
		int len = a.length;
		for (i = 0; i < len - 1; ++i) {
			for (j = len - 1; j > i; --j) {
				if (a[j] < a[j - 1]) {
					temp = a[j];
					a[j] = a[j - 1];
					a[j - 1] = temp;
				}
			}
		}
	}

	static void MyBubsort(int[] a) {
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
}

// ��������������
class QuickSort {
	private static final int CUTOFF = 10;

	public static <E extends Comparable<? super E>> void quicksort(E[] a) {
		quicksort(a, 0, a.length - 1);
	}

	private static <E extends Comparable<? super E>> void quicksort(E[] a, int low, int high) {
		if (low + CUTOFF < high) {
			// �������룬�ò�������
			for (int p = 1; p < a.length; p++) {
				E tmp = a[p];
				int j = p;
				for (; j > 0 && tmp.compareTo(a[j - 1]) < 0; j--)
					a[j] = a[j - 1];
				a[j] = tmp;
			}
		} else {
			int middle = (low + high) / 2;
			if (a[middle].compareTo(a[low]) < 0)
				swapReferences(a, low, middle);
			if (a[middle].compareTo(a[high]) > 0)
				swapReferences(a, low, middle);
			if (a[high].compareTo(a[low]) < 0)
				swapReferences(a, low, middle);

			swapReferences(a, middle, high - 1);
			E pivot = a[high - 1];

			int i, j;
			for (i = low, j = high - 1;;) {
				while (a[++i].compareTo(pivot) < 0)
					;
				while (pivot.compareTo(a[j--]) < 0)
					;
				if (i >= j)
					break;
				swapReferences(a, i, j);
			}
			swapReferences(a, i, high - 1);
			quicksort(a, low, i - 1);
			quicksort(a, i + 1, high);
		}
	}

	private static <E extends Comparable<? super E>> void swapReferences(E[] a, int low, int middle) {
		E temp = a[low];
		a[low] = a[middle];
		a[middle] = temp;
	}

}

class sys {
	public void systest() {
		Runtime run = Runtime.getRuntime();
		Process pro = null;
		System.out.println("freeMemory:" + run.freeMemory());
		System.out.println("maxMemory():" + run.maxMemory());
		try {
			pro = run.exec("notepad.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		pro.destroy();
	}
}

// ����ģʽ
// ����ʽ���ഴ����ͬʱ���Ѿ�������һ����̬�Ķ���ϵͳʹ�ã��Ժ��ٸı䣬�����������̰߳�ȫ�ġ�
class Test {
	private Test() {}
	private static final Test single = new Test();
	public static Test getInstance() {
		return single;
	}
}
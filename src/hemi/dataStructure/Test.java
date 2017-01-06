package hemi.dataStructure;

public class Test {
	public static void main(String[] args) {
		// test sort
		Integer[] a = { 8, 1, 4, 1, 5, 9, 2, 6, 5, -2 };
		System.out.println("Before Sorting:");
		for (int i : a) {
			System.out.print(i + "¡¢");
		}
		System.out.println();

//		Sort.insertionSort(a);
		Sort.shellsort(a);

		System.out.println("After Sorting:");
		for (int i : a) {
			System.out.print(i + "¡¢");
		}
//		Integer[] b = { 2,1,3 };
//		System.out.println(b[1].compareTo(b[0]));
		
	}

}

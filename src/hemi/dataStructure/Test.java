package hemi.dataStructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hemi.dataStructure.interfaces.Stack;
import hemi.mldn.undefine.Example_Pet;


public class Test {
	public static void main(String[] args) {
		// test sort
/*		
		 * Integer[] a = { 8, 1, 4, 1, 5, 9, -2 };
		 * System.out.println("Before Sorting:"); for (int i : a) {
		 * System.out.print(i + "¡¢"); } System.out.println(); //
		 * Sort.insertionSort(a); // Sort.shellsort(a); // Sort.mergeSort(a); //
		 * Sort.quicksort(a); // Sort.quickSelect(a, 1);
		 * System.out.println("After Sorting:"); for (int i : a) {
		 * System.out.print(i + "¡¢"); }
		 */
		
		
		try{
			throw new MyException("zidiyiyic");
		}catch(Exception e){
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
}

class MyException extends Exception{
	public MyException(String msg){
		super(msg);
	}
}

package hemi.battle.huawei;

import java.util.Arrays;
import java.util.LinkedList;

import hemi.battle.huawei.Official.Main;

public class Test {
	public static void main(String[] args) {
//		LinkedList<Integer> list = new LinkedList<Integer>();
//		list.add(1);
//		list.add(2);
//		list.add(3);list.add(4);list.add(5);list.add(2);
//		for(int i:list){
//			System.out.print(i+" ");
//		}
//		System.out.println();
//		System.out.println(list.getLast());
//		System.out.println(list.getFirst());
		
//		String graphFilePath = "D:\\Documents\\华为软赛\\case_example\\case1.txt";
//		String resultFilePath = "D:\\Documents\\华为软赛\\result\\test.txt";
//		String[] input = {graphFilePath,resultFilePath};
//		Main.main(input);
		int a=3,b=9;
		int ecost[] = new int[a*b];
		
		Object[] arr = {1 ,5,-2,4,8,-9};
//		Arrays.sort(arr);
		System.out.println(getMinBandOfPath(arr));
		
		System.out.println(40%6*2);
		
	}
	public static int getMinBandOfPath(Object[] path) {
		int min = 999;
		for (int i = 0; i < path.length; i++) {
			if((int) path[i]<min)
				min = (int) path[i];			
		}
		return min;
	}
}

package hemi.battle.huawei;

import java.util.LinkedList;

import hemi.battle.huawei.Official.Main;

public class Test {
	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);list.add(4);list.add(5);list.add(2);
		for(int i:list){
			System.out.print(i+" ");
		}
		System.out.println();
		System.out.println(list.getLast());
		System.out.println(list.getFirst());
		
		String graphFilePath = "D:\\Documents\\华为软赛\\case_example\\case1.txt";
		String resultFilePath = "D:\\Documents\\华为软赛\\result\\test.txt";
		String[] input = {graphFilePath,resultFilePath};
//		Main.main(input);
	}
}

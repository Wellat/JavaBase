package hemi.battle.huawei;

import java.util.ArrayList;

public class NLCount {
	public int count;
	public ArrayList<Integer> parent ;
	public ArrayList<Integer> node ;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public ArrayList<Integer> getParent() {
		return parent;
	}
	public void setParent(ArrayList<Integer> parent) {
		this.parent = parent;
	}
	public ArrayList<Integer> getNode() {
		return node;
	}
	public void setNode(ArrayList<Integer> node) {
		this.node = node;
	}
	@Override
	public String toString() {
		return "NLCount [count=" + count + ", parent=" + parent + ", node=" + node + "]";
	}


}

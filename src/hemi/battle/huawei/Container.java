package hemi.battle.huawei;

import java.util.Arrays;

public class Container {
	public int count;
	public int[] parent;
	
	public int[] destination;

	@Override
	public String toString() {
		return "Container [count=" + count + ", parent=" + Arrays.toString(parent) + ", destination="
				+ Arrays.toString(destination) + "]";
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int[] getParent() {
		return parent;
	}

	public void setParent(int[] parent) {
		this.parent = parent;
	}

	public int[] getDestination() {
		return destination;
	}

	public void setDestination(int[] destination) {
		this.destination = destination;
	}

}
